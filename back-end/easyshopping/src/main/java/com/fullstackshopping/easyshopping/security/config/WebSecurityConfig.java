package com.fullstackshopping.easyshopping.security.config;

import com.fullstackshopping.easyshopping.security.util.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration that defines security rules and settings for the application.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final RSAKeyProperties keys;
    @Autowired
    public WebSecurityConfig(RSAKeyProperties keys){
        this.keys = keys;
    }

    //password encoder for user passwords
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the AuthenticationManager used to authenticate users with the database via UserDetailsService.
     *
     * @param userDetails The UserDetailsService implementation used for user authentication.
     * @return An instance of AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetails){
        //used to authenticate users in the database via UserDetailsService
        //compares the encoded password to plaintext password
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        //associates the provider with the data source for user information
        daoProvider.setUserDetailsService(userDetails);
        // set PasswordEncoder for DAO
        daoProvider.setPasswordEncoder(passwordEncoder());
        // ProviderManager manages a list of AuthenticationProvider instances
        // gives the authentication request to the first provider that supports the specific authentication token
        return new ProviderManager(daoProvider);
    }

    // authorize all traffic into the api [useful while in development]
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize ->
                        {
                            authorize.requestMatchers("/auth/**").permitAll();
                            authorize.requestMatchers(
                                    "/api/swagger-ui/**",
                                    "/api/users/register",
                                    "/v3/api-docs/**"
                            ).permitAll();
                            authorize.requestMatchers("",
                                    "/admin/**",
                                    "/api/users",
                                    "/api/users/delete/**"
                                    ).hasRole("ADMIN");
                            authorize.requestMatchers(
                                    "/user/**",
                                    "/api/addresses",
                                    "/api/products"
                                    ).hasAnyRole("ADMIN","USER");
                            authorize.anyRequest().authenticated();
                        })
                // configure Spring Security to act as OAuth 2.0 resource server
                // default uses RS256 algorithm to verify JWT tokens
                .oauth2ResourceServer((oauth2) -> oauth2
                        // Use JWT for authentication
                        .jwt(jwt -> jwt
                                // use this to extract the authorities/roles from the JWT
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                )
                // we are using JWTs so there is no reason to store anything in the server side session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    /**
     * Creates a JwtDecoder configured to verify the digital signature of JWTs with the public key.
     *
     * @return JwtDecoder configured for signature verification with the public key.
     */
    @Bean
    public JwtDecoder jwtDecoder(){
        // uses public key to verify digital signature of JWT
        // ensures that it has not been altered since signed
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    /**
     * Creates and configures a JwtEncoder to sign JWTs using the Config's RSA key pair.
     *
     * @return JwtEncoder configured to sign JWTs with the Config's RSA key pair.
     */
    @Bean
    public JwtEncoder jwtEncoder(){
        // JWK contains the necessary information to sign JWTs
        JWK jwk = new RSAKey.Builder(this.keys.getPublicKey()).privateKey(this.keys.getPrivateKey()).build();
        // source provides the JWK to the JwtEncoder when needed for signing JWTs
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }


    /**
     * Creates and configures a JwtAuthenticationConverter to handle JWT authentication.
     *
     * @return JwtAuthenticationConverter configured to extract user authorities from the JWT.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        // JwtGrantedAuthoritiesConverter converts claims into JWT user authorities
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // configure the name of the claim in the JWT from user role
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("role");

        // add prefix to the role; Spring Security convention
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        // helps extract user authentication details from JWT and create an Authentication object
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();

        // configure to use the JwtGrantedAuthoritiesConverter we created
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtConverter;
    }

}
