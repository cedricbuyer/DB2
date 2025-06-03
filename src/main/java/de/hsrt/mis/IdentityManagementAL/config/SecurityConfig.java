package de.hsrt.mis.IdentityManagementAL.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/* We are replacing default filter config of spring security, by our self-made config.
 * So that this config is injected is important to annotate EnableWebSecurity and Bean
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // Source: https://youtu.be/oeni_9g7too?si=sQka-sGiCCgOjZ6A

    /* I want spring to inject this object, and as spring may not know how to handle this object, or it may be default,
     * I will implement manually the userdetailservice class
     */

    @Autowired
    // Spring will search a bean which implements UserDetailsService and will inject here.
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(customizer -> customizer.disable())
                /* I disable as I want http to be
                 * stateless(each request different sessionId), so csrf vulneb is not a problem anymore
                 */
                .authorizeHttpRequests(requests ->requests
                        .requestMatchers("register", "login").permitAll()
                        .anyRequest().authenticated())

                /* .formLogin(Customizer.withDefaults()) disable as I prefer stateless: otherway each request to the
                 * answered form will be redirected to the same form, since for accessing to another resource you will
                 * need a sessionId, and remember in stateless every request has a different sessionId
                 */

                // This is needed if testing in postman, as otherway response will be the form in html
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Spring security filter chain begins with UsernamePasswordAuthenticationFilter, but as we want to verify the token:
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // FIXME: Insert PASSWORD SALTING
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /* We need to set our auth manager as we want to configure the auth process(login),
     * in a different way than default (jwt token verification)
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}