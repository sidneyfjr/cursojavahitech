package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;


@WebServlet("/autenticador.do")
public class AutenticadorController extends HttpServlet {
	
	@Override
	// Proteger a aplicação de requisições pelo get (pela url)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// O parâmetro false faz com que ele não crie uma sessão, apenas capture caso exista.
		HttpSession sessao = req.getSession(false);
		
		if(sessao!=null){
			
			sessao.invalidate();
			
		}
		
		resp.sendRedirect("login.html");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp )
		throws ServletException, IOException {
		// 1) Capturando dados da tela.
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		// (2 Colocando dados em objeto Usuario
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		// (3 Consultando se usuario existe no banco
		UsuarioDAO usuarioDAO  = new UsuarioDAO();
		Usuario usuAutenticado = usuarioDAO.autenticar(usuario);
		// (4 Verificando se usuario foi encontrado
		if (usuAutenticado != null) {
			// 5) Colocando usuário na sessão
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			
			sessao.setMaxInactiveInterval(60*5);
			// (6 Redirecionando usuário para tela principal.
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
			
		} else {
			
			resp.getWriter().print("<script>window.alert('Não encontrado!');location.href='login.html';</script>");
		}
	 
	}

}
