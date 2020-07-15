package bookstore.customer.service;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import bookstore.BaseService;
import bookstore.MessageKey;
import bookstore.Response;
import bookstore.customer.entity.Customer;
import bookstore.customer.model.CustomerDetail;
import bookstore.customer.repository.CustomerRepository;
import bookstore.utils.PasswordUtils;

/**
 * Service Layer for Authentication
 * 
 * @author Anurak Sirivoravit
 *
 */
@Service
public class AuthenticationService extends BaseService implements IAuthenticationService {
	private CustomerRepository customerRepository;

	public static final String SEPERATOR = ":";

	@Autowired
	public AuthenticationService(CustomerRepository CustomerRepository, MessageSource messageSource) {
		super(messageSource);

		this.customerRepository = CustomerRepository;
	}

	@Override
	public Response<CustomerDetail> authen(String email, String password) {
		Customer customer = customerRepository.findByEmail(email);

		// search Customer by login if not found return invalid email/password
		if (customer == null) {
			return Response.createError(getMessage(MessageKey.AUTHEN_INVALID_EMAIL_PASSWORD));
		}

		// if password match return success with authen key else return invalid
		// Customername/password
		if (PasswordUtils.verifyUserPassword(password, customer.getPassword(), customer.getSalt())) {
			String key = PasswordUtils.getSalt(10) + SEPERATOR + customer.getEmail() + SEPERATOR + customer.getId();
			String authenToken = Base64.getEncoder().encodeToString(key.getBytes());

			CustomerDetail customerDetail = new CustomerDetail();
			customerDetail.setId(customer.getId());
			customerDetail.setName(customer.getName());
			customerDetail.setToken(authenToken);

			return Response.createSuccess(customerDetail, getMessage(MessageKey.AUTHEN_SUCCESS));

		} else {
			return Response.createError(getMessage(MessageKey.AUTHEN_INVALID_EMAIL_PASSWORD));

		}
	}

	@Override
	public Response<Customer> getCustomerFromToken(String token) {
		try {
			byte[] bytes = Base64.getDecoder().decode(token);
			String decoded = new String(bytes);
			// decode message should be 3 strings separate by SEPERATOR
			String[] splits = decoded.split(SEPERATOR);
			// if decoded string not contain 3 strings mean some other string encoded in
			// base64 to bypass token check then return invalid token message
			if (splits.length != 3) {
				return Response.createError(getMessage(MessageKey.AUTHEN_INVALID_TOKEN));
			}

			// first token is expect to be salt for random change encoded message and second
			// token is email. third
			// token is id string
			String customerEmail = splits[1];
			long id = Long.parseLong(splits[2]);
			Optional<Customer> customerOption = customerRepository.findById(id);

			// if Customer not found return invalid token message
			if (!customerOption.isPresent()) {
				return Response.createError(getMessage(MessageKey.AUTHEN_INVALID_TOKEN));
			}

			Customer customer = customerOption.get();

			return Response.createSuccess(customer);

		} catch (IllegalArgumentException e) {
			// This case can be throws from Base64 decoding non base64 string so return
			// invalid token
			return Response.createError(getMessage(MessageKey.AUTHEN_INVALID_TOKEN));
		}

	}

}
