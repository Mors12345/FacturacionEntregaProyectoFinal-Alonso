package com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Client_Repository extends JpaRepository<Model_Client,Long> {

    Optional<Model_Client> findBydocnumber(String docnumber);

}
