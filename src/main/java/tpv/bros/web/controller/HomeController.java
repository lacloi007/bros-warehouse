package tpv.bros.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tpv.bros.common.table.User;
import tpv.core.query.Query;

@Controller
public class HomeController {
	public static final String PATH_HOME = "/home";
	@GetMapping(path = {"/", PATH_HOME})
	public String home(Model model) {
		List<User> list = Query.select(User.USERNAME).from(User.class).queryList();
		System.out.println(list.size());
		return "pages/home";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "pages/dashboard";
	}
}
