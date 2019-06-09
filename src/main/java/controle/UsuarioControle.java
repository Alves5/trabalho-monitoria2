package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public boolean verificarUser(UsuarioModelo mod) {
		boolean resultado = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
			String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, mod.getUsuario());
			ps.setString(2, mod.getSenha());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				resultado = true;
			}else {
				resultado = false;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}
}
