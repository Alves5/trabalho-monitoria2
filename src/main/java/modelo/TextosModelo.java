package modelo;

public class TextosModelo {
	private Integer id;
	private String titulo;
	private String paragrafo;
	private byte[] file;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getParagrafo() {
		return paragrafo;
	}
	public void setParagrafo(String paragrafo) {
		this.paragrafo = paragrafo;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
}
