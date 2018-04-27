package com.thoreauz.bootlearn.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 2018/4/28 下午4:23.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
@WebServlet("/welcome")
public class SyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Thread.sleep(1000);
            PrintWriter out = resp.getWriter();
            out.write("Welcome Servlet 3 .");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
