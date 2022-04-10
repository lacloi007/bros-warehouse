package tpv.bros.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import tpv.bros.web.controller.HomeController;

@Order(1)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] anonymous = { HomeController.PATH_HOME };
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(anonymous).anonymous();
		super.configure(http);
	}

	public static class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	}

	public static class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	}
}
