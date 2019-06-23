package com.dad.serviciorest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;



public class FacturaSocket {

	
	public static void facturaSocket () {
		int port= 9999;

	try {
		ServerSocket serverSocket= new ServerSocket(port);
		while(true) {
		System.out.println("Aceptando conexion");
		Socket socket = serverSocket.accept();
		System.out.println("Aceptada conexion");
		Thread t = new Thread(new ProcesadorSocket(socket));
		t.start();
		System.out.println("ejecutando hilo");
		}
	}
	catch(IOException e){
		e.printStackTrace();
		
	}
	}
}
