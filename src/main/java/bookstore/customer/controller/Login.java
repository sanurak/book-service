package bookstore.customer.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * Object used for authentication service
 * 
 * @author Anurak Sirivoravit
 *
 */
@Getter
@Setter
public class Login {
	private String email;
	private String password;
}
