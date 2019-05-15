package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import modelo.UsuarioModelo;

public class UsuarioControle {
	public boolean insertUser(DataSource ds, UsuarioModelo um) throws SQLException{
		boolean resultado = false;
		
		Connection con = ds.getConnection();
		String sql ="INSERT INTO usuario(nome, usuario, senha, foto, tipo) VALUES(?, ?, ?, ?, ?);";
		PreparedStatement ps = con.prepareStatement(sql);
		
		return resultado;
	}
}
