package com.tutorial.Springboot.controller;


import com.tutorial.Springboot.model.Product;
import com.tutorial.Springboot.model.ResponseObject;
import com.tutorial.Springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    //DI = Dependency Injection

    @Autowired
    private ProductRepository repository;


    @GetMapping("/getAllProducts")
    //this request is http://localhost:8080/api/v1/Products/getAllProducts

    List<Product> getAllProducts(){

        /*return List.of(
                new Product(1L,"Macbook",2022, 2600.0,"" ),
                new Product(2L,"Ipad",2021, 2000.0,"" )
        );*/

        return repository.findAll(); //where is data?
        // You must save this to Database,Now we have H2 DB = Im-memory Database
        //You can also send request using Postman
    }

    //Get detail product
    @GetMapping("/{id}")
    //Let return  an object with: data, message, status


    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false","Cannot find  product with id = " + id, "")
                );
    }


    // insert new Product with POST method
    //Postman : Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        //2 product must not have the same name
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed","Product name already taken","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("ok","Insert Product successfully", repository.save(newProduct))
        );
    }

    //update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct,@PathVariable Long id){
        Product updateProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return  repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return  repository.save(newProduct);
                });
        return  ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Update Product Successfully", updateProduct)
        );
    }

    // Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exists  = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete Product Successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Cannot find product to delete","")
        );
    }

    //Test
    @GetMapping("/event/{id}/calendar")
    ResponseEntity<ResponseObject> findById1(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false","Cannot find  product with id = " + id, "")
                );
    }

}
