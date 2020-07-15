package bookstore.customer.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Getter
@Setter
public class CustomerDetail {
	private long id;
	private String name;
	private String token;
}
