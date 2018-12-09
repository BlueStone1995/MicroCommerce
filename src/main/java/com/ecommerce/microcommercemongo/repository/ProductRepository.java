package com.ecommerce.microcommercemongo.repository;

import com.ecommerce.microcommercemongo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findBy_id(ObjectId id);

    List<Product> findByNom(@Param("nom") String nom);
}
