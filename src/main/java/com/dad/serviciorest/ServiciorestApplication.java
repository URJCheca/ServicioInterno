package com.dad.serviciorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiciorestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciorestApplication.class, args);
		String destinatario =  "alexisasi12@hotmail.com"; //A quien le quieres escribir.
	    String asunto = "Correo de prueba enviado desde Java";
	    String cuerpo = "Esta es una prueba de correo...";

	    //EmailSenderService.enviarConGMail(destinatario, asunto, cuerpo);
	    System.out.println("Entro");
	    FacturaSocket.facturaSocket();
	}

}
