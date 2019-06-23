package com.dad.serviciorest;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.dad.amigoanimal.Producto;;
public class ProcesadorSocket implements Runnable {
	
	Socket socket;
	String destinatario; //A quien le quieres escribir.
    String asunto = "Tu pedido en AmigoAnimal";
    String cuerpo = "Hola\n Aqui tienes la factura del pedido que has realizado:\n";
	
	public ProcesadorSocket (Socket Socket) {
		this.socket = Socket;
	}
	
	public void run() {
		int precioTotal=0;
		try {
			InputStream is =  socket.getInputStream();
			ObjectInputStream ois= new ObjectInputStream (is);
			
			String emailCliente = (String) ois.readObject();
//			
			HashMap<Producto, Integer> carrito = (HashMap<Producto, Integer>) ois.readObject();
			
			System.out.println("Procesando");
			Set<Entry<Producto, Integer>> contenido = carrito.entrySet();
		
			for (Entry<Producto, Integer> entrada :contenido) {
				Producto producto = entrada.getKey();
				cuerpo=cuerpo+"-"+producto.getName()+": "+producto.getDescription()+"\nCantidad:"+ entrada.getValue()+" Precio: "+ producto.getPrice()+"\n";
				precioTotal= precioTotal+(producto.getPrice()*entrada.getValue().intValue());
			}
			
			cuerpo = cuerpo + "Precio Total: "+precioTotal+"\nMuchas gracias por confiar en nosotros";
			
			destinatario= emailCliente;
			System.out.println("Enviando");
		    EmailSenderService.enviarConGMail(destinatario, asunto, cuerpo);
		    System.out.println("Enviado");
		}catch (IOException | ClassNotFoundException e)  {
			System.out.println(e.getLocalizedMessage());
			
			
			
		}
			
		
	}
}
