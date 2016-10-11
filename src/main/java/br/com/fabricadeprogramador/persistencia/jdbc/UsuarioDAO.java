package br.com.fabricadeprogramador.persistencia.jdbc;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

// O objeto DAO possui connection, statment e resultset. 
// Ele que irá persistir os dados do usuario no banco.
// É aqui onde criamos os métodos.
public class UsuarioDAO {
	
    private Connection con = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usu) {
		
	    String sql	= "insert into usuario (nome,login,senha) values (?,?,md5(?))";
		
	    try {
	    	
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setString(1,usu.getNome()); // Cada linha está substituindo o ? pelo dado do usuário
			preparador.setString(2,usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			//Executando o sql no banco
			preparador.execute();
			//Encerrando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
    
	}

	public void alterar(Usuario usu) {
		
		String sql	= "update usuario set nome=?, login=?, senha=md5(?) where id=? ";
		
	    try {
	    	
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setString(1,usu.getNome()); // Cada linha está substituindo o ? pelo dado do usuário
			preparador.setString(2,usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			//Executando o sql no banco
			preparador.execute();
			//Encerrando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}

	public void excluir(Usuario usu) {
		
		String sql	= "delete from usuario where id=? ";
		
	    try {
	    	
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setInt(1, usu.getId());
			
			//Executando o sql no banco
			preparador.execute();
			//Encerrando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	public void salvar(Usuario usuario){
		
		if(usuario.getId() != null && usuario.getId()!=0 ){
			
			alterar(usuario);
			
		} else {
			
			cadastrar(usuario);
			
		}
		
	}
	
	/**
	 * Busca um registro pelo id do usuário
	 * @param id é um inteiro que representa o número do id do usuário a ser buscado
	 * @return um objeto usuario quando encontra e nulo quando não encontra
	 */

	// Método busca por ID.
	public Usuario buscaPorId(Integer id) {
		
		//Declarar uma string contendo a instrução sql.
		String sql = "select * from usuario where id=?";
		
		// Para usar o comando sql é necessário um preparador.
		
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);
			
		ResultSet resultado = preparador.executeQuery();
		//Posicionando o cursor
		if(resultado.next()){
			
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			
			return usuario;
			
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<Usuario> buscarTodos() {

		String sql = "select * from usuario";
		List<Usuario> lista = new ArrayList<Usuario>();
		try (PreparedStatement preparador = con.prepareStatement(sql)){
		
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				//Adicionando usuários nas listas
				lista.add(usuario);
				
			}
			
		 } catch (SQLException e) {
			 
			 e.printStackTrace();
			 
		 }
		
		 return lista;
		
	   }
	
		public Usuario autenticar(Usuario usuConsulta){
	
			String sql = "select * from usuario where login=? and senha=md5(?)";
			
			try(PreparedStatement preparador = con.prepareStatement(sql)){
			
				preparador.setString(1, usuConsulta.getLogin());
				preparador.setString(2, usuConsulta.getSenha());
				ResultSet resultado = preparador.executeQuery();
				
				if (resultado.next()){
				
				Usuario usuario = new Usuario();
				
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
				
				}
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			
			 return null;			
		}
	}
