package bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import modelo.TextosModelo;
import controle.TextosControle;

@ManagedBean
@SessionScoped
public class TextosBean {


private Part foto;
private Part video;
private TextosModelo texto = new TextosModelo();
private List<TextosModelo> textos = new ArrayList<>();
List<TextosModelo> textos2 = new TextosControle().buscar();
List<TextosModelo> tres = new TextosControle().pegar();


public void adicionar() throws IOException, ClassNotFoundException {
	try {
		InputStream in = foto.getInputStream();
		File f = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/seguranca/img/uploads/foto/"+foto.getSubmittedFileName());
		InputStream in2 = video.getInputStream();
		File f2 = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/seguranca/img/uploads/video/"+video.getSubmittedFileName());
		f.createNewFile();
		f2.createNewFile();
		FileOutputStream out = new FileOutputStream(f);
		FileOutputStream out2 = new FileOutputStream(f2);
		String fotoString = f.toString();
		texto.setFotoS(fotoString.substring(61));
		String videoString = f2.toString();
		texto.setVideoS(videoString.substring(61));
		
		byte[] buffer  = new byte[1024];
		int lenght;
		while((lenght=in.read(buffer)) > 0) {
			out.write(buffer, 0, lenght);
		}
		
		byte[] buffer2  = new byte[1024];
		int lenght2;
		while((lenght2=in2.read(buffer2)) > 0) {
			out2.write(buffer2, 0, lenght2);
		}
		out.close();
		out2.close();
		in.close();
		in2.close();
		new TextosControle().inserirTextos(texto);
		FacesMessage message = new FacesMessage("Exito", texto.getTitulo() + " filme inserido! Agora faça a listagem.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (SQLException e) {
		FacesMessage message = new FacesMessage("ERRO AO INSERIR.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	texto = new TextosModelo();
}
public void listarT() {
	try {
		textos = new TextosControle().buscar();
		if(textos == null || textos.size() == 0) {
			FacesMessage message = new FacesMessage("Nada foi encontrado!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
}
public void editarT(TextosModelo tm) {
	texto = tm;
}
public void deletarT(TextosModelo tm){
	try {
		new TextosControle().deletarTextos(tm.getId());
		this.editarT(tm);
		FacesMessage message = new FacesMessage("Exito", tm.getTitulo() + " Deletado!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	} catch (SQLException e) {
		FacesMessage message = new FacesMessage("ERRO AO DELETAR.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}

public TextosModelo getTexto() {
	return texto;
}
public void setTexto(TextosModelo texto) {
	this.texto = texto;
}
public List<TextosModelo> getTextos() {
	
	return textos;
}
public void setTextos(List<TextosModelo> textos) {
	this.textos = textos;
}
public List<TextosModelo> getTextos2(){
	return textos2;
}
public void setTextos2(List<TextosModelo> textos2) {
	this.textos2 = textos2;
}
public List<TextosModelo> getTres() {
	return tres;
}
public void setTres(List<TextosModelo> tres) {
	this.tres = tres;
}

public Part getFoto() {
	return foto;
}
public void setFoto(Part foto) {
	this.foto = foto;
}
public Part getVideo() {
	return video;
}
public void setVideo(Part video) {
	this.video = video;
}

	
}
