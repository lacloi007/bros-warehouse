package tpv.bros.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tpv.bros.Const;
import tpv.bros.common.security.CurrentUserInfo;

@Controller
public class BrosController {
	final static String DEFAULT = "redirect:" + Const.PATH_HOME;
	final static Map<String, String> MAPPER = Map.of(
			  "admin" , "redirect:/admin"    // for administrator
			, "user"  , "redirect:/user"     // for normal user
			, "staff" , "redirect:/staff"    // for staff
	);

	@GetMapping(path = {"/", "/index"}) protected String GET__DEFAULT(Model model) { return DEFAULT; }
	@GetMapping(path = Const.PATH_HOME) protected String GET__P001(Model model) { return "pages/P001_home"; }
	@GetMapping(path = "/register") protected String GET__P002(Model model) { return "pages/P002_register"; }
	@GetMapping(path = Const.PATH_LOGIN) protected String GET__P003(Model model) { return "pages/P003_login"; }
	@GetMapping(path = "/reset-password") protected String GET__P004(Model model) { return "pages/P004_reset-password"; }
	@GetMapping(path = "/confirm-password") protected String GET__P005(Model model) { return "pages/P005_confirm-password"; }
	@GetMapping(path = "/price-list") protected String GET__P006(Model model) { return "pages/P006_price-list"; }
	@GetMapping(path = "/admin") protected String GET__A001(Model model) { return "pages/QA001_home"; }
	@GetMapping(path = "/staff") protected String GET__S001(Model model) { return "pages/QS001_home"; }
	@GetMapping(path = "/user") protected String GET__U001(Model model) { return "pages/QU001_home"; }

	@GetMapping(path = Const.PATH_DEFAULT_SUCCESS_URL)
	protected String GET__P003Redirect(Model model) {
		String userRole = CurrentUserInfo.getUserRole();
		return MAPPER.getOrDefault(userRole, DEFAULT);
	}
}
