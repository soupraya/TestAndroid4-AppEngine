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

@Api(name = "chargeableitemsendpoint", namespace = @ApiNamespace(ownerDomain = "velan.com", ownerName = "velan.com", packagePath = "testandroid4"))
public class ChargeableItemsEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listChargeableItems")
	public CollectionResponse<ChargeableItems> listChargeableItems(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<ChargeableItems> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr
					.createQuery("select from ChargeableItems as ChargeableItems");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<ChargeableItems>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (ChargeableItems obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<ChargeableItems> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getChargeableItems")
	public ChargeableItems getChargeableItems(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		ChargeableItems chargeableitems = null;
		try {
			chargeableitems = mgr.find(ChargeableItems.class, id);
		} finally {
			mgr.close();
		}
		return chargeableitems;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param chargeableitems the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertChargeableItems")
	public ChargeableItems insertChargeableItems(ChargeableItems chargeableitems) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsChargeableItems(chargeableitems)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(chargeableitems);
		} finally {
			mgr.close();
		}
		return chargeableitems;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param chargeableitems the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateChargeableItems")
	public ChargeableItems updateChargeableItems(ChargeableItems chargeableitems) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsChargeableItems(chargeableitems)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(chargeableitems);
		} finally {
			mgr.close();
		}
		return chargeableitems;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeChargeableItems")
	public void removeChargeableItems(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			ChargeableItems chargeableitems = mgr.find(ChargeableItems.class,
					id);
			mgr.remove(chargeableitems);
		} finally {
			mgr.close();
		}
	}

	private boolean containsChargeableItems(ChargeableItems chargeableitems) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			ChargeableItems item = mgr.find(ChargeableItems.class,
					chargeableitems.getId());
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
