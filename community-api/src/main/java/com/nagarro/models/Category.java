package com.nagarro.models;

/*
 * @author Bhumika_arora 
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column
	@NotBlank
	@Size(max = 50)
	String name;

	@Column
	@Size(max = 500)
	String description;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	String categoryCode = "P-" + RandomStringUtils.randomAlphanumeric(3);

	public Category(final int id, @NotBlank @Size(max = 50) final String name, @Size(max = 500) final String description,
			final String categoryCode) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryCode = categoryCode;
	}

	public Category(final int id) {
		super();
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	public Category() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(final String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", categoryCode="
				+ categoryCode + "]";
	}

	

}
