package com.innovaid.subscriptionmanager.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // 1. Extract token from Authorization header ("Bearer <token>")
    String header = request.getHeader("Authorization");
    String token = null;

    if (header != null && header.startsWith("Bearer ")) {
      token = header.substring(7);
    } else {
      // Optional: Fallback to check URL parameters if needed (e.g., ?token=xyz)
      token = request.getParameter("token");
    }

    // 2. Validate token and set authentication (pseudo-code)
    if (token != null /* && tokenService.isValid(token) */) {
      // String username = tokenService.extractUsername(token);
      // UsernamePasswordAuthenticationToken auth = ...;
      // SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);
  }
}