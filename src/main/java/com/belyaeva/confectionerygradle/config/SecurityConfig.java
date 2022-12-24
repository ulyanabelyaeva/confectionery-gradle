package com.belyaeva.confectionerygradle.config;

import com.belyaeva.confectionerygradle.model.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((request) -> {
                    try {
                        request
                                .requestMatchers("/", "/catalog/**").permitAll()
                                .requestMatchers("/login", "/reg").permitAll()
                                .requestMatchers("/cart/**", "/user/**").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/styles/**", "/image/**", "/js/**").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .formLogin((form) -> form
                                        .loginPage("/login")
                                        .defaultSuccessUrl("/catalog")
                                        .permitAll())
                                .logout((logout) -> logout
                                        .logoutUrl("/logout")
                                        .invalidateHttpSession(true)
                                        .permitAll()
                                        .logoutSuccessUrl("/catalog"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
