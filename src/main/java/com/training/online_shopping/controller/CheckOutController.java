package com.training.online_shopping.controller;


import com.training.online_shopping.common.UserModel;
import com.training.online_shopping.model.Cart;
import com.training.online_shopping.repository.CardLineRepository;
import com.training.online_shopping.repository.CartRepository;
import com.training.online_shopping.repository.ProductRepository;
import com.training.online_shopping.service.CartLineServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cart")
public class CheckOutController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartLineServices cartLineServices;
    @Autowired
    private CardLineRepository cardLineRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private HttpSession session;

    private Cart getCart() {
        UserModel userModel=((UserModel)session.getAttribute("userModel"));
        Cart cart=new Cart();
        cart.setId(userModel.getId());
        return cart;
    }

    @GetMapping
    public String getChekOut(Model model){
        Cart cart = this.getCart();
        return "page";
    }
}
