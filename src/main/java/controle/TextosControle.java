package controle;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.TextosModelo;

public class TextosControle {
	public TextosControle() {
		super();
	}
	
	public boolean inserirTextos(TextosModelo tm) throws SQLException, ClassNotFoundException, FileNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
        PreparedStatement ps;
        if(tm.getId() == null) {
        	ps = con.prepareStatement("INSERT INTO filmes(titulo, descricao, foto, video, genero) VALUES(?, ? ,?, ?, ?);");
        }else {
        	ps = con.prepareStatement("UPDATE filmes SET titulo=?, descricao=?, foto=?, video=?, genero=? WHERE id=?;");
        	ps.setInt(6, tm.getId());
        }
        	
        ps.setString(1, tm.getTitulo());
        ps.setString(2, tm.getDescricao());
        ps.setString(3, tm.getFotoS());
        ps.setString(4, tm.getVideoS());
        ps.setString(5, tm.getGenero());
        int toReturn = ps.executeUpdate();
		con.close();
		return toReturn > 0;
	}
	
	
	public void deletarTextos(Integer id) throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
			PreparedStatement ps = con.prepareStatement("DELETE FROM filmes WHERE id=?;");
			ps.setInt(1, id);
			ps.execute();
			con.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<TextosModelo> buscar(){
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM filmes");
	        ResultSet rs = ps.executeQuery();
	        List<TextosModelo> lista = new ArrayList<>();
	        if(rs != null) {
	        	while(rs.next()) {
	        		TextosModelo mod = new TextosModelo();
	        		mod.setId(rs.getInt("id"));
	        		mod.setTitulo(rs.getString("titulo"));
	        		mod.setDescricao(rs.getString("descricao"));
	        		mod.setFotoS(rs.getString("foto"));
	        		mod.setVideoS(rs.getString("video"));
	        		mod.setGenero(rs.getString("genero"));
	        		lista.add(mod);
	        	}
	        }
	        return lista;
    	}catch(Exception e) {
    		System.out.println("Deu erro aqui 4"+e.getMessage());
    		return null;
    	}
	}
	
	public List<TextosModelo> pegar(){
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM filmes WHERE (id <= 3) OR id > 3 AND id <= 6 OR id > 6 AND id <= 9;");
	        ResultSet rs = ps.executeQuery();
	        List<TextosModelo> lista = new ArrayList<>();
	        if(rs != null) {
	        	while(rs.next()) {
	        		TextosModelo mod = new TextosModelo();
	        		mod.setId(rs.getInt("id"));
	        		mod.setTitulo(rs.getString("titulo"));
	        		mod.setDescricao(rs.getString("descricao"));
	        		mod.setFotoS(rs.getString("foto"));
	        		mod.setVideoS(rs.getString("video"));
	        		mod.setGenero(rs.getString("genero"));
	        		lista.add(mod);
	        	}
	        }
	        return lista;
    	}catch(Exception e) {
    		System.out.println("Deu erro aqui 4"+e.getMessage());
    		return null;
    	}
	}
}
