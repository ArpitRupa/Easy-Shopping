package com.fullstackshopping.easyshopping.common.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * Redirects requests with a trailing slash ("/") to the same URL without it.
 */
@Component
public class TrailingSlashRedirectFilter extends OncePerRequestFilter {

/**
 * Processes incoming requests to remove trailing slashes and redirect if present.
 *
 */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if (requestUri.endsWith("/")) {
            String newRequestUri = requestUri.substring(0, requestUri.length() - 1);
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", newRequestUri);
            return;
        }
        filterChain.doFilter(request, response);
    }
}