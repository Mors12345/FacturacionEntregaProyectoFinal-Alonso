package com.Alonso.FacturacionPrimeraEntrega.Alonso.service;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.ClientAlreadyExistsException;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Exeption.ClientNotFoundException;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Client_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class Service_Client {
    @Autowired
    private Client_Repository clientRepository;


    public Model_Client create(Model_Client newClient) throws ClientAlreadyExistsException {

        Optional<Model_Client> clientOp = this.clientRepository.findBydocnumber(newClient.getDocnumber());

        if (clientOp.isPresent()) {
            log.info("El siguiente cliente ya existe en la base de datos : " + newClient);
            throw new ClientAlreadyExistsException("El cliente que intenta agregar ya existe en la base de datos");
        } else {
            return this.clientRepository.save(newClient);
        }
    }

    public Model_Client update(Model_Client newClient, Long id) throws Exception {
        log.info("Id ingresado: " + id);
        if (id <= 0) {
            throw new Exception("El id brindado no es valido");
        }
        Optional<Model_Client> clientOp = this.clientRepository.findById(id);
        if (clientOp.isEmpty()) {
            log.info("El siguiente producto no existe en la base de datos : " + newClient);
            throw new ClientNotFoundException("El cliente que intenta modificar no existe en la base de datos");
        } else {
            log.info("El cliente fue encontrado: " + newClient);
            Model_Client clienteBd = clientOp.get();

            clienteBd.setDocnumber(newClient.getDocnumber());
            clienteBd.setName(newClient.getName());
            clienteBd.setLastname(newClient.getLastname());
            // preguntarle al profe como se hace esto
            clienteBd.setInvoices(newClient.getInvoices());
            log.info("El cliente fue actualizado: " + clienteBd);

            return this.clientRepository.save(clienteBd);
        }
    }

    public Model_Client findById(Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id brindado no es valido");
        }

        Optional<Model_Client> clientOp = this.clientRepository.findById(id);

        if (clientOp.isEmpty()) {
            log.info("El siguiente cliente no existe en la base de datos : " + id);
            throw new ClientNotFoundException("El cliente no existe en la base de datos");
        } else {
            return clientOp.get();
        }
    }

    public List<Model_Client> findAll() {
        return this.clientRepository.findAll();
    }


}
