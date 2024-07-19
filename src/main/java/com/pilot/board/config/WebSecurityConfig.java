package com.pilot.board.config;

import com.pilot.board.handler.AuthFailureHandler;
import com.pilot.board.handler.CustomAccessDeniedHandler;
import com.pilot.board.handler.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/favicon.ico", "/error", "classpath:/static/**", "classpath:/templates/**").permitAll()
                        .requestMatchers("/error-page", "/accessDenied").permitAll()
                        .requestMatchers("/", "/users/*", "/boards").permitAll()
                        .requestMatchers("/boards/new", "/replys/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler()));

        http
                .formLogin((form) -> form
                        .loginPage("/users/signIn")
                        .loginProcessingUrl("/users/auth")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/boards")
                        .failureHandler(authFailureHandler())
                        .permitAll());

        http
                .logout((logout) -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/boards")
                        .deleteCookies("JSESSIONID"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthFailureHandler authFailureHandler() {
        return new AuthFailureHandler();
    }
}
