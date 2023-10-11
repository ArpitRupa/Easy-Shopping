package com.fullstackshopping.easyshopping.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //password encoder for user passwords
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetails){
        //used to authenticate users in the database via UserDetailsService
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        //associates the provider with the data source for user information
        daoProvider.setUserDetailsService(userDetails);
        // ProviderManager manages a list of AuthenticationProvider instances
        // gives the authentication request to the first provider that supports the specific authentication token
        return new ProviderManager(daoProvider);
    }

    // authorize all traffic into the api [useful while in development]
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize ->
                        {
                            authorize.requestMatchers("/auth/**").permitAll();
                            authorize.requestMatchers("/api/**").permitAll();
                            authorize.anyRequest().authenticated();
                        })
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
