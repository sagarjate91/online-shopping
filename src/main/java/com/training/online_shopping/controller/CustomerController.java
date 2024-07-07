package com.training.online_shopping.controller;


import com.training.online_shopping.common.UserModel;
import com.training.online_shopping.dto.CustomerDTO;
import com.training.online_shopping.exception.ProductNotFoundException;
import com.training.online_shopping.model.Cart;
import com.training.online_shopping.model.Customer;
import com.training.online_shopping.model.Product;
import com.training.online_shopping.model.Role;
import com.training.online_shopping.repository.CartRepository;
import com.training.online_shopping.repository.CategoryRepository;
import com.training.online_shopping.repository.CustomerRepository;
import com.training.online_shopping.repository.ProductRepository;
import com.training.online_shopping.service.ConstantService;
import com.training.online_shopping.service.URLServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
@RequestMapping(ConstantService.USER)
public class CustomerController {
    @Autowired
    private CustomerRepository repo;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/customer/{userID}")
    public String getCustomer(@PathVariable("userID") int id,Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickUserViewProfile",true);
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        Customer customer=repo.findById(id).orElse(null);
        model.addAttribute("customer",customer);
        return "page";
    }

    @PostMapping("/signup-add")
    public String user(@Validated @ModelAttribute("command") CustomerDTO customer1, RedirectAttributes redirectAttributes){
        if(repo.findByEmail(customer1.getEmail())!=null){
            redirectAttributes.addFlashAttribute("message","User Already added,Please try new one..!");
        }else{
        	
            Customer customer=new Customer();
            customer.setFirstName(customer1.getFirstName());
            customer.setLastName(customer1.getLastName());
            customer.setEmail(customer1.getEmail());
            customer.setPassword(customer1.getPassword());
            customer.setMobileNumber(customer1.getMobileNumber());
            customer.setAddress(customer1.getAddress());
            customer.setPinCode(customer1.getPinCode());
            customer.setPassword(customer.getPassword());

            Role role=new Role();
            role.setRole("USER");
            customer.setRoles(Collections.singleton(role));
            Cart cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
            repo.save(customer);
            redirectAttributes.addFlashAttribute(ConstantService.MESSAGE,"User added successfully....!!!");
            
        }
         return "redirect:/customer/registerPanel.htm";
    }

    @PostMapping("/login-validate")
    public String loginValidate(@Validated @ModelAttribute("command") UserModel userModel, HttpSession session, RedirectAttributes redirectAttribute, Model model){

      Customer customer=repo.findByEmail(userModel.getEmail());
        if(customer==null){
            redirectAttribute.addAttribute(ConstantService.MESSAGE,"User does not exist");
            return URLServices.USER_URL;
        }
        if(!userModel.getPassword().equalsIgnoreCase(customer.getPassword())){
            redirectAttribute.addAttribute(ConstantService.MESSAGE,"Password mismatch");
            return URLServices.USER_URL;
        }
        if(customer.getStatus()!=1){
            redirectAttribute.addAttribute(ConstantService.MESSAGE,"Your Account not activated");
            return URLServices.USER_URL;
        }
        addUserInSession(session,customer.getEmail(),ConstantService.USER_ROLE);
        // set the name and the id
        userModel.setId(customer.getId());
        session.setAttribute("userModel", userModel);
        session.setAttribute("userID", customer.getId());
        model.addAttribute("userClickUserHome",true);
        return "redirect:/customer/user-home.htm";
    }

    @GetMapping("/show/{id}/product")
    public String showSingleProduct(@PathVariable int id, Model model) throws ProductNotFoundException {
        Product product=productRepository.findById(id).orElse(null);
        if(product==null){
            throw new ProductNotFoundException("Product not found");
        }
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickShowSingleProduct",true);
        model.addAttribute(ConstantService.CATEGORY,product.getCategory());
        model.addAttribute(ConstantService.PRODUCT,product);
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        return "page";
    }

    @GetMapping("/show/category/{categoryName}/products")
    public String adminCategoryProducts(@PathVariable("categoryName") String categoryName,Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickHomeCategory",true);
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        model.addAttribute(ConstantService.CATEGORY,categoryName);
        model.addAttribute(ConstantService.TITLE,categoryName);
        return "page";
    }

    @GetMapping(value = {"/userHome.htm","user-home.htm"})
    public String userHome(Model model){
        model.addAttribute("userClickUserHome",true);
        model.addAttribute(ConstantService.TITLE,"All Products");
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        return "page";
    }

    @GetMapping("/{userID}/view-profile.htm")
    public String viewProfile(@PathVariable("userID")int id, Model model){
        model.addAttribute("userClickUserViewProfile",true);
        model.addAttribute(ConstantService.TITLE,"View Profile");
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        Customer customer=repo.findById(id).orElse(null);
        model.addAttribute("customer",customer);
        return "page";
    }

    @GetMapping("/purchase-product.htm")
    public String purchaseProduct(Model model,@ModelAttribute("title") String title){
        model.addAttribute("userClickUserPurchaseProduct",true);
        model.addAttribute(ConstantService.TITLE,"Purchase Product");
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        return "page";
    }

    @GetMapping("/my-order.htm")
    public String myOrderProduct(Model model,@ModelAttribute("title") String title){
        model.addAttribute("userClickUserMyOrder",true);
        model.addAttribute(ConstantService.TITLE,"My Order");
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        return "page";
    }

    public void addUserInSession(HttpSession session, String email, String role) {
        try{
            session.setAttribute("email", email);
            session.setAttribute("role", role);
        }catch(Exception e){
           e.printStackTrace();
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,Model model){
        session.invalidate();
        model.addAttribute(ConstantService.MESSAGE,"Logout Successfully done...!!");
        return "redirect:/customer/";
    }
}
