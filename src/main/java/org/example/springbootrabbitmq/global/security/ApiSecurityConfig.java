package org.example.springbootrabbitmq.global.security;

import lombok.RequiredArgsConstructor;
import org.example.springbootrabbitmq.global.rsData.RsData;
import org.example.springbootrabbitmq.standard.util.Ut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ApiSecurityConfig {
    private final AuthenticationFilter authenticationFilter;

    @Bean
    @Order(2)
    SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(HttpMethod.GET, "/api/*/posts/{id:\\d+}", "/api/*/posts", "/api/*/postComments/{id:\\d+}")
                                .permitAll()
                                .requestMatchers("/api/*/members/login", "/api/*/members/logout")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .csrf(
                        csrf -> csrf
                                .disable()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                )
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(
                                        (request, response, authException) -> {
                                            response.setContentType("application/json;charset=UTF-8");
                                            response.setStatus(403);
                                            response.getWriter().write(
                                                    Ut.json.toString(
                                                            RsData.of("403-1", request.getRequestURI() + ", " + authException.getLocalizedMessage())
                                                    )
                                            );
                                        }
                                )
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
