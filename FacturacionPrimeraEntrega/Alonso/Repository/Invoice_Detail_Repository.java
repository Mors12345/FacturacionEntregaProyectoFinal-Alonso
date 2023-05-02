package com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Invoice_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Invoice_Detail_Repository extends JpaRepository<Model_Invoice_Detail,Long> {

    Optional<Model_Invoice_Detail> findBycode(Long code);

    void deleteById(Long id);

}
