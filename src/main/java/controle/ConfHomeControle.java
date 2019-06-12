package controle;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.ConfHome;

public class ConfHomeControle {
	public boolean editar(ConfHome ch) throws SQLException, ClassNotFoundException, FileNotFoundException{
			boolean resultado = false;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
		        PreparedStatement ps;
		        ps = con.prepareStatement("UPDATE confHome SET imageHeaderH=?, imageHeaderH2=?, titulo=? WHERE id=?;");
		        int id = 1;
		        ps.setString(1, ch.getImageHeaderH());
		        ps.setString(2, ch.getImageHeaderH2());
		        ps.setString(3, ch.getTitulo());
		        ps.setInt(4, id);
		        
		        ps.executeUpdate();
		        con.close();
		        resultado = true;
			}catch(Exception e) {
				System.out.println("Nesse da homi"+e.getMessage());
				resultado = false;
			}
			
	        return resultado;
	}
	
	public List<ConfHome> listar(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM confHome;");
	        ResultSet rs = ps.executeQuery();
	        List<ConfHome> lista = new ArrayList<>();
	        if(rs != null) {
	        	while(rs.next()) {
	        		ConfHome co = new ConfHome();
	        		co.setId(rs.getInt("id"));
	        		co.setImageHeaderH(rs.getString("imageHeaderH"));
	        		co.setImageHeaderH2(rs.getString("imageHeaderH2"));
	        		co.setTitulo(rs.getString("titulo"));
	        		lista.add(co);
	        	}
	        }
	        return lista;
		}catch(Exception e) {
			System.out.println("aqui na Conf home"+e.getMessage());
			return null;
		}

	}
}
