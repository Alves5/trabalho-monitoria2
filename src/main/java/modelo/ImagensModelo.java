package modelo;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;



@ManagedBean
public class ImagensModelo {
	private int id;
	private UploadedFile file;
	private String nome;
	private String descrisao;
	
	
	public String cifrar(String texto) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String resultado = null;
			 Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		     byte[] mensagem = texto.getBytes();

		     // Usando chave de 128-bits (16 bytes)
		     byte[] chave = "chave de 16bytes".getBytes();

		     // Encriptando...
		     try {
				cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(chave, "AES"));
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     byte[] encrypted = cipher.doFinal(mensagem);
		     String resto = Base64.getEncoder().encodeToString(encrypted);
		     resultado = resto;
		return resultado;
	}
	public String decifrar(String texto) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		String resultado = null;
		 Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    String textoB = new String(Base64.getDecoder().decode(texto));
	    byte[] mensagem = textoB.getBytes();

	    // Usando chave de 128-bits (16 bytes)
	    byte[] chave = "chave de 16bytes".getBytes();
	    
	    // Decriptando...
	    cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chave, "AES"));
	    byte[] decrypted = cipher.doFinal(mensagem);
	    resultado = new String(decrypted);
	    return resultado;
	}

	
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
					PreparedStatement ps = con.prepareStatement("INSERT INTO teste(imagem, nome, descrisao) VALUES(?, ?, ?);");
					ps.setBinaryStream(1, file.getInputstream());
					String nomeC = cifrar(nome);
					String descrisaoC = cifrar(descrisao);
					ps.setBytes(2, nomeC.getBytes());
					ps.setBytes(3, descrisaoC.getBytes());
					ps.execute();
					con.close();
					FacesMessage message = new FacesMessage("Exito", file.getFileName() + " Deu certo");
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
