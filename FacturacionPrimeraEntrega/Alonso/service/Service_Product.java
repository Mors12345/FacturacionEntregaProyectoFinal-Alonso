package com.Alonso.FacturacionPrimeraEntrega.Alonso.service;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.ProductAlreadyExistsExeption;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.ProductNotFoundExeption;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.cannotBuyThatStoock;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Product_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class Service_Product {

    @Autowired
    private Product_Repository productRepository;

    public Model_Product create (Model_Product newProduct) throws ProductAlreadyExistsExeption{

        Optional<Model_Product> productOp = this.productRepository.findBycode(newProduct.getCode());

        if (productOp.isPresent()){
            log.info("El siguiente producto ya existe en la base de datos : " + newProduct);
            throw new ProductAlreadyExistsExeption("El producto que intenta agregar ya existe en la base de datos");
        }else {
            return this.productRepository.save(newProduct);
        }
    }
    public Model_Product update (Model_Product newProduct, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <=0){
            throw new Exception("El id brindado no es valido");
        }
        Optional<Model_Product> productOp = this.productRepository.findById(id);
        if (productOp.isEmpty()){
            log.info("El siguiente producto no existe en la base de datos : " + newProduct);
            throw new ProductNotFoundExeption("El producto que intenta modificar no existe en la base de datos");
        }else {
            log.info("El producto fue encontrado: " + newProduct);
            Model_Product productBd = productOp.get();

            productBd.setCode(newProduct.getCode());
            productBd.setDescription(newProduct.getDescription());
            productBd.setPrice(newProduct.getPrice());
            productBd.setStock(newProduct.getStock());
            log.info("El producto fue actualizado: " + productBd);

            return this.productRepository.save(productBd);
        }
    }

    public Model_Product findById (Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id brindado no es valido");
        }

        Optional<Model_Product> productOp = this.productRepository.findById(id);

        if (productOp.isEmpty()) {
            log.info("El siguiente producto no existe en la base de datos : " + id);
            throw new ProductNotFoundExeption("El producto no existe en la base de datos");
        } else {
            return productOp.get();
        }
    }
    public List<Model_Product> findAll(){
        return this.productRepository.findAll();
    }

   public Model_Product restar (Long id, int amount) throws Exception {
       Optional<Model_Product> productOp = this.productRepository.findById(id);
       Model_Product productBd = productOp.get();
       productBd.setStock(productBd.getStock() - amount);
       update(productBd, id);

       return productBd;
   }

    public void chequeoStock (Long id, int amount) throws Exception {
        Optional<Model_Product> productOp = this.productRepository.findById(id);
        boolean verificar = true;
        Model_Product productBd = productOp.get();
        if (amount > productBd.getStock() || amount < 0){ // chequeo que el stock sea valido
            verificar = false;
        }
        if (!verificar){
            throw new cannotBuyThatStoock("No puede comprar tanto stock de : " + productBd.getId());
        }

    }

}
