package com.mvc.henry.filter;

import com.mvc.henry.constats.BaseConstats;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhaozhou on 16/8/11.
 */
public class LogSessionFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String logSessionId;
        if( null == req.getHeader(BaseConstats.LOG_SESSION)) {
             logSessionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        }else {
            logSessionId = req.getHeader(BaseConstats.LOG_SESSION);
        }
        MDC.put(BaseConstats.LOG_SESSION,logSessionId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
