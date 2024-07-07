package com.training.online_shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRecoverDTO {

    private int productId;
    private String fileName;
    private String postName;
    private String category;
    private String modelNo;
    private Long price;
    private Integer quantity;
    private String description;
    private int active;
    private int view;

}
