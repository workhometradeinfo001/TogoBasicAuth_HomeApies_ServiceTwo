package org.togo.homeapies.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.togo.homeapies.components.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
<<<<<<< HEAD
=======
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)
@EnableJpaRepositories(basePackages = "org.togo.homeapies.repos")
@RequiredArgsConstructor
public class SpringSecurity {
    private final JwtAuthenticationFilter authenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
<<<<<<< HEAD
                .authorizeHttpRequests(request -> request
                        .anyRequest().permitAll()
=======
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Add this!
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/mtmdt/**").authenticated()
                        .requestMatchers("/newsfeedpost/**").authenticated()
                        .requestMatchers("/exchange-at/**").permitAll()
                        .anyRequest().authenticated()
>>>>>>> d2b5cde (added craete post, exchange access token with refresh_token)
                );
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
