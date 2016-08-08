package com.andresolarte.harness.webapp.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;


public class LogFilter implements Filter{

     @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log(request);
        chain.doFilter(request,response);
    }

    private void log(ServletRequest request) throws IOException {

        System.out.println("Log request: " + formatRequest((HttpServletRequest)request));
    }

    private String formatRequest(HttpServletRequest servletRequest) throws IOException {
        StringBuilder sb=new StringBuilder();
        sb.append("Method: ");
        sb.append(servletRequest.getMethod());
        sb.append("\n");
        Enumeration<String> headerNames=servletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            sb.append(headerName);
            sb.append(": ");
            sb.append(servletRequest.getHeader(headerName));
            sb.append("\n");
        }
        Enumeration<String> parameterNames=servletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            sb.append(parameterName);
            sb.append(": ");
            sb.append(servletRequest.getParameter(parameterName));
            sb.append("\n");
        }
        sb.append("QueryString: ");
        sb.append(servletRequest.getQueryString());
        sb.append("\n");

        sb.append("Body: ");
        sb.append(getBody(servletRequest));
        sb.append("\n");


        return sb.toString();
    }

    @Override
    public void destroy() {

    }

    private String getBody(HttpServletRequest request) throws IOException {
        String body = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
        return body;

    }


}