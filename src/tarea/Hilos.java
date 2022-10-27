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
	private String error = "El libro no está en la Base de Datos de la Biblioteca";
	private String error2 = "No existen libros del autor solicitado";
	
	public Hilos(Socket socketCliente) {
		
		numeroUsuario++;
		hilo = new Thread(this, "Usuario_" + numeroUsuario);
		this.socketCliente = socketCliente;
		hilo.start();
		
	}

	@Override
	public void run() {
		
		System.out.println("Estableciendo conexión con: " +hilo.getName() + "\n");
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

					
						String info;
						Libro info1;
						ArrayList<Libro> infoArray;
						String libroCompleto;
						String[] libroCompuestoSeparado;
						
						
				        try {

				        	switch (text) {
					        
					        case "1":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " +text);
					        	salida.println("Introduzca el ISBN");					        	
								String isbn = entradaBr.readLine();
								System.out.println("El " +  hilo.getName() + " solicita información a través del ISBN: " + isbn);
								info = libroIsbn(isbn);	
								System.out.println("la info solicitada por el " +  hilo.getName() + " es: \n" +info);
								salida.println(info);
								break;
								
					        case "2":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " +text);
					        	salida.println("Introduzca el Título del libro");
								String titulo = entradaBr.readLine();
								System.out.println("El " +  hilo.getName() + "solicita información a través del título: " + titulo);
								info = libroTitulo(titulo);
								System.out.println("la info solicitada por el " +  hilo.getName() + " es: \n" +info);
					        	salida.println(info);
					            break;
					            
					        case "3":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " +text);
					        	salida.println("Introduzca el Autor a consultar");
								String autor = entradaBr.readLine();
								System.out.println("El " +  hilo.getName() + "solicita información a través del autor: " + autor);
								info = libroAutor(autor);
								System.out.println("la info solicitada por el " +  hilo.getName() + " es: \n" +info);
					        	salida.println(info);					        	
					            break;
					            
					        case "4":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " + text + "\n");
					        	salida.println("Introduzca los datos según el formato especificado: ISBN-Título-Autor-Precio");
					        	
								libroCompleto = entradaBr.readLine();
								libroCompuestoSeparado = libroCompleto.split("-");
								String isbn1 = libroCompuestoSeparado[0];
								String titulo1= libroCompuestoSeparado[1];
								String autor1 = libroCompuestoSeparado[2];
								String precio1 = libroCompuestoSeparado[3];
								añadirLibros(isbn1, titulo1, autor1, precio1);
								salida.println("Libro añadido correctamente");
								info1 = añadirLibros(isbn1, titulo1, autor1, precio1);
								salida.println(info1);
								System.out.println("El libro añadido por el " +  hilo.getName() + " es: \n" +info1);
					            break;
					        
					        default: 
					        	
					        	salida.println("Opción errónea");
					        	break;
					        
					        }
					        
					        					    				            
				        }catch(Exception e){
				        	
				        	System.err.println("Opción errónea");
				        	e.printStackTrace();
				        	
				        }
				        	
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
				return libro.toString() + "\n";
			
		}
		
		return error + "\n";	
	}
	
	public String libroTitulo(String titulo){
		
		for(Libro libro : Biblioteca.libros) {
			if(libro.getTitulo().equalsIgnoreCase(titulo)) 
				return libro.toString() + "\n";
		}
		
		return error + "\n";	
	}
	
	public String libroAutor(String autor){
		
		ArrayList<Libro>lista2 = new ArrayList<Libro>();
		for(Libro libro : Biblioteca.libros) {
			if(libro.getAutor().equalsIgnoreCase(autor)) 
				lista2.add(libro);
		}
		return lista2.toString() + "\n";	
	}
	
	public synchronized Libro añadirLibros(String isbn1, String titulo1, String autor1, String precio1) {

		int lastIdx = 0;
			
		Biblioteca.libros.add(new Libro(isbn1, titulo1, autor1, precio1));
		lastIdx = Biblioteca.libros.size() - 1;				
		
		return Biblioteca.libros.get(lastIdx);
	}
	
	
	
}
	

