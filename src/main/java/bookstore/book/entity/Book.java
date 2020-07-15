package bookstore.book.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import bookstore.book.model.BookDetail;

/**
 * 
 * @author Anurak Sirivoravit
 *
 */
@Entity
@Table(name = "books")
public class Book {
	private long id;
	private String name;
	private String description;
	private BigDecimal priceUsd;
	private String coverImageUrl;

	public Book() {

	}

	public Book(String name, String description, BigDecimal priceUsd, String coverImageUrl) {
		this.name = name;
		this.description = description;
		this.priceUsd = priceUsd;
		this.coverImageUrl = coverImageUrl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false)
	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price_usd", nullable = false)
	public BigDecimal getPriceUsd() {
		return this.priceUsd;
	}

	public void setPriceUsd(BigDecimal priceUsd) {
		this.priceUsd = priceUsd;
	}

	@Column(name = "cover_image_url", nullable = false)
	public String getCoverImageUrl() {
		return this.coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public BookDetail toDetail() {
		BookDetail bookDetail = new BookDetail();
		bookDetail.setName(getName());
		bookDetail.setDescription(getDescription());
		bookDetail.setPriceUsd(getPriceUsd());
		bookDetail.setCoverImageUrl(getCoverImageUrl());

		return bookDetail;

	}
}
