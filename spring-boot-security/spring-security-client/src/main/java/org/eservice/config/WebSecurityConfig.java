package org.eservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/register"
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    public SecurityFilterChain securityFilterChain
            (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests((authz) -> authz
                        .antMatchers("/register").permitAll()
                        .antMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
                );
//                .formLogin()
//                .and()
//                .logout()
//                .permitAll();
        return httpSecurity.build();


    }



}
