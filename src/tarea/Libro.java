package tarea;

/*Clase Libro con la que crear los objetos Libro que compondrán la biblioteca*/

public class Libro {
	
	//Atributos de la clase	
	private String isbn;
	private String titulo;
	private String autor;
	private String precio;
	
	//Constructor con y sin parámetros (respectivamente)	
	public Libro(String isbn, String titulo, String autor, String precio) {		
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;	
	}
	
	public Libro() {
		super();
	}
	
	/*Métodos getter and setter*/
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getPrecio() {
		return precio;
	}
	
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	//Sobreescritura del método toString()
	@Override
	public String toString() {
		String resultado = "Libro ISBN=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio ;
		
		return resultado;
		
	}
	
	
	
	
	
	

	
	
	
}

