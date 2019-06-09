package controle;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ConfFL;

public class ConfFLControle {
	public ConfFLControle() {
		super();
	}
	
	public boolean editarConf(ConfFL conf) throws SQLException, ClassNotFoundException, FileNotFoundException{
		boolean resultado = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps = con.prepareStatement("UPDATE confFL SET imageHeaderF=?, imageHeaderL=?, logoSite=?, pint=?, twi=?, face=? WHERE id=?");
	        ps.setString(1, conf.getImageHeaderF());
	        ps.setString(2, conf.getImageHeaderL());
	        ps.setString(3, conf.getLogoSite());
	        ps.setString(4, conf.getPint());
	        ps.setString(5, conf.getTwi());
	        ps.setString(6, conf.getFace());
	        ps.setInt(7, conf.getId());
	        ps.executeUpdate();
	        con.close();
	        resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	       return resultado;
	}
	
	public List<ConfFL> buscar(){
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM confFL");
	        ResultSet rs = ps.executeQuery();
	        List<ConfFL> lista = new ArrayList<>();
	        if(rs != null) {
	        	while(rs.next()) {
	        		ConfFL mod = new ConfFL();
	        		mod.setId(rs.getInt("id"));
	        		mod.setImageHeaderF(rs.getString("imageHeaderF"));
	        		mod.setImageHeaderL(rs.getString("imageHeaderL"));
	        		mod.setLogoSite(rs.getString("logoSite"));
	        		mod.setPint(rs.getString("pint"));
	        		mod.setTwi(rs.getString("twi"));
	        		mod.setFace(rs.getString("face"));
	        		lista.add(mod);
	        	}
	        }
	        return lista;
    	}catch(Exception e) {
    		System.out.println("Deu erro aqui 3"+e.getMessage());
    		return null;
    	}
	}
}
