package controle;



import java.util.Base64;


public class Encriptacao {

public String encrip(String texto) {
	String toReturn = null;
		try {
			
			String textoCodificado = Base64.getEncoder().encodeToString(texto.getBytes());
			toReturn = textoCodificado;
			
		}catch(Exception e) {
			System.out.println("Erro em criptografar"+e.getMessage());
			toReturn = null;
		}
		return toReturn;
}
public String descrip(String texto) {
	String toReturn = null;
	try {
		
		byte[] decodificado = Base64.getDecoder().decode(texto.getBytes());
		String textoDecodificado = new String(decodificado);
		toReturn = textoDecodificado;
		
	}catch(Exception e) {
		System.out.println("Erro em descriptografar"+e.getMessage());
		toReturn = null;
	}
	return toReturn;
}
	


}
