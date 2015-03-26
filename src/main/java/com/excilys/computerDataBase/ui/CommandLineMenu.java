/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerDataBase.ui;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.CompanyServiceInterface;
import com.excilys.computerDataBase.service.ComputerServiceInterface;
import com.excilys.computerDataBase.service.impl.CompanyService;
import com.excilys.computerDataBase.service.impl.ComputerService;
import com.excilys.computerDataBase.sort.SortCriteria;
import com.excilys.computerDataBase.validation.Validator;


/**
 * The Class CommandLineMenu.
 */
public class CommandLineMenu {

	private Scanner scanner = new Scanner(System.in);
	
	ComputerServiceInterface computerService = ComputerService.INSTANCE;
	
	CompanyServiceInterface companyService = CompanyService.INSTANCE;
	
	public CommandLineMenu() {
		super();
	}
	
	public static void main(String[] args) {
		new CommandLineMenu().displayMainMenu();
	}

	private void displayMainMenu() {
		System.out.println("Chose operation : ");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create a computer");
		System.out.println("5 - Update a computer");
		System.out.println("6 - Delete a computer");
		System.out.println("7 - Delete a company");

		String result = scanner.nextLine();
		int resultAsAnInt = 0;
		try {
			resultAsAnInt = Integer.parseInt(result);
		} catch (NumberFormatException e) {

		}
		operationSwitch(resultAsAnInt);
		scanner.close();
	}

	private void operationSwitch(int resultAsAnInt) {
		switch (resultAsAnInt) {
		case 1:
			listComputer();
			break;
		case 2:
			listCompanies();
			break;
		case 3:
			showComputerDetails();
			break;
		case 4:
			createComputer();
			break;
		case 5:
			updateComputer();
			break;
		case 6:
			deleteComputer();
			break;
		case 7:
			deleteCompany();
			break;
		default:
			break;
		}
	}

	private void listComputer() {
		Paginator.print(computerService.list(new SortCriteria()));
	}

	private void listCompanies() {
		Paginator.print(computerService.list(new SortCriteria()));
	}

	private void showComputerDetails() {
		Long id = getLongFromCommandLine("Computer Id : ");
		Computer computer = computerService.getById(id);
		System.out.println(computer.toString());
	}

	private void createComputer() {
		String name = getStringFromCommandLine("Computer name : ");
		LocalDateTime introduced = getLocalDateTimeFromCommandLine("Computer introduced : ");
		LocalDateTime discontinued = getLocalDateTimeFromCommandLine("Computer discontinued : ");
		Long company_id = getLongFromCommandLine("Computer company_id : ");
		Computer computer = new Computer(new Long(0), name, introduced,
				discontinued, new Company(company_id, null));
		computerService.create(computer);
		System.out.println("computer created : " + computer.toString());
	}

	private void updateComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		String name = getStringFromCommandLine("Computer (new) name : ");
		LocalDateTime introduced = getLocalDateTimeFromCommandLine("Computer (new) introduced : ");
		LocalDateTime discontinued = getLocalDateTimeFromCommandLine("Computer (new) discontinued : ");
		Long company_id = getLongFromCommandLine("Computer (new) company_id : ");
		Computer computer = new Computer(id, name, introduced, discontinued,
				new Company(company_id, null));
		computerService.update(computer);
		System.out.println("computer updated : " + computer.toString());

	}

	private void deleteComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		computerService.delete(id);
	}

	private void deleteCompany() {
		Long id = getLongFromCommandLine("Company id : ");
		companyService.delete(id);
	}
	
	private Long getLongFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		Long resultAsALong = null;
		try {
			resultAsALong = new Long(result);
		} catch (NumberFormatException e) {
			System.out.println(ParsingException.CAN_NOT_PARSE_INTO_LONG);
		}
		return resultAsALong;
	}

	private String getStringFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		return result;
	}

	private LocalDateTime getLocalDateTimeFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		if (Validator.isDateValid(result)) {
			return LocalDateTime.parse(result);
		} else {
			System.out.println(Validator.WRONG_DATE_FORMAT);
		}
		return null;
	}

}
