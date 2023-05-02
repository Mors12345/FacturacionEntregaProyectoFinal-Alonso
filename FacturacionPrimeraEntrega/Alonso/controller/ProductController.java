package com.Alonso.FacturacionPrimeraEntrega.Alonso.controller;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.ProductAlreadyExistsExeption;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Product;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.service.Service_Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    @Autowired
    private Service_Product service_product;

    @PutMapping(path = "/{id}")
    public ResponseEntity<Model_Product> update (@RequestBody Model_Product product, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.service_product.update(product,id), HttpStatus.OK );
    }
    @PostMapping(path = "/")
    public ResponseEntity<Model_Product> create (@RequestBody Model_Product product) throws ProductAlreadyExistsExeption{
        return new ResponseEntity<>(this.service_product.create(product), HttpStatus.OK );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Model_Product> findById (@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.service_product.findById(id), HttpStatus.OK );
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Model_Product>> findAll () {
        return new ResponseEntity<>(this.service_product.findAll(), HttpStatus.OK);
    }

}
