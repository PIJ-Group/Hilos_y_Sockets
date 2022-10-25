package tarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Hilos implements Runnable {
	
	private Thread hilo;
	private static int numeroUsuario = 0;
	private Socket socketCliente;
	
	public Hilos(Socket socketCliente) {
		numeroUsuario++;
		hilo = new Thread(this, "Usuario_" + numeroUsuario);
		this.socketCliente = socketCliente;
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Estableciendo conexión con: " +hilo.getName());
		InputStreamReader entrada;
		PrintStream salida;
		BufferedReader entradaBr;
		
		try {
			entrada = new InputStreamReader(socketCliente.getInputStream());
			salida = new PrintStream(socketCliente.getOutputStream());
			entradaBr = new BufferedReader(entrada);
			
			String text;
			boolean control = true;
			
			while(control) {
				
				text = entradaBr.readLine();
				if(text.trim().equalsIgnoreCase("5")) {
					
					salida.println("5");

					System.out.println("\nConexión cerrada por el " + hilo.getName());

					control = false;
					
				} else {
					

					
					

					//int opcion = Integer.parseInt(entradaBr.readLine());
										

					do {
						
						
						
						
				        try {

				        	Scanner sc = new Scanner(System.in); 
					        switch (text) {
					        
					        case "1":
					        	System.out.println("Introduzca el ISBN");
								String isbn = sc.nextLine();
								//salida.println(libroIsbn(isbn));								
								break;
					        case "2":
					        	salida.println("Introduzca el Título del libro");
								String titulo = sc.nextLine();
					        	salida.println(libroTitulo(titulo));
					            break;
					        case "3":
					        	salida.println("Introduzca el Autor a consultar");
								String autor = sc.nextLine();
					        	salida.println(libroAutor(autor));
					            break;
					        case "4":
					        	salida.println("Introduzca los datos según se lo vamos pidiendo");
					        	salida.println("ISBN: ");
								//isbn = sc.nextLine();
								
								salida.println("Titulo: ");
								titulo = sc.nextLine();
								
								salida.println("Autor");
								autor = sc.nextLine();
								
								salida.println("Precio");
								String precio = sc.nextLine();
								
								//salida.println(añadirLibros(isbn, titulo, autor, precio));													        	
					            break;
					        case "5":
					            salida.println("Has salido de la aplicación" + "\n" + "Que tenga un buen día");
					            break;
					        
					        }
					        
					        
					    				            
				        }catch(Exception e){
				        	System.err.println("Opción errónea");
				        	e.printStackTrace();
				        }

	
				    } while (text != "5");					

				}
			}
			
			socketCliente.close();
		
			
		} catch (IOException e) {
			System.err.println("Error entrada/salida");
			e.printStackTrace();
			
		} catch (Exception e) {
			System.err.println("Error del hilo");
			e.printStackTrace();			
		}
			
	}
		
	public Libro libroIsbn(){
		System.out.println("Introduzca el ISBN");
		Scanner sc = new Scanner(System.in);
		String isbn = sc.toString();
		for(Libro libro : Biblioteca.libros) {
			if(libro.getIsbn().equalsIgnoreCase(isbn)) 
				return libro;
		}
		return null;	
	}
	
	public Libro libroTitulo(String titulo){
		for(Libro libro : Biblioteca.libros) {
			if(libro.getTitulo().equalsIgnoreCase(titulo)) 
				return libro;
		}
		return null;	
	}
	
	public ArrayList<Libro> libroAutor(String autor){
		ArrayList<Libro>lista2 = new ArrayList<Libro>();
		for(Libro libro : Biblioteca.libros) {
			if(libro.getAutor().equalsIgnoreCase(autor)) 
				lista2.add(libro);
		}
		return lista2;	
	}
	
	public ArrayList<Libro> añadirLibros(String isbn, String titulo, String autor, String precio) {
		Biblioteca.libros.add(new Libro(isbn, titulo, autor, precio));
		return Biblioteca.libros;		
	}
	
}

