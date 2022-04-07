package tpv.bros.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	public static final String PATH_HOME = "/home";
	@GetMapping(path = {"/", PATH_HOME})
	public String home(Model model) {
		return "pages/home";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "pages/dashboard";
	}
}
