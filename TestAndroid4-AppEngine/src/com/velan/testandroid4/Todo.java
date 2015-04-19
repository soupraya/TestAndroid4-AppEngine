package com.velan.testandroid4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class which will store the Todo Items
 * 
 * @author Lars Vogel
 * 
 */

@Entity
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String author;
  private String summary;
  private String description;
  private String url;
  private boolean finished;
  
  private Double A=0.0;
  private Double B=0.0;
  private Double C=0.0;

  public Todo(String author, String summary, String description,
      String url,String A,String B) {
    this.author = author;
    this.summary = summary;
    this.description = description;
    this.url = url;
    finished = false;
    this.A=Double.parseDouble(A);
    this.B=Double.parseDouble(B);
    //this.C=this.A*this.B;
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

  public String getShortDescription() {
    return summary;
  }

  public void setShortDescription(String shortDescription) {
    this.summary = shortDescription;
  }

  public String getLongDescription() {
    return description;
  }

  public void setLongDescription(String longDescription) {
    this.description = longDescription;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }

public Double getA() {
	return A;
}

public void setA(Double a) {
	A = a;
	//updateC();
}

public Double getB() {
	return B;
}

public void setB(Double b) {
	B = b;
	//CalculateCost();
}

public Double getC() {
	return C;
}

public void setC(Double c) {
	C = c;
}


} 
