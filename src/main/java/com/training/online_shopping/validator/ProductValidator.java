package com.training.online_shopping.validator;

import com.training.online_shopping.dto.ProductDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ProductDTO product = (ProductDTO) target;
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select a file to upload!");
			return;
		}
		if(! (product.getFile().getContentType().equals("image/jpeg") || product.getFile().getContentType().equals("image/jpg") || 
				product.getFile().getContentType().equals("image/png")) ||
				product.getFile().getContentType().equals("image/gif")
			 )
			{
				errors.rejectValue("file", null, "Please select an image file to upload!");
				return;	
			}

	}

}
