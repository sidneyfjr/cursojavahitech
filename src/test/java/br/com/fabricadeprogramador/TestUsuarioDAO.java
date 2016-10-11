package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

// É aqui onde testamos os métodos criados no UsuarioDAO.

public class TestUsuarioDAO { 

	public static void main(String[] args) {
		
		testAutenticar();
		
	}
	
	public static void testExcluir(){
		
		//Criando o usuário
		Usuario usu = new Usuario();
		usu.setId(4);
		
		//Excluir usuário no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluído com sucesso!");			
		
	} 
	
	public static void testAlterar(){
		
		//Criando o usuário
		Usuario usu = new Usuario();
		usu.setId(4);
		usu.setNome("jãozão da Silva");
		usu.setLogin("jzaoss");
		usu.setSenha("123456");
		
		//Alterar usuário no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso!");			
		
	} 
	
	public static void testCadastrar(){
		
		//Criando o usuário
		Usuario usu = new Usuario();
		usu.setNome("jãozão");
		usu.setLogin("jzao");
		usu.setSenha("123");
		
		//Cadastrando usuário no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso!");			
		
	} 
	
	public static void testSalvar(){
		
		Usuario usuario = new Usuario();
		//usuario.setId(2);
		usuario.setNome("Virmerson");
		usuario.setLogin("vir");
		usuario.setSenha("123456");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);
		
		System.out.println("Salvo com sucesso!");
		
	}
	
	private static void testBuscarPorId() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscaPorId(5);
		System.out.println(usuario);
		
	}
	
private static void testBuscarTodos() {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodos();
		for (Usuario u : lista) {
		System.out.println(u);
		
	}

  }

	private static void testAutenticar(){
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usu = new Usuario();
		usu.setLogin("jao");
		usu.setSenha("123");
		
		Usuario usuRetorno = usuarioDAO.autenticar(usu);
		System.out.println(usuRetorno); 
	}

}
