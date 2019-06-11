package modelo;

import java.io.Serializable;

public class UsuarioModelo implements Serializable{
	public static final long serialVersionUID = 1L;
	
	private int id;
	private String usuario;
	private String senha;
	
	public UsuarioModelo() {		
	}
	
	public UsuarioModelo(int id, String usuario, String senha) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
