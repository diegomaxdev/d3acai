package d3DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ConexaoBancoDados.parametroConexao;
import Entity.DireitosTabela;
import Entity.DireitosUsuario;
import Entity.Usuario;
import Interface.IUsuarioDAO;
import d3Validacoes.ValidaDataVencimento;

public class SenhaDAO implements IUsuarioDAO {
			
	//VARIÁVEIS USADAS NOS MÈTODOS QUE TRATAM O BANCO, SUBSTITUEM AS QUERYS
	private static final String SQL_DELETE = 
			"delete from Usuarios where ID = ?";
	
	private static final String SQL_UPDATE_REMOVER_USUARIO = 
			"UPDATE Usuarios Set ATIVO = 0  WHERE Usuario = ?";
	
	private static final String SQL_RESETSENHA = 
			"update Usuarios set Senha = 'INCPP' where ID = ?";
	private static final String SQL_FIND_ALL = 
			"select * from Usuarios WHERE ATIVO = 1 order by ID";
	private static final String SQL_VALIDA_SENHA = 
			"select * from Usuarios where Usuario = ?";
	private static final String SQL_VERIFICA_USUARIO = 
			"select * from Usuarios where Usuario = ?";
	private static final String SQL_INSERT_USUARIO = 
			"insert into Usuarios (Usuario, Senha) values (?,?)";
	private static final String SQL_UPDATE_USUARIO = 
			"update Usuarios set Usuario = ? where ID = ?";	
	private static final String SQL_CARREGA_DIREITOS = 
			"Select * from Direitos WHERE IdUsuario = ?";	/*INSERTED ID*/
	private static final String SQL_ATUALIZA_SENHA = 
			"UPDATE Usuarios Set Senha = ?  WHERE Usuario = ?";
	private static final String SQL_VERIFICA_DIREITOS = 
			"SELECT Usuarios.ID, Usuarios.Usuario, Direitos.IdRotina, Rotinas.Descricao FROM Usuarios JOIN Direitos on Direitos.IdUsuario=Usuarios.ID JOIN Rotinas on Direitos.IdRotina=Rotinas.IdRotina WHERE ID=?";
	private static final String SQL_TODOS_DIREITOS	 = 
			"SELECT * FROM Rotinas WHERE IdRotina NOT IN (SELECT IdRotina FROM Direitos WHERE IdUsuario = ?) ";
	private static final String SQL_INSERT_DIREITOS	 = 
			"insert into Direitos (IdUsuario, IdRotina) values (?,?)";
	private static final String SQL_INSERT_TODOS_DIREITOS	 = 
			"insert into Direitos (IdUsuario, IdRotina) values (?,?)";
	private static final String SQL_REMOVE_DIREITOS	 = 
			"delete from Direitos where (IdRotina = ?) and (IdUsuario =?)";
	private static final String SQL_REMOVE_TODOS_DIREITOS	 = 
			"delete from Direitos where (IdUsuario =?)";
	private static final String SQL_CARREGA_DIREITOSALTERARBASE = 
			"Select * from Usuarios join Direitos on (Usuarios.ID = Direitos.IdUsuario) WHERE Usuario = ? AND IdRotina=9999";	/*INSERTED ID*/	
		
	//USADO NO METODO VALIDA SENHA, PARA SABERMOS SE A SENHA OU LOGIN ESTÂO CORRETOS
	private boolean loginValido;
	private boolean senhaValida;
		
	private static String login;
	private static String senha;
	private static String email;
	private static int IDSelecionado;
	private static int IDLogado;
	private static String direitosAtribuidos;
	private static String loginSelecionado;
	private static int rotinaSelecionada;

