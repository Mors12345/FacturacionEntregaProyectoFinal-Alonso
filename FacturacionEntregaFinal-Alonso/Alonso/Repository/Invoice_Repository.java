package com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface Invoice_Repository extends JpaRepository<Model_Invoice,Long> {

    Optional<Model_Invoice> findBycode(Long code);

}
