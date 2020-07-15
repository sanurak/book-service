package bookstore.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookstore.Response;
import bookstore.book.entity.Book;
import bookstore.book.service.BookService;

/**
 * RestController for book.
 * 
 * @author Anurak Sirivoravit
 *
 */
@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	@Autowired
	private BookService bookService;

	/**
	 * provide service to list all user
	 * 
	 * @param token user token - mandatory to pass from request header
	 * @return
	 */
	@GetMapping(produces = "application/json")
	public Response<List<Book>> listBook() {

		return bookService.random();
	}

	/**
	 * 
	 * @param token      user token - mandatory to pass from request header
	 * @param employeeId
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public Response<Book> getBookById(@PathVariable("id") long bookId) {
		return bookService.getBookById(bookId);
	}

}
