package tpv.bros.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tpv.bros.web.form.U011Form;

@RestController
public class U010Controller {
	@PostMapping(value = "/receivingOrderCreate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody U011Form form) {
		return new ResponseEntity<>(form, HttpStatus.OK);
	}
}
