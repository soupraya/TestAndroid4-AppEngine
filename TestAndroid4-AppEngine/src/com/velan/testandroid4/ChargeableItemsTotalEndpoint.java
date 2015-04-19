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

@Api(name = "chargeableitemstotalendpoint", namespace = @ApiNamespace(ownerDomain = "velan.com", ownerName = "velan.com", packagePath = "testandroid4"))
public class ChargeableItemsTotalEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listChargeableItemsTotal")
	public CollectionResponse<ChargeableItemsTotal> listChargeableItemsTotal(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<ChargeableItemsTotal> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from ChargeableItemsTotal as ChargeableItemsTotal");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ChargeableItemsTotal>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ChargeableItemsTotal obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ChargeableItemsTotal> builder()
				.setItems(execute).setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getChargeableItemsTotal")
	public ChargeableItemsTotal getChargeableItemsTotal(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		ChargeableItemsTotal chargeableitemstotal = null;
		try {
			chargeableitemstotal = mgr.find(ChargeableItemsTotal.class, id);
		} finally {
			mgr.close();
		}
		return chargeableitemstotal;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param chargeableitemstotal the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertChargeableItemsTotal")
	public ChargeableItemsTotal insertChargeableItemsTotal(
			ChargeableItemsTotal chargeableitemstotal) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsChargeableItemsTotal(chargeableitemstotal)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(chargeableitemstotal);
		} finally {
			mgr.close();
		}
		return chargeableitemstotal;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param chargeableitemstotal the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateChargeableItemsTotal")
	public ChargeableItemsTotal updateChargeableItemsTotal(
			ChargeableItemsTotal chargeableitemstotal) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsChargeableItemsTotal(chargeableitemstotal)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(chargeableitemstotal);
		} finally {
			mgr.close();
		}
		return chargeableitemstotal;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeChargeableItemsTotal")
	public void removeChargeableItemsTotal(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ChargeableItemsTotal chargeableitemstotal = mgr.find(
					ChargeableItemsTotal.class, id);
			mgr.remove(chargeableitemstotal);
		} finally {
			mgr.close();
		}
	}

	private boolean containsChargeableItemsTotal(
			ChargeableItemsTotal chargeableitemstotal) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ChargeableItemsTotal item = mgr.find(ChargeableItemsTotal.class,
					chargeableitemstotal.getId());
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
