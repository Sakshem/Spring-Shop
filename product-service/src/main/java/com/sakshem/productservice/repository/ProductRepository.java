package com.sakshem.productservice.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sakshem.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
