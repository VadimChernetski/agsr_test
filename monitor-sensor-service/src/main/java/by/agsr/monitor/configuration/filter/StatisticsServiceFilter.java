package by.agsr.monitor.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Objects;

import static by.agsr.monitor.Constants.BEARER_PREFIX;

@Component
@RequiredArgsConstructor
public class StatisticsServiceFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Value("${statistics.token}")
    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/statistics")) {
            try {
                String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
                if (!headerToken.startsWith(BEARER_PREFIX)) {
                    throw new BadCredentialsException("Invalid auth header");
                }
                headerToken = headerToken.replace(BEARER_PREFIX, "");
                if (!Objects.equals(headerToken, token)) {
                    throw new BadCredentialsException("Invalid auth token");
                }
                filterChain.doFilter(request, response);
            } catch (Exception e) {
                handlerExceptionResolver.resolveException(request, response, null, e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/auth/refresh-token") ||
          request.getServletPath().equals("/auth/sign-in") ||
          request.getServletPath().equals("/auth/sign-up") ||
          request.getServletPath().equals("/v3/api-docs");
    }


}
