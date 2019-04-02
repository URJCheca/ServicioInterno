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
	try {
		ServerSocket serverSocket = new ServerSocket(port) ;
		Socket socket = serverSocket.accept();
		InputStream is = socket.getInputStream();
		
		ObjectInputStream ois= new ObjectInputStream (is);
		Producto producto = (Producto) ois.readObject();
		System.out.println("Ha llegado el producto "+producto.getName());
		
		
	}
	catch(IOException | ClassNotFoundException e){
		e.printStackTrace();
		
	}
	}
}