package com.Alonso.FacturacionPrimeraEntrega.Alonso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "table_clients")
public class Model_Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
private String name;
    @NotNull
private String lastname;

    @NotNull
private String docnumber;


@OneToMany(mappedBy = "client")
private List<Model_Invoice> invoices;

    public Model_Client() {
    }

}
