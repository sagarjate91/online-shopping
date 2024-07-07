package com.training.online_shopping.service;


import com.training.online_shopping.common.UserModel;
import com.training.online_shopping.model.Cart;
import com.training.online_shopping.model.CartLine;
import com.training.online_shopping.model.Customer;
import com.training.online_shopping.model.Product;
import com.training.online_shopping.repository.CardLineRepository;
import com.training.online_shopping.repository.CartRepository;
import com.training.online_shopping.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class CartLineServices {
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

    public List<CartLine> getCartLines() {
        Cart cart = this.getCart();
        return cardLineRepository.list(cart.getId());
    }

    public String addCartLine(int productId) {
        Cart cart = this.getCart();
        String response = null;
        CartLine cartLine = cardLineRepository.getByCartAndProduct(cart.getId(), productId);
        Product product = productRepository.findById(productId).orElse(null);
        if(cartLine==null) {
            // add a new cartLine if a new product is getting added
            cartLine = new CartLine();
             // transfer the product details to cartLine
            cartLine.setCartId(cart.getId());
            cartLine.setProduct(product);
            cartLine.setProductCount(1);
            cartLine.setBuyingPrice(product.getPrice());
            cartLine.setTotal(product.getPrice());

            product.setQuantity(product.getQuantity()-1);
            productRepository.saveAndFlush(product);

            // insert a new cartLine
            cardLineRepository.save(cartLine);

            // update the cart
            cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
            cart.setCartLines(cart.getCartLines() + 1);

            response = "result=added";
        }
        else {
            // check if the cartLine has been already reached to maximum count
            if(cartLine.getProductCount() < 3) {
                // call the manageCartLine method to increase the count
                response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
            }
            else {
                response = "result=maximum";
            }
        }
        return response;
    }

    /* to update the cart count */
    public String manageCartLine(int cartLineId, int count) {

        CartLine cartLine = cardLineRepository.findById(cartLineId).orElse(null);

        double oldTotal = cartLine.getTotal();
        Product product = cartLine.getProduct();

        // check if that much quantity is available or not
        if(product.getQuantity() < count) {
            return "result=unavailable";
        }
        if(cartLine.getProductCount()>count){
            product.setQuantity(product.getQuantity()+(cartLine.getProductCount()-count));
        }else {
            product.setQuantity(product.getQuantity()-(count-cartLine.getProductCount()));
        }
        productRepository.saveAndFlush(product);
        // update the cart line
        cartLine.setProductCount(count);
        cartLine.setBuyingPrice(product.getPrice());
        cartLine.setTotal(product.getPrice() * count);
        cardLineRepository.saveAndFlush(cartLine);

        return "result=updated";
    }

    public String validateCartLine() {

        Cart cart = this.getCart();
        List<CartLine> cartLines = cardLineRepository.list(cart.getId());
        double grandTotal = 0.0;
        int lineCount = 0;
        String response = "result=success";
        boolean changed = false;
        Product product = null;
        for(CartLine cartLine : cartLines) {
            product = cartLine.getProduct();
            changed = false;

            // check if the product is active or not
            // if it is not active make the availability of cartLine as false
            if((product.getActive()==0 && product.getQuantity() == 0) && cartLine.isAvailable()) {
                cartLine.setAvailable(false);
                changed = true;
            }
            // check if the cartLine is not available
            // check whether the product is active and has at least one quantity available
            if((product.getActive()!=0 && product.getQuantity() > 0) && !(cartLine.isAvailable())) {
                cartLine.setAvailable(true);
                changed = true;
            }

            // check if the buying price of product has been changed
            if(cartLine.getBuyingPrice() != product.getPrice()) {
                // set the buying price to the new price
                cartLine.setBuyingPrice(product.getPrice());
                // calculate and set the new total
                cartLine.setTotal(cartLine.getProductCount() * product.getPrice());
                changed = true;
            }

            // check if that much quantity of product is available or not
            if(cartLine.getProductCount() > product.getQuantity()) {
                cartLine.setProductCount(product.getQuantity());
                cartLine.setTotal(cartLine.getProductCount() * product.getPrice());
                changed = true;

            }

            // changes has happened
            if(changed) {
                //update the cartLine
                cardLineRepository.saveAndFlush(cartLine);
                // set the result as modified
                response = "result=modified";
            }
            grandTotal += cartLine.getTotal();
            lineCount++;
        }
        cart.setCartLines(lineCount++);
        cart.setGrandTotal(grandTotal);

        UserModel userModel=((UserModel)session.getAttribute("userModel"));
        Customer customer=new Customer();
        customer.setId(userModel.getId());
        cart.setCustomer(customer);
        userModel.setCart(cart);
        cartRepository.saveAndFlush(cart);

        return response;
    }

    public String removeCartLine(int cartLineId) {

       CartLine cartLine = cardLineRepository.findById(cartLineId).orElse(null);
       Product product = cartLine.getProduct();
       product.setQuantity(product.getQuantity()+cartLine.getProductCount());

        // deduct the cart
        // update the cart
        Cart cart = this.getCart();
        cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
        cart.setCartLines(cart.getCartLines() - 1);
        UserModel userModel=((UserModel)session.getAttribute("userModel"));
        Customer customer=new Customer();
        customer.setId(userModel.getId());
        cart.setCustomer(customer);
        userModel.setCart(cart);
        cartRepository.saveAndFlush(cart);

        // remove the cartLine
        cardLineRepository.delete(cartLine);
        return "result=deleted";
    }
}
