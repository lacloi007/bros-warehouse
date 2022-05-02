package tpv.bros.web.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import tpv.bros.Const;
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
		SystemAuthenticationProvider provider = new SystemAuthenticationProvider();
		provider.setHideUserNotFoundExceptions(true);
		provider.setUserDetailsService(userDetailsService);
		auth.authenticationProvider(provider);
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
		// http.csrf().disable();

		// Các trang không yêu cầu login
		http.authorizeRequests()
			.antMatchers(Const.PUBLIC_URL).permitAll()
			.antMatchers("/userPanel/**").hasAuthority("user")
			.anyRequest().authenticated()

			.and().formLogin()
				.loginPage(Const.PATH_P003_LOGIN)
				.defaultSuccessUrl(Const.PATH_DEFAULT_SUCCESS_URL)
				.failureUrl(Const.PATH_FAILURE_URL)
				.permitAll()
				// .usernameParameter("username")
				// .passwordParameter("password")

			.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.logoutSuccessUrl(Const.PATH_LOGOUT_SUCCESS_URL)
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()

			// .and().csrf().disable().exceptionHandling().accessDeniedPage("/401.html")
			.and().exceptionHandling().accessDeniedPage("/401.html")

			// for restful
			.and().authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, "/register").permitAll()
			.and().csrf().disable()

			// Cấu hình Remember Me.
			//.and().rememberMe()
			//	.tokenRepository(this.persistentTokenRepository())
			//	.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
		;

		http.addFilterAfter(new GenericFilterBean() {
			@Override
			public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.setHeader("Set-Cookie", "locale=vn; HttpOnly; SameSite=strict");
				chain.doFilter(request, response);
			}
		}, BasicAuthenticationFilter.class);

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

	/**
	@Bean
	public PasswordEncoder passwordEncoder() {
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
	*/

	/**
	public static class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	}

	public static class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	}
	*/
}
