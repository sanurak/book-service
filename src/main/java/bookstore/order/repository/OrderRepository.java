package bookstore.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bookstore.order.entity.Order;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("select o from Order o where o.customerId = ?1 order by o.orderDate DESC")
	public List<Order> findByCustomerId(long customerId);
}
