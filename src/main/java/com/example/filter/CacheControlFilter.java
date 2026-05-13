package com.example.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CacheControlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res =
                (HttpServletResponse) response;

        res.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate");

        res.setHeader("Pragma", "no-cache");

        res.setDateHeader("Expires", 0);

        chain.doFilter(request, response);
    }
}