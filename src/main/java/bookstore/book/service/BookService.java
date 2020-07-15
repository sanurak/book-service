package bookstore.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import bookstore.BaseService;
import bookstore.MessageKey;
import bookstore.Response;
import bookstore.book.entity.Book;
import bookstore.book.repository.BookRepository;

@Service
public class BookService extends BaseService implements IBookService {
	@Autowired
	private BookRepository bookRepository;

	@Value("${book.random.no}")
	private int bookRandomNo;

	@Autowired
	public BookService(MessageSource messageSource) {
		super(messageSource);
	}

	@Override
	public Response<List<Book>> listAll() {
		List<Book> bookList = bookRepository.findAll();

		if (bookList.isEmpty()) {
			Response.createSuccess(bookList, getMessage(MessageKey.BOOK_NOT_FOUND));
		}
		return Response.createSuccess(bookRepository.findAll(),
				getMessage(MessageKey.BOOKS_FOUND, new Object[] { bookList.size() }));
	}

	@Override
	public Response<Book> getBookById(Long bookId) {
		Optional<Book> bookOptional = bookRepository.findById(bookId);

		// if book found return success
		if (bookOptional.isPresent()) {
			return Response.createSuccess(bookOptional.get(), getMessage(MessageKey.BOOK_FOUND));

		} else {
			// if book not found return with message employee not found
			return Response.createError(getMessage(MessageKey.BOOK_NOT_FOUND));
		}
	}

	@Override
	public Response<List<Book>> random() {
		List<Book> bookList = bookRepository.findAll();

		if (bookList.size() <= bookRandomNo) {
			return Response.createSuccess(bookList,
					getMessage(MessageKey.BOOKS_FOUND, new Object[] { bookList.size() }));
		}

		List<Book> newList = new ArrayList<Book>();

		Random random = new Random();
		for (int i = 0; i < bookRandomNo; i++) {
			int nextBook = random.nextInt(bookList.size());

			Book book = bookList.remove(nextBook);

			newList.add(book);
		}

		return Response.createSuccess(bookList, getMessage(MessageKey.BOOKS_FOUND, new Object[] { newList.size() }));
	}

}
