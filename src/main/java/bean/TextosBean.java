package bean;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import modelo.TextosModelo;
import controle.TextosControle;

@ManagedBean
@SessionScoped
public class TextosBean {

@Resource(name="jdbc/project")
private DataSource ds;

private TextosModelo texto = new TextosModelo();
private List<TextosModelo> textos = new ArrayList<>();


public void adicionar() throws ClassNotFoundException, FileNotFoundException{
	try {
		new TextosControle().inserirTextos(texto);
		FacesMessage message = new FacesMessage("Exito", texto.getTitulo() + " texto inserido!");
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

	
}
