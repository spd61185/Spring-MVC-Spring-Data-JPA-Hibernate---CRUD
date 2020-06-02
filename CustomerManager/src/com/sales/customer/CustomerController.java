package com.sales.customer;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		List<Customer> listCustomer = customerService.listAll();
		
		mv.addObject("listCustomer", listCustomer);
		
		return mv;
	}
	
	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
		model.put("customer", new Customer());
		return "addCustomer";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer")Customer customer) {
		customerService.save(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/edit")
	public ModelAndView editCustomerForm(@RequestParam long id) {
		ModelAndView mv = new ModelAndView("edit_customer");
		Customer customer = customerService.get(id);
		mv.addObject("customer", customer);
		return mv;
	}
	
	@RequestMapping("/delete")
	public String deleteCustomer(@RequestParam long id) {
		customerService.delete(id);
		return "redirect:/";
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam String keyword) {
		ModelAndView mv = new ModelAndView("search");
		List<Customer> result =customerService.search(keyword);
		mv.addObject("result", result);
		return mv;
	}
}

