package com.dad.serviciorest;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map.Entry;
import java.util.Set;

import com.dad.amigoanimal.Carrito;
import com.dad.amigoanimal.Cliente;
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
		OutputStream os;
		try {
			InputStream is =  socket.getInputStream();
			ObjectInputStream ois= new ObjectInputStream (is);
			Carrito carrito = (Carrito) ois.readObject();
			Cliente cliente = (Cliente) ois.readObject();
			Set<Entry<Producto, Integer>> contenido = carrito.getCosas();
			for (Entry<Producto, Integer> entrada :contenido) {
				Producto producto = entrada.getKey();
				cuerpo=cuerpo+"-"+producto.getName()+": "+producto.getDescription()+"\nCantidad:"+ entrada.getValue()+" Precio: "+ producto.getPrice()+"\n";
			}
			cuerpo = cuerpo + "Precio Total: "+carrito.getPrecioTotal()+"\nMuchas gracias por confiar en nosotros";
			destinatario= cliente.getEmail();

		    EmailSenderService.enviarConGMail(destinatario, asunto, cuerpo);
		    os = socket.getOutputStream();
			ObjectOutputStream oos= new ObjectOutputStream (os);
			oos.writeObject("OK");
		}catch (IOException | ClassNotFoundException e)  {
			System.out.println("Fallo en la comunicacion");
			try {
				os = socket.getOutputStream();
				ObjectOutputStream oos= new ObjectOutputStream (os);
				oos.writeObject("FAIL");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}
			
		
	}
}
