package com.training.online_shopping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


@Entity
@Table(name = "PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name ="file_name")
    private String fileName;
    @Column(name = "post_name")
    private String postName;
    private String category;
    @Column(name = "model_no")
    private String modelNo;
    private Long price;
    private int quantity;
    private String description;
    private int active;
    private int view;
    
    @Transient
    private MultipartFile file;

}
