// package com.javatechie.crud.example.repository;

// import com.javatechie.crud.example.entity.Product;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface ProductRepository extends JpaRepository<Product,Integer> {
//     Product findByName(String name);
// }












// package com.javatechie.crud.example.repository;

// import com.javatechie.crud.example.entity.Product;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface ProductRepository extends JpaRepository<Product, Integer> {
//     Product findByName(String name);
//     List<Product> findByNameContaining(String keyword);
// }























package com.javatechie.crud.example.repository;

import com.javatechie.crud.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByPrice(Double price);

    List<Product> findByQuantity(Integer quantity);

    List<Product> findByNameContainingAndPrice(String name, Double price);

    List<Product> findByNameContainingAndQuantity(String name, Integer quantity);

    List<Product> findByPriceAndQuantity(Double price, Integer quantity);

    List<Product> findByNameContainingAndPriceAndQuantity(String name, Double price, Integer quantity);
}