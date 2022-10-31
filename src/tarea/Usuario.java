package tarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*Cliente de la aplicación, llamado Usuario, que contendrá el menú 
  para interactuar con el servidor y la creación de su Socket.*/

public class Usuario {
	
	//Atributos a utilizar en la clase.
	private static final int PUERTO = 2047;
	private static final String IP_SERVER = "localhost";
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		System.out.println("        APLICACIÓN USUARIO         ");
		System.out.println("-----------------------------------");
		
		//Variables para poder crear los streams del Socket.
		InputStreamReader entrada;
		PrintStream salida;
		BufferedReader entradaBr;
		//Creación de la dirección por la cual nos conectaremos al servidor.
		InetSocketAddress direccionServer = new InetSocketAddress(IP_SERVER,PUERTO);
		
		/*Utilizamos un bloque TRY para crear el Socket, sus streams, y la lógica del cliente para cerrarse 
		a la vez que el hilo o devolver los datos del menú.*/
		try{
			
			//Creación del nuevo Socket para el Cliente, conectácdolo a la dirección anteriormente creada
			System.out.println("Esperando la respuesta del servidor");
			Socket socketServidor = new Socket();
			socketServidor.connect(direccionServer);
			System.out.println("Conexión establecida con la dirección " + IP_SERVER + " por el puerto " + PUERTO + "\n");
			
			//Creamos también los streams de intercambio de información con el servidor.
			entrada = new InputStreamReader(socketServidor.getInputStream());
			salida = new PrintStream(socketServidor.getOutputStream());
			entradaBr = new BufferedReader(entrada);
			
			//Variables a utilizar en la lógica del menú.
			String text;
			boolean control = true;
			String opcion;
			System.out.println("---Bienvenido a la biblioteca virtual---");
			
			/*Utilización de un bloque DO_WHILE para la lógica del menú.
			  Escogemos este tipo de bucle para que siempre salga el menú 
			  excepto en el caso de querer salir de él*/
			do {
				System.out.println("Escoja una de las siguientes opciones\n");
				//Llamamos a la función menú.
				opcion = menu();
				//Mandamos por el Stream de salida la opción escogida
				salida.println(opcion);		       
			    
				//Simulamos la espera de la respuesta del servidor con un Thread.
		        System.out.println("Esperando respuesta del servidor");
		        Thread.sleep(2000);
		        String datosObtenidos = entradaBr.readLine();     
		       
		        /*Bloque IF, en el cual si el servidor (concretamente la clase Hilo) devuelve 5, 
		        cambiamos la condición y salimos del bucle.*/
		        if("5".equalsIgnoreCase(datosObtenidos)) {
		        	control = false;
		        	System.out.println("Conexión terminada");
		       
		        //Bloque Else, en el cual recibimos los datos de la opción escogida.
		        } else {
		        	
		        	System.out.println(datosObtenidos); 
		        	text = sc.nextLine();
		        	salida.println(text);
		        	String datosObtenidos2 = entradaBr.readLine();
		        	System.out.println(datosObtenidos2 + "\n");
		        	String datosObtenidos3 = entradaBr.readLine();
		        	System.out.println(datosObtenidos3 + "\n");		        	

		        }
		       
		        		        	
			}while(control);
			
			socketServidor.close();
		
		//Bloques de captura de excepciones.
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
	
	//Función para poder acceder al menú, lo separamos del bloque principal del código por limpieza y organización.
	public static String menu() {
		
			System.out.println("1. Consultar libro por ISBN");
			System.out.println("2. Consultar libro por título");
			System.out.println("3. Consultar libro por autor");
			System.out.println("4. Añadir libro");
			System.out.println("5. Salir de la aplicación");
			String option = sc.nextLine();
			System.out.println("Has elegido la opción: " + option);
			return option;
			
	}
	
}
