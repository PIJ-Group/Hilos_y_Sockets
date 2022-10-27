package tarea;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Biblioteca {
	
	private static final int PUERTO = 2047;
	private static int peticion = 0;
	static ArrayList<Libro>libros = new ArrayList<>();
	
	
	
	public static void main(String[] args) {
		
		System.out.println("              BIBLIOTECA              ");
		System.out.println("--------------------------------------");
		
		InetSocketAddress direccion = new InetSocketAddress(PUERTO);
		
		System.out.println("Precargando los libros de la biblioteca");
		
		try {
			
			Thread.sleep(2000);
			
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		
		libros.add(new Libro("9780765311771", "Elantris", "Brandon Sanderson", "26.50"));
		libros.add(new Libro("9788468402772", "Los muertos vivientes", "Robert Kirkman", "20"));
		libros.add(new Libro("9788417347291", "El imperio final", "Brandon Sanderson", "19.86"));
		libros.add(new Libro("8483468018", "La niebla", "Stephen King", "4.85"));
		libros.add(new Libro("9780007136599", "The Fellowship of The Ring", "J.R.R. Tolkien","253.04"));
		
		System.out.println("Biblioteca cargada");
		
		try(ServerSocket servidor = new ServerSocket()){
			
			servidor.bind(direccion);
			
			System.out.println("Esperando petición por el puerto " + PUERTO);
			
			while(true) {
				
				Socket socketCliente = servidor.accept();
				System.out.println("\nPetición nº " + ++peticion + " recibida" + "\n" );
				new Hilos(socketCliente);
				
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
