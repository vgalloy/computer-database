/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.service.services;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;

/**
 * The Interface ComputerService.
 */
public interface ComputerService extends CommonService<Computer> {
	
	/**
	 * Gets the name contains.
	 *
	 * @param string the string
	 * @param from the from
	 * @param to the to
	 * @param sortCriteria the sort criteria
	 * @return the name contains
	 */
	public List<Computer> getNameContains(String string, Long from, Long to, SortCriteria sortCriteria);
	
	/**
	 * Gets the name contains element.
	 *
	 * @param string the string
	 * @return the name contains element
	 */
	public Long getNameContainsElement(String string);
}
