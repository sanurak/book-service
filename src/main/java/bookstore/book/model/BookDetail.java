package bookstore.book.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDetail {
	private long id;
	private String name;
	private String description;
	private BigDecimal priceUsd;
	private String coverImageUrl;
}
