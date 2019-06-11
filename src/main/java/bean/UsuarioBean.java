package bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.UsuarioModelo;
import controle.UsuarioControle;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UsuarioModelo usuario;
	
	public UsuarioBean() {
		usuario = new UsuarioModelo();
	}
	
	
	public String veri() throws IOException {
		if(new UsuarioControle().verificarUser(usuario)) {
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.setAttribute("usuario", usuario);
			
			return "/admin/testeAdmin?faces-redirect=true";
		}else {
			return "/seguranca/Logar?faces-redirect=true";
		}
	}
	public String logout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/seguranca/Logar?faces-redirect=true";
	}
	
	public UsuarioModelo getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModelo usuario) {
		this.usuario = usuario;
	}
	
}
