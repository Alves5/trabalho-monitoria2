package bean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import controle.ClienteDao;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;




@ManagedBean(name = "mBeanCliente")
public class MBeanCliente {
	//Para receber as fotos
	private Part foto;
	private Part video;
	private Cliente cliente = new Cliente();
	private List<Cliente> lista = new ArrayList<>();
	

	public void upload() {
			try {
				InputStream in = foto.getInputStream();
				File f = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/img/uploads/foto/"+foto.getSubmittedFileName());
				InputStream in2 = video.getInputStream();
				File f2 = new File("/home/daniel/Documentos/CreateEdit/src/main/webapp/img/uploads/video/"+video.getSubmittedFileName());
				f.createNewFile();
				f2.createNewFile();
				FileOutputStream out = new FileOutputStream(f);
				FileOutputStream out2 = new FileOutputStream(f2);
				String fotoString = f.toString();
				cliente.setFotoS(fotoString.substring(51));
				String videoString = f2.toString();
				cliente.setVideoS(videoString.substring(51));
				
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
				ClienteDao clienteDao = new ClienteDao();
				clienteDao.salvar(cliente);
			}catch(Exception e) {
				System.out.println("Aqui 1"+e.getMessage());
			}
	}

	public void buscar() {
		try {
			lista = new ClienteDao().listar();
		}catch(Exception e) {
			System.out.println("Deu erro aqui no Bean2"+e.getMessage());
		}
	}
	
	public void editarT(Cliente cl) {
		cliente = cl;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
	
}