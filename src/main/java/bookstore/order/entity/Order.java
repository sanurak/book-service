package bookstore.order.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Entity
@Table(name = "orders")
public class Order {
	private long id;
	private long customerId;
	private long bookId;
	private int quantity;
	private Timestamp orderDate;

	public Order() {

	}

	public Order(long id, long customerId, long bookId, int quantity, Timestamp orderDate) {
		this.id = id;
		this.customerId = bookId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.orderDate = orderDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "customer_id", nullable = false)
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "book_id", nullable = false)
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "order_date", nullable = false)
	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

}
