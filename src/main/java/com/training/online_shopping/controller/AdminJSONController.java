package com.training.online_shopping.controller;


import com.training.online_shopping.model.Customer;
import com.training.online_shopping.model.Product;
import com.training.online_shopping.service.ConstantService;
import com.training.online_shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ConstantService.ADMIN)
public class AdminJSONController {
    @Autowired
    private CustomerService service;

    @GetMapping("/all/Users")
    public List<Customer> users(){
        return service.users();
    }

    @PutMapping("/manage/{id}/activation")
    public String getUserUpdate(@PathVariable int id){
        return service.getUserUpdate(id);
    }

    @GetMapping("/json/data/all/products")
    public List<Product> products(){
        return service.productsAdmin();
    }

    @GetMapping("/json/data/category/{categoryName}/products")
    public List<Product> productsCategory(@PathVariable("categoryName") String categoryName){
     return service.productsCategory(categoryName);

    }

    @PutMapping("/manage/product/{id}/activation")
    public String updateProduct(@PathVariable int id){
        return service.updateProduct(id);
    }


}
