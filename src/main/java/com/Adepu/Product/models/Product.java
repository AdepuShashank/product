package com.Adepu.Product.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;


@Entity
public class Product extends BaseModel {
		
		private String name;
		private Double price;
		private String image;
		private String category;

//		@ManyToOne
//		@OnDelete(action = OnDeleteAction.CASCADE)
//		private Category category;

		public Product(String name, Long id, Double price, String image,String category) {
			
			this.name = name;
			this.price = price;
			this.image = image;
			this.category = category;
		}

		public Product() {
			
		}
		
		public void setCategory(String category) {
	        this.category = category;
	    }

	    public String getCategory() {
	        return category;
	    }
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public Double getPrice() {
			return price;
		}
		@JsonProperty("price")
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}


		@Override
		public String toString() {
			return "Product [name=" + name + ", price=" + price + ", image=" + image +  ", category=" + category + "]";
		}
		
	} 


	


