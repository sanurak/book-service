package bookstore;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * Base service class for service layer to support basic need for service.
 * 
 * Version 1.0 - Support message source with Locale.ENGLISH only
 * 
 * @version 1.0
 * @author Anurak Sirivoravit
 *
 */
public abstract class BaseService {
	private MessageSource messageSource;

	public BaseService(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String messageKey) {
		return this.getMessage(messageKey, null);
	}

	public String getMessage(String messageKey, Object[] args) {
		if (messageSource == null) {
			return "";
		}

		return messageSource.getMessage(messageKey, args, Locale.ENGLISH);
	}
}
