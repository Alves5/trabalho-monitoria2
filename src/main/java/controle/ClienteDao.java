package controle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;

public class ClienteDao{
    public void salvar(Cliente cliente){
        try{
        	Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps;
	        	ps = con.prepareStatement("INSERT INTO filmes(titulo, descricao, foto, video, genero) VALUES(?, ? ,?, ?, ?);");
	        ps.setString(1, cliente.getTitulo());
	        ps.setString(2, cliente.getDescricao());
	        ps.setString(3, cliente.getFotoS());
	        ps.setString(4, cliente.getVideoS());
	        ps.setString(5, cliente.getGenero());
	        ps.execute();
	        con.close();
        }catch(Exception e){
            System.out.print("Algo deu errado 1 "+ e.getMessage());
        }
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