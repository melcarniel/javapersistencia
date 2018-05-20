package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

public abstract class Dao<T> {
	//elementos de acesso a dados
	protected Connection cn;			//mant�m a conex�o com o bd
	protected PreparedStatement stmt;	//permite opera��es no bd
	protected ResultSet rs;				//referencia a consulta
	
	//string de conexao jdbc - mysql
	private String conexao = "jdbc:mysql://localhost:3306/dbeventos";
	
	public void abrirConexao() throws Exception{
		//Especificar o driver de acesso
		Class.forName("com.mysql.jdbc.Driver");
		
		//estabelecer a conex�o com o banco de dados
		cn = DriverManager.getConnection(conexao, "root", "1234");
		
	}
	
	public void fecharConexao() throws Exception{
		if(cn != null && !cn.isClosed()) {
			cn.close();
		}		
	}
	
	public abstract void incluir(T elemento) throws Exception;
	public abstract T buscar(int id) throws Exception;
	public abstract Set<T> listar(int... param) throws Exception;

	/*
	 * varargs:
	 * 
	 * listar()
	 * listar(1)
	 * listar(2,4,5,3,1,7)
	 */

}