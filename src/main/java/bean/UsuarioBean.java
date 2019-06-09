package bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import modelo.UsuarioModelo;
import controle.UsuarioControle;

@ManagedBean
public class UsuarioBean {
	 
	private UsuarioModelo modelo = new UsuarioModelo();
	
	public void veri() throws IOException {
		if(new UsuarioControle().verificarUser(modelo)) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("testeTextos.jsf");
		}else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Logar.jsf");
		}
	}

	public UsuarioModelo getModelo() {
		return modelo;
	}

	public void setModelo(UsuarioModelo modelo) {
		this.modelo = modelo;
	}
	
}