	//USADO PARA FILTRO DE DIREITOS - ESTE È VALIDADO E ATRIBUIDO O VALOR EM VERIFICA SENHA - CLASSE SENHA
	public static int getIDLogado() {return IDLogado;}
	public static void setIDLogado(int iDLogado) {IDLogado = iDLogado;}
	//USADO PARA SELECIONAR UMA ROTINA NA TELA DIREITOS DE USUARIO FRAME ESTE È ATRIBUIDO NA TELA DE USUARIO AO CLICARMOS
	public static String getLoginSelecionado() {return loginSelecionado;}
	public static void setLoginSelecionado(String login) {loginSelecionado = login;}
	//USADO PARA SELECIONAR UMA ROTINA NA TELA DIREITOS DE USUARIO FRAME ESTE È ATRIBUIDO NA TELA DE USUARIO AO CLICARMOS
	public static int getIDSelecionado() {return IDSelecionado;}
	public static void setIDSelecionado(int IDSELECIONADO) {IDSelecionado = IDSELECIONADO;}
	//USADO PARA SELECIONAR UMA ROTINA NA TELA DIREITOS DE USUARIO FRAME
	public static int getRotinaSelecionada() {return rotinaSelecionada;	}
	public static void setRotinaSelecionada(int rotinaSelecionada) {SenhaDAO.rotinaSelecionada = rotinaSelecionada;}
	//CARREGA TODOS OS DIREITOS DO USUARIO ATRAVÈS DE UMA STRING
	public static String getDireitosAtribuidos() {return direitosAtribuidos;}
	public static void setDireitosAtribuidos(String direitosAtribuidos) {SenhaDAO.direitosAtribuidos = direitosAtribuidos;}
	//RETORNA VERIFICAÇÂO DO LOGIN E SENHA SE SÂO VERDADEIROS AO LOGAR
	public boolean isLoginValido() {return loginValido;	}
	public void setLoginValido(boolean loginValido) {this.loginValido = loginValido;}
	
	public boolean isSenhaValida() {return senhaValida;	}
	public void setSenhaValida(boolean senhaValido) {this.senhaValida = senhaValido;}
	/*USADO PARA RECEBER OS AGENDAMENTOS*/
	public static String getEmail() {return email;}
	public static void setEmail(String email) {SenhaDAO.email = email;}
	//USADO NA CLASSE CADASTRA USUARIO, ESTE É PASSADO PARA ADICIONARMOS LOGIN E SENHA
	public static String getLogin( ){return login;} 
	public void setLogin(String login){	SenhaDAO.login= login;} 
	
	public static String getSenha(){return senha;}
	public void setSenha(String senha){	SenhaDAO.senha = senha;}
	
	public static boolean loginAssinaturas;
	public static boolean isLoginAssinaturas(){return loginAssinaturas;}
	public static void setLoginAssinaturas(boolean loginAssinaturas){SenhaDAO.loginAssinaturas = loginAssinaturas;}
		
	public static final DefaultComboBoxModel opcoesUsuario = new DefaultComboBoxModel();
	public static final DefaultComboBoxModel opcoesTodosUsuario = new DefaultComboBoxModel();
	
	private static final String SQL_ATUALIZA_LICENÇA = "UPDATE Clientes Set DataInclusao = ?  WHERE IDCliente = 1";
	private static String dataVencimento;
	public static String getDataVencimento() {return dataVencimento;}
	public static void setDataVencimento(String dataVencimento) {SenhaDAO.dataVencimento = dataVencimento;}
	
	//USADO PARA ATUALIZAR A LICENÇA DO SISTEMA MENSALMENTE
	public void AtualizarLicenca()
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet usuarios = null;
		try 
		{		// Criando uma conexão com o banco - através do parâmetro passado pela classe parametroConexao
			
			SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date dataAtual = new java.util.Date();
			String dataFinal = formatoSQL.format(dataAtual.getTime());
			String ano = dataFinal.substring(0,4);
			String mes = dataFinal.substring(5,7);
			String dia = dataFinal.substring(8,10);;
											
			ValidaDataVencimento validar = new ValidaDataVencimento();
			
			validar.setAno(Integer.parseInt(ano));
			validar.setDia(Integer.parseInt(dia));
			validar.setMes(Integer.parseInt(mes)+1);
			validar.validaData();

			SenhaDAO.setDataVencimento(formatoBrasileiro.format(validar.getDataCorrigida()));
			pstm = conexao.prepareStatement(SQL_ATUALIZA_LICENÇA);
			pstm.setDate(1, validar.getDataCorrigida());
			pstm.executeUpdate(); 

		}
		catch (SQLException se)
		{
			System.out.println("Erro de conexão com o Banco de Dados");
		}
		finally
		{
			parametroConexao.close(conexao, pstm, usuarios);
		}
	}

