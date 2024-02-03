package com.example.assessment2.dao;

import com.example.assessment2.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDAO {
    static List<Product> productList = null;
    public ProductDAO() {
        productList = new ArrayList<>();
    }
    public void addProduct(Product product){
        productList.add(product);
    }

    public void deleteProduct(Product product){
        productList.remove(product);
    }

    public Product getProductByIndex(UUID index){
        return productList.get(index);
    }

    public List<Product> getAllOrders(){
        return productList;
    }
}
