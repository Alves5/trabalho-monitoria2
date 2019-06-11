package controle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.UsuarioModelo;

@WebFilter(filterName = "FilterLogar", urlPatterns =  {"/seguranca/*"})
public class FilterLogar implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = (HttpSession)req.getSession();
		
		UsuarioModelo usuario = (UsuarioModelo)session.getAttribute("usuario");
		
		if(usuario == null) {
			chain.doFilter(request, response);
		}else {
			res.sendRedirect(req.getContextPath() + "/admin/testeAdmin.jsf");
		}
		
	}

}
