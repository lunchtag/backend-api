package nl.lunchtag.resource.Lunchtag.config;

import nl.lunchtag.resource.Lunchtag.config.jwt.TokenConfigurer;
import nl.lunchtag.resource.Lunchtag.config.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;

    @Autowired
    public SecurityConfiguration(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .cors()
                .and()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers("/h2/**").permitAll()
                    .antMatchers("/account/all").permitAll()
                    .antMatchers("/console/**").permitAll()
                    .antMatchers("/swagger-ui.html/**").permitAll()
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                    .antMatchers("/v2/api-docs/**").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/auth/**").permitAll()
                    .antMatchers("/files/**").authenticated()

                .anyRequest().authenticated()
                .and()
                .apply(new TokenConfigurer(tokenProvider));
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
