package clases;


public class Libro {
	private String isbn;
	private String titulo;
	private String autor;
	private String precio;
	
	
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
	
	@Override
	public String toString() {
		return "Lib [ISBN=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + "]\n";
	}
	
	
	
	
	
	

	
	
	
}

