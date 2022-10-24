package clases;

import java.util.ArrayList;
import java.util.Scanner;

import tarea.Libro;

public class ListaLibros {
	private Scanner sc = new Scanner (System.in);
	private ArrayList<Libro>lista;
	private String nombre;
	
	
	public ListaLibros(String nombre) {
		super();
		this.nombre = nombre;
		lista = new ArrayList<>();
		cargarLibros();
	}

	public ListaLibros() {
		super();
}
	
	
	public ArrayList<Libro> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Libro> lista) {
		this.lista = lista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Libro libroIsbn(String isbn){
		for(Libro libro : lista) {
			if(libro.getIsbn().equalsIgnoreCase(isbn)) 
				return libro;
		}
		return null;	
	}
	public Libro libroTitulo(String titulo){
		for(Libro libro : lista) {
			if(libro.getTitulo().equalsIgnoreCase(titulo)) 
				return libro;
		}
		return null;	
	}
	
	public ArrayList<Libro> libroAutor(String autor){
		ArrayList<Libro>lista2 = new ArrayList<Libro>();
		for(Libro libro : lista) {
			if(libro.getAutor().equalsIgnoreCase(autor)) 
				lista2.add(libro);
		}
		return lista2;	
	}
	
	public ArrayList<Libro> añadirLibros(String isbn, String titulo, String autor, String precio) {
		lista.add(new Libro(isbn, titulo, autor, precio));
		return lista;		
	}
	
	public void cargarLibros() {
		lista.add(new Libro("A1523", "El atraco", "Diego López", "25"));
		lista.add(new Libro("B5230", "La evasion", "Duran pérez", "10"));
		lista.add(new Libro("C1433", "Oceanos", "Maria López", "5"));
		lista.add(new Libro("D1111", "La biblia", "Astro carlo", "12.6"));
		lista.add(new Libro("V1214", "El asesino", "Astro carlo","5.3"));
		
	}
	public void inicioApp(){	//intentar con try catch para que no se bloquee con opcion erronea
	
	
	int opcion;
	
	do {
		
		System.out.println("Bienvenido a la biblitoteca virtual" + "\n"
				+ "Escoja una de las siguientes opciones" );
        opcion = menu();
        sc = new Scanner (System.in);
      

        switch (opcion) {
        case 1:System.out.println("Introduzca el ISBN");
			String isbn = sc.nextLine();
			System.out.println(libroIsbn(isbn));
			
			break;
        case 2:
        	System.out.println("Introduzca el Título del libro");
			String titulo = sc.nextLine();
        	System.out.println(libroTitulo(titulo));
            break;
        case 3:
        	System.out.println("Introduzca el Autor a consultar");
			String autor = sc.nextLine();
        	System.out.println(libroAutor(autor));
            break;
        case 4:
        	System.out.println("Introduzca los datos según se lo vamos pidiendo");
        	System.out.println("ISBN: ");
			isbn = sc.nextLine();
			
			System.out.println("Titulo: ");
			titulo = sc.nextLine();
			
			System.out.println("Autor");
			autor = sc.nextLine();
			
			System.out.println("Precio");
			String precio = sc.nextLine();
			
			System.out.println(añadirLibros(isbn, titulo, autor, precio));
			
        	
            break;
        case 5:
            System.out.println("Has salido de la aplicación" + "\n" + "Que tenga un buen día");
            break;
        default:
            System.out.println("Opción errónea");
            break;
        }


    } while (opcion > 0 && opcion < 5);
	
}
	
	public int menu() {
		sc = new Scanner (System.in); 
		System.out.println("1. Consultar libro por ISBN");
	    System.out.println("2. Consultar libro por título");
	    System.out.println("3. Consultar libro por autor");
	    System.out.println("4. Añadir libro");
	    System.out.println("5. Salir de la aplicación");
	    int option = sc.nextInt();
	    return option;
		
	}
}

