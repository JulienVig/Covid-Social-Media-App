package coronhackathon.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    /**
     * csrf() is Ben's magic to allow post requests
     * Cross-site request forgery is a type of attack. See wikipedia page for more detail.
     * https://en.wikipedia.org/wiki/Cross-site_request_forgery
     */
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().
                authorizeRequests().antMatchers("/**").permitAll();
    }

}