package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class UsuarioModelo {
	private int id;
	private String nome;
	private String usuario;
	private String senha;
	private UploadedFile foto;
	
	public void cadastro() {
		try {
			if(nome != null && usuario != null && senha != null && foto != null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
				String sql ="INSERT INTO usuario(nome, usuario, senha, foto) VALUES(?, ?, ?, ?);";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setBytes(1, nome.getBytes());
				ps.setBytes(2, usuario.getBytes());
				ps.setBytes(3, senha.getBytes());
				ps.setBinaryStream(4, foto.getInputstream());
				ps.execute();
				con.close();
				FacesMessage message = new FacesMessage("Exito", nome.getBytes() + " Deu certo");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}catch(Exception e) {
			FacesMessage message = new FacesMessage("Deu errado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public UploadedFile getFoto() {
		return foto;
	}
	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}
	
}
