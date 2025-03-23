// package com.javatechie.crud.example.controller;

// import com.javatechie.crud.example.entity.Product;
// import com.javatechie.crud.example.service.ProductService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// public class ProductController {

//     @Autowired
//     private ProductService service;

//     @PostMapping("/addProduct")
//     public Product addProduct(@RequestBody Product product) {
//         return service.saveProduct(product);
//     }

//     @PostMapping("/addProducts")
//     public List<Product> addProducts(@RequestBody List<Product> products) {
//         return service.saveProducts(products);
//     }

//     @GetMapping("/products")
//     public List<Product> findAllProducts() {
//         return service.getProducts();
//     }

//     @GetMapping("/productById/{id}")
//     public Product findProductById(@PathVariable int id) {
//         return service.getProductById(id);
//     }

//     @GetMapping("/product/{name}")
//     public Product findProductByName(@PathVariable String name) {
//         return service.getProductByName(name);
//     }

//     @PutMapping("/update")
//     public Product updateProduct(@RequestBody Product product) {
//         return service.updateProduct(product);
//     }

//     @DeleteMapping("/delete/{id}")
//     public String deleteProduct(@PathVariable int id) {
//         return service.deleteProduct(id);
//     }
// }





















// package com.javatechie.crud.example.controller;

// import com.javatechie.crud.example.entity.Product;
// import com.javatechie.crud.example.service.ProductService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// public class ProductController {

//     @Autowired
//     private ProductService service;

//     @GetMapping("/health")
//     public String healthCheck() {
//         return "Server is up and running!";
//     }

//     @PostMapping("/addProduct")
//     public Product addProduct(@RequestBody Product product) {
//         return service.saveProduct(product);
//     }

//     @PostMapping("/addProducts")
//     public List<Product> addProducts(@RequestBody List<Product> products) {
//         return service.saveProducts(products);
//     }

//     @GetMapping("/products")
//     public List<Product> findAllProducts() {
//         return service.getProducts();
//     }

//     @GetMapping("/productById/{id}")
//     public Product findProductById(@PathVariable int id) {
//         return service.getProductById(id);
//     }

//     @GetMapping("/product/{name}")
//     public Product findProductByName(@PathVariable String name) {
//         return service.getProductByName(name);
//     }

//     @GetMapping("/products/search")
//     public List<Product> searchProducts(@RequestParam String keyword) {
//         return service.searchProducts(keyword);
//     }

//     @PutMapping("/update")
//     public Product updateProduct(@RequestBody Product product) {
//         return service.updateProduct(product);
//     }

//     @DeleteMapping("/delete/{id}")
//     public String deleteProduct(@PathVariable int id) {
//         return service.deleteProduct(id);
//     }
// }





























package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/health")
    public String healthCheck() {
        return "Server is up and running!";
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        try {
            Product product = service.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name) {
        try {
            Product product = service.getProductByName(name);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer quantity) {
        try {
            List<Product> products = service.searchProducts(name, price, quantity);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}