package com.Adepu.Product.Controller;


import com.Adepu.Product.DTO.ProductDTO;
import com.Adepu.Product.Mapper.ProductMapper;
import com.Adepu.Product.Service.ProductService;
import com.Adepu.Product.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

	ProductService ProductService;
	ProductMapper productMapper;
	
	public ProductController(ProductService productService, ProductMapper productMapper) {
        this.ProductService = productService;
        this.productMapper = productMapper;
    }

	@GetMapping("/product/{id}")
	public Product getProduct(@PathVariable("id") Long id) {

		Product prfdb = ProductService.GetProduct(id);
		
		return prfdb;
	}

	@GetMapping("/product")
	public List<ProductDTO> getAllProduccts(
			@RequestParam("page") int page,
			@RequestParam("limit") int limit,
			@RequestParam("sortby") String sortby)
	{
		List<ProductDTO> allprfdb = ProductService.GetAllProducts(page-1, limit, sortby);
		return allprfdb;
		
	}


	@PostMapping("/product")
	public ProductDTO postProduct(@RequestBody ProductDTO productfromuser) {
		ProductDTO saveProduct = ProductService.PostProduct(
				productfromuser.getName(),
				productfromuser.getPrice(),
				productfromuser.getImage(),
				productfromuser.getCategoryName());
		
		return saveProduct;
	}

	

	@PutMapping("/product/{id}")
	public ProductDTO UpdateProduct(
			@PathVariable("id") Long id,
			@RequestBody ProductDTO product
			){

		ProductDTO updatedProduct;
		updatedProduct = ProductService.UpdateProduct(
				id,
				product.getName(),
				product.getPrice(),
				product.getImage(),
				product.getCategoryName()
		);

		return updatedProduct;
	}

	
	@DeleteMapping("/product/{id}")
	public String DeleteProduct(@PathVariable("id") long id) {
		return ProductService.deleteProduct(id);
	}

}
