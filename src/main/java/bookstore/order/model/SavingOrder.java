package bookstore.order.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Getter
@Setter
public class SavingOrder {
	private long customerId;
	private long bookId;
	private int quantity;

}