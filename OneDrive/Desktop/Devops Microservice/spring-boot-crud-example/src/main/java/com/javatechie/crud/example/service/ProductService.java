// package com.javatechie.crud.example.service;

// import com.javatechie.crud.example.entity.Product;
// import com.javatechie.crud.example.repository.ProductRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ProductService {
//     @Autowired
//     private ProductRepository repository;

//     public Product saveProduct(Product product) {
//         return repository.save(product);
//     }

//     public List<Product> saveProducts(List<Product> products) {
//         return repository.saveAll(products);
//     }

//     public List<Product> getProducts() {
//         return repository.findAll();
//     }

//     public Product getProductById(int id) {
//         return repository.findById(id).orElse(null);
//     }

//     public Product getProductByName(String name) {
//         return repository.findByName(name);
//     }

//     public String deleteProduct(int id) {
//         repository.deleteById(id);
//         return "product removed !! " + id;
//     }

//     public Product updateProduct(Product product) {
//         Product existingProduct = repository.findById(product.getId()).orElse(null);
//         existingProduct.setName(product.getName());
//         existingProduct.setQuantity(product.getQuantity());
//         existingProduct.setPrice(product.getPrice());
//         return repository.save(existingProduct);
//     }


// }




















// package com.javatechie.crud.example.service;

// import com.javatechie.crud.example.entity.Product;
// import com.javatechie.crud.example.repository.ProductRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class ProductService {
//     @Autowired
//     private ProductRepository repository;

//     public Product saveProduct(Product product) {
//         return repository.save(product);
//     }

//     public List<Product> saveProducts(List<Product> products) {
//         return repository.saveAll(products);
//     }

//     public List<Product> getProducts() {
//         return repository.findAll();
//     }

//     public Product getProductById(int id) {
//         return repository.findById(id).orElse(null);
//     }

//     public Product getProductByName(String name) {
//         return repository.findByName(name);
//     }

//     public List<Product> searchProducts(String keyword) {
//         return repository.findByNameContaining(keyword);
//     }

//     public String deleteProduct(int id) {
//         repository.deleteById(id);
//         return "product removed !! " + id;
//     }

//     public Product updateProduct(Product product) {
//         Product existingProduct = repository.findById(product.getId()).orElse(null);
//         existingProduct.setName(product.getName());
//         existingProduct.setQuantity(product.getQuantity());
//         existingProduct.setPrice(product.getPrice());
//         return repository.save(existingProduct);
//     }
// }


































package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }

    public Product getProductByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NoSuchElementException("Product not found with name: " + name));
    }

    public List<Product> searchProducts(String name, Double price, Integer quantity) {
        if (name != null && price != null && quantity != null) {
            return repository.findByNameContainingAndPriceAndQuantity(name, price, quantity);
        } else if (name != null && price != null) {
            return repository.findByNameContainingAndPrice(name, price);
        } else if (name != null && quantity != null) {
            return repository.findByNameContainingAndQuantity(name, quantity);
        } else if (price != null && quantity != null) {
            return repository.findByPriceAndQuantity(price, quantity);
        } else if (name != null) {
            return repository.findByNameContaining(name);
        } else if (price != null) {
            return repository.findByPrice(price);
        } else if (quantity != null) {
            return repository.findByQuantity(quantity);
        } else {
            return repository.findAll();
        }
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "Product removed !! " + id;
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            return repository.save(existingProduct);
        } else {
            throw new NoSuchElementException("Product not found with id: " + product.getId());
        }
    }
}