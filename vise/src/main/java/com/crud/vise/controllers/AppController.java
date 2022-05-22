package com.crud.vise.controllers;

import java.util.List;
import java.util.Map;

import com.crud.vise.models.Product;
import com.crud.vise.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class AppController {

    @Autowired
    private ProductService service;

    @RequestMapping("/products")
    public List<Product> viewHomePage(Model model) {
        return service.listAll();
    }
    
    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product)
    {
        return service.save(product);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id ){
        ResponseEntity<Product> product = service.get(id);
        return product;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product dataProduct ){
        ResponseEntity<Product> product = service.update(id, dataProduct);

        return product;
    }

    @DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable Long id){        
        return service.delete(id);			
    }

}
