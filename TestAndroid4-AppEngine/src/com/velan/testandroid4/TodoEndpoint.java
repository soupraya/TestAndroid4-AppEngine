package com.velan.testandroid4;

import com.velan.testandroid4.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "todoendpoint", namespace = @ApiNamespace(ownerDomain = "velan.com", ownerName = "velan.com", packagePath = "testandroid4"))
public class TodoEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listTodo")
	public CollectionResponse<Todo> listTodo(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Todo> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Todo as Todo");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Todo>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Todo obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Todo> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getTodo")
	public Todo getTodo(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Todo todo = null;
		try {
			todo = mgr.find(Todo.class, id);
		} finally {
			mgr.close();
		}
		return todo;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param todo the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertTodo")
	public Todo insertTodo(Todo todo) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsTodo(todo)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(todo);
		} finally {
			mgr.close();
		}
		return todo;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param todo the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateTodo")
	public Todo updateTodo(Todo todo) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsTodo(todo)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(todo);
		} finally {
			mgr.close();
		}
		return todo;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeTodo")
	public void removeTodo(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Todo todo = mgr.find(Todo.class, id);
			mgr.remove(todo);
		} finally {
			mgr.close();
		}
	}

	private boolean containsTodo(Todo todo) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Todo item = mgr.find(Todo.class, todo.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
