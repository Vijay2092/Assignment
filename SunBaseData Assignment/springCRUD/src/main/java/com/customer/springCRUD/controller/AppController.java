package com.customer.springCRUD.controller;

import java.util.List;

import com.customer.springCRUD.model.Customer;
import com.customer.springCRUD.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	CustomerServices service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> listCustomer = service.listAll();
		model.addAttribute("listCustomer",listCustomer);
		return "index";
	}
	
	@RequestMapping("/new")
	public String newCustomerPage(Model model) {
		Customer customer=new Customer();
		model.addAttribute(customer);
		return "new_customer";
	}
	
	@RequestMapping(value = "/save", method =RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer ) {
		service.save(customer);
		return "redirect:/";
	}
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditStudentPage(@PathVariable (name="id") Long id) {
		ModelAndView mav=new ModelAndView("edit_customer");
		Customer customer=service.get(id);
		mav.addObject("customer",customer);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteStudentPage(@PathVariable (name="id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	
}
