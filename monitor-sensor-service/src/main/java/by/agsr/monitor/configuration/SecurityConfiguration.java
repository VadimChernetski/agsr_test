package by.agsr.monitor.configuration;

import by.agsr.common.Role;
import by.agsr.monitor.configuration.filter.JwtAuthorizationFilter;
import by.agsr.monitor.configuration.filter.StatisticsServiceFilter;
import by.agsr.monitor.dao.interfaces.RefreshTokenService;
import by.agsr.monitor.dao.interfaces.UserService;
import by.agsr.monitor.service.auth.impl.JwtServiceImpl;
import by.agsr.monitor.service.auth.interfaces.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final StatisticsServiceFilter statisticsServiceFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
          .csrf(AbstractHttpConfigurer::disable)
          .httpBasic(AbstractHttpConfigurer::disable)
          .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
          .cors(corsConfig -> corsConfig.configurationSource(request -> {
              CorsConfiguration config = new CorsConfiguration();
              config.setAllowedMethods(Collections.singletonList("*"));
              config.setExposedHeaders(Collections.singletonList("*"));
              config.setAllowedHeaders(Collections.singletonList("*"));
              //here we should provide list of allowed origins in case if we have to set up  allowCredentials = true
              config.setAllowedOrigins(Collections.singletonList("*"));
              config.setAllowCredentials(false);
              config.setMaxAge(3000L);
              UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
              source.registerCorsConfiguration("/**", config);
              return config;
          }))
          .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
              .requestMatchers("/installed-sensor").hasAnyAuthority(Role.ADMINISTRATOR.getAuthority(), Role.VIEWER.getAuthority())
              .requestMatchers("/sensor", "/sensor/*", "/installed-sensor",
                "/installed-sensor/*", "/measurement-unit", "/sensor-type").hasAuthority(Role.ADMINISTRATOR.getAuthority())
              .requestMatchers("/auth/*", "/statistics").permitAll()
              .requestMatchers("/v3/api-docs", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
              .anyRequest().permitAll()
          )
          .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
          .addFilterBefore(statisticsServiceFilter, UsernamePasswordAuthenticationFilter.class)
          .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(JwtAuthenticationProvider jwtAuthenticationProvider) {
        return new ProviderManager(List.of(jwtAuthenticationProvider));
    }
}
