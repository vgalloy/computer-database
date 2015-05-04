/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

/**
 * The Class ComputerServiceImpl.
 */
@Service
@Transactional(readOnly=true)
public class ComputerServiceImpl implements ComputerService {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/** The computer dao. */
	@Autowired
	private ComputerDao computerDao;
	
	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.CommonService#list(com.excilys.computerdatabase.sort.SortCriteria)
	 */
	@Override
	public List<Computer> list(SortCriteria sortCriteria) {
		log.info("List computers with criteria : {} ", sortCriteria);
		return computerDao.getAll(sortCriteria);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.CommonService#getById(java.lang.Long)
	 */
	@Override
	public Computer getById(Long id) {
		log.info("Get computer with id : {}", id);
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPUTER_ID);
		}
		return computerDao.getById(id);

	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.CommonService#create(java.lang.Object)
	 */
	@Override
	@Transactional(readOnly=false)
	public void create(Computer c) {
		log.info("Update computer : {}", c);
		if (!Validator.isComputerCorrect(c)) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.create(c);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.CommonService#update(java.lang.Object)
	 */
	@Override
	@Transactional(readOnly=false)
	public void update(Computer c) {
		log.info("Update computer : {}", c);
		if (!Validator.isComputerCorrect(c) && Validator.isIdCorrect(c.getId())) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.update(c);

	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.CommonService#delete(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Long id) {
		log.info("Get computer with id : {}", id);
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.CommonService#getNumberOfElement()
	 */
	@Override
	public Long getNumberOfElement() {
		log.info("Get number of computers");
		return computerDao.getNumberOfElement();
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.CommonService#list(java.lang.Long, java.lang.Long, com.excilys.computerdatabase.sort.SortCriteria)
	 */
	@Override
	public List<Computer> list(Long from, Long to, SortCriteria sortCriteria) {
		log.info("List computers with criteria : {} from {} to {} ", sortCriteria, from, to);
		if (!Validator.isDateFromToCorrect(from, to)) {
			throw new ServiceException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return computerDao.getAll(from, to, sortCriteria);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.ComputerService#getNameContains(java.lang.String, java.lang.Long, java.lang.Long, com.excilys.computerdatabase.sort.SortCriteria)
	 */
	@Override
	public List<Computer> getNameContains(String search, Long from, Long to,
			SortCriteria sortCriteria) {
		log.info("List computers with criteria : {} and contains {} from {} to {} ", sortCriteria, search, from, to);
		if (!Validator.isStringForSearchCorrect(search)) {
			throw new ServiceException(Validator.INVALID_STRING_FOR_SEARCH);
		}
		if (!Validator.isStringForSearchCorrect(search)) {
			throw new ServiceException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return computerDao.getByName(search, from, to, sortCriteria);

	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.service.services.ComputerService#getNameContainsElement(java.lang.String)
	 */
	@Override
	public Long getNameContainsElement(String string) {
		if (!Validator.isStringForSearchCorrect(string)) {
			throw new ServiceException(Validator.INVALID_STRING_FOR_SEARCH);
		}
		return computerDao.getByNameNumberOfElement(string);
	}
	
	/**
	 * Gets the computer dao.
	 *
	 * @return the computer dao
	 */
	public ComputerDao getComputerDao() {
		return computerDao;
	}
	
	/**
	 * Sets the computer dao.
	 *
	 * @param computerDao the new computer dao
	 */
	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}
}