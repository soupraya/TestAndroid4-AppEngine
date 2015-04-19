package com.velan.testandroid4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChargeableItems {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String author;
	  private String project;
	  private String description;
	  private boolean finished;
	  private Double price=0.0;
	  private Integer numberOfItems=0;
	  private Double priceTotal=0.0;
	  
	  public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public ChargeableItems(String author, String project) {
		  this.author = author;
		  this.project = project;
	  }
	  
}
