package com.excilys.computerdatabase.webservice;

import java.util.List;

import javax.ws.rs.core.Response;

/**
 * @author Vincent Galloy
 */
public interface CommonRessource <T>{
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<T> getAll();
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 */
	T getById(Long id);
	
	/**
	 * Creates the.
	 *
	 * @param t the t
	 * @return the t
	 */
	T create(T t);
	
	/**
	 * Update.
	 *
	 * @param t the t
	 * @return the t
	 */
	default T update(T t) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response
	 */
	Response delete(Long id);
}
