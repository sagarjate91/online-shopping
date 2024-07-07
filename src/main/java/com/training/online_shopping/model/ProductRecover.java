package com.training.online_shopping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "PRODUCT_RECOVER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRecover {

    @Id
    @Column(name = "product_id")
    private Integer productId;
    @Column(name ="file_name")
    private String fileName;
    @Column(name = "post_name")
    private String postName;
    private String category;
    @Column(name = "model_no")
    private String modelNo;
    private Long price;
    private Integer quantity;
    private String description;
    private int active;
    private int view;


}
