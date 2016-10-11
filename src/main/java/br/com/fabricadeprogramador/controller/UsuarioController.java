package br.com.fabricadeprogramador.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;
//http://localhost:8080/fabricaweb/usucontroller.do
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	
	public UsuarioController() {
		System.out.println("Construtor..");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("Init..");
		super.init();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		String acao = req.getParameter("acao");
		if (acao.equals("exc")) {
		String id = req.getParameter("id");
		Usuario usu = new Usuario();
		if(id != null)
			usu.setId(Integer.parseInt(id));
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usu);
		
		//resp.getWriter().println("Excluído com sucesso!");
		//O sendRedirect responde para o browser pedindo a ele para fazer outra requisição para a página "usucontroller.do?acao=lis"
		resp.sendRedirect("usucontroller.do?acao=lis");
		
	} else if (acao.equals("lis")){
			//Implementar lista
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodos();
		//for (Usuario u: lista){
		//resp.getWriter().print(u.getNome() + "<br>");
		//}
		req.setAttribute("lista", lista);
		// O getRequestDispatcher cria uma instância do RequestDispatcher, ele é que faz o redirecionamento.
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
		//Redirecionando o req e resp de um servlet para o jsp.
		dispatcher.forward(req,resp);
		
	  } else if (acao.equals("alt")) {
		  
		  String id = req.getParameter("id");
		  UsuarioDAO usuarioDAO = new UsuarioDAO();
		  Usuario usuario = usuarioDAO.buscaPorId(Integer.parseInt(id));
		  req.setAttribute("usu", usuario);
		  RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
		  dispatcher.forward(req, resp);
		  
	  } else if (acao.equals("cad")) {
		  
		  Usuario usuario = new Usuario();
		  usuario.setId(0);
		  usuario.setNome("");
		  usuario.setLogin("");
		  usuario.setSenha("");
		  
		  req.setAttribute("usu",usuario);
		  RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
		  dispatcher.forward(req, resp);
		  
	  }
				
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		
		String id    = req.getParameter("id");
		String nome  = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usu = new Usuario();
		
		if(id != null)
			usu.setId(Integer.parseInt(id));
		
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usu);
		
		resp.getWriter().println("<h1>Sucesso!</h1>");
		
	}

	public void destroy(){
		System.out.println("Destroy..");
		super.destroy();
	}
}
