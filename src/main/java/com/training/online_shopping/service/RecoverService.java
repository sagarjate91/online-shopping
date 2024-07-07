package com.training.online_shopping.service;


import com.training.online_shopping.model.Product;
import com.training.online_shopping.model.ProductRecover;
import com.training.online_shopping.repository.ProductRecoverRepository;
import com.training.online_shopping.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecoverService {

    Logger logger= LoggerFactory.getLogger(RecoverService.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRecoverRepository productRecoverRepository;

    @Scheduled(initialDelay =100000,fixedRate =100000)
    public void productRecover(){
        	
        List<Product> productList=productRepository.findAll();
        List<Product> products=productList
                .stream()
                .filter(p->p.getCategory()==null || p.getFileName()==null || p.getPostName()==null || p.getModelNo()==null || p.getDescription()==null)
                .collect(Collectors.toList());
     
        products
                .stream()
                .filter(p -> {
                    ProductRecover productRecover = productRecoverRepository.findById(p.getProductId()).orElse(null);
                    if (productRecover != null && p.getActive()==1) {
                        Product product = new Product();
                        product.setProductId(productRecover.getProductId());
                        product.setFileName(productRecover.getFileName());
                        product.setPostName(productRecover.getPostName());
                        product.setCategory(productRecover.getCategory());
                        product.setModelNo(productRecover.getModelNo());
                        product.setPrice(productRecover.getPrice());
                        product.setQuantity(productRecover.getQuantity());
                        product.setDescription(productRecover.getDescription());
                        product.setActive(1);
                        product.setView(productRecover.getView());
                        productRepository.saveAndFlush(product);
                        logger.info("Product Id "+productRecover.getProductId()+" is Successfully Recover");
                    }
                    return false;
                }).collect(Collectors.toList());

        logger.info("Scheduling....");
       
    }
}
