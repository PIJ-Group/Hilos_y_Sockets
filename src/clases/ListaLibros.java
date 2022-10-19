package clases;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaLibros {
	static Scanner sc;
	private ArrayList<Libro>lista;
	private String nombre;
	
	public  ListaLibros() {
		super();
		lista = new ArrayList<>();
		cargarLibros();
		
	}
	public ListaLibros(String nombre) {
		super();
		lista = new ArrayList<>();
		cargarLibros();
		this.nombre = nombre;
		
	}
	
	public Libro libroIsbn(String ISBN){
		for(Libro libro : lista) {
			if(libro.getISBN().equals(ISBN)) 
				return libro;
		}
		return null;	
	}
	public Libro libroTitulo(String titulo){
		for(Libro libro : lista) {
			if(libro.getTitulo().equals(titulo)) 
				return libro;
		}
		return null;	
	}
	public ArrayList<Libro> libroAutor(String autor){
		ArrayList<Libro>lista2 = new ArrayList<Libro>();
		for(Libro libro : lista) {
			if(libro.getAutor().equals(autor)) 
				lista2.add(libro);
		}
		return lista2;	
	}
	
	public void añadirLibros(String ISBN, String titulo, String autor, double precio) {
		lista.add(new Libro(ISBN, titulo, autor, precio));
		
	}
	public void cargarLibros() {
		lista.add(new Libro("A1523", "El atraco", "Diego López", 25));
		lista.add(new Libro("B5230", "La evasion", "Duran pérez", 10));
		lista.add(new Libro("C1433", "Oceanos", "Maria López", 5));
		lista.add(new Libro("D1111", "La biblia", "Astro carlo", 12.6));
		lista.add(new Libro("V1214", "El asesino", "Astro carlo",5.3));
		
	}
}
}
	sc = new Scanner(System.in);
	int opcion;
	do {

        opcion = menu();

        switch (opcion) {
        case 1:
        	libroIsbn("");
            break;
        case 2:
        	libroTitulo("");
            break;
        case 3:
        	libroAutor("");
            break;
        case 4:
        	añadirLibros("","","",10);
            break;
        case 5:
            System.out.println("Has salido del programa");
            break;
        default:
            System.out.println("Opción errónea");
        }


    }while (opcion != 5);


	
	/*int option;
	do{
		System.out.println("Bienvenido a la biblitoteca virtual" + "\n"
				+ "Escoja una de las siguientes opciones" );
		option = menu();
		
		switch (option) {
		case 1:
			System.out.println("Introduzca el ISBN");
				String isbn = sc.nextLine();
				libroIsbn(isbn);
			
			break;

		default: 
			System.out.println("opcion erroneas");
			break;
		}
		
	}while(option > 0 && option < 6); */

	

	
}
}

