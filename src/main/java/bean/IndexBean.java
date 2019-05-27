package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class IndexBean {
	
	public String pagina(String link) {
		String resultado = null;
		switch(link) {
			case "home":
				resultado = "teste.xhtml";
				break;
			case "filmes":
				resultado = "Filmes.xhtml";
				break;
			case "perfil":
				resultado = "Perfil.xhtml";
				break;
			default:
				resultado = "Error.xhtml";
		}
		return resultado;
	}
}
