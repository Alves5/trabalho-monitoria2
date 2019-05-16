package controle;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.primefaces.model.UploadedFile;

import modelo.ImagensModelo;

@WebServlet("/ImagemServ")
public class ImagemServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImagemServ() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Resource(name="jdbc/projetc")
    private DataSource ds;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ImagensControle cont = new ImagensControle();
			ImagensModelo mod = new ImagensModelo();
			String file = request.getParameter("file");
			File f = new File (file);
			mod.setFile((UploadedFile) f);
			mod.setNome(request.getParameter("nome"));
			mod.setDescrisao(request.getParameter("descrisao"));
			if(cont.insertImagem(ds, mod)) {
				response.getWriter().print("Deu Ok");
			}else {
				response.getWriter().print("Não Ok");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
