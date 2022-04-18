package tpv.bros.common.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			String code = Role.valueOf(username).name();
			return User.withUsername(code).password(code).roles(code)
					.build();
		} catch (Exception e) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

	enum Role { user, admin, staff }
}
