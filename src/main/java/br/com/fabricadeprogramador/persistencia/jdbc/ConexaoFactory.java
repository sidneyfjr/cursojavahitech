package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Essa classe possui um driver manager e um connect para fazer a conexão no banco.

public class ConexaoFactory {

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb","postgres","123");
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}

}
