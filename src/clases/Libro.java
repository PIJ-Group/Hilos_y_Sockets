package clases;


public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private double precio;
	
	
	public Libro(String ISBN, String titulo, String autor, double precio) {
		super();
		this.ISBN = ISBN;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}
	public Libro() {
		super();
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "Lib [ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + "]\n";
	}
	
	
	
	
	
	

	
	
	
}

