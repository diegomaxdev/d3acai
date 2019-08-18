package ConexaoBancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class parametroConexao {
	
	public static String conexao;
	public static String login;
	public static String senha;
	
	public static boolean existeBase;
	public static boolean isExisteBase(){return existeBase;}
	public static void setExisteBase(boolean existeBase){parametroConexao.existeBase = existeBase;}
	
	public static String getConexao() {return conexao;}
	public static void setConexao(String conexao) {parametroConexao.conexao = conexao;}
	public static String getLogin() {return login;}
	public static void setLogin(String login) {parametroConexao.login = login;}
	public static String getSenha() {return senha;}
	public static void setSenha(String senha) {parametroConexao.senha = senha;}
		 
	 public static Connection getConnection_Validacao() {
	       
		 System.out.println("Conectando ao Banco de Dados");
	     
	       try 
	       {
	    	   Class.forName("com.mysql.jdbc.Driver");
	           return DriverManager.getConnection("jdbc:mysql://d3acai.mysql.dbaas.com.br/d3acai","d3acai","p65f85d88");
	       } 
	       catch (SQLException | ClassNotFoundException e2) 
	       {
					// TODO Auto-generated catch block
				e2.printStackTrace();
	       }
	          
	       return null;
	    }
	
	
    public static Connection getConnection_Inicial() {
        System.out.println("Conectando ao Banco de Dados");
        
        try 
        {
        	Class.forName("com.mysql.jdbc.Driver");
	        return DriverManager.getConnection("jdbc:mysql://d3acai.mysql.dbaas.com.br/d3acai","d3acai","p65f85d88");
        } 
        catch (SQLException | ClassNotFoundException e1) 
        {
        	System.out.println("SISTEMA ON LINE");

            try 
            {
            	Class.forName("com.mysql.jdbc.Driver");
 	           	return DriverManager.getConnection("jdbc:mysql://d3acai.mysql.dbaas.com.br/d3acai","d3acai","p65f85d88");
			} 
            catch (SQLException | ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
        } 
        
        return null;
    }
   	  	    
	//FECHA CONEXAO COM O BANCO DE DADOS
	public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) 
	{
	  try 
	  {
	       if (conn!= null) 
	       {
	            conn.close();
	       }
	 
	       if (stmt!= null) 
	       {
	            stmt.close();
	       }
	 
	       if (rs!= null) 
	       {
	           rs.close();
           }
      }
	  catch (SQLException e) 
	  {
            e.printStackTrace();
      }
   }
}
