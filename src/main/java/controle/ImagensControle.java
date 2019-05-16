package controle;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import modelo.ImagensModelo;

public class ImagensControle {
	public boolean insertImagem(DataSource ds, ImagensModelo im) throws SQLException {
		boolean resultado = false;
		Connection con = ds.getConnection();
		String sql = "INSERT INTO imagens(imagem, nome, descrisao) VALUES(?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBinaryStream(1, (InputStream) im.getFile());
		ps.setString(2, im.getNome());
		ps.setString(3, im.getDescrisao());
		if (ps.execute()) {
			resultado = true;
		} else {
			throw new SQLException("Problma em inserir informações.");
		}
		con.close();
		return resultado;
	}

}