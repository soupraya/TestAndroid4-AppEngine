package com.velan.testandroid4;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public enum Dao {
  INSTANCE;

  public List<Todo> listTodos() {
    EntityManager em = EMFService.get().createEntityManager();
    // read the existing entries
    Query q = em.createQuery("select m from Todo m");
    List<Todo> todos = q.getResultList();
    return todos;
  }

  public List<Task> listTasks() {
	    EntityManager em = EMFService.get().createEntityManager();
	    // read the existing entries
	    Query q = em.createQuery("select m from Task m");
	    List<Task> tasks = q.getResultList();
	    return tasks;
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
  
  public void add(Todo todo) {
	    synchronized (this) {
	      EntityManager em = EMFService.get().createEntityManager();
	      //Todo todo = new Todo(userId, summary, description, url,A,B);
	      em.persist(todo);
	      em.close();
	    }
	  }
  

  public void addTask(Task task) {
	    synchronized (this) {
	      EntityManager em = EMFService.get().createEntityManager();
	      //Task task = new Task(author, summary, description, AT, BT);
	      em.persist(task);
	      em.close();
	    }
	  }
  
  public List<Todo> getTodos(String userId) {
    EntityManager em = EMFService.get().createEntityManager();
    Query q = em
        .createQuery("select t from Todo t where t.author = :userId");
    q.setParameter("userId", userId);
    List<Todo> todos = q.getResultList();
    return todos;
  }

  public void remove(long id) {
    EntityManager em = EMFService.get().createEntityManager();
    try {
      Todo todo = em.find(Todo.class, id);
      //em.remove(todo);
      todo.setFinished(true);
    } finally {
      em.close();
    }
  }
    public List<Task> getTasks(String userId) {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em
            .createQuery("select t from Task t where t.author = :userId");
        q.setParameter("userId", userId);
        List<Task> tasks = q.getResultList();
        return tasks;
      }

      public void removeTask(long id) {
        EntityManager em = EMFService.get().createEntityManager();
        try {
          Task task = em.find(Task.class, id);
          em.remove(task);
        } finally {
          em.close();
        }
      }
} 
