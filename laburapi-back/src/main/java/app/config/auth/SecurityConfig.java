package app.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
		http
		.authorizeRequests()
		.antMatchers("/")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.csrf().disable()
		.headers().frameOptions().disable();
	}
}