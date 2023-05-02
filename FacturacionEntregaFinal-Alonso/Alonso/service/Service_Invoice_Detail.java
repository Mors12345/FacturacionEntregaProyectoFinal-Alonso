package com.Alonso.FacturacionPrimeraEntrega.Alonso.service;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.Invoice_Detail_Not_Found;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Invoice_Detail_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Product_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Invoice_Detail;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class Service_Invoice_Detail {

    @Autowired
    private Service_Product serviceProduct;
    @Autowired
    private Invoice_Detail_Repository invoiceDetailRepository;

    @Autowired
    private Product_Repository productRepository;

    public Model_Invoice_Detail create (Model_Invoice_Detail newInvoice_Detail) throws Exception {
        Model_Invoice_Detail invoice_DetailOp = newInvoice_Detail;
        invoice_DetailOp.setPrice(calcularsubtotal(invoice_DetailOp.getProduct().getId(), invoice_DetailOp.getAmount()));


        return this.invoiceDetailRepository.save(newInvoice_Detail);
    }

    public Model_Invoice_Detail findById (Long id) throws Exception {
            if (id <= 0) {
                throw new Exception("El id brindado no es valido");
            }

            Optional<Model_Invoice_Detail> invoiceDetailOp = this.invoiceDetailRepository.findById(id);

            if (invoiceDetailOp.isEmpty()) {
                log.info("El siguiente invoice detail no existe en la base de datos : " + id);
                throw new Invoice_Detail_Not_Found("El invoice detail no existe en la base de datos");
            } else {
                return invoiceDetailOp.get();
            }
        }
    public List<Model_Invoice_Detail> findAll(){
            return this.invoiceDetailRepository.findAll();
    }
    public double calcularsubtotal (Long id, Integer amount) throws Exception {
        Optional<Model_Product> productOp = this.productRepository.findById(id);
        double subtot = 0;
        Model_Product productBd = productOp.get();
        subtot = productBd.getPrice() * amount;

        return subtot;
    }

    public void validadorDetail (int amount, Model_Product product) throws Exception {
        Optional<Model_Product> productOp = this.productRepository.findById(product.getId());
        serviceProduct.chequeoStock(productOp.get().getId(), amount); // chequeo stock

    }

}
