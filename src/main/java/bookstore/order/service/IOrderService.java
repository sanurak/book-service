package bookstore.order.service;

import java.util.List;

import bookstore.Response;
import bookstore.order.model.OrderDetail;
import bookstore.order.model.SavingOrder;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
public interface IOrderService {
	public Response<OrderDetail> saveOrder(SavingOrder order);

	public Response<List<OrderDetail>> getHistory(long customerId);
}
