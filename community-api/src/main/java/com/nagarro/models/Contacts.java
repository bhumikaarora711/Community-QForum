package com.nagarro.models;
/*
 * @author Bhumika_arora 
 */
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Contacts {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column @NotNull
	String name;
	
	@Column @NotNull
	String email;
	
	@Column @NotNull
	String comment;

	public Contacts(final int id, @NotNull final String name, @NotNull final String email, @NotNull final String comment) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.comment = comment;
	}

	public Contacts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}
	
}
