package bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import modelo.ConfFL;
import controle.ConfFLControle;

@ManagedBean
public class ConfBean {
	
	public int id = 1;
	private Part imageHF;
	private Part imageHL;
	private Part logo;
	private ConfFL conf = new ConfFL();
	private List<ConfFL> lista = new ConfFLControle().buscar();
	
	public void editar() throws ClassNotFoundException, IOException{
		try {
			InputStream in = imageHF.getInputStream();
			InputStream in2 = imageHL.getInputStream();
			InputStream in3 = logo.getInputStream();
			
			File f = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/img/uploads/confHFL/"+imageHF.getSubmittedFileName());
			File f2 = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/img/uploads/confHFL/"+imageHL.getSubmittedFileName());
			File f3 = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/img/uploads/confHFL/"+logo.getSubmittedFileName());
			
			f.createNewFile();
			f2.createNewFile();
			f3.createNewFile();
			
			FileOutputStream out = new FileOutputStream(f);
			FileOutputStream out2 = new FileOutputStream(f2);
			FileOutputStream out3 = new FileOutputStream(f3);
			
			conf.setId(id);
			String HFstring = f.toString();
			conf.setImageHeaderF(HFstring.substring(51));
			String HLstring = f2.toString();
			conf.setImageHeaderL(HLstring.substring(51));
			String logoString = f3.toString();
			conf.setLogoSite(logoString.substring(51));
			
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
			
			byte[] buffer3  = new byte[1024];
			int lenght3;
			while((lenght3=in3.read(buffer3)) > 0) {
				out3.write(buffer3, 0, lenght3);
			}
			out.close();
			out2.close();
			out3.close();
			in.close();
			in2.close();
			in3.close();
			
			new ConfFLControle().editarConf(conf);
			FacesMessage message = new FacesMessage("Exito", conf.getImageHeaderF() + " Edição concluída!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch(Exception e) {
			System.out.println("nesse aqui"+e.getMessage());
		}
	} 
	
	public ConfFL getConf() {
		return conf;
	}
	public void setConf(ConfFL conf) {
		this.conf = conf;
	}

	public Part getImageHF() {
		return imageHF;
	}

	public void setImageHF(Part imageHF) {
		this.imageHF = imageHF;
	}

	public Part getImageHL() {
		return imageHL;
	}

	public void setImageHL(Part imageHL) {
		this.imageHL = imageHL;
	}

	public Part getLogo() {
		return logo;
	}

	public void setLogo(Part logo) {
		this.logo = logo;
	}

	public List<ConfFL> getLista() {
		return lista;
	}

	public void setLista(List<ConfFL> lista) {
		this.lista = lista;
	}	
	
	
}
