package com.velan.testandroid4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class which will store the Todo Items
 * 
 * @author Velan
 * 
 */

@Entity
public class CostTotal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String author;
  private String project;
  private boolean finished;
  private Double total=0.0;

  public Double getTotal() {
	return total;
}

public void setTotal(Double total) {
	this.total = total;
}

public CostTotal(String author, String project) {
	  this.author = author;
	  this.project = project;
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

} 
