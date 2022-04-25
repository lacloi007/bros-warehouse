package tpv.bros.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tpv.bros.common.security.CurrentUserInfo;

@Controller
public class BrosController {
	public static final String PATH_HOME = "/home";
	final static String DEFAULT = "redirect:" + PATH_HOME;
	final static Map<String, String> MAPPER = Map.of(
			  "admin" , "redirect:/admin"    // for administrator
			, "user"  , "redirect:/home"     // for normal user
			, "staff" , "redirect:/staff"    // for staff
	);

	@GetMapping(path = { "/", PATH_HOME })
	public String home(Model model) {
		return "pages/home";
	}

	@GetMapping(path = "/admin")
	public String admin(Model model) {
		return "pages/admin/home";
	}

	@GetMapping(path = "/staff")
	public String staff(Model model) {
		return "pages/staff/home";
	}

	@GetMapping(path = "/loginSuccess")
	public String login(Model model) {
		String userRole = CurrentUserInfo.getUserRole();
		return MAPPER.getOrDefault(userRole, DEFAULT);
	}
}
