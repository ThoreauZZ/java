package com.thoreauz.bootlearn.web;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 2018/4/28 下午5:08.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
public class AsyncRequestProcessor implements Runnable {
    private AsyncContext asyncContext;

    public AsyncRequestProcessor(AsyncContext asyncCtx) {
        this.asyncContext = asyncCtx;
    }

    @Override
    public void run() {
        try (PrintWriter out = asyncContext.getResponse().getWriter()) {
            Thread.sleep(1000);
            out.write("Welcome Servlet 3 .");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            asyncContext.complete();
        }
    }
}
