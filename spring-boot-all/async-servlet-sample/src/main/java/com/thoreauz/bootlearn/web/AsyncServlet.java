package com.thoreauz.bootlearn.web;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 2018/4/28 下午4:40.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
@WebServlet(value = "/hello", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        AsyncContext asyncCtx = request.startAsync();
        //asyncCtx.addListener(new AppAsyncListener());
        asyncCtx.setTimeout(9000);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
            .getServletContext().getAttribute("executor");
        executor.execute(new AsyncRequestProcessor(asyncCtx));
    }
}
