package com.training.online_shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int productId;
    private String fileName;
    private String postName;
    private String category;
    private String modelNo;
    private Long price;
    private Integer quantity;
    private String description;
    private int active=0;
    private int view=0;

    private MultipartFile file;

}
