package tarea;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*Servidor de la aplicación, llamado Biblioteca, 
  que contendrá la base de datos de libros que componen la biblioteca y la creación del socket 
  mediante la escucha del serversocket*/

public class Biblioteca {
	
	//Atributos de la clase
	private static final int PUERTO = 2047;
	private static int peticion = 0;
	static ArrayList<Libro>libros = new ArrayList<>();
	
	
	
	public static void main(String[] args) {
		
		System.out.println("              BIBLIOTECA              ");
		System.out.println("--------------------------------------");
		
		//Dirección por la cual queremos que se conecte el servidor con el cliente.
		InetSocketAddress direccion = new InetSocketAddress(PUERTO);
		
		//Utilización de sleep para simular la carga de los libros.
		System.out.println("Precargando los libros de la biblioteca");
		
		try {
			
			Thread.sleep(2000);
			
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		
		//Añadimos los libros al array cread anteriormente.
		libros.add(new Libro("9780765311771", "Elantris", "Brandon Sanderson", "26.50"));
		libros.add(new Libro("9788468402772", "Los muertos vivientes", "Robert Kirkman", "20"));
		libros.add(new Libro("9788417347291", "El imperio final", "Brandon Sanderson", "19.86"));
		libros.add(new Libro("8483468018", "La niebla", "Stephen King", "4.85"));
		libros.add(new Libro("9780007136599", "The Fellowship of The Ring", "J.R.R. Tolkien","253.04"));
		
		System.out.println("Biblioteca cargada");
		
		/*Utilizamos un try-with-resources con ServerSocket, 
		  para poder cerrarlo automáticamente cuando terminemos de usar dicho bloque.*/
		try(ServerSocket servidor = new ServerSocket()){
			
			//Ponemos a la escucha al ServerSocket en la dirección anteriormente creada.
			servidor.bind(direccion);
			
			System.out.println("Esperando petición por el puerto " + PUERTO);
			
			/*Bloque While en el que, mientras que la condición sea TRUE, 
			  creamos un Socket con un Hilo (al que le pasamos el Socket creado)*/
			while(true) {
				
				//Creación del Socket
				Socket socketCliente = servidor.accept();
				System.out.println("\nPetición nº " + ++peticion + " recibida" + "\n" );
				//Creación del Hilo asociado al Socket
				new Hilos(socketCliente);
				
			}
		
		/*Varias excepciones a tratar*/	
		
		}catch(IOException e) {
			
			System.err.println("Error entrada/salida");
			e.printStackTrace();
			
		}catch(Exception e) {
			
			System.err.println("Error del servidor");
			e.printStackTrace();
			
		}
			
	}

}
