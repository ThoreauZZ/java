package com.thoreauz.bootlearn.web;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 2018/4/28 下午4:49.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Create the thread pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100),
            new ThreadFactoryBuilder().setNameFormat("my-name-%d").build());
        servletContextEvent.getServletContext().setAttribute("executor", executor);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)servletContextEvent.getServletContext()
                                                                             .getAttribute("executor");
        executor.shutdown();
    }
}
