package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.primefaces.model.UploadedFile;
import modelo.ImagensModelo;

public class ImagensControle {
	public ArrayList<ImagensModelo> consultaCartoes() throws SQLException, ClassNotFoundException{
		ArrayList<ImagensModelo> lista = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
		String sql = "SELECT * FROM teste;";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			lista = new ArrayList<ImagensModelo>();
			while(rs.next()){
				ImagensModelo mode = new ImagensModelo();
				mode.setId(rs.getInt("id"));
				mode.setFile((UploadedFile) rs.getBinaryStream("imagem"));
				mode.setNome(rs.getString("nome"));
				mode.setDescrisao(rs.getString("descrisao"));
				lista.add(mode);
			}
		}else {
			throw new SQLException("Erro ao buscar informações.");
		}
		con.close();
		return lista;
	}


}