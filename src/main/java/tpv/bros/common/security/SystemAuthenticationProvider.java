package tpv.bros.common.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.Setter;

public class SystemAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	@Setter UserDetailsService userDetailsService;
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null)
			throw new BadCredentialsException("BadCredentialsException");
		Actor actor = Actor.class.cast(userDetails);
		if (passwordComparator(actor, authentication.getCredentials().toString()) == false)
			throw new BadCredentialsException("BadCredentialsException");
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		return userDetailsService.loadUserByUsername(username);
	}

	/**
	 * @param actor
	 * @param pwd
	 * @return
	 */
	private boolean passwordComparator(Actor actor, String pwd) {
		if (pwd.equals(actor.getPassword()))
			return true;
		return false;
	}
}
