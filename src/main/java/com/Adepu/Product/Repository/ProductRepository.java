package com.Adepu.Product.Repository;


import com.Adepu.Product.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM product WHERE category_id IS NULL", nativeQuery = true)
    int deleteProductWhereCategoryNull();
}
