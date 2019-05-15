package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class ImagensModelo {
	private int id;
	private UploadedFile file;
	private String nome;
	private String descrisao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
		public void upload() {
			try {
				if(file != null) {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
					PreparedStatement ps = con.prepareStatement("INSERT INTO imagens(imagem) VALUES(?);");
					ps.setBinaryStream(1, file.getInputstream());
					ps.execute();
					con.close();
					FacesMessage message = new FacesMessage("Exito", file.getFileName() + "Deu certo");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			}catch(Exception e) {
				FacesMessage message = new FacesMessage("Deu errado");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
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
