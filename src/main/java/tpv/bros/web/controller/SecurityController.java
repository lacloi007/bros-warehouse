package tpv.bros.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	@GetMapping(path = "/login")
	public String login(Model model) {
		return "pages/login";
	}

	@GetMapping("/hello")
	public String hello(Model model) {
		return "pages/hello";
	}
}
