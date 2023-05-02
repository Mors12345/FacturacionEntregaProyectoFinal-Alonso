package com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Product_Repository extends JpaRepository<Model_Product,Long> {

    Optional<Model_Product> findBycode(String code);

}
