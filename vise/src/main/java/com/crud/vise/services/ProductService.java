package com.crud.vise.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crud.vise.Exceptions.ResourceNotFoundException;
import com.crud.vise.models.Product;
import com.crud.vise.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        return repo.findAll();
    }
     
    public Product save(Product product) {
       return repo.save(product);
    }
     
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Product product = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID Product Not Found: "+id));
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product dataProduct) {
        Product product = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID Product Not Found: "+id));

        product.setBrand(dataProduct.getBrand());
        product.setMadein(dataProduct.getMadein());
        product.setName(dataProduct.getName());
        product.setPrice(dataProduct.getPrice());

        return ResponseEntity.ok(repo.save(product));
    }
     
    public ResponseEntity<Map<String, Boolean>> delete(long id) {

        Product product = repo.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("ID Product Not Found: : " + id));      
                            
        repo.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(response);
    }

}
