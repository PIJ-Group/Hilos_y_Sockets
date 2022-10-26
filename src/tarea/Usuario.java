package tarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Usuario {
	
	private static final int PUERTO = 2047;
	private static final String IP_SERVER = "localhost";
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("        APLICACIÓN USUARIO         ");
		System.out.println("-----------------------------------");
		
		InputStreamReader entrada;
		PrintStream salida;
		BufferedReader entradaBr;
		InetSocketAddress direccionServer = new InetSocketAddress(IP_SERVER,PUERTO);
		
		try{
			
			System.out.println("Esperando la respuesta del servidor");
			Socket socketServidor = new Socket();
			socketServidor.connect(direccionServer);
			System.out.println("Conexión establecida con la dirección " + IP_SERVER + " por el puerto " + PUERTO + "\n");
			
			entrada = new InputStreamReader(socketServidor.getInputStream());
			salida = new PrintStream(socketServidor.getOutputStream());
			entradaBr = new BufferedReader(entrada);
			
			String text;
			boolean control = true;
			String opcion;
			System.out.println("---Bienvenido a la biblitoteca virtual---");
			 
			do {
				
				System.out.println("Escoja una de las siguientes opciones\n");  
				
				opcion = menu();
			    salida.println(opcion);
		       
			    
		        System.out.println("Esperando respuesta del servidor");
		        Thread.sleep(2000);
		        String datosObtenidos = entradaBr.readLine();
		       
		        
		        if("5".equalsIgnoreCase(datosObtenidos)) {
		        	
		        	control = false;
		        	System.out.println("Conexión terminada"); //Esto hay que cambiarlo de sitio
		        	
		        }else if("4".equalsIgnoreCase(datosObtenidos)) {
		        	
		        	System.out.println(datosObtenidos); /*Esto es lo que te decía Pablo, si tienes un Switch en un lado con 3 líneas, aquí tendras que recoger esas líneas (de ahí que lo llamar espejo)*/
		        	text = sc.nextLine();
		        	salida.println(text);
		        	String datosObtenidos4 = entradaBr.readLine();
		        	System.out.println(datosObtenidos4 + "\n");
		        	String datosObtenidos5 = entradaBr.readLine();
		        	System.out.println(datosObtenidos5 + "\n");
		        	
		        }else {
		        	
		        	System.out.println(datosObtenidos); /*Esto es lo que te decía Pablo, si tienes un Switch en un lado con 3 líneas, aquí tendras que recoger esas líneas (de ahí que lo llamar espejo)*/
		        	text = sc.nextLine();
		        	salida.println(text);
		        	String datosObtenidos2 = entradaBr.readLine();
		        	System.out.println(datosObtenidos2 + "\n");
		        	String datosObtenidos3 = entradaBr.readLine();
		        	System.out.println(datosObtenidos3 + "\n");	
		        	
		        }
		        		        	
			}while(control);
			
			socketServidor.close();
			
		}catch (UnknownHostException e){
			
			System.err.println("Servidor " + IP_SERVER + " no encontrado");
			e.printStackTrace();
			
		}catch (IOException e) {
			
			System.err.println("Error entrada/salida");
			e.printStackTrace();
			
		}catch (Exception e) {
			
			System.err.println("Error de cliente");
			e.printStackTrace();
			
		}
		
	}
	
	public static String menu() {
		
			System.out.println("1. Consultar libro por ISBN");
			System.out.println("2. Consultar libro por título");
			System.out.println("3. Consultar libro por autor");
			System.out.println("4. Añadir libro");
			System.out.println("5. Salir de la aplicación");
			String option = sc.nextLine();
			System.out.println("Has elegido la opción: " +option);
			return option;
			
	}
	
}
