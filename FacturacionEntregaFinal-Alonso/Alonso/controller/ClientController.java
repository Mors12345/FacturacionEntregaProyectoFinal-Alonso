package com.Alonso.FacturacionPrimeraEntrega.Alonso.controller;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.ClientAlreadyExistsException;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Client;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.service.Service_Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {
    @Autowired
    private Service_Client service_client;

    @PutMapping(path = "/{id}")
    public ResponseEntity<Model_Client> update (@RequestBody Model_Client client, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.service_client.update(client,id), HttpStatus.OK );
    }
    @PostMapping(path = "/")
    public ResponseEntity<Model_Client> create (@RequestBody Model_Client client) throws ClientAlreadyExistsException {
        return new ResponseEntity<>(this.service_client.create(client), HttpStatus.OK );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Model_Client> findById (@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.service_client.findById(id), HttpStatus.OK );
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Model_Client>> findAll () {
        return new ResponseEntity<>(this.service_client.findAll(), HttpStatus.OK);
    }
}
