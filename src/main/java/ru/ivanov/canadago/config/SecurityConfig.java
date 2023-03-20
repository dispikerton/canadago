package ru.ivanov.canadago.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(CorsConfigurationSource corsConfigurationSource, HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().permitAll(); // Разрешаем все запросы (замените настроением авторизации по вашему выбору)
    http.cors().configurationSource(corsConfigurationSource); // Используем настройки CORS из CorsConfig
    http.csrf().disable(); // Отключаем CSRF (если не требуется)
    return http.build();
  }
}
