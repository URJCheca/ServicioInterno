package com.dad.serviciorest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class FacturaSocket {

	
	public static void facturaSocket () {
		int port= 9999;
		System.out.println("Esperando1");
	try {
		System.out.println("Esperando2");
		ServerSocket serverSocket = new ServerSocket(port) ;
		System.out.println("Esperando3");
		Socket socket = serverSocket.accept();
		System.out.println("Esperando4");
		InputStream is = socket.getInputStream();
		System.out.println("Esperando5");
		ObjectInputStream ois= new ObjectInputStream (is);
		System.out.println("Fuera");
		while(true) {
		System.out.println("Dentro");
		Producto producto = (Producto) ois.readObject();
		System.out.println("Ha llegado el producto "+producto.getName());
		}
		
		
	}
	catch(IOException | ClassNotFoundException e){
		e.printStackTrace();
		
	}
	}
}
