package com.example.classassignment.config;

import com.example.classassignment.filter.JwtFilter;
import com.example.classassignment.security.JwtEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JwtEntryPoint jwtEntryPoint;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return  provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth->auth.requestMatchers("/home/**").hasAnyAuthority("ROLE_STUDENT","ROLE_TEACHER")
                        .requestMatchers("/user/teacher/**").hasAuthority("ROLE_TEACHER")
                        .requestMatchers("/test/**").hasAnyAuthority("ROLE_STUDENT","ROLE_TEACHER")
                        .requestMatchers("/user/student/**").hasAnyAuthority("ROLE_STUDENT","ROLE_TEACHER")
                        .requestMatchers("/auth/login")
                        .permitAll().requestMatchers("/auth/register").permitAll()
                        .anyRequest()
                        .authenticated()).exceptionHandling(excp->excp.authenticationEntryPoint(jwtEntryPoint))
                        .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


                return http.build();
    }
}
