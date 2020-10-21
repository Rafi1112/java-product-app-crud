package com.productapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.productapp.entity.Product;
import com.productapp.service.ProductService;

@Controller
@RequestMapping("/menu/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String productList(Model model) {
		model.addAttribute("listProducts", productService.findAllProduct());
		return "menu-templates/product/product-list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam(name = "id", required = false) Long id, Model model) {
		Product product = new Product();
		if (id != null) {
			product = productService.getProductById(id);
			if (product == null) {
				return "redirect:/menu/product";
			}
		}
		model.addAttribute("product", product);
		return "menu-templates/product/product-detail";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {

		if (productService.getProductByCode(product.getProductCode())) {
			redirectAttributes.addFlashAttribute("err", " Product Code is Exists !");
			return "redirect:/menu/product/detail";
		}
		
		redirectAttributes.addFlashAttribute("success", " You successfully Save the product !");
		productService.saveProduct(product);
		return "redirect:/menu/product";
	}
	
	@GetMapping("/delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("delete", " You successfully Delete the product !");
		productService.deleteProduct(id);
		return "redirect:/menu/product";
	}
	
}
