package com.safatoyota32bit.backendproject.config;
import com.safatoyota32bit.backendproject.filter.JwtRequestFilter;
import com.safatoyota32bit.backendproject.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{


    private final JwtRequestFilter jwtRequestFilter;
    private final userService UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/verification/**").permitAll()
                        .requestMatchers("/user/create").permitAll()
                        .requestMatchers("/user/authenticate").permitAll()
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/sales/**").hasRole("Role_Cashier")
                        .requestMatchers("/user/delete/{id}").hasRole("Role_Admin")
                        .requestMatchers("/user/update/{userID}").hasRole("Role_Admin")
                        .requestMatchers("/user").hasRole("Role_Admin")
                        .requestMatchers("/{userID}/roles/{roleID}").hasRole("Role_Admin")
                        .requestMatchers("/report/**").hasRole("Role_Store_Manager")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(UserService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

}
