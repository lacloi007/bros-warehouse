package tpv.bros.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tpv.bros.common.table.User;
import tpv.core.database.Database;
import tpv.core.database.EntityValidator;

@RestController
public class RestUserController {
	/**
	 * 
	 * @return
	 */
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody User user) {
		/** for validating */ {
			EntityValidator.validateBeforeInsert(user);
			if (StringUtils.isEmpty(user.getRegisterConfirmPassword()))
				user.errors.addError("registerConfirmPassword", "Field [registerConfirmPassword] is mandatory");
			if (StringUtils.equals(user.getPassword(), user.getRegisterConfirmPassword()) == false)
				user.errors.addError("Password is not matched");
			if (user.errors.hasError())
				return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}

		// update default role from register function
		user.getRoles().add("user");

		try {
			Database.insert(user);
		} catch (Exception e) {
			e.printStackTrace(System.err);

			user.errors.addError(e.getMessage());
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
