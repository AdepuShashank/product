package com.Adepu.Product.DTO;


public class ProductDTO {
	private Long id;
	private String name;
	private String image;
	private double price;
	private String categoryName;
	
	public ProductDTO(Long id, String name, String image, double price, String categoryName) {
		
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.categoryName = categoryName;
	}
	public ProductDTO() {

	}
	public long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
}
