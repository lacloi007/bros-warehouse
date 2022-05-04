package tpv.bros.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tpv.bros.common.table.ReceivingOrder;
import tpv.core.database.Database;

@RestController
@RequestMapping("/api")
public class U010Controller {
	final static String target = "/ReceivingOrder";

	@PutMapping(value = target, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ReceivingOrder form) {
		try {
			Database.insert(form);
		} catch (Exception e) {
			e.printStackTrace(System.err);

			form.errors.addError(e.getMessage());
			return new ResponseEntity<>(form, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(form, HttpStatus.OK);
	}

	@PostMapping(value = target, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody ReceivingOrder form) {
		try {
			Database.update(form);
		} catch (Exception e) {
			e.printStackTrace(System.err);

			form.errors.addError(e.getMessage());
			return new ResponseEntity<>(form, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(form, HttpStatus.OK);
	}

	@DeleteMapping(value = target, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestBody ReceivingOrder form) {
		try {
			Database.delete(form);
		} catch (Exception e) {
			e.printStackTrace(System.err);

			form.errors.addError(e.getMessage());
			return new ResponseEntity<>(form, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(form, HttpStatus.OK);
	}
}
