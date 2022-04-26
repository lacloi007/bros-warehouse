package tpv.bros.common.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrentUserInfo {
	private final static String DEFAULT_VALUE = "";
	private static Actor loadActor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof Actor) {
			return (Actor) authentication.getPrincipal();
		}
		return null;
	}

	public static String getUserName() { return Optional.ofNullable(loadActor()).map(Actor::getUsername).orElse(DEFAULT_VALUE); }
	public static String getUserId() { return Optional.ofNullable(loadActor()).map(Actor::getUserId).orElse(DEFAULT_VALUE); }
	public static String getUserRole() { return Optional.ofNullable(loadActor()).map(Actor::getDefaultRole).orElse(DEFAULT_VALUE); }
	public static String getCreatedBy() { return Optional.ofNullable(loadActor()).map(Actor::getUserId).orElse("C00B1X20220426X000000000000000"); }
}
