package com.productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.productapp.service.ProductService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String menu(Model model) {
		model.addAttribute("counts", productService.counts());
		return "menu-templates/dashboard";
	}

}
