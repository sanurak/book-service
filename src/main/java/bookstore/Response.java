package bookstore;

/**
 * Generic Response Object. used in service layer to serve service response to
 * indicate success or fail with message
 * 
 * @author Anurak Sirivoravit
 *
 * @param <T> class which result for service that want to attach with response.
 */
/**
 * @author Anurak Sirivoravit
 *
 * @param <T> Generic class for result response
 */
public class Response<T> {
	private String status;
	private String message;
	private Exception exception;
	private T result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public T getResult() {
		return this.result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * create success response with return object
	 * 
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> Response<T> createSuccess(T t) {
		return createSuccess(t, null);
	}

	/**
	 * create success response with return object and message
	 * 
	 * @param <T>
	 * @param t
	 * @param message
	 * @return
	 */
	public static <T> Response<T> createSuccess(T t, String message) {
		Response<T> response = new Response<T>();
		response.setStatus(Status.SUCCESS);
		response.setMessage(message);
		response.setResult(t);

		return response;
	}

	/**
	 * create success response
	 * 
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> createSuccess() {
		Response<T> response = new Response<T>();
		response.setStatus(Status.SUCCESS);

		return response;
	}

	/**
	 * create success response with message
	 * 
	 * @param <T>
	 * @param message
	 * @return
	 */
	public static <T> Response<T> createSuccess(String message) {
		return createSuccess(null, message);
	}

	/**
	 * create error response with message and exception
	 * 
	 * @param <T>
	 * @param message
	 * @param exception
	 * @return
	 */
	public static <T> Response<T> createError(String message, Exception exception) {
		Response<T> response = new Response<T>();

		response.setStatus(Status.ERROR);
		response.setMessage(message);
		response.setException(exception);

		return response;
	}

	/**
	 * create error response with message
	 * 
	 * @param <T>
	 * @param message
	 * @return
	 */
	public static <T> Response<T> createError(String message) {
		return createError(message, null);
	}
}
