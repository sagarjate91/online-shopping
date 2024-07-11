package com.training.online_shopping.controller;

import com.training.online_shopping.dto.CategoryDTO;
import com.training.online_shopping.dto.CustomerDTO;
import com.training.online_shopping.dto.ProductDTO;
import com.training.online_shopping.exception.ProductNotFoundException;
import com.training.online_shopping.model.Category;
import com.training.online_shopping.model.Product;
import com.training.online_shopping.repository.CategoryRepository;
import com.training.online_shopping.repository.CustomerRepository;
import com.training.online_shopping.repository.ProductRecoverRepository;
import com.training.online_shopping.repository.ProductRepository;
import com.training.online_shopping.service.ConstantService;
import com.training.online_shopping.service.CustomerService;
import com.training.online_shopping.util.FileUploadUtility;
import com.training.online_shopping.validator.ProductValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping(ConstantService.ADMIN)
public class AdminController {
    @Autowired
    private CustomerService service;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRecoverRepository productRecoverRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping({"/adminHome.htm"})
    public String adminHome(Model model,@ModelAttribute("message")String message){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickAdminHome",true);
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        model.addAttribute(ConstantService.TITLE, "Admin Home");
        if(message!=null){
           model.addAttribute(ConstantService.MESSAGE,message+"");
        }
        return "page";
    }
    
    @GetMapping({"/{id}/delete"})
    public String adminDelete(@PathVariable("")int id,RedirectAttributes redirectAttributes){
    	customerRepository.deleteById(id);
    	redirectAttributes.addFlashAttribute(ConstantService.MESSAGE, "User deleted successfully!");
        return "redirect:/admin/adminHome.htm";
    }
    
    @GetMapping({"/Product.htm"})
    public String adminProduct(Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickAdminProduct",true);
        model.addAttribute(ConstantService.ACTION,"admin/product-upload");
        model.addAttribute(ConstantService.COMMAND,new ProductDTO());
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        model.addAttribute(ConstantService.CATEGORY_MODEL,new CategoryDTO());
        model.addAttribute(ConstantService.TITLE, "Admin Product");
        return "page";
    }

    @PostMapping("/manage/category")
    public String addCategory(@ModelAttribute("category") CategoryDTO category, Model model){

        Category category1=new Category();
        category1.setName(category.getName());
        category1.setDescription_category(category.getDescription_category());

        categoryRepository.save(category1);
        model.addAttribute(ConstantService.MESSAGE,"Category added successfully!");
        return "redirect:/admin/Product.htm";
    }

    @GetMapping({"/ProductList.htm"})
    public String adminProductList(Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickAdminProductList",true);
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        model.addAttribute(ConstantService.TITLE, "Product List");
        return "page";
    }

    @GetMapping("/show/category/{categoryName}/products")
    public String adminCategoryProducts(@PathVariable("categoryName") String categoryName,Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickCategoryProducts",true);
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        model.addAttribute(ConstantService.CATEGORY,categoryName);
        model.addAttribute(ConstantService.TITLE,categoryName);
        return "page";
    }

    @GetMapping("/show/{id}/product")
    public String showSingleProduct(@PathVariable int id,Model model) throws ProductNotFoundException {
        Product product=productRepository.findById(id).orElse(null);
        if(product==null){
            throw new ProductNotFoundException("Product not found exception");
        }
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("adminClickShowSingleProduct",true);
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        model.addAttribute(ConstantService.CATEGORY,product.getCategory());
        model.addAttribute(ConstantService.TITLE,product.getCategory());
        model.addAttribute(ConstantService.PRODUCT,product);
        return "page";
    }

    @GetMapping({"/manage/{id}/product"})
    public String adminProductUpdate(@PathVariable("id") int id, Model model){
        model.addAttribute(ConstantService.PROJECT_NAME, ConstantService.TITLE);
        model.addAttribute("userClickAdminProduct",true);
        model.addAttribute(ConstantService.ACTION,"admin/product-upload");
        model.addAttribute(ConstantService.COMMAND,productRepository.findById(id));
        model.addAttribute(ConstantService.CATEGORIES, categoryRepository.findAll());
        model.addAttribute(ConstantService.CATEGORY_MODEL,new CategoryDTO());
        return "page";
    }

    @PostMapping(value="/product-upload")
    public String productAdd(@Validated @ModelAttribute("command") ProductDTO product, HttpServletRequest request, BindingResult results, Model model){

        // mandatory file upload check
        if(product.getProductId() == 0) {
           new ProductValidator().validate(product, results);
         }
        else {
            // edit check only when the file has been selected
            if(!product.getFile().getOriginalFilename().equals("")) {
                new ProductValidator().validate(product, results);
            }
        }
        if(results.hasErrors()) {
            model.addAttribute(ConstantService.MESSAGE,"Validation fails for adding the product!");
            return "redirect:/admin/ProductList.htm";
        }

        if(!product.getFile().getOriginalFilename().equals("")){
            FileUploadUtility.uploadProductDetails(product.getFile(),product);
        }

        Product product1=new Product();
        product1.setProductId(product.getProductId());
        product1.setFileName(product.getFileName());
        product1.setPostName(product.getPostName());
        product1.setCategory(product.getCategory());
        product1.setModelNo(product.getModelNo());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        product1.setDescription(product.getDescription());
        productRepository.saveAndFlush(product1);

        model.addAttribute(ConstantService.MESSAGE, "product updated successfully!");
        return "redirect:/admin/ProductList.htm";
    }

    @PostMapping({"/admin-validate"})
    public String adminValidate(@ModelAttribute("command") CustomerDTO customer, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        if(customer.getEmail().equalsIgnoreCase("admin@gmail.com") && customer.getPassword().equalsIgnoreCase("123")){
            addUserInSession(session,customer.getEmail(),ConstantService.ADMIN_ROLE);
            return "redirect:adminHome.htm";
        }else{
        	redirectAttributes.addFlashAttribute(ConstantService.MESSAGE, "Wrong Email and password,Please try new one");
            return "redirect:/customer/adminPanel.htm";
        }
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
        model.addAttribute("userClickHome",true);
        return "redirect:/customer/";
    }

}
