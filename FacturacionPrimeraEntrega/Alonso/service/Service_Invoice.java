package com.Alonso.FacturacionPrimeraEntrega.Alonso.service;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.InvoiceAlreadyExists;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.InvoiceNotFoundException;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Invoice_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Invoice;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Invoice_Detail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class Service_Invoice {



    @Autowired
    private Invoice_Repository invoiceRepository;

    @Autowired
    private Service_Product serviceProduct;


    @Autowired
    private Service_Invoice_Detail serviceInvoiceDetail;

    public Model_Invoice create (Model_Invoice newInvoice) throws Exception {

        Optional<Model_Invoice> invoiceOp = this.invoiceRepository.findById(newInvoice.getId());

        if (invoiceOp.isPresent()){
            log.info("El siguiente invoice ya existe en la base de datos : " + newInvoice);
            throw new InvoiceAlreadyExists("El invoice que intenta agregar ya existe en la base de datos");
        }else {
            Model_Invoice invoiceBd = newInvoice;
            invoiceBd.setCreated_at(LocalDate.now());
            invoiceBd.setClient(newInvoice.getClient());
            invoiceBd.setInvoice_detail_list(newInvoice.getInvoice_detail_list());

            for (Model_Invoice_Detail item: newInvoice.getInvoice_detail_list()) {
                serviceInvoiceDetail.validadorDetail(item.getAmount(), item.getProduct());      // Valida cada uno de los detalles
            }

            for (Model_Invoice_Detail item: newInvoice.getInvoice_detail_list()) {
                serviceProduct.restar(item.getProduct().getId(), item.getAmount()); // resto stock
            }

            for (Model_Invoice_Detail item: newInvoice.getInvoice_detail_list()) {
                serviceInvoiceDetail.create(item);     // creo todas los detalles
            }

            for (Model_Invoice_Detail item: newInvoice.getInvoice_detail_list()) {
                invoiceBd.setTotal(item.getPrice() + invoiceBd.getTotal());// calculo total
                invoiceRepository.save(invoiceBd);
            }

            return this.invoiceRepository.save(invoiceBd);
        }
    }

    public Model_Invoice findById (Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id brindado no es valido");
        }

        Optional<Model_Invoice> invoiceOp = this.invoiceRepository.findById(id);

        if (invoiceOp.isEmpty()) {
            log.info("El siguiente invoice no existe en la base de datos : " + id);
            throw new InvoiceNotFoundException("El invoice no existe en la base de datos");
        } else {
            return invoiceOp.get();
        }
    }
    public List<Model_Invoice> findAll(){
        return this.invoiceRepository.findAll();
    }

}

