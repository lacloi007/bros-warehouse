package tpv.bros.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tpv.bros.common.security.Actor;
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
			List<GrantedAuthority> authorities = new ArrayList<>();
			if (user.getRoles().isEmpty() == false) {
				for (String role: user.getRoles())
					authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}

			Actor actor = new Actor(user.getId(), user.getUsername(), user.getPassword(), user.getRoles(), authorities);
			return actor;
		} catch (Exception e) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}

	enum Role { user, admin, staff }
}