public void carregarListaTodosUsuarios()
{					
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet rs = null ;
	opcoesTodosUsuario.removeAllElements();	
	opcoesTodosUsuario.addElement("TODOS");
	
	try 
	{
		pstm = conexao.prepareStatement("SELECT * FROM Usuarios");
		rs = pstm.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getString("Usuario").trim());
			opcoesTodosUsuario.addElement(rs.getString("Usuario").trim());
		}	
	} catch (SQLException e) 
	{
		System.out.println("Erro de conexão com o Banco de Dados" + e);
	}

	finally
	{
		parametroConexao.close(conexao, pstm, rs);
	}

}


public void carregarEmail()
{					
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet rs = null ;
	try 
	{
		pstm = conexao.prepareStatement("SELECT * FROM Usuarios WHERE ID = ?");
		pstm.setInt(1, SenhaDAO.getIDSelecionado());
		rs = pstm.executeQuery();
		if(rs.next())
		{
			SenhaDAO.setEmail(rs.getString("email"));
		}	
	} catch (SQLException e) 
	{
		System.out.println("Erro de conexão com o Banco de Dados" + e);
	}

	finally
	{
		parametroConexao.close(conexao, pstm, rs);
	}

}

public void carregarTodosUsuarios()
{					
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet rs = null ;
	opcoesUsuario.removeAllElements();	
	try 
	{
		pstm = conexao.prepareStatement("SELECT * FROM Usuarios");
		rs = pstm.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getString("Usuario").trim());
			opcoesUsuario.addElement(rs.getString("Usuario").trim());
		}	
	} catch (SQLException e) 
	{
		System.out.println("Erro de conexão com o Banco de Dados" + e);
	}

	finally
	{
		parametroConexao.close(conexao, pstm, rs);
	}

}


public void carregarUsuarios()
{					
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet rs = null ;
	opcoesUsuario.removeAllElements();	
	try 
	{
		pstm = conexao.prepareStatement("SELECT * FROM Usuarios WHERE ID = ?");
		pstm.setInt(1, SenhaDAO.getIDLogado());
		rs = pstm.executeQuery();
		while(rs.next())
		{
			System.out.println(rs.getString("Usuario").trim());
			opcoesUsuario.addElement(rs.getString("Usuario").trim());
		}	
	} catch (SQLException e) 
	{
		System.out.println("Erro de conexão com o Banco de Dados" + e);
	}

	finally
	{
		parametroConexao.close(conexao, pstm, rs);
	}

}


//VALIDA SENHA AO FAZER O LOGIN DO USUARIO - SET ID LOGADO
public void ValidaSenha() throws SQLException
{					
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet usuarios = null;
	try 
	{
		pstm = conexao.prepareStatement(SQL_VALIDA_SENHA);
		pstm.setString(1, SenhaDAO.getLogin());


		//VALIDA APENAS UM USUARIO DO RETORNO IF, ESTE VEM ATRAVÈS DO RESULT SET DA QUERY
		usuarios = pstm.executeQuery(); 
		if(usuarios.next()) 
		{
			if (SenhaDAO.login.equals(usuarios.getString("Usuario").trim()))
			{
				SenhaDAO.setIDLogado(usuarios.getInt("ID"));
				this.setLoginValido(true);

			}
			else
			{
				this.setLoginValido(false);
			}

			if( SenhaDAO.senha.equals(usuarios.getString("Senha").trim()))
			{
				this.setSenhaValida(true);	
			}
			else
			{
				this.setSenhaValida(false);
			}
		}		
	} 
	catch (SQLException e) 
	{
		System.out.println("Erro de conexão com o Banco de Dados " + e);
	}

	finally
	{
		parametroConexao.close(conexao, pstm, null);
	}

}

//USADO PELA CLASSE CADASTRA USUARIO PARA INSERIR UM NOVO USUÀRIO NO BANCO
public void AlterarUsuario()
{
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet usuarios = null;
	try 
	{		// Criando uma conexão com o banco - através do parâmetro passado pela classe parametroConexao

		pstm = conexao.prepareStatement(SQL_UPDATE_USUARIO);
		pstm.setString(1, SenhaDAO.getLoginSelecionado());
		pstm.setInt(2, SenhaDAO.getIDSelecionado());
		pstm.executeUpdate(); 

	}
	catch (SQLException se)
	{
		System.out.println("Erro de conexão com o Banco de Dados");
	}
	finally
	{
		parametroConexao.close(conexao, pstm, usuarios);
	}
}

