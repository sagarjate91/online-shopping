package com.training.online_shopping.dto;

import com.training.online_shopping.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLineDTO {

    private int id;
    private int cartId;
    private int productCount;
    private double total;
    private double buyingPrice;
    private boolean available = true;

    private Product product;

}
