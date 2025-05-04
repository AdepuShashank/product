package com.Adepu.Product.Service;



import com.Adepu.Product.DTO.ProductDTO;
import com.Adepu.Product.Exceptions.ProductNotFoundException;
import com.Adepu.Product.Mapper.ProductMapper;
import com.Adepu.Product.Repository.ProductRepository;
import com.Adepu.Product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	
//	CategoryService categoryService;
	ProductRepository productRepository;
//	CategoryRepository categoryRepository;
	ProductMapper productMapper;
	RedisTemplate<String,Object> redisTemplate;
	
	public ProductService(ProductRepository productRepository, ProductMapper productMapper, RedisTemplate<String,Object> redisTemplate) {
		this.productRepository = productRepository;
//		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
//		this.categoryService = categoryService;
		this.redisTemplate = redisTemplate;
	}
	
	public Product GetProduct(Long id) throws ProductNotFoundException {
		
//		Product pFromCache= (Product)redisTemplate.opsForHash().get("PRODUCT_"+id, id);
//		System.out.println("done with pfromcache");
//
//		if(pFromCache != null) {
//			System.out.println("hit");
//			return pFromCache;
//		}
		
		Optional<Product> GetASingleProductById;
		GetASingleProductById = productRepository.findById(id);
		
		Product SingleProduct = null;
		
		if(GetASingleProductById.isEmpty()) {
			throw new ProductNotFoundException("no product of that type in database");
		}
		else {
			SingleProduct = GetASingleProductById.get();
		}
		
//		redisTemplate.opsForHash().put("PRODUCT_"+id, id, SingleProduct);
//		System.out.println("saved in cache");
		
		return SingleProduct;
	}
	
	public List<ProductDTO> GetAllProducts(int page , int limit, String sortby){
		Page<Product> ProductFromDB;
		
		ProductFromDB = productRepository.findAll(
						PageRequest.of(page, limit, Sort.by(sortby).descending()));
		
		List<ProductDTO> productDTOS = new ArrayList<>();
		
		for(Product p : ProductFromDB) {
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setId(p.getId());
			productDTO.setName(p.getName());
			productDTO.setPrice(p.getPrice());
			productDTO.setImage(p.getImage());
			productDTO.setCategoryName(p.getCategory());

			
			productDTOS.add(productDTO);
		}
		return productDTOS;
	}

	public ProductDTO PostProduct(String name, Double price, String image, String category) {

		Product saveProduct = new Product();
		
		saveProduct.setName(name);
		saveProduct.setImage(image);
		saveProduct.setPrice(price);
		saveProduct.setCategory(category);
//		Optional<Category> optionalcategory = categoryRepository.getCategoryByName(category);
//
//		if(optionalcategory.isPresent()) {
//			saveProduct.setCategory(optionalcategory.get());
//		}
//		else {
//			Category c = new Category();
//			c.setName(category);
//
//			Category savedCategory = categoryRepository.save(c);
//
//			saveProduct.setCategory(savedCategory);
//
//		}
		
		return productMapper.toProductDTO(productRepository.save(saveProduct));
	}

	public ProductDTO UpdateProduct(long id ,String name, Double price, String image, String category) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isEmpty()) {
			throw new ProductNotFoundException("no product to update");
		}
		else {
			Product ProductToUpdate = optionalProduct.get();
		
			if(name!=null) {
				ProductToUpdate.setName(name);
			}
			if(price!=null) {
				ProductToUpdate.setPrice(price);
			}
			if(image!=null) {
				ProductToUpdate.setImage(image);
			}
			
			
//			Optional<Category> optionalcategory = categoryRepository.getCategoryByName(category);
//
//			if(optionalcategory.isPresent()) {
//				ProductToUpdate.setCategory(optionalcategory.get());
//			}
//			else {
//				Category c = new Category();
//				c.setName(category);
//
//				Category savedCategory = categoryRepository.save(c);
//
//				ProductToUpdate.setCategory(savedCategory);
//
//			}
			Product updaetdProduct = ProductToUpdate;
			ProductDTO updatedProductDTO = productMapper.toProductDTO(updaetdProduct);
			
			return updatedProductDTO;
		}
	}

	/// handled exception
	public String deleteProduct(long id) throws ProductNotFoundException {
		
		Optional<Product> productById = productRepository.findById(id);
		if(productById.isEmpty())
			throw new ProductNotFoundException("no such product with id" + id);
		else
			productRepository.deleteById(id);
		
		return "Deleted product " + id;
	}

}