//USADO PELA CLASSE CADASTRA USUARIO PARA INSERIR UM NOVO USUÀRIO NO BANCO
public void IserirUsuario()
{
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet usuarios = null;
	try 
	{		// Criando uma conexão com o banco - através do parâmetro passado pela classe parametroConexao

		pstm = conexao.prepareStatement(SQL_INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, SenhaDAO.getLoginSelecionado());
		pstm.setString(2, SenhaDAO.getSenha());
		pstm.executeUpdate();

		ResultSet rs = pstm.getGeneratedKeys();  
		int id = 0;  
		if(rs.next())
		{  
		     id = rs.getInt(1);  
		     SenhaDAO.setIDSelecionado(id);
		}          
		System.out.println("Id gerado pelo insert foi " + id); 
	}
	catch (SQLException se)
	{
		System.out.println("Erro de conexão com o Banco de Dados " + se);
	}
	finally
	{
		parametroConexao.close(conexao, pstm, usuarios);
	}
}
//USADO NA CRIAÇÃO DO USUARIO
public void usuarioExiste() 
{					
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet usuarios = null;
	this.setLoginValido(false);
	try 
	{
		pstm = conexao.prepareStatement(SQL_VERIFICA_USUARIO);
		pstm.setString(1, SenhaDAO.getLoginSelecionado());


		//VALIDA APENAS UM USUARIO DO RETORNO IF, ESTE VEM ATRAVÈS DO RESULT SET DA QUERY
		usuarios = pstm.executeQuery(); 
		if(usuarios.next()) 
		{
			if (  SenhaDAO.getLoginSelecionado().trim().equals(usuarios.getString("Usuario").trim()))
			{
				this.setLoginValido(true);
			}
		}	
	} catch (SQLException e) 
	{
		System.out.println("Erro de conexão com o Banco de Dados" + e);
	}

	finally
	{
		parametroConexao.close(conexao, pstm, null);
	}

}

