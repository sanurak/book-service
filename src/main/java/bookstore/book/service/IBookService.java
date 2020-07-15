package bookstore.book.service;

import java.util.List;

import bookstore.Response;
import bookstore.book.entity.Book;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
public interface IBookService {
	public Response<List<Book>> listAll();

	public Response<Book> getBookById(Long bookId);

	public Response<List<Book>> random();
}
