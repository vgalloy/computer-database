package com.excilys.computerdatabase.controler;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.excilys.computerdatabase.dto.model.ComputerDto;

public abstract class AbstractController {
	public static final String EDIT_COMPUTER = "editComputer";
	public static final String ADD_COMPUTER = "addComputer";
	public static final String DASHBOARD = "dashboard";
	public static final String REDIRECT = "redirect:/";
	
	@ModelAttribute("computerDto")
	public ComputerDto getComputerDto(){
	    return new ComputerDto();
	}
	
}