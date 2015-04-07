/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.test.unitaire.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.dao.CompanyDaoInterface;
import com.excilys.computerdatabase.dao.impl.CompanyDao;
import com.excilys.computerdatabase.dao.impl.ComputerDao;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.computerdatabase.sort.SortCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-application-context.xml" })
public class TestCompanyService {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyServiceImpl companyService;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;

	@After
	public void after() {
		companyService.setCompanyDao(companyDao);
		companyService.setComputerDao(computerDao);
	}

	@Test
	public void testGetAll() {
		CompanyDaoInterface companyDaoMock = Mockito
				.mock(CompanyDaoInterface.class);
		List<Company> companies = new ArrayList<Company>();
		companies.add(new Company());
		Mockito.when(companyDaoMock.getAll(Mockito.any()))
				.thenReturn(companies);

		companyService.setCompanyDao(companyDaoMock);

		assertEquals(companyService.list(new SortCriteria()).size(), 1);

		companyService.setCompanyDao(companyDao);
	}

	@Test(expected = ServiceException.class)
	public void testCreate() {
		companyService.create(null);
	}

	@Test
	public void testCreateOk() {
		Long l = companyService.getNumberOfElement();
		Company company = new Company(null, "company_test");
		companyService.create(company);
		Company company2 = companyService.getById(company.getId());
		Long l2 = companyService.getNumberOfElement();
		assertEquals(new Long(l + 1), l2);
		assertEquals(company2, company);
	}

	@Test(expected = ServiceException.class)
	public void testDetails() {
		companyService.getById(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() {
		companyService.update(null);
	}

	@Test(expected = ServiceException.class)
	public void testDelete() {
		companyService.delete(null);
	}

	/**
	 * 
	 */
	@Test
	public void testDeleteOk() {
		Company company = new Company(null, "company_test");
		companyService.create(company);
		Computer computer = new Computer(null, "nameDeleteCompanyTest", null,
				null, new Company(company.getId(), null));
		computerService.create(computer);

		companyService.delete(company.getId());

		try {
			companyService.getById(company.getId());
			fail("no exception throw");
		} catch (DaoException ignored) {
		}
		try {
			computerService.getById(computer.getId());
			fail("no exception throw");
		} catch (DaoException ignored) {
		}
	}

	@Test
	public void testDeleteNullCompanyDao() {
		Company company = new Company(null, "company_test");
		companyService.create(company);
		Computer computer = new Computer(null, "nameDeleteCompanyTest", null,
				null, company);
		computerService.create(computer);
		companyService.setCompanyDao(null);
		try {
			companyService.delete(company.getId());
			fail("No exception occured");
		} catch (NullPointerException expectedException) {
			
		}
		companyService.setCompanyDao(companyDao);
		Computer computer2 = computerService.getById(computer.getId());
		assertEquals(computer, computer2);
		Company company2 = companyService.getById(company.getId());
		assertEquals(company, company2);
	}
	
	@Test
	public void testDeleteNullComputerDao() {
		Company company = new Company(null, "company_test");
		companyService.create(company);
		Computer computer = new Computer(null, "nameDeleteCompanyTest", null,
				null, company);
		computerService.create(computer);
		companyService.setComputerDao(null);
		try {
			companyService.delete(company.getId());
			fail("No exception occured");
		} catch (NullPointerException expectedException) {
		}
		Computer computer2 = computerService.getById(computer.getId());
		assertEquals(computer, computer2);
		Company company2 = companyService.getById(company.getId());
		assertEquals(company, company2);
	}

}
