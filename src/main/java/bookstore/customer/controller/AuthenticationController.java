package bookstore.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookstore.Response;
import bookstore.customer.model.CustomerDetail;
import bookstore.customer.service.AuthenticationService;

/**
 * Controller for authentication. Version 1.0 Support only authentication from
 * existing db
 * 
 * @version 1.0
 * @author Anurak Sirivoravit
 *
 */
@RestController
@RequestMapping("/api/v1/authen")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Response<CustomerDetail> authenticate(@RequestBody Login login) {
		return authenticationService.authen(login.getEmail(), login.getPassword());
	}

}
