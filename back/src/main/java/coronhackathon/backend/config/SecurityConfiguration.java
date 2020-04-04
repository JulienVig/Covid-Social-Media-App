package coronhackathon.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("userPass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }

    @Override
    /**
     * csrf() is Ben's magic to allow post requests
     * Cross-site request forgery is a type of attack. See wikipedia page for more detail.
     * https://en.wikipedia.org/wiki/Cross-site_request_forgery
     */
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().
                authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}