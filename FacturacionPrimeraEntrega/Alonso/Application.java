package com.Alonso.FacturacionPrimeraEntrega.Alonso;

import com.Alonso.FacturacionPrimeraEntrega.Alonso.Repository.Client_Repository;
import com.Alonso.FacturacionPrimeraEntrega.Alonso.model.Model_Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	Client_Repository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... args) throws Exception {


	}
}
