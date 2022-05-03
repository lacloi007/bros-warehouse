package tpv.bros.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tpv.bros.web.form.EnumerateForm;

@RestController
public class EnumerateController {
	@PostMapping(value = "/enumerate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> loader(@RequestBody EnumerateForm form) {
		return null;
	}
}
