package com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerExeption {

    @ExceptionHandler(ProductNotFoundExeption.class)
    public ResponseEntity<?> productNotFoundExeption(Exception e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) ;
    }
    @ExceptionHandler(ProductAlreadyExistsExeption.class)
    public ResponseEntity<?> productAlreadyExistsException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT) ;
    }
    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<?> clientAlreadyExistsException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT) ;
    }
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> ilientNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) ;
    }
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<?> invoiceNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) ;
    }
    @ExceptionHandler(InvoiceAlreadyExists.class)
    public ResponseEntity<?> invoiceAlreadyExists(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT) ;
    }
    @ExceptionHandler(Invoice_Detail_Not_Found.class)
    public ResponseEntity<?> invoice_Detail_Not_Found(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND) ;
    }
    @ExceptionHandler(Invoice_Detail_AlreadyExists.class)
    public ResponseEntity<?> invoice_Detail_AlreadyExists(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT) ;
    }
    @ExceptionHandler(cannotBuyThatStoock.class)
    public ResponseEntity<?> cannotBuyThatStoock(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT) ;
    }
}
