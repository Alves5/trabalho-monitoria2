package modelo;

public class VideosModelo {
	private int id;
	private String video;
	private String tipo;
	private String nome;
	private String descrisao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrisao() {
		return descrisao;
	}
	public void setDescrisao(String descrisao) {
		this.descrisao = descrisao;
	}
	
}
