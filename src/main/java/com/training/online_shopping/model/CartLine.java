package com.training.online_shopping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "cart_line")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartLine implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int productId;

	@Column(name = "cart_id")
	private int cartId;	
	@Column(name = "product_count")
	private int productCount;
	private double total;
	@Column(name = "buying_price")
	private double buyingPrice;
	@Column(name = "is_available")
	private boolean available = true;

	@Transient
	private Product product;



}
