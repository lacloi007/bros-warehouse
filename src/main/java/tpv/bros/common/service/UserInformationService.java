package tpv.bros.common.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tpv.bros.common.table.User;
import tpv.core.query.Query;

@Service
public class UserInformationService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = Query.select(User.USERNAME, User.PASSWORD, User.ROLES)
					.from(User.class)
					.where(User.USERNAME.equal(username))
					.querySingle();
			if (user == null)
				throw new RuntimeException();

			return org.springframework.security.core.userdetails.User
					.withUsername(user.getUsername())
					.password(user.getPassword())
					.roles(user.getRoles().toArray(String[]::new))
					.build();
		} catch (Exception e) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

	enum Role { user, admin, staff }
}
