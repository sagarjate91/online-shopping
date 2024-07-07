package com.training.online_shopping.controller;

import com.training.online_shopping.common.UserModel;
import com.training.online_shopping.model.Customer;
import com.training.online_shopping.repository.CardLineRepository;
import com.training.online_shopping.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class GlobalController {
	@Autowired
	private CustomerRepository userDAO;
	@Autowired
	private HttpSession session;
	public UserModel userModel=null;
	public Customer user=null;
	@Autowired
	private CardLineRepository cardLineRepository;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
	   if(session.getAttribute("email")!=null) {
				   // get the user from the database
				   user = userDAO.findByEmail(session.getAttribute("email").toString());
				   if (user != null) {
					   // create a new model
					   userModel = new UserModel();
					   // set the name and the id
					   userModel.setId(user.getId());
					   userModel.setFirstName(user.getFirstName() + " " + user.getLastName());
					   userModel.setCart(user.getCart());
					   session.setAttribute("userModel", userModel);
					   return userModel;
				   }
		}
		return (UserModel) session.getAttribute("userModel");
	}
		
}
