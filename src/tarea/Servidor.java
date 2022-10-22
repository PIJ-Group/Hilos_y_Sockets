package tarea;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	
	private static final int PUERTO = 2047;
	private static int peticion = 0;
	static ArrayList<Libro>libros = new ArrayList<>();
	
	
	
	public static void main(String[] args) {
		
		System.out.println("        SERVIDOR         ");
		System.out.println("-----------------------------------");
		
		System.out.println("Precargando los libros de la biblioteca");
		System.out.println("-----------------------------------");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		libros.add(new Libro("A1523", "El atraco", "Diego López", "25"));
		libros.add(new Libro("B5230", "La evasion", "Duran pérez", "20"));
		libros.add(new Libro("C1433", "Oceanos", "Maria López", "3.5"));
		libros.add(new Libro("D1111", "La biblia", "Astro carlo", "4.6"));
		libros.add(new Libro("V1214", "El asesino", "Astro carlo","10")); 
		
		try(ServerSocket servidor = new ServerSocket()){
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			servidor.bind(direccion);
			
			System.out.println("Esperando petición por el puerto " + PUERTO);
			
			while(true) {
				Socket socketCliente = servidor.accept();
				System.out.println("Petición nº " + ++peticion + " recibida" );
				//new Hilos(socketCliente);
			}
			
		}catch(IOException e) {
			System.err.println("Error entrada/salida");
			e.printStackTrace();
		}catch(Exception e) {
			System.err.println("Error del servidor");
			e.printStackTrace();
		}
		
	}

}
