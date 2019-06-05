package bean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;


@ManagedBean(name = "mBeanCliente")
public class MBeanCliente {

	//private int id;
	//private String nome;
	private Part image;
	//private List<Cliente> clientes;

	public void upload() {
			try {
				InputStream in = image.getInputStream();
				File f = new File("/home/daniel/Imagens/temp/"+image.getSubmittedFileName());
				f.createNewFile();
				FileOutputStream out = new FileOutputStream(f);
				String caminho = f.toString();
				byte[] buffer  = new byte[1024];
				int lenght;
				while((lenght=in.read(buffer)) > 0) {
					out.write(buffer, 0, lenght);
				}
				out.close();
				in.close();
				
				Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
		        PreparedStatement ps = con.prepareStatement("INSERT INTO cliente(image) VALUES(?);");
		        ps.setString(1, caminho);
		        ps.execute();
		        ps.close();
			}catch(Exception e) {
				System.out.println("Aqui 1"+e.getMessage());
			}
	}

		/*public void salvar() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
		        PreparedStatement ps = con.prepareStatement("INSERT INTO cliente(nome, image) VALUES(?, ?);");
		        ps.setString(1, nome);
		        ps.setBinaryStream(2, image);
		        ps.execute();
		        ps.close();
			}catch(Exception e) {
				System.out.println("Aqui 2"+e.getMessage());
			}
			
		}*/

	/*public void alterar(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}*/

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}
}