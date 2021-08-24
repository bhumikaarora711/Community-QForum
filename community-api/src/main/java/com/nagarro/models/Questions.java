package com.nagarro.models;

/*
 * @author Bhumika_arora   
 */
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int question_id;

	@Column
	@NotNull
	@Size(max = 200)
	String title;

	@Column
	@NotNull
	@Length(max = 500)
	String body;

	@Column
	@NotNull
	String category;

	@Column
	@NotNull
	String author;

	@Column
	String categoryCode;

	@Column
	@NotNull
	Date date = new Date();

	@Column
	@NotNull
	String instant = Instant.now().toString();

	@Column
	boolean que_closed = false;

	public Questions(final int question_id, @NotNull final String title, @NotNull final String body,
			final String category, @NotNull final String author, final Date date, final boolean que_closed,
			final String categoryCode, final String instant) {
		super();
		this.question_id = question_id;
		this.title = title;
		this.body = body;
		this.category = category;
		this.author = author;
		this.date = date;
		this.que_closed = que_closed;
		this.categoryCode = categoryCode;
		this.instant = instant;
	}

	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(final int question_id) {
		this.question_id = question_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(final String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public boolean isQue_closed() {
		return que_closed;
	}

	public void setQue_closed(final boolean que_closed) {
		this.que_closed = que_closed;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	public String getInstant() {
		return instant;
	}

	public void setInstant(@NotNull final String instant) {
		this.instant = instant;
	}

	@Override
	public String toString() {
		return "Questions [question_id=" + question_id + ", title=" + title + ", body=" + body + ", category="
				+ category + ", author=" + author + ", categoryCode=" + categoryCode + ", date=" + date + ", instant="
				+ instant + ", que_closed=" + que_closed + "]";
	}

}
