package tpv.bros.web.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.thymeleaf.util.StringUtils;

import tpv.bros.common.security.SystemAuthenticationProvider;
import tpv.bros.common.service.UserInformationService;

@Order(1)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired DataSource dataSource;
	@Autowired UserInformationService userDetailsService;

	/**
	AuthenticationSuccessHandler logicSuccessHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		handler.setDefaultTargetUrl(HomeController.PATH_HOME);
		return handler;
	}

	AuthenticationFailureHandler loginFailureHandler() {
		SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler(HomeController.PATH_HOME);
		return handler;
	}
	*/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		SystemAuthenticationProvider system = new SystemAuthenticationProvider();
		system.setUserDetailsService(userDetailsService);
		auth.authenticationProvider(system);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
			"/js/**"
			, "/static/**"
			, "/css/**"
		);

		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowSemicolon(true);
		web.httpFirewall(firewall);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// Các trang không yêu cầu login
		http.authorizeRequests()
			.antMatchers("/", "/home", "/login").permitAll()
			.anyRequest().authenticated()

			.and().formLogin()
				.loginPage("/login")//
				.successForwardUrl("/home")
				.failureUrl("/home")
				.usernameParameter("username")//
				.passwordParameter("password")

			.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/home")

			.and().csrf().disable().exceptionHandling().accessDeniedPage("/home")

			// Cấu hình Remember Me.
			.and().rememberMe()
				.tokenRepository(this.persistentTokenRepository())
				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
		;

		// Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		//http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		// Trang chỉ dành cho ADMIN
		//http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
	}

	/**
	@Bean public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	} 
	*/

	PersistentTokenRepository persistentTokenRepository() {
		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
		return memory;
	}

	PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return StringUtils.equals(rawPassword, encodedPassword);
			}

			@Override
			public String encode(CharSequence rawPassword) {
				return String.valueOf(rawPassword);
			}

		};
	}

	/**
	public static class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	}

	public static class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	}
	*/
}
