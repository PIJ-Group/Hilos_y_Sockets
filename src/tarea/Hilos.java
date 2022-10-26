package tarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class Hilos implements Runnable {
	
	private Thread hilo;
	private static int numeroUsuario = 0;
	private Socket socketCliente;
	private String error="El libro no está en la Base de Datos de la Biblioteca";
	
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
					salida.println("Has salido de la aplicación" + "\n" + "Que tenga un buen día");

					control = false;
					
				} else {		

					do {
						String info;
						ArrayList<Libro> infoArray;
						
						
				        try {

				        	switch (text) {
					        
					        case "1":
					        	System.out.println("El usuario ha solicitado la opción " +text);
					        	salida.println("Introduzca el ISBN");					        	
								String isbn = entradaBr.readLine();
								System.out.println("El usuario solicita información a través del ISBN: " + isbn);
								info = libroIsbn(isbn);	
								System.out.println("la info solicitada por el usuario es: \n" +info);
								salida.println(info);
								break;
					        case "2":
					        	System.out.println("El usuario ha solicitado la opción " +text);
					        	salida.println("Introduzca el Título del libro");
								String titulo = entradaBr.readLine();
								System.out.println("El usuario solicita información a través del título: " + titulo);
								info = libroTitulo(titulo);
								System.out.println("la info solicitada por el usuario es: \n" +info);
					        	salida.println(info);
					            break;
					        case "3":
					        	System.out.println("El usuario ha solicitado la opción " +text);
					        	salida.println("Introduzca el Autor a consultar");
								String autor = entradaBr.readLine();
								System.out.println("El usuario solicita información a través del autor: " + autor);
								infoArray = libroAutor(autor);
								System.out.println("la info solicitada por el usuario es: \n" +infoArray);
					        	salida.println(infoArray);
					            break;
					        case "4":
					        	salida.println("Introduzca los datos según se lo vamos pidiendo");
					        	salida.println("ISBN: ");
								isbn = entradaBr.readLine();
								
								salida.println("Titulo: ");
								titulo = entradaBr.readLine();
								
								salida.println("Autor");
								autor = entradaBr.readLine();;
								
								salida.println("Precio");
								String precio = entradaBr.readLine();
								
								//salida.println(añadirLibros(isbn, titulo, autor, precio));													        	
					            break;
					        
					        default: 
					        	salida.println("Opción errónea");
					        	break;
					        
					        }
					        
					        
					    				            
				        }catch(Exception e){
				        	System.err.println("Opción errónea");
				        	e.printStackTrace();
				        }
				        
	
				    } while (text == "1" ||  text == "2" || text == "3"|| text == "4");					

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
		
	public String libroIsbn(String isbn){
		for(Libro libro : Biblioteca.libros) {
			if(libro.getIsbn().equalsIgnoreCase(isbn)) 
				return libro.toString();
		}
		return error;	
	}
	
	public String libroTitulo(String titulo){
		for(Libro libro : Biblioteca.libros) {
			if(libro.getTitulo().equalsIgnoreCase(titulo)) 
				return libro.toString();
		}
		return error;	
	}
	
	public ArrayList<Libro> libroAutor(String autor){
		ArrayList<Libro>lista2 = new ArrayList<Libro>();
		for(Libro libro : Biblioteca.libros) {
			if(libro.getAutor().equalsIgnoreCase(autor)) 
				lista2.add(libro);
		}//else
			//lista2.add("No hay ningún libro de ese autor");
		return lista2;	
	}
	
	public synchronized ArrayList<Libro> añadirLibros(String isbn, String titulo, String autor, String precio) {
		Biblioteca.libros.add(new Libro(isbn, titulo, autor, precio));
		return Biblioteca.libros;		
	}
	
}

