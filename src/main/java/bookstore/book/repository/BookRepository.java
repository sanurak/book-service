package bookstore.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bookstore.book.entity.Book;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
