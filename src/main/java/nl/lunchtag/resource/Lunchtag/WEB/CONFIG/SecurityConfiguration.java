package nl.lunchtag.resource.Lunchtag.WEB.CONFIG;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


protected void configure(HttpSecurity http) throws Exception{

    // Enables CORS configuration, for setting up trusted sources.
    http.cors();

    // No token generation because this is a REST-API and not a SSR-Application
    http.csrf().disable();

    // No sessions because REST API is stateless
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Disables frame options, fix for being able to view h2 UI.
    http.headers().frameOptions().disable();
//
//    // Add the JWT filter, such that the authentication can be retrieved from the token.
//    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    // Setup all allowed endpoints
    http.authorizeRequests().antMatchers("/auth/login").permitAll();
    http.authorizeRequests().antMatchers("/auth/register").permitAll();
    http.authorizeRequests().antMatchers("/auth/refresh").permitAll();

    // Allow all to h2 (maybe not best but for development)
    http.authorizeRequests().antMatchers("/h2/**").permitAll();
    http.authorizeRequests().antMatchers("/console/**").permitAll();
    http.authorizeRequests().antMatchers("/swagger-ui.html/**").permitAll();
    http.authorizeRequests().antMatchers("/api/**").permitAll();
    http.authorizeRequests().antMatchers("/swagger-resources/**").permitAll();
    http.authorizeRequests().antMatchers("/v2/api-docs/**").permitAll();
    http.authorizeRequests().antMatchers("/webjars/**").permitAll();



    // All other endpoints are blocked if no valid token is provided.
    http.authorizeRequests().anyRequest().authenticated();
}

}
