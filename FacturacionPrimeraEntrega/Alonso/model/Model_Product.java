package com.Alonso.FacturacionPrimeraEntrega.Alonso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "table_products")
public class Model_Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private String code;

    @NotNull
    private Integer stock;

    @NotNull
    private Double price;



    public Model_Product() {
    }

}