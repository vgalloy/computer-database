package com.excilys.computerDataBase.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerDataBase.model.Computer;

/**
 * The Interface CommonDaoInterface.
 *
 * @param <T>
 *            the generic type
 */
public interface ComputerDaoInterface extends DaoInterface<Computer> {
	public List<Computer> getNameContains(String string);

	public List<Computer> getByCompanyId(Connection connection, Long id);
}
