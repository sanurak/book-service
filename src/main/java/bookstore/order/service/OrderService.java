package bookstore.order.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import bookstore.BaseService;
import bookstore.MessageKey;
import bookstore.Response;
import bookstore.book.entity.Book;
import bookstore.book.repository.BookRepository;
import bookstore.customer.entity.Customer;
import bookstore.customer.model.CustomerDetail;
import bookstore.customer.repository.CustomerRepository;
import bookstore.order.entity.Order;
import bookstore.order.model.OrderDetail;
import bookstore.order.model.SavingOrder;
import bookstore.order.repository.OrderRepository;

/**
 * Service Layer for Authentication
 * 
 * @author Anurak Sirivoravit
 *
 */
@Service
public class OrderService extends BaseService implements IOrderService {
	private CustomerRepository customerRepository;
	private BookRepository bookRepository;
	private OrderRepository orderRepository;

	@Value("${order.maximum.book}")
	private int orderMaxBook;

	@Autowired
	public OrderService(CustomerRepository customerRepository, BookRepository bookRepository,
			OrderRepository orderRepository, MessageSource messageSource) {
		super(messageSource);

		this.customerRepository = customerRepository;
		this.bookRepository = bookRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	public Response<OrderDetail> saveOrder(SavingOrder savingOrder) {
		if (savingOrder.getQuantity() > orderMaxBook) {
			return Response.createError(getMessage(MessageKey.ORDER_QUANTITY_EXCEED_LIMIT,
					new Object[] { savingOrder.getQuantity(), orderMaxBook }));
		}
		Optional<Customer> customerOptional = customerRepository.findById(savingOrder.getCustomerId());

		if (!customerOptional.isPresent()) {
			return Response.createError(getMessage(MessageKey.CUSTOMER_NOT_FOUND));
		}

		Optional<Book> bookOptional = bookRepository.findById(savingOrder.getBookId());
		if (!bookOptional.isPresent()) {
			return Response.createError(getMessage(MessageKey.BOOK_NOT_FOUND));
		}

		Order order = new Order();
		order.setCustomerId(savingOrder.getCustomerId());
		order.setBookId(savingOrder.getBookId());
		order.setQuantity(savingOrder.getQuantity());
		order.setOrderDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

		Order newOrder = orderRepository.save(order);

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCustomerDetail(customerOptional.get().toDetail());
		orderDetail.setBookDetail(bookOptional.get().toDetail());
		orderDetail.setQuantity(order.getQuantity());
		orderDetail.setOrderDate(order.getOrderDate());
		return Response.createSuccess(orderDetail, getMessage(MessageKey.ORDER_SAVED));
	}

	@Override
	public Response<List<OrderDetail>> getHistory(long customerId) {
		List<Order> orderList = orderRepository.findByCustomerId(customerId);

		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (!customerOptional.isPresent()) {
			return Response.createError(getMessage(MessageKey.CUSTOMER_NOT_FOUND));
		}

		CustomerDetail customerDetail = customerOptional.get().toDetail();

		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		for (Order order : orderList) {
			Optional<Book> bookOptional = bookRepository.findById(order.getBookId());
			Book book = bookOptional.get();

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setCustomerDetail(customerDetail);
			orderDetail.setBookDetail(book.toDetail());

			orderDetail.setQuantity(order.getQuantity());
			orderDetail.setOrderDate(order.getOrderDate());
			orderDetailList.add(orderDetail);
		}
		return Response.createSuccess(orderDetailList,
				getMessage(MessageKey.HISTORY_LIST_RESULT, new Object[] { orderDetailList.size() }));
	}

}
