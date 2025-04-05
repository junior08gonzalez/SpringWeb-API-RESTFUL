package com.junior.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.junior.curso.springboot.app.springboot_crud.entities.Product;
import com.junior.curso.springboot.app.springboot_crud.repositories.ProductRepository;

@Service
public class ProductServiceJPA implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
      return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {

        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
  
        return repository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {

        Optional<Product> productDb = repository.findById(id);
        productDb.ifPresent(prod ->{
            repository.delete(prod);
        });

        return productDb;
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productDb = repository.findById(id);
        if(productDb.isPresent()){
            Product prod = productDb.orElseThrow();
            prod.setName(product.getName());
            prod.setPrice(product.getPrice());
            prod.setDescription(product.getDescription());
            return Optional.of(repository.save(prod));
        }
        
        return productDb;
    }


}
