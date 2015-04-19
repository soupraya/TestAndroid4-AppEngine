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
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String author;
  private String project;
  private boolean finished;
  
  private Double AT=0.0;
  private Double BT=0.0;
  private Double CT=0.0;
  private Double DT=0.0;

  public Task(String author, String project) {
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

public Double getAT() {
	return AT;
}

public void setAT(Double aT) {
	AT = aT;
}

public Double getBT() {
	return BT;
}

public void setBT(Double bT) {
	BT = bT;
}

public Double getCT() {
	return CT;
}

public void setCT(Double dT) {
	CT = dT;
}

public Double getDT() {
	return DT;
}

public void setDT(Double dT) {
	DT = dT;
}

} 
