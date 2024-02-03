package com.example.assessment2.controller;

import com.example.assessment2.entity.Address;
import com.example.assessment2.entity.Order;
import com.example.assessment2.entity.Product;
import com.example.assessment2.entity.User;
import com.example.assessment2.service.OrderService;
import com.example.assessment2.service.ProductService;
import com.example.assessment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> showProduct(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam UUID productId) {
        productService.deleteProduct(productService.getProductById(productId));
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID productId, @RequestBody Product product) {
        Product oldProduct = productService.getProductById(productId);
        productService.deleteProduct(oldProduct);
        productService.addProduct(product);
        return ResponseEntity.ok("Product updated successfully");
    }
}