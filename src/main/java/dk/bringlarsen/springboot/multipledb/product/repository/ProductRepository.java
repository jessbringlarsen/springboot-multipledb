package dk.bringlarsen.springboot.multipledb.product.repository;

import dk.bringlarsen.springboot.multipledb.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> { }
