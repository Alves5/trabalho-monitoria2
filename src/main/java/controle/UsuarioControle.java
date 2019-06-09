package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import modelo.UsuarioModelo;

public class UsuarioControle {
	public boolean insertUser(UsuarioModelo um){
		boolean resultado = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
			String sql ="UPDATE usuario SET usuario=?, senha=? WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, um.getUsuario());
			ps.setString(2, um.getSenha());
			ps.setInt(3, um.getId());
			ps.executeUpdate();
			con.close();
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	public boolean verificarUser() {
		boolean resultado = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}
}
