package com.Alonso.FacturacionPrimeraEntrega.Alonso.model;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;


import javax.persistence.*;
@Data
@Entity
@Table(name = "table_invoice_details")
public class Model_Invoice_Detail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int amount;

    private double price;

@ManyToOne
@JoinColumn(name = "id_invoice")
@ToString.Exclude
private  Model_Invoice invoices;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn (name = "id_product")
private  Model_Product product;



    public Model_Invoice_Detail() {
    }

}


