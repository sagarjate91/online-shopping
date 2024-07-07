package com.training.online_shopping.controller;

import com.training.online_shopping.model.Product;
import com.training.online_shopping.repository.CustomerRepository;
import com.training.online_shopping.service.ConstantService;
import com.training.online_shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(ConstantService.USER)
public class JSONController {
	@Autowired
	private CustomerRepository repo;
	@Autowired
	private CustomerService service;

	@RequestMapping("/json/data/mv/products")
	@ResponseBody
	public List<Product> getMostViewedProducts() {
		return service.getMostViewedProducts();
	}

	@RequestMapping("/json/data/mp/products")
	@ResponseBody
	public List<Product> getMostPurchasedProducts() {
		return service.getMostPurchasedProducts();
	}


}
