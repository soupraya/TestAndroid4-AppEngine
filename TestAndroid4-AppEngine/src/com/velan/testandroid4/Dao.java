package com.velan.testandroid4;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public enum Dao {
  INSTANCE;

  public List<CostItem> listCostItems() {
    EntityManager em = EMFService.get().createEntityManager();
    // read the existing entries
    Query q = em.createQuery("select m from CostItem m");
    List<CostItem> costTotals = q.getResultList();
    return costTotals;
  }

  public List<CostTotal> listCostTotals() {
	    EntityManager em = EMFService.get().createEntityManager();
	    // read the existing entries
	    Query q = em.createQuery("select m from CostTotal m");
	    List<CostTotal> costTotals = q.getResultList();
	    return costTotals;
	  }
  
  
  /*public void add(String userId, String summary, String description,
      String url,String A, String B) {
    synchronized (this) {
      EntityManager em = EMFService.get().createEntityManager();
      Todo todo = new Todo(userId, summary, description, url,A,B);
      em.persist(todo);
      em.close();
    }
  }*/
  
  public void add(CostItem todo) {
	    synchronized (this) {
	      EntityManager em = EMFService.get().createEntityManager();
	      //Todo todo = new Todo(userId, summary, description, url,A,B);
	      em.persist(todo);
	      em.close();
	    }
	  }
  

  public void addCostTotal(CostTotal task) {
	    synchronized (this) {
	      EntityManager em = EMFService.get().createEntityManager();
	      //Task task = new Task(author, summary, description, AT, BT);
	      em.persist(task);
	      em.close();
	    }
	  }
  
  public List<CostItem> getCostItems(String userId) {
    EntityManager em = EMFService.get().createEntityManager();
    Query q = em
        .createQuery("select t from CostItem t where t.author = :userId");
    q.setParameter("userId", userId);
    List<CostItem> todos = q.getResultList();
    return todos;
  }

  public void removeCostItem(long id) {
    EntityManager em = EMFService.get().createEntityManager();
    try {
      CostItem todo = em.find(CostItem.class, id);
      //em.remove(todo);
      todo.setFinished(true);
    } finally {
      em.close();
    }
  }
    public List<CostTotal> getCostTotals(String userId) {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em
            .createQuery("select t from CostTotal t where t.author = :userId");
        q.setParameter("userId", userId);
        List<CostTotal> tasks = q.getResultList();
        return tasks;
      }

      public void removeCostTotal(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
          CostTotal task = em.find(CostTotal.class, id);
          em.remove(task);
        } finally {
          em.close();
        }
      }
} 
