package com.thoreauz.bootlearn.longpolling;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 2018/4/28 下午6:10.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
public class LongPollingService {
    private final static int TIME_OUT = 30000;
    final ScheduledExecutorService scheduler;
    final static List<ClientProcessor> processors = new LinkedList<>();
    protected Map<String, ServiceInfo> serviceInfoCaches = new ConcurrentHashMap<>();

    private volatile static LongPollingService longPollingService = null;

    public static LongPollingService newInstance() {
        if (longPollingService == null){
            synchronized (LongPollingService.class){
                if (longPollingService == null){
                    longPollingService =  new LongPollingService();
                }
            }
        }
        return longPollingService;
    }
    private LongPollingService() {
        scheduler = Executors.newScheduledThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("long-polling-thread");
            return t;
        });
        // 定时任务打印请求数
        scheduler.scheduleWithFixedDelay(() -> System.out.println(("[long-pulling] client count " + processors.size())),
            0L, 2L, TimeUnit.SECONDS);

        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setIp("192.168.99.100");
        serviceInfo.setPort(8080);
        serviceInfoCaches.put("my-app-name", serviceInfo);
    }

    public void addLongPullingClient(HttpServletRequest req, HttpServletResponse rsp,String appName) {
        final AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(-1);
        scheduler.execute(new ClientProcessor(asyncContext,appName));
    }

    class ClientProcessor implements Runnable {
        final AsyncContext asyncContext;
        private String key;
        Future<?> asyncTimeoutFuture;
        ClientProcessor(AsyncContext ac,String appName) {
            this.key = appName;
            this.asyncContext = ac;
        }
        @Override
        public void run() {
            // 过期清理
            asyncTimeoutFuture = scheduler.schedule(() -> {
                processors.remove(ClientProcessor.this);
                sendResponse(null);
            }, TIME_OUT, TimeUnit.MILLISECONDS);
            if (serviceInfoCaches.get(key)==null) {
                processors.add(this);
            }
            sendResponse(serviceInfoCaches.get(key));
        }

        // 发送响应
        void sendResponse(Object obj) {
            HttpServletResponse response = (HttpServletResponse)asyncContext.getResponse();
            // 删除 "清理任务"
            if (null != asyncTimeoutFuture) {
                asyncTimeoutFuture.cancel(false);
            }
            try {
                PrintWriter writer = response.getWriter();
                writer.write(obj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                asyncContext.complete();
            }
        }
    }
}
