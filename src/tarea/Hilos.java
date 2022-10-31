package tarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/*Clase Hilos en la cual creamos un hilo por cada socket creado.
  Para la utilización de esta clase, hemos decidido que implemente la interfaz Runnable*/

public class Hilos implements Runnable {
	
	//Atributos de la clase.
	private Thread hilo;
	private static int numeroUsuario = 0;
	private Socket socketCliente;
	private String error = "El libro no está en la Base de Datos de la Biblioteca";
	
	/*Constructor del hilo, al cual pasamos el objeto Socket creado en la clase Biblioteca.
	  Además, arrancamos el hilo junto con su creación*/
	public Hilos(Socket socketCliente) {
		
		numeroUsuario++;
		hilo = new Thread(this, "Usuario_" + numeroUsuario);
		this.socketCliente = socketCliente;
		hilo.start();
		
	}
	
	//Sobreescritura del método run, donde implementamos la lógica de la clase.
	@Override
	public void run() {
		
		//Variables que utilizaremos para los streams del Socket.
		System.out.println("Estableciendo conexión con: " +hilo.getName() + "\n");
		InputStreamReader entrada;
		PrintStream salida;
		BufferedReader entradaBr;
		
		try {
			
			/*Cremamos un nuevo stream de entrada y salida, además de un objeto (BufferedReader)
			  para ayudar con la lectura de entrada.*/
			entrada = new InputStreamReader(socketCliente.getInputStream());
			salida = new PrintStream(socketCliente.getOutputStream());
			entradaBr = new BufferedReader(entrada);
			
			String text;
			boolean control = true;
			
			/*Bloque While al que cambiaremos la condición para que cierre el Hilo 
			  y se comunique con el Cliente para cerrarlo también.*/
			while(control) {
				
				text = entradaBr.readLine();
				
				/*Bloque IF en el que si la clase recibe por el stream de entrada el número 5, 
				  mandará al Cliente el número 5 para cerrarlo.
				  Además, cambiaremos la condición a FALSE para que salga del bucle While y cierre el Socket.*/
				if(text.trim().equalsIgnoreCase("5")) {
					
					salida.println("5");

					System.out.println("\nConexión cerrada por el " + hilo.getName());
					salida.println("Has salido de la aplicación" + "\n" + "Que tenga un buen día");

					control = false;
				
				/*Bloque ELSE en el que implementaremos la lógica de la aplicación, 
				  tanto las búsquedas como la inclusión del libro.*/
				} else {
					
						///Variables utilizadas.
						String info;
						Libro info1;
						String libroCompleto;
						String[] libroCompuestoSeparado;						
						
				        try {
				        	
				        	//Creación de un SWITCH para el control de la lógica del menú del usuario.
				        	switch (text) {
					        
				        	//Apartado 1 del menú: solicitud de un libro mediante su ISBN.
					        case "1":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " +text);
					        	salida.println("Introduzca el ISBN");					        	
								String isbn = entradaBr.readLine();
								System.out.println("El " +  hilo.getName() + " solicita información a través del ISBN: " + isbn);
								info = libroIsbn(isbn);	
								System.out.println("la info solicitada por el " +  hilo.getName() + " es: \n" +info);
								salida.println(info);
								break;
							
							//Apartado 2 del menú: solicitud de un libro mediante su título.	
					        case "2":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " +text);
					        	salida.println("Introduzca el Título del libro");
								String titulo = entradaBr.readLine();
								System.out.println("El " +  hilo.getName() + " solicita información a través del título: " + titulo);
								info = libroTitulo(titulo);
								System.out.println("la info solicitada por el " +  hilo.getName() + " es: \n" +info);
					        	salida.println(info);
					            break;
					            
					        //Apartado 3 del menú: solicitud de un libro mediante su autor.    
					        case "3":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " +text);
					        	salida.println("Introduzca el Autor a consultar");
								String autor = entradaBr.readLine();
								System.out.println("El " +  hilo.getName() + " solicita información a través del autor: " + autor);
								info = libroAutor(autor);
								System.out.println("la info solicitada por el " +  hilo.getName() + " es: \n" +info);
					        	salida.println(info);					        	
					            break;
					            
					        //Apartado 4 del menú: añadir libro a la biblioteca.    
					        case "4":
					        	
					        	System.out.println("El " +  hilo.getName() + " ha solicitado la opción " + text + "\n");
					        	salida.println("Introduzca los datos según el formato especificado: ISBN-Título-Autor-Precio");					        	
								libroCompleto = entradaBr.readLine();
								libroCompuestoSeparado = libroCompleto.split("-");
								String isbn1 = libroCompuestoSeparado[0];
								String titulo1= libroCompuestoSeparado[1];
								String autor1 = libroCompuestoSeparado[2];
								String precio1 = libroCompuestoSeparado[3];
								info1 = añadirLibros(isbn1, titulo1, autor1, precio1);
								salida.println("Libro añadido correctamente");
								salida.println(info1);
								System.out.println("El libro añadido por el " +  hilo.getName() + " es: \n" +info1);
					            break;
					        
					        //Caso Default por si el cliente selecciona una opción superior al 5.
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
		
		//Bloques de excepciones contempladas.
		} catch (IOException e) {
			
			System.err.println("Error entrada/salida");
			e.printStackTrace();
			
		} catch (Exception e) {
			
			System.err.println("Error del hilo");
			e.printStackTrace();
			
		}
			
	}
		
	//Función de busqueda del libro mediante su isbn.
	public String libroIsbn(String isbn){
		
		for(Libro libro : Biblioteca.libros) {
			if(libro.getIsbn().equalsIgnoreCase(isbn)) 
				return libro.toString() + "\n";			
		}
		
		return error + "\n";	
	}
	
	//Función de búsqueda del libro mediante su título
	public String libroTitulo(String titulo){
		
		for(Libro libro : Biblioteca.libros) {
			if(libro.getTitulo().equalsIgnoreCase(titulo)) 
				return libro.toString() + "\n";
			
		}
		
		return error + "\n";	
	}
	
	//Función de búsqueda del libro mediante su autor
	public String libroAutor(String autor){
		
		ArrayList<Libro>lista2 = new ArrayList<Libro>();
		for(Libro libro : Biblioteca.libros) {
			if(libro.getAutor().equalsIgnoreCase(autor)) 
				lista2.add(libro);
			
		}
		
		return lista2.toString() + "\n";
		
	}
	
	//Función con la que poder añadir libros a la biblioteca
	public synchronized Libro añadirLibros(String isbn1, String titulo1, String autor1, String precio1) {

		int lastIdx = 0;
			
		Biblioteca.libros.add(new Libro(isbn1, titulo1, autor1, precio1));
		lastIdx = Biblioteca.libros.size() - 1;				
		
		return Biblioteca.libros.get(lastIdx);
		
	}	
	
}
	

