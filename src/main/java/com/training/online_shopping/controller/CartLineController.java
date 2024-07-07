package com.training.online_shopping.controller;


import com.training.online_shopping.service.CartLineServices;
import com.training.online_shopping.service.ConstantService;
import com.training.online_shopping.service.URLServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer/cart")
@Lazy
public class CartLineController {
    @Autowired
    private CartLineServices cardLineServices;

    @GetMapping("/show")
    public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Shopping Cart");
        mv.addObject("userClickShowCart", true);

        if(result!=null) {
            switch(result) {
                case "added":
                    mv.addObject(ConstantService.MESSAGE, "Product has been successfully added inside cart!");
                    cardLineServices.validateCartLine();
                    break;
                case "unavailable":
                    mv.addObject(ConstantService.MESSAGE, "Product quantity is not available!");
                    break;
                case "updated":
                    mv.addObject(ConstantService.MESSAGE, "Cart has been updated successfully!");
                    cardLineServices.validateCartLine();
                    break;
                case "modified":
                    mv.addObject(ConstantService.MESSAGE, "One or more items inside cart has been modified!");
                    break;
                case "maximum":
                    mv.addObject(ConstantService.MESSAGE, "Maximum limit for the item has been reached!");
                    break;
                case "deleted":
                    mv.addObject(ConstantService.MESSAGE, "CartLine has been successfully removed!");
                    cardLineServices.validateCartLine();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + result);
            }
        }
        else {
            String response = cardLineServices.validateCartLine();
            if(response.equals("result=modified")) {
                mv.addObject("message", "One or more items inside cart has been modified!");
            }
        }
        mv.addObject("cartLines", cardLineServices.getCartLines());
        return mv;

    }


    @GetMapping("/{cartLineId}/update")
    public String udpateCartLine(@PathVariable int cartLineId, @RequestParam int count) {
        String response = cardLineServices.manageCartLine(cartLineId, count);
        return URLServices.CART_SHOW_URL +response;
    }

    @GetMapping("/add/{productId}/product")
    public String addCartLine(@PathVariable int productId) {
        String response = cardLineServices.addCartLine(productId);
        return URLServices.CART_SHOW_URL+response;
    }

    @GetMapping("/{cartLineId}/remove")
    public String removeCartLine(@PathVariable int cartLineId) {
        String response = cardLineServices.removeCartLine(cartLineId);
        return URLServices.CART_SHOW_URL+response;
    }

    /* after validating it redirect to checkout
     * if result received is success proceed to checkout
     * else display the message to the user about the changes in cart page
     * */
    @GetMapping("/validate")
    public String validateCart() {
        String response = cardLineServices.validateCartLine();
        if(!response.equals("result=success")) {
            return URLServices.CART_SHOW_URL+response;
        }
        else {
            return "redirect:/customer/cart/checkout";
        }
    }
}
