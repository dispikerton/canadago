package ru.ivanov.canadago.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(CorsConfigurationSource corsConfigurationSource, HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().permitAll(); // Разрешаем все запросы
    http.cors().configurationSource(corsConfigurationSource); // Используем настройки CORS из CorsConfig
    http.csrf().disable(); // Отключаем CSRF
    http.headers().frameOptions().disable(); // Отключаем X-Frame-Options
    http.headers().xssProtection().disable(); // Отключаем X-XSS-Protection
    http.headers().contentTypeOptions().disable(); // Отключаем X-Content-Type-Options
    return http.build();
  }
}
