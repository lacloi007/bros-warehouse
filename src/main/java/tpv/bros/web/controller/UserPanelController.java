package tpv.bros.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userPanel")
public class UserPanelController {
	@GetMapping(path = "/receive") protected String GET__U010(Model model) { return "pages/U010_receive"; }
}
