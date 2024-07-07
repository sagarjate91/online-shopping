package com.training.online_shopping.service;


import com.training.online_shopping.model.Customer;
import com.training.online_shopping.model.Product;
import com.training.online_shopping.repository.CustomerRepository;
import com.training.online_shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Customer> users() {
        return customerRepository.findAll();
    }

    public String getUserUpdate(int id){
        Customer customer = customerRepository.findById(id).orElse(null);
        int isActive = customer.getStatus();
        boolean flag;

        if(isActive==0){
            customer.setStatus(1);
            flag=true;
        }
        else{
            customer.setStatus(0);
            flag=false;
        }
        customerRepository.saveAndFlush(customer);
        return (flag)? "User Activated Successfully": "User De-activated Successfully!";
    }

    public List<Product> products() {

        return productRepository.findByActive(1);
    }

    public List<Product> productsAdmin() {

        return productRepository.findAll();
    }

    public String updateProduct(int id) {
        Product product=productRepository.findById(id).orElse(null);
        int isActive = product.getActive();
        boolean flag;

        if(isActive==0){
            product.setActive(1);
            flag=true;
        }
        else{
            product.setActive(0);
            flag=false;
        }
        productRepository.saveAndFlush(product);
        return (flag)? "Product Activated Successfully": "Product De-activated Successfully!";

    }

    public List<Product> productsCategory(String categoryName) {
        return productRepository.findByCategory(categoryName);
    }

    public List<Product> getMostViewedProducts() {
        return productRepository.findByActive(1);
    }

    public List<Product> getMostPurchasedProducts() {
        return productRepository.findByActive(1);
    }


    public Customer findById(int id) {
        return customerRepository.findById(id).orElse(null);
    }
}
