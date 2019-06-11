package bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import controle.ConfHomeControle;
import modelo.ConfHome;

@ManagedBean
@SessionScoped
public class HomeBean {

	private Part imageH;
	private Part imageH2;
	private ConfHome conf = new ConfHome();
	private List<ConfHome> lista = new ConfHomeControle().listar();
	
	public void editar() throws ClassNotFoundException, IOException{
		try {
			InputStream in = imageH.getInputStream();
			InputStream in2 = imageH2.getInputStream();
			
			File f = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/seguranca/img/uploads/confHome/"+imageH.getSubmittedFileName());
			File f2 = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/seguranca/img/uploads/confHome/"+imageH2.getSubmittedFileName());
			
			f.createNewFile();
			f2.createNewFile();
			
			FileOutputStream out = new FileOutputStream(f);
			FileOutputStream out2 = new FileOutputStream(f2);
			
			String Homestring = f.toString();
			conf.setImageHeaderH(Homestring.substring(61));
			String Home2string = f2.toString();
			conf.setImageHeaderH2(Home2string.substring(61));
			
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
			
			new ConfHomeControle().editar(conf);
			FacesMessage message = new FacesMessage("Exito", conf.getTitulo() + " Edição concluída!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch(Exception e) {
			System.out.println("nesse aqui"+e.getMessage());
		}
	} 
	
	
	
	public Part getImageH() {
		return imageH;
	}
	public void setImageH(Part imageH) {
		this.imageH = imageH;
	}
	public Part getImageH2() {
		return imageH2;
	}
	public void setImageH2(Part imageH2) {
		this.imageH2 = imageH2;
	}
	public ConfHome getConf() {
		return conf;
	}
	public void setConf(ConfHome conf) {
		this.conf = conf;
	}

	public List<ConfHome> getLista() {
		return lista;
	}

	public void setLista(List<ConfHome> lista) {
		this.lista = lista;
	}
	
	
}
