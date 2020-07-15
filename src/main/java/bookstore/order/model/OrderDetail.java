package bookstore.order.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import bookstore.book.model.BookDetail;
import bookstore.customer.model.CustomerDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {
	private long id;
	private CustomerDetail customerDetail;
	private BookDetail bookDetail;
	private int quantity;
	private Timestamp orderDate;

	public BigDecimal getTotalAmount() {
		return bookDetail.getPriceUsd().multiply(new BigDecimal(quantity));
	}
}
