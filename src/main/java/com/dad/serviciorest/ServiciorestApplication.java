package com.dad.serviciorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiciorestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciorestApplication.class, args);
	    System.out.println("Entro");
	    FacturaSocket.facturaSocket();
	}

}
