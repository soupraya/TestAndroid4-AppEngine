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
public class CostItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String author;
  private String project;
  private String description;
  private boolean finished;  
  private Double unitCost=0.0;
  private Integer numberOfItem=0;
  private Double total=0.0;

  public CostItem(String author, String summary, String description,
      String url,String UnitCost,String NumberOfItem) {
    this.author = author;
    this.description = description;
    finished = false;
    this.unitCost=Double.parseDouble(UnitCost);
    this.numberOfItem=Integer.parseInt(NumberOfItem);
    //this.C=this.A*this.B;
  }



  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }
  public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public Integer getNumberOfItem() {
		return numberOfItem;
	}

	public void setNumberOfItem(Integer numberOfItem) {
		this.numberOfItem = numberOfItem;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setId(Long id) {
		this.id = id;
	}

	  public Long getId() {
		    return id;
		  }


} 
