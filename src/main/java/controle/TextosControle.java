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
				ps = con.prepareStatement("INSERT INTO textos(titulo, paragrafo) VALUES(?, ?);");
			}else {
				ps = con.prepareStatement("UPDATE textos SET titulo=?, paragrafo=? WHERE id=?;");
				ps.setInt(3, tm.getId());
			}
			ps.setString(1, tm.getTitulo());
			ps.setString(2, tm.getParagrafo());
			int toReturn = ps.executeUpdate();
			con.close();	
		return toReturn > 0;
	}
	
	
	public void deletarTextos(Integer id) throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
			PreparedStatement ps = con.prepareStatement("DELETE FROM textos WHERE id=?;");
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
			String sql ="SELECT * FROM textos;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<TextosModelo> textos = new ArrayList<>();
			while(rs.next()) {
				TextosModelo texto = new TextosModelo();
				texto.setId(rs.getInt("id"));
				texto.setTitulo(rs.getString("titulo"));
				texto.setParagrafo(rs.getString("paragrafo"));
				textos.add(texto);
			}
			return textos;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
