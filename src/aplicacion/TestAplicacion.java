package aplicacion;

import clases.ListaLibros;

public class TestAplicacion {

	public static void main(String[] args) {
		
		ListaLibros L1 = new ListaLibros("Biblioteca");
		L1.cargarLibros();
		
		L1.inicioApp();
		System.out.println(L1.getLista());

	}

}
