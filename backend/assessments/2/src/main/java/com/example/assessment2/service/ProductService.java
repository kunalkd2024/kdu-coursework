package com.example.assessment2.service;

import com.example.assessment2.dao.ProductDAO;
import com.example.assessment2.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ProductService {
    ProductDAO productDAO;
    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    public void addProduct(Product product){
        productDAO.addProduct(product);
    }

    public void deleteProduct(Product product){
        productDAO.deleteProduct(product);
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllOrders();
    }

    public Product getProductById(UUID id){
        return productDAO.getProductByIndex(id);
    }

    public Product getProduct(UUID id){
        for(Product u : productDAO.getAllOrders()){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
}
