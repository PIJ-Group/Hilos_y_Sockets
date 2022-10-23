package tarea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

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
					System.out.println("\nCerrado el hilo: " + hilo.getName());
					control = false;
				}else {
					// funciones del menú...
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
	
	
	
	

}
