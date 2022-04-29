package tpv.bros.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tpv.bros.web.form.P002Form;
import tpv.core.database.Database;
import tpv.core.database.EntityValidator;
import tpv.core.define.enm.EntityErrorCode;

@RestController
public class P002Controller {
	/**
	 * 
	 * @return
	 */
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody P002Form user) {
		/** for validating */ {
			EntityValidator.validateBeforeInsert(user);
			if (StringUtils.isEmpty(user.getRegisterConfirmPassword()))
				user.errors.addError("registerConfirmPassword", EntityErrorCode.mandatory, "Field [registerConfirmPassword] is mandatory");
			if (StringUtils.equals(user.getPassword(), user.getRegisterConfirmPassword()) == false)
				user.errors.addError("Password is not matched");
			if (user.errors.hasError())
				return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
		}

		// update default role from register function
		user.getRoles().add("user");

		try {
			Database.insert(user);
		} catch (Exception e) {
			e.printStackTrace(System.err);

			user.errors.addError(e.getMessage());
			return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
