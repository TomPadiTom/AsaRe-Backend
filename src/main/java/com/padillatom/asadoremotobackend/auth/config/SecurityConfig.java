package com.padillatom.asadoremotobackend.auth.config;

import com.padillatom.asadoremotobackend.auth.filter.JwtTokenFilter;
import com.padillatom.asadoremotobackend.auth.provider.UsernameAndPasswordAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsernameAndPasswordAuthenticationProvider usernameAndPasswordAuthenticationProvider;

    private final JwtTokenFilter jwtTokenFilter;

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(usernameAndPasswordAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic(httpBasic -> httpBasic.disable());
        http.formLogin(formLogin -> formLogin.disable());
        http.csrf(csrf -> csrf.disable());

        http.authorizeRequests()
                // Authentication Endpoints
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                // User Endpoints
                .requestMatchers(HttpMethod.GET, "api/v1/user/me").authenticated()
                .requestMatchers(HttpMethod.PUT, "api/v1/user/me").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/v1/user/{id}").hasRole(ROLE_ADMIN)

                // Category Endpoints
                .requestMatchers(HttpMethod.GET, "api/v1/category/all").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/category/create").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT, "api/v1/category/{id}").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, "api/v1/category/{id}").hasRole(ROLE_ADMIN)

                // Test Endpoints
                .requestMatchers(HttpMethod.GET, "/api/v1/test").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/test/admin").hasRole(ROLE_ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/v1/test/user").hasRole(ROLE_USER)
                .anyRequest().permitAll();

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/*", "http://localhost:4200/*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
