package tarea;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import clases.Libro;

public class Servidor {
	
	private static final int PUERTO = 2047;
	private static int peticion = 0;
	
	
	
	public static void main(String[] args) {
		
		System.out.println("        SERVIDOR         ");
		System.out.println("-----------------------------------");
		
		/*ArrayList<String>libros = new ArrayList<String>();
		libros.add("A1523", "El atraco", "Diego López", "25");
		libros.add("B5230", "La evasion", "Duran pérez", 10);
		libros.add("C1433", "Oceanos", "Maria López", 5);
		libros.add("D1111", "La biblia", "Astro carlo", 12.6);
		libros.add("V1214", "El asesino", "Astro carlo",5.3); */
		
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