//USADO PELO USUARIOS PARA RESET DE SENHA  - FORÇANDO A SENHA SEGMENTO
public void ResetSenha() 
{
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	try 
	{	
		pstm = conexao.prepareStatement(SQL_RESETSENHA);
		pstm.setInt(1, SenhaDAO.getIDSelecionado());
		pstm.executeUpdate(); 
	}
	catch (SQLException se)
	{
		System.out.println("Erro - conexão com o Banco de Dados PROBLEMA URL");
	}
	finally
	{
		parametroConexao.close(conexao, pstm, null);
	}	
}
//USADO NA CLASSE LOGIN UMA VEZ QUE A SENHA FOR RESETADA - ATUALIZA A SENHA AO LOGAR, VALIDA ATRAVÈS DO USUARIO
public void AtualizarSenha() 
{
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	try 
	{
		pstm = conexao.prepareStatement(SQL_ATUALIZA_SENHA);
		pstm.setString(1, SenhaDAO.getSenha());
		pstm.setString(2, SenhaDAO.getLogin());
		pstm.executeUpdate(); 
	}
	catch (SQLException se)
	{
		System.out.println("Erro de conexão com o Banco de Dados");
	}
	finally
	{
		parametroConexao.close(conexao, pstm, null);
	}

}
//REMOVE USUARIO DA LISTA - USADO NA CLASSE USUARIOS 
public void removeUsuario() 
{
	//CONEXÂO COM O BANCO ATRAVÈS DA CLASSE PARAMETRO DE CONEXÂO
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;

	try {
		pstm = conexao.prepareStatement(SQL_UPDATE_REMOVER_USUARIO);
		pstm.setLong(1, SenhaDAO.IDSelecionado);
		pstm.executeUpdate();

	} catch (SQLException e) {
		try {
			if(conexao != null)
			{
				conexao.rollback();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally
		{
			parametroConexao.close(conexao, pstm, null);
		}
		System.out.println("Erro ao conectar a Base de Dados;");
	}
}

//CARREGA TODOS OS DIREITOS DOS USUARIO - SET DIREITOS USUARIO (CARREGA UMA STRING COM OS VALORES)
public void CarregaDireitosUsuario() 
{

	//CONEXÂO COM O BANCO ATRAVÈS DA CLASSE PARAMETRO DE CONEXÂO 
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	ResultSet rs = null ;
	String direitosCarregados = "";
	try {
		pstm = conexao.prepareStatement(SQL_CARREGA_DIREITOS);
		pstm.setLong(1, SenhaDAO.IDLogado);
		rs = pstm.executeQuery();

		while(rs.next())
		{					
			direitosCarregados += rs.getString("IdRotina") + "   ";
		}

		SenhaDAO.setDireitosAtribuidos(direitosCarregados);

	} 
	catch (SQLException e) 
	{
		try {
			if(conexao != null)
			{
				conexao.rollback();
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			parametroConexao.close(conexao, pstm, null);
		}
		System.out.println("Erro ao conectar a Base de Dados;");
	}
}


//CARREGA LISTAS NA TELA DE USUARIOS - TODOS OS USUARIOS, SENHAS E IDS
@Override
public List<Usuario> findAll() {

	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	List<Usuario> usuarios = new ArrayList<Usuario>();
	ResultSet rs = null ;
	try 
	{
		pstm = conexao.prepareStatement(SQL_FIND_ALL);
		rs = pstm.executeQuery();
		while(rs.next()){
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("ID"));
			usuario.setUsuario(rs.getString("Usuario"));

			//CARREGA A SENHA PORÈM NÂO EXISTE VISUALIZAÇÂO
			usuario.setSenha(rs.getString("Senha"));
			usuarios.add(usuario);
		}

	} catch (SQLException e) {
		try {
			if(conexao != null)
			{
				conexao.rollback();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally
		{
			parametroConexao.close(conexao, pstm, rs);
		}
		System.out.println("Erro ao conectar a Base de Dados;");
	}
	return usuarios;
}
//CARREGA LISTAS DOS DIREITOS ATRIBUIDOS NA TELA DIREITOS DE USUARIO FRAME ID, USUARIO, ID ROTINA, DESCRICAO ROTINA 
public List<DireitosUsuario> findDireitos() {

	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	List<DireitosUsuario> direitos = new ArrayList<DireitosUsuario>();
	ResultSet rs = null ;

	try 
	{
		pstm = conexao.prepareStatement(SQL_VERIFICA_DIREITOS);
		pstm.setInt(1, SenhaDAO.getIDSelecionado());
		rs = pstm.executeQuery();
		while(rs.next()){

			DireitosUsuario direitoUsuario = new DireitosUsuario();

			direitoUsuario.setIdusuario(rs.getInt("ID"));
			direitoUsuario.setUsuario(rs.getString("Usuario"));
			direitoUsuario.setIdRotina(rs.getInt("IdRotina"));
			direitoUsuario.setDescricao(rs.getString("Descricao").trim());

			direitos.add(direitoUsuario);
		}


	} catch (SQLException e) {
		try {
			if(conexao != null)
			{
				//RETORNA O BANCO AO ESTADO ANTERIOR	
				conexao.rollback();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		finally
		{
			parametroConexao.close(conexao, pstm, rs );
		}
		System.out.println("Erro ao conectar a Base de Dados " + e);
	}
	return direitos;

}


//CARREGA LISTAS DOS DIREITOS NÂO ATRIBUIDOS E EXISTENTES NA TELA DIREITOS DE USUARIO FRAME 
@Override
public List<DireitosTabela> findAllDireitos() {
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	List<DireitosTabela> todosDireitos = new ArrayList<DireitosTabela>();
	ResultSet rs = null ;

	try 
	{
		pstm = conexao.prepareStatement(SQL_TODOS_DIREITOS);
		pstm.setInt(1, SenhaDAO.getIDSelecionado());
		rs = pstm.executeQuery();
		while(rs.next())
		{
			DireitosTabela direitos = new DireitosTabela();
			direitos.setIdRotina(rs.getInt("IdRotina"));
			direitos.setDescricao(rs.getString("Descricao").trim());

			todosDireitos.add(direitos);
		}
	} catch (SQLException e) {
		try {
			if(conexao != null)
			{
				//RETORNA O BANCO AO ESTADO ANTERIOR	
				conexao.rollback();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		finally
		{
			parametroConexao.close(conexao, pstm, rs);
		}
		System.out.println("Erro ao conectar a Base de Dados " + e);
	}
	return todosDireitos;
}


//USADO NA CLASSE DIREITOS USUARIO FRAME PARA ADICIONAR UM DIREITO AO USUARIO
public void adicionaDireito() 
{
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	try 
	{		// Criando uma conexão com o banco - através do parâmetro passado pela classe parametroConexao

		pstm = conexao.prepareStatement(SQL_INSERT_DIREITOS);
		pstm.setInt(1, SenhaDAO.getIDSelecionado());
		pstm.setInt(2, SenhaDAO.getRotinaSelecionada());
		pstm.executeUpdate();;	
	}
	catch (SQLException se)
	{
		System.out.println("Erro de conexão com o Banco de Dados");
	}
	finally
	{
		parametroConexao.close(conexao, pstm, null);
	}
}
	
//USADO NA CLASSE DIREITOS USUARIO FRAME PARA ADICIONAR TODOS OS DIREITOS DA TABELA ROTINA AO USUARIO
public void adicionarTodosDireitos() 
{
	Connection conexaoResult = parametroConexao.getConnection_Inicial();
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstmResult = null;
	PreparedStatement pstm = null;
	ResultSet rs = null ;

	try 
	{
		pstmResult = conexaoResult.prepareStatement(SQL_TODOS_DIREITOS);
		pstmResult.setInt(1, SenhaDAO.getIDSelecionado());
		rs = pstmResult.executeQuery();
		while(rs.next())
		{
			try 
			{		// Criando uma conexão com o banco - através do parâmetro passado pela classe parametroConexao

				pstm = conexao.prepareStatement(SQL_INSERT_TODOS_DIREITOS);
				pstm.setInt(1, SenhaDAO.getIDSelecionado());
				pstm.setInt(2, rs.getInt("IdRotina"));
				pstm.executeUpdate();;	
			}
			catch (SQLException se)
			{
				System.out.println("Erro de conexão com o Banco de Dados");
			}
		}
	} 
	catch (SQLException e) 
	{
		try 	
		{
			if(conexao != null)
			{
				//RETORNA O BANCO AO ESTADO ANTERIOR	
				conexao.rollback();
			}

		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	finally
	{
		parametroConexao.close(conexao, pstm, null);
		parametroConexao.close(conexaoResult, pstmResult, rs);
	}
}



//USADO NA CLASSE DIREITOS USUARIO FRAME PARA REMOVER DIREITO DA TABELA ROTINA AO USUARIO
public void removeDireitos() {

	//CONEXÂO COM O BANCO ATRAVÈS DA CLASSE PARAMETRO DE CONEXÂO
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	try 
	{
		pstm = conexao.prepareStatement(SQL_REMOVE_DIREITOS);
		pstm.setInt(1, SenhaDAO.getRotinaSelecionada());
		pstm.setInt(2, SenhaDAO.getIDSelecionado());
		pstm.executeUpdate();;	
	} 
	catch (SQLException e) 
	{
		System.out.println("Erro ao conectar a Base de Dados;");
		e.printStackTrace();
	}
	finally
	{
		parametroConexao.close(conexao, pstm, null);
	}

}

//USADO NA CLASSE DIREITOS USUARIO FRAME PARA REMOVER TODOS OS DIREITOS ADICIONADOS AO USUARIO	
public void removerTodosDireitos() {

	//CONEXÂO COM O BANCO ATRAVÈS DA CLASSE PARAMETRO DE CONEXÂO
	Connection conexao = parametroConexao.getConnection_Inicial();
	PreparedStatement pstm = null;
	try 
	{
		pstm = conexao.prepareStatement(SQL_REMOVE_TODOS_DIREITOS);
		pstm.setInt(1, SenhaDAO.getIDSelecionado());
		pstm.executeUpdate();;	
	} 
	catch (SQLException e) 
	{
		System.out.println("Erro ao conectar a Base de Dados;");
		e.printStackTrace();
	}
	finally
	{
		parametroConexao.close(conexao, pstm, null);
	}

}
}


