package com.example.ShopCMSBlog.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Kích hoạt tính năng bảo mật web của Spring Security
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Sử dụng BCryptPasswordEncoder
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF (thường cần cho REST API)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/users/search").permitAll() // Cho phép truy cập tất cả các đường dẫn dưới /api/v1/users mà không cần xác thực
                        .anyRequest().authenticated() // Tất cả các request khác yêu cầu xác thực
                );
        return http.build();
    }
}