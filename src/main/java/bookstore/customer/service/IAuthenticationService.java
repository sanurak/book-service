package bookstore.customer.service;

import bookstore.Response;
import bookstore.customer.entity.Customer;
import bookstore.customer.model.CustomerDetail;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
public interface IAuthenticationService {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Response<CustomerDetail> authen(String email, String password);

	/**
	 * 
	 * @param token
	 * @return
	 */
	public Response<Customer> getCustomerFromToken(String token);

}
