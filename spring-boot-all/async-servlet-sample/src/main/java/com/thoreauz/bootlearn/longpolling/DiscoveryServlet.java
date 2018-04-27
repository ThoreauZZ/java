package com.thoreauz.bootlearn.longpolling;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2018/4/28 下午5:58.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
@WebServlet(value = "/discovery",asyncSupported = true)
public class DiscoveryServlet extends HttpServlet {
    private static LongPollingService longPollingService = LongPollingService.newInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // check params
        String appName = request.getParameter("applicationName");
        longPollingService.addLongPullingClient(request,response,appName);
    }
}
