package by.agsr.monitor.configuration.filter;

import by.agsr.common.Role;
import by.agsr.monitor.Tuple;
import by.agsr.monitor.service.auth.interfaces.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

import static by.agsr.monitor.Constants.BEARER_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (jwt == null) {
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.unauthenticated(null, null));
                filterChain.doFilter(request, response);
            } else {
                if (!jwt.startsWith(BEARER_PREFIX)) {
                    throw new BadCredentialsException("Invalid JWT header");
                }
                jwt = jwt.replace(BEARER_PREFIX, "");
                Tuple<String, Role> nicknameAndRole = jwtService.getNicknameAndRole(jwt);
                Authentication authentication =
                  new UsernamePasswordAuthenticationToken(nicknameAndRole.getT1(), null, List.of(nicknameAndRole.getT2()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/auth/refresh-token") ||
          request.getServletPath().equals("/auth/sign-in") ||
          request.getServletPath().equals("/auth/sign-up") ||
          request.getServletPath().equals("/statistics");
    }
}
