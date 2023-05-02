package com.Alonso.FacturacionPrimeraEntrega.Alonso.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table (name = "table_invoices")
public class Model_Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate created_at;

    private Double total;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_client")
@ToString.Exclude
private Model_Client client;


@OneToMany(mappedBy = "invoices")
private List<Model_Invoice_Detail> invoice_detail_list;

    public Model_Invoice() {
    }

}
