package controle;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public class ClienteDao{
	public ClienteDao() {
		super();
	}
	
    public boolean salvar(Cliente cliente) throws ClassNotFoundException, SQLException, FileNotFoundException{
        	Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps;
	        if(cliente.getId() == null) {
	        	ps = con.prepareStatement("INSERT INTO filmes(titulo, descricao, foto, video, genero) VALUES(?, ? ,?, ?, ?);");
	        }else {
	        	ps = con.prepareStatement("UPDATE filmes SET titulo=?, descricao=?, foto=?, video=?, genero=? WHERE id=?;");
	        	ps.setInt(6, cliente.getId());
	        }
	        	
	        ps.setString(1, cliente.getTitulo());
	        ps.setString(2, cliente.getDescricao());
	        ps.setString(3, cliente.getFotoS());
	        ps.setString(4, cliente.getVideoS());
	        ps.setString(5, cliente.getGenero());
	        int toReturn = ps.executeUpdate();
			con.close();	
			return toReturn > 0;
    }
    public List<Cliente> listar(){
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM filmes");
	        ResultSet rs = ps.executeQuery();
	        List<Cliente> lista = new ArrayList<>();
	        if(rs != null) {
	        	while(rs.next()) {
	        		Cliente cliente = new Cliente();
	        		cliente.setId(rs.getInt("id"));
	        		cliente.setTitulo(rs.getString("titulo"));
	        		cliente.setDescricao(rs.getString("descricao"));
	        		cliente.setFotoS(rs.getString("foto"));
	        		cliente.setVideoS(rs.getString("video"));
	        		cliente.setGenero(rs.getString("genero"));
	        		lista.add(cliente);
	        	}
	        }
	        return lista;
    	}catch(Exception e) {
    		System.out.println("Deu erro aqui 4"+e.getMessage());
    		return null;
    	}
    }
}