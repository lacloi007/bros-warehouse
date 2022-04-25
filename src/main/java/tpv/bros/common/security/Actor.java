package tpv.bros.common.security;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class Actor extends User {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	@Getter private String userId;
	@Getter private String defaultRole;
	@Getter private Set<String> roles = new LinkedHashSet<String>();

	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public Actor(String uid, String username, String password, Set<String> roles, List<GrantedAuthority> authorities) {
		super(username, password, true, true, true, true, authorities);

		this.userId = uid;
		this.roles.addAll(roles);
		if (this.roles.isEmpty() == false)
			this.defaultRole = this.roles.stream().findFirst().get();
	}
}
