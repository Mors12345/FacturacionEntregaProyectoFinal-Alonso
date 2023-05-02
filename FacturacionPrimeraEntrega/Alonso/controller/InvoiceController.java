package com.Alonso.FacturacionPrimeraEntrega.Alonso.controller;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.Invoice_Detail_Not_Found;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Invoice_Detail_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Product_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Invoice_Detail;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.InvoiceAlreadyExists;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Invoice;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Product;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.service.Service_Invoice;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.service.Service_Invoice_Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/invoice")
public class InvoiceController {
    @Autowired
    private Service_Invoice service_invoice;
    @Autowired
    private Service_Invoice_Detail service_invoice_detail;
    @Autowired
    private Product_Repository productRepository;
    @Autowired
    private Invoice_Detail_Repository invoice_detail_repository;

    @PostMapping(path = "/")
    public ResponseEntity<Model_Invoice> create (@Validated @RequestBody Model_Invoice invoice) throws Exception {
        return new ResponseEntity<>(this.service_invoice.create(invoice), HttpStatus.OK );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Model_Invoice> findById (@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.service_invoice.findById(id), HttpStatus.OK );
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Model_Invoice>> findAll () {
        return new ResponseEntity<>(this.service_invoice.findAll(), HttpStatus.OK);
    }
}





