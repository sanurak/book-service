package bookstore.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bookstore.customer.entity.Customer;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("select c from Customer c where c.email = ?1")
	public Customer findByEmail(String email);
}
