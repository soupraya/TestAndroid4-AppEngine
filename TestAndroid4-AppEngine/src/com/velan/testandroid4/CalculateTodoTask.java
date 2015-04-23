package com.velan.testandroid4;

import java.util.List;

public class CalculateTodoTask {
	CostItem todo;
	CostTotal task;
	
	public CalculateTodoTask(String author, String summary, String description, String url,String A, String B) {
		todo = new CostItem(author, summary, description, url,A,B);
	    CalculateC();
	    
	    //Dao.INSTANCE.add(user.getUserId(), summary, longDescription, url,A,B);
	    Dao.INSTANCE.add(todo);
	    
	    System.out.println("Creating new task ");
	    List<CostTotal> listTask = Dao.INSTANCE.getCostTotals(author);
	    
	    boolean fineshed = false;
	    Double taskCTTmp = 0.0;
	    for(int i=0;i <listTask.size() & fineshed == false;i++)
	    {
	    	task = listTask.get(i);
	    	System.out.println(task.getProject() + " " + summary);
	    	if(task.getProject().equals(summary))
	    	{
	    		fineshed = true;
	    		taskCTTmp=task.getTotal();
	    		Dao.INSTANCE.removeCostTotal(task.getId());
	    	}
	    }
	    
	    
	    task = new CostTotal(author, summary);	    
	    task.setTotal(taskCTTmp);
	    CalculateCT();
	    //Dao.INSTANCE.addTask(user.getUserId(),summary, longDescription, AT, BT);
	    Dao.INSTANCE.addCostTotal(task);
	    //velan & Sonnie = <3 <3 <3 ....
	    }

	
	public void CalculateC() {
		Double C;
		C = todo.getUnitCost()*todo.getNumberOfItem();
		todo.setTotal(C);
	}
	
	public void CalculateCT() {
		Double CT;
		CT = task.getTotal()+todo.getTotal();
		task.setTotal(CT);
	}

}
