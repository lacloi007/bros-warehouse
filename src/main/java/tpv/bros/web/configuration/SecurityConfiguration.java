package tpv.bros.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import tpv.bros.common.service.UserInformationService;

@Order(1)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	/**
	@Bean
	AuthenticationSuccessHandler logicSuccessHandler() {
		LoginSuccessHandler handler = new LoginSuccessHandler();
		handler.setDefaultTargetUrl(HomeController.PATH_HOME);
		return handler;
	}

	@Bean
	AuthenticationFailureHandler loginFailureHandler() {
		SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler(HomeController.PATH_HOME);
		return handler;
	}
	*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	public UserDetailsService UserDetailsService() {
		return new UserInformationService();
	}

	/**
	public static class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	}

	public static class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	}
	*/
}
