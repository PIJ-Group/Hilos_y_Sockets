package clases;

public class Libtest {

	public static void main(String[] args) {
		
		ListaLibros biblioteca = new ListaLibros("Mi Biblioteca");
		
		// Llamadas a los métodos
		
		//ISBN
		System.out.println(biblioteca.libroIsbn("A1523"));
		
		//Título
		System.out.println(biblioteca.libroTitulo("La evasion"));
		
		//Autor
		System.out.println(biblioteca.libroAutor("Astro carlo"));
		
		//Añadir libro
		
		
		System.out.println(biblioteca.libroIsbn("G1258"));
		
	}

}
