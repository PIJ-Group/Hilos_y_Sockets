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

	public static void main(String[] args) {
		
		System.out.println("        APLICACIÓN USUARIO         ");
		System.out.println("-----------------------------------");
		
		InputStreamReader entrada;
		PrintStream salida;
		BufferedReader entradaBr;
		InetSocketAddress direccionServer = new InetSocketAddress(IP_SERVER,PUERTO);
		
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Esperando la respuesta del servidor");
			Socket socketServidor = new Socket();
			socketServidor.connect(direccionServer);
			System.out.println("Conexión establecida con la dirección " + IP_SERVER + " por el puerto " + PUERTO + "\n");
			
			entrada = new InputStreamReader(socketServidor.getInputStream());
			salida = new PrintStream(socketServidor.getOutputStream());
			entradaBr = new BufferedReader(entrada);
			
			//String text;
			boolean control = true;
			int opcion;
			
			do {
				System.out.println("Bienvenido a la biblitoteca virtual" + "\n"
						+ "Escoja una de las siguientes opciones\n" );
				
		        opcion = menu();
		        
		        salida.println(opcion);
		        
		        //System.out.println("Esperando respuesta del servidor");
		        
		        String datosObtenidos = entradaBr.readLine();
		        
		        if("5".equalsIgnoreCase(datosObtenidos)) {
		        	control = false;
		        	System.out.println("Conexión terminada"); //Esto hay que cambiarlo de sitio //Isra: creo que así está bien
		        }else {
		        	System.out.println(datosObtenidos);
		        	System.out.println("Esperando respuesta del servidor");
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
			System.err.println("Error de usuario");
			e.printStackTrace();
		}

		
	}
	
	public static int menu() {
		try (Scanner scm = new Scanner(System.in)) {
			System.out.println("1. Consultar libro por ISBN");
			System.out.println("2. Consultar libro por título");
			System.out.println("3. Consultar libro por autor");
			System.out.println("4. Añadir libro");
			System.out.println("5. Salir de la aplicación");
			int option = scm.nextInt();
			return option;
		}
		
	}
	

}
