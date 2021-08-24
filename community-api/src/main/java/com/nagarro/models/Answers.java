package com.nagarro.models;
/*
 * @author Bhumika_arora 
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Answers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column
	String subject;

	@Column
	@NotBlank
	String content;
	
	@Column 
	int question_id;
	
	@Column
	String author;
	
	@Column
	boolean correct_ans=false;
	
	
	public boolean isCorrect_ans() {
		return correct_ans;
	}

	public void setCorrect_ans(final boolean correct_ans) {
		this.correct_ans = correct_ans;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public int getQuestion() {
		return question_id;
	}

	public void setQuestion(final int question_id) {
		this.question_id = question_id;
	}
	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}
	
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public Answers(final int id, @Size(max = 500) @NotBlank final String content, final int question_id, final String author, final String subject, final boolean correct_ans) {
		super();
		this.id = id;
		this.content = content;
		this.question_id = question_id;
		this.author=author;
		this.subject=subject;
		this.correct_ans=correct_ans;
	}

	public Answers() {
		super();
	}
	
	

}
