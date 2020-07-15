package bookstore.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookstore.Response;
import bookstore.Status;
import bookstore.customer.entity.Customer;
import bookstore.customer.service.AuthenticationService;
import bookstore.order.model.OrderDetail;
import bookstore.order.model.SavingOrder;
import bookstore.order.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private OrderService orderService;

	/**
	 * validate if token is valid user token
	 * 
	 * @param token
	 * @return
	 */
	protected Response<Customer> validateToken(String token) {
		Response<Customer> customerResponse = authenticationService.getCustomerFromToken(token);

		if (Status.ERROR.equals(customerResponse.getStatus())) {
			return Response.createError(customerResponse.getMessage());
		}

		return Response.createSuccess(customerResponse.getResult());
	}

	@GetMapping(value = "/history", produces = "application/json")
	public Response<List<OrderDetail>> history(@RequestHeader("token") String token) {
		Response<Customer> customerResponse = validateToken(token);

		if (Status.ERROR.equals(customerResponse.getStatus())) {
			return Response.createError(customerResponse.getMessage());
		}

		return orderService.getHistory(customerResponse.getResult().getId());
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public Response<OrderDetail> saveOrder(@RequestHeader("token") String token, @RequestBody SavingOrder savingOrder) {
		Response<Customer> customerResponse = validateToken(token);

		if (Status.ERROR.equals(customerResponse.getStatus())) {
			return Response.createError(customerResponse.getMessage());
		}

		savingOrder.setCustomerId(customerResponse.getResult().getId());

		return orderService.saveOrder(savingOrder);
	}
}
