package com.velan.testandroid4;

import java.util.List;

public class CalculateTodoTask {
	Todo todo;
	Task task;
	
	public CalculateTodoTask(String author, String summary, String description, String url,String A, String B) {
		todo = new Todo(author, summary, description, url,A,B);
	    CalculateC();
	    
	    //Dao.INSTANCE.add(user.getUserId(), summary, longDescription, url,A,B);
	    Dao.INSTANCE.add(todo);
	    
	    System.out.println("Creating new task ");
	    List<Task> listTask = Dao.INSTANCE.getTasks(author);
	    
	    boolean fineshed = false;
	    Double taskCTTmp = 0.0;
	    for(int i=0;i <listTask.size() & fineshed == false;i++)
	    {
	    	task = listTask.get(i);
	    	System.out.println(task.getProject() + " " + summary);
	    	if(task.getProject().equals(summary))
	    	{
	    		fineshed = true;
	    		taskCTTmp=task.getCT();
	    		Dao.INSTANCE.removeTask(task.getId());
	    	}
	    }
	    
	    
	    task = new Task(author, summary);	    
	    task.setCT(taskCTTmp);
	    CalculateCT();
	    //Dao.INSTANCE.addTask(user.getUserId(),summary, longDescription, AT, BT);
	    Dao.INSTANCE.addTask(task);
	    //velan & Sonnie = <3 <3 <3 ....
	    }

	
	public void CalculateC() {
		Double C;
		C = todo.getA()*todo.getB();
		todo.setC(C);
	}
	
	public void CalculateCT() {
		Double CT;
		CT = task.getCT()+todo.getC();
		task.setCT(CT);
	}

}
