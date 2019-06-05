package controle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import modelo.Cliente;

public class ClienteDao{
    public void salvar(Cliente cliente){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS?user=root&password=123");
            PreparedStatement ps = con.prepareStatement("INSERT INTO cliente(nome, caminhoImagem) VALUES(?, ?);");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCaminhoImagem());
            ps.execute();
            ps.close();
        }catch(Exception e){
            System.out.print("Algo deu errado 1 "+ e.getMessage());
        }
    }
    /*public List<Cliente> listar(){
    	
    }*/
    public void buscar(){

    }
}