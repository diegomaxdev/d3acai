package d3DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ConexaoBancoDados.parametroConexao;
import Entity.Cliente;
import Interface.IManutencaoCliente;
import d3DAO.SenhaDAO;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ManutencaoClienteSiacDAO implements IManutencaoCliente{
	
	private static String  nome, tipoDeEndereco, logradouro,
	 complemento, numero, cidade, UF, CEP, telefone, celular, CPF, RG, email, sexo, empresa, salario, periodo, cargo, origem, status;
	private static java.sql.Date data;
	private static int idCliente, idUsuario;
	private static String buscarCliente;
	private static String ClienteEncontrado;
	
	public static String getClienteEncontrado() {		return ClienteEncontrado;	}
	public static void setClienteEncontrado(String clienteEncontrado) {		ClienteEncontrado = clienteEncontrado;	}
	public static String getBuscarCliente() {		return buscarCliente;	}
	public static void setBuscarCliente(String buscarCliente) {		ManutencaoClienteSiacDAO.buscarCliente = buscarCliente;	}
	public static int getIdCliente() {		return idCliente;	}
	public static void setIdCliente(int idCliente) {		ManutencaoClienteSiacDAO.idCliente = idCliente;	}
	public static String getNome() {		return nome;	}
	public static void setNome(String nome) {		ManutencaoClienteSiacDAO.nome = nome;	}
	public static String getTipoDeEndereco() {		return tipoDeEndereco;	}
	public static void setTipoDeEndereco(String tipoDeEndereco) {		ManutencaoClienteSiacDAO.tipoDeEndereco = tipoDeEndereco;	}
	public static String getLogradouro() {		return logradouro;	}
	public static void setLogradouro(String logradouro) {		ManutencaoClienteSiacDAO.logradouro = logradouro;	}
	public static String getComplemento() {		return complemento;	}
	public static void setComplemento(String complemento) {		ManutencaoClienteSiacDAO.complemento = complemento;	}
	public static String getNumero() {		return numero;	}
	public static void setNumero(String numero) {		ManutencaoClienteSiacDAO.numero = numero;	}
	public static String getCidade() {		return cidade;	}
	public static void setCidade(String cidade) {		ManutencaoClienteSiacDAO.cidade = cidade;	}
	public static String getUF() {		return UF;	}
	public static void setUF(String uF) {		UF = uF;	}
	public static String getCEP() {		return CEP;	}
	public static void setCEP(String cEP) {		CEP = cEP;	}
	public static String getTelefone() {		return telefone;	}
	public static void setTelefone(String telefone) {		ManutencaoClienteSiacDAO.telefone = telefone;	}
	public static String getCPF() {		return CPF;	}
	public static void setCPF(String cPF) {		CPF = cPF;	}
	public static String getRG() {		return RG;	}
	public static void setRG(String rG) {		RG = rG;	}
	public static String getEmail() {		return email;	}
	public static void setEmail(String email) {		ManutencaoClienteSiacDAO.email = email;	}
	public static String getSexo() {		return sexo;	}
	public static void setSexo(String sexo) {		ManutencaoClienteSiacDAO.sexo = sexo;	}
	public static String getCelular() {return celular;}
	public static void setCelular(String celular) {ManutencaoClienteSiacDAO.celular = celular;}
	public static String getEmpresa() {return empresa;}
	public static void setEmpresa(String empresa) {ManutencaoClienteSiacDAO.empresa = empresa;}
	public static String getSalario() {return salario;}
	public static void setSalario(String salario) {ManutencaoClienteSiacDAO.salario = salario;}
	public static String getPeriodo() {	return periodo;}
	public static void setPeriodo(String periodo) {ManutencaoClienteSiacDAO.periodo = periodo;}
	public static String getCargo() {return cargo;}
	public static void setCargo(String cargo) {ManutencaoClienteSiacDAO.cargo = cargo;}
	public static String getOrigem() {return origem;}
	public static void setOrigem(String origem) {ManutencaoClienteSiacDAO.origem = origem;}
	public static java.sql.Date getData() {return data;}
	public static void setData(java.sql.Date data) {ManutencaoClienteSiacDAO.data = data;}
	public static int getIdUsuario() {return idUsuario;}
	public static void setIdUsuario(int idUsuario) {ManutencaoClienteSiacDAO.idUsuario = idUsuario;}
	public static String getStatus() {return status;}
	public static void setStatus(String status) {ManutencaoClienteSiacDAO.status = status;}

	public static List<Cliente> clientesPesquisados = new ArrayList<Cliente>();
	Cliente clientes = new Cliente();
	
	private static final String SQL_INSERT_CLIENTE = 
			"insert into Clientes (NOME, CIDADE, UF, CEP, TELEFONE, CARGO, SEXO, DATA, EMAIL, TipoEnde, Logradouro, Complemento, Numero, CPF, RG, EMPRESA, SALARIO, PERIODO, CELULAR, STATUS, ORIGEM, IDUSUARIO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_CLIENTE = 
			"UPDATE Clientes SET NOME = ?, CIDADE = ?, UF = ?, CEP = ?, TELEFONE = ? , CARGO = ?, SEXO = ?, "
			+ "DATA = ? , EMAIL = ?, TipoEnde = ?, Logradouro = ?, Complemento = ?, Numero = ?, CPF = ?, RG = ?, EMPRESA =?, SALARIO = ?, PERIODO = ?, CELULAR = ?, STATUS = ?, ORIGEM = ?, IDUSUARIO = ?  WHERE IDCLIENTE = ?";

	private static final String SQL_VERIFICA_EMAIL_E_CPF = 
			"SELECT * FROM Clientes where EMAIL = ? AND CPF = ? ORDER BY IDCLIENTE DESC";
	
	private static final String SQL_VERIFICA_EMAILUNICO = 
			"SELECT * FROM Clientes where EMAIL = ? ORDER BY IDCLIENTE DESC";
	
	private static final String SQL_VERIFICA_CPFUNICO = 
			"SELECT * FROM Clientes where CPF = ? ORDER BY IDCLIENTE DESC";
	
	private static final String SQL_PESQUISA_CLIENTES = 
			"SELECT * FROM Clientes WHERE IDCLIENTE = ? ORDER BY IDCLIENTE DESC";
	
	private static final String SQL_CARREGA_CLIENTE = 
			"SELECT * FROM Clientes WHERE IDCLIENTE = ? ORDER BY IDCLIENTE DESC";
	
	private static final String SQL_UPDATE_STATUS = 
			"UPDATE Clientes SET STATUS = ?  WHERE IDCLIENTE = ?";
	
	private static boolean cpfValido;
	private static boolean cnpjValido;
	private static boolean emailValido;
	
	public static boolean isCpfValido() {return cpfValido;}
	public static void setCpfValido(boolean cpfValido) {ManutencaoClienteSiacDAO.cpfValido = cpfValido;	}

	public static boolean isCnpjValido() {return cnpjValido;}
	public static void setCnpjValido(boolean cnpjValido) {ManutencaoClienteSiacDAO.cnpjValido = cnpjValido;	}

	public static boolean isEmailValido() {	return emailValido;	}
	public static void setEmailValido(boolean emailValido) {ManutencaoClienteSiacDAO.emailValido = emailValido;	}

	private static boolean ClienteValido;
	public static boolean isClienteValido(){return ClienteValido;}
	public static void setClienteValido(boolean clienteValido)	{ClienteValido = clienteValido;	}
	
	private static boolean LocalizouCliente;
	public static boolean isLocalizouCliente(){return LocalizouCliente;}
	public static void setLocalizouCliente(boolean localizouCliente){LocalizouCliente = localizouCliente;}
	
	private static String SQLencontraNomeCliente = " ";
	public static String getSQLencontraNomeCliente(){return SQLencontraNomeCliente;}
	public static void setSQLencontraNomeCliente(String sQLencontraNomeCliente){SQLencontraNomeCliente = sQLencontraNomeCliente;}
	
	public void ImportarClientes()
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;

		JFileChooser abertura = new JFileChooser();  
		// Possibilita a seleção de vários arquivos  
		abertura.setMultiSelectionEnabled(false);  
		abertura.showOpenDialog(null);  
		File arquivo = abertura.getSelectedFile();  

		if(arquivo == null)
		{
			JOptionPane.showMessageDialog(null, "Favor selecionar o arquivo a ser inportado ");
			return;
		}

		try 
		{	
			pstm = conexao.prepareStatement(SQL_INSERT_CLIENTE);
			/* pega o arquiivo do Excel */  
			Workbook caminhoArquivo = Workbook.getWorkbook(arquivo);  
			/* pega a primeira planilha dentro do arquivo XLS */  
			Sheet posicao = caminhoArquivo.getSheet(0);  
			//Pega a quantidade de linhas da planilha   
			int linhas = posicao.getRows();  
			//CASO DESEJARM<OS PULAR A LINHA COMEÇAMOS O VALOR POR 1  
			for (int i = 1; i < linhas; i++) 
			{  
				Cell coluna1 = posicao.getCell(0, i);  
				Cell coluna2 = posicao.getCell(1, i);
				Cell coluna3 = posicao.getCell(2, i);
				Cell coluna4 = posicao.getCell(3, i);  
				Cell coluna5 = posicao.getCell(4, i);
				Cell coluna6 = posicao.getCell(5, i);
				Cell coluna7 = posicao.getCell(6, i);  
				Cell coluna8 = posicao.getCell(7, i);
				Cell coluna9 = posicao.getCell(8, i);
				Cell coluna10 = posicao.getCell(9, i);
				Cell coluna11 = posicao.getCell(10, i);
				Cell coluna12 = posicao.getCell(11, i);
				Cell coluna13 = posicao.getCell(12, i);
				Cell coluna14 = posicao.getCell(13, i);  
				Cell coluna15 = posicao.getCell(14, i);
				Cell coluna16 = posicao.getCell(15, i);
				Cell coluna17 = posicao.getCell(16, i);  
				Cell coluna18 = posicao.getCell(17, i);
				Cell coluna19 = posicao.getCell(18, i);
		
				if(coluna1.getContents().trim().toUpperCase().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "Erro na Linha " + i + ". Este Erro encontra-se na Coluna Nome, que está em Branco");
					continue;
				}
				else
				{
					setNome(coluna1.getContents().toUpperCase().trim()); 
				}

				setCidade(coluna2.getContents().toUpperCase().trim() + " "); 
				setUF(coluna3.getContents().toUpperCase().trim() + " "); 
				setCEP(coluna4.getContents().toUpperCase().trim() + " "); 
				setTelefone(coluna5.getContents().toUpperCase().trim() + " "); 
				setCargo(coluna6.getContents().toUpperCase().trim() + " "); 
				setSexo(coluna7.getContents().toUpperCase().trim() + " "); 
				//CONVERSÃO PARA AS DATAS NO FORMATO SQL APÓS TRATAMENTO
				Date dataAtual = new Date();
				java.sql.Date dataCadastroSQL = new java.sql.Date(dataAtual.getTime()); 
				setData(dataCadastroSQL); 

				setEmail(coluna8.getContents().toUpperCase().trim() + " "); 
				setTipoDeEndereco(coluna9.getContents().toUpperCase().trim() + " "); 
				setLogradouro(coluna10.getContents().toUpperCase().trim() + " "); 
				setComplemento(coluna11.getContents().toUpperCase().trim() + " "); 
				setNumero(coluna12.getContents().toUpperCase().trim() + " "); 
				setCPF(coluna13.getContents().trim() + " "); 
				setRG(coluna14.getContents().trim() + " "); 
				setEmpresa(coluna15.getContents().toUpperCase().trim() + " "); 
				setSalario(coluna16.getContents().toUpperCase().trim() + " "); 
				setPeriodo(coluna17.getContents().trim() + " "); 
				setCelular(coluna18.getContents().trim() + " "); 
				setStatus(" "); 
				setOrigem(coluna19.getContents().toUpperCase().trim() + " "); 

				IserirCliente();
			}          
			
		}
		catch (SQLException se)
		{
			System.out.println("Erro de conexão com o Banco de Dados" + se);
		} 
		catch (BiffException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			parametroConexao.close(conexao, pstm, null);
		}
	}	   
	
	public void alterarStatus()
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{
			pstm = conexao.prepareStatement(SQL_UPDATE_STATUS);

			pstm.setString(1, ManutencaoClienteSiacDAO.getStatus());
			pstm.setInt(2, ManutencaoClienteSiacDAO.getIdCliente());

			pstm.executeUpdate();	
		}
		catch (SQLException se)
		{
			System.out.println("Erro de conexão com o Banco de Dados" + se);
		}
		finally
		{
			parametroConexao.close(conexao, pstm, null);
		}
	}
	
	
	public void CarregaNomeClienteInclusao() 
	{					
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		
		ManutencaoClienteSiacDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(getSQLencontraNomeCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{								
				ManutencaoClienteSiacDAO.setIdCliente(rs.getInt("IDCLIENTE"));
				ManutencaoClienteSiacDAO.setNome(rs.getString("NOME").trim());
				
				JOptionPane.showMessageDialog(null, "Cliente localizado Cod. CLiente : " + rs.getInt("IDCLIENTE") + " - " +  rs.getString("NOME").trim() );
			}	
		} 
			catch (SQLException e) 
		{
			System.out.println("Erro de conexão com o Banco de Dados " + e);
		}
		
		finally
		{
		parametroConexao.close(conexao, pstm, rs);
		}
		
	}
	
	/**
	 * CLASSE USADA PARA EMISSÂO DE ARQUIVOS TXT DEVIDO A NECESSIDADE DOS BRANCOS
	 */
	public void CarregaClienteCompleto() 
	{					
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		
		ManutencaoClienteSiacDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(SQL_CARREGA_CLIENTE);
			pstm.setInt(1, ManutencaoClienteSiacDAO.getIdCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				System.out.println("ACHOU");
				ManutencaoClienteSiacDAO.setClienteValido(true);
									
					ManutencaoClienteSiacDAO.setIdCliente(rs.getInt("IDCLIENTE"));
					ManutencaoClienteSiacDAO.setNome(rs.getString("NOME"));
					ManutencaoClienteSiacDAO.setTipoDeEndereco(rs.getString("TipoEnde"));
					ManutencaoClienteSiacDAO.setLogradouro(rs.getString("Logradouro"));
					ManutencaoClienteSiacDAO.setComplemento(rs.getString("Complemento"));
					ManutencaoClienteSiacDAO.setNumero(rs.getString("Numero"));
					ManutencaoClienteSiacDAO.setCidade(rs.getString("CIDADE"));
					ManutencaoClienteSiacDAO.setUF(rs.getString("UF"));
					//TRATA CEP RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
					ManutencaoClienteSiacDAO.setCEP(rs.getString("CEP"));
					ManutencaoClienteSiacDAO.setTelefone(rs.getString("TELEFONE"));
					int cont = 0;
				
					//TRATA CPF RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
					String recebeCPF = rs.getString("CPF").trim();
					String cpf = "";
					cont = 0;
					for(char c: recebeCPF.toCharArray()){
						cont++;
						if(cont == 4 || cont == 7){	cpf += ".";	}
						if(cont == 10)	{cpf += "-";}
						cpf += c;
					}
					ManutencaoClienteSiacDAO.setCPF(cpf);
					ManutencaoClienteSiacDAO.setRG(rs.getString("RG"));
					ManutencaoClienteSiacDAO.setEmail(rs.getString("EMAIL"));
					ManutencaoClienteSiacDAO.setSexo(rs.getString("SEXO"));
					
					ManutencaoClienteSiacDAO.setLocalizouCliente(true);
					ManutencaoClienteSiacDAO.setCargo(rs.getString("CARGO").trim());
					ManutencaoClienteSiacDAO.setEmpresa(rs.getString("EMPRESA").trim());
					ManutencaoClienteSiacDAO.setSalario(rs.getString("SALARIO").trim());
					ManutencaoClienteSiacDAO.setOrigem(rs.getString("ORIGEM").trim());
					ManutencaoClienteSiacDAO.setPeriodo(rs.getString("PERIODO").trim());
					if(rs.getString("STATUS").trim().length() == 0)
					{
						ManutencaoClienteSiacDAO.setStatus("CLIENTE NÃO TRABALHADO");
					}
					else
					{
						ManutencaoClienteSiacDAO.setStatus(rs.getString("STATUS").trim());
					}
					ManutencaoClienteSiacDAO.setCelular(rs.getString("CELULAR").trim());
				}
			else
			{
				System.out.println("Não ACHOU");
			}
		} 
			catch (SQLException e) 
		{
			System.out.println("Erro de conexão com o Banco de Dados");
		}
		
		finally
		{
		parametroConexao.close(conexao, pstm, rs);
		}
		
	}
	
	public void CarregaCliente() 
	{					
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		
		ManutencaoClienteSiacDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(SQL_CARREGA_CLIENTE);
			pstm.setInt(1, ManutencaoClienteSiacDAO.getIdCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				ManutencaoClienteSiacDAO.setClienteValido(true);
									
					ManutencaoClienteSiacDAO.setIdCliente(rs.getInt("IDCLIENTE"));
					ManutencaoClienteSiacDAO.setNome(rs.getString("NOME").trim());
					ManutencaoClienteSiacDAO.setTipoDeEndereco(rs.getString("TipoEnde").trim());
					ManutencaoClienteSiacDAO.setLogradouro(rs.getString("Logradouro").trim());
					ManutencaoClienteSiacDAO.setComplemento(rs.getString("Complemento").trim());
					ManutencaoClienteSiacDAO.setNumero(rs.getString("Numero").trim());
					ManutencaoClienteSiacDAO.setCidade(rs.getString("CIDADE").trim());
					ManutencaoClienteSiacDAO.setUF(rs.getString("UF").trim());
					//TRATA CEP RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
					ManutencaoClienteSiacDAO.setCEP(rs.getString("CEP").substring(0,5) + "-" +  rs.getString("CEP").substring(5,8));
					ManutencaoClienteSiacDAO.setTelefone(rs.getString("TELEFONE").trim());
					//TRATA CNPJ RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
					int cont = 0;
					//TRATA CPF RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
					String recebeCPF = rs.getString("CPF").trim();
					String cpf = "";
					cont = 0;
					for(char c: recebeCPF.toCharArray()){
						cont++;
						if(cont == 4 || cont == 7){	cpf += ".";	}
						if(cont == 10)	{cpf += "-";}
						cpf += c;
					}
										
					ManutencaoClienteSiacDAO.setCPF(cpf);
					ManutencaoClienteSiacDAO.setRG(rs.getString("RG").trim());
					ManutencaoClienteSiacDAO.setEmail(rs.getString("EMAIL").trim());
					ManutencaoClienteSiacDAO.setSexo(rs.getString("SEXO").trim());
				}
		} 
			catch (SQLException e) 
		{
			System.out.println("Erro de conexão com o Banco de Dados " + e);
		}
		
		finally
		{
		parametroConexao.close(conexao, pstm, rs);
		}
		
	}
	
	public void ValidaCampoEmail_CPF()
	{	
		String emailExiste = "";
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet resultado = null;
		try 
		{
			pstm = conexao.prepareStatement(SQL_VERIFICA_EMAIL_E_CPF);
			pstm.setString(1, ManutencaoClienteSiacDAO.getEmail().toLowerCase().trim());
			pstm.setString(2, ManutencaoClienteSiacDAO.getCPF());
			resultado = pstm.executeQuery(); 
			ManutencaoClienteSiacDAO.setEmailValido(true);
			if(resultado.next()) 
			{
				ManutencaoClienteSiacDAO.setEmailValido(false);
				emailExiste = (resultado.getInt("IDCLIENTE")  +" - "+  resultado.getString("NOME").trim());
				setClienteEncontrado(emailExiste);
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro de conexão com o Banco de Dados" + e);
		}
		
		finally
		{
		parametroConexao.close(conexao, pstm, resultado);
		}
	}
	
	
	//VERIFICA EMAIL EXISTENTE NA BASE
	public void ValidaCampoEmailUnico()
	{	
		String emailExiste = "";
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet resultado = null;
		try 
		{
			pstm = conexao.prepareStatement(SQL_VERIFICA_EMAILUNICO);
			pstm.setString(1, ManutencaoClienteSiacDAO.getEmail().toLowerCase().trim());
			resultado = pstm.executeQuery(); 
			ManutencaoClienteSiacDAO.setEmailValido(true);
			if(resultado.next()) 
			{
				System.out.println("NOME DO CLIENTE ACHADO + " + resultado.getString("NOME").trim());
				ManutencaoClienteSiacDAO.setEmailValido(false);
				emailExiste = (resultado.getInt("IDCLIENTE")  +" - "+  resultado.getString("NOME").trim());
				ManutencaoClienteSiacDAO.setClienteEncontrado(emailExiste);
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro de conexão com o Banco de Dados" + e);
		}
		
		finally
		{
		parametroConexao.close(conexao, pstm, resultado);
		}
	}
	
	//VERIFICA CPF EXISTENTE NA BASE
	public void ValidaCampoCPFUnico()
	{	
		String CPFExiste = "";
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet resultado = null;
		try 
		{
			pstm = conexao.prepareStatement(SQL_VERIFICA_CPFUNICO);
			pstm.setString(1, ManutencaoClienteSiacDAO.getCPF().toLowerCase().trim());
			resultado = pstm.executeQuery(); 
			ManutencaoClienteSiacDAO.setCpfValido(true);
			if(resultado.next()) 
			{
				ManutencaoClienteSiacDAO.setCpfValido(false);
				CPFExiste = resultado.getInt("IDCLIENTE") +" - " + resultado.getString("NOME").trim();
				ManutencaoClienteSiacDAO.setIdCliente(resultado.getInt("IDCLIENTE"));
				ManutencaoClienteSiacDAO.setEmail(resultado.getString("EMAIL"));
				ManutencaoClienteSiacDAO.setCEP(resultado.getString("CEP"));
			}
			setClienteEncontrado(CPFExiste);
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro de conexão com o Banco de Dados" + e);
		}
		
		finally
		{
		parametroConexao.close(conexao, pstm, resultado);
		}
	}

	public void IserirCliente()
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{	
			pstm = conexao.prepareStatement(SQL_INSERT_CLIENTE , Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, ManutencaoClienteSiacDAO.getNome());
			pstm.setString(2, ManutencaoClienteSiacDAO.getCidade());
			pstm.setString(3, ManutencaoClienteSiacDAO.getUF());
			pstm.setString(4, ManutencaoClienteSiacDAO.getCEP());
			pstm.setString(5, ManutencaoClienteSiacDAO.getTelefone());
			pstm.setString(6, ManutencaoClienteSiacDAO.getCargo());
			pstm.setString(7, ManutencaoClienteSiacDAO.getSexo());
			pstm.setDate(8, ManutencaoClienteSiacDAO.getData());
			pstm.setString(9, ManutencaoClienteSiacDAO.getEmail());
			pstm.setString(10, ManutencaoClienteSiacDAO.getTipoDeEndereco());
			pstm.setString(11, ManutencaoClienteSiacDAO.getLogradouro());
			pstm.setString(12, ManutencaoClienteSiacDAO.getComplemento());
			pstm.setString(13, ManutencaoClienteSiacDAO.getNumero());
			pstm.setString(14, ManutencaoClienteSiacDAO.getCPF());
			pstm.setString(15, ManutencaoClienteSiacDAO.getRG());
			pstm.setString(16, ManutencaoClienteSiacDAO.getEmpresa());
			pstm.setString(17, ManutencaoClienteSiacDAO.getSalario());
			pstm.setString(18, ManutencaoClienteSiacDAO.getPeriodo());
			pstm.setString(19, ManutencaoClienteSiacDAO.getCelular());
			pstm.setString(20, ManutencaoClienteSiacDAO.getStatus());
			pstm.setString(21, ManutencaoClienteSiacDAO.getOrigem());
			pstm.setInt(22, SenhaDAO.getIDLogado());
			
			
												
			pstm.executeUpdate();	
			
			 ResultSet rs = pstm.getGeneratedKeys();  
			 if(rs.next())
			 {  
				 System.out.println("GERANDO O ID CLIENTE");
				 //RETORNA O ID DO CLIENTE AO INSERIR PARA O ID ASSINATURA
				 ManutencaoClienteSiacDAO.setIdCliente(rs.getInt(1)); 
				 System.out.println("RECEBEU O ID " + rs.getInt(1));
			 }   
			 else
			 {
				 System.out.println("GERANDO O ID CLIENTE ERRADO");
			 }
		}
		catch (SQLException se)
		{
			System.out.println("Erro de conexão com o Banco de Dados" + se);
		}
		finally
		{
			parametroConexao.close(conexao, pstm, null);
		}
	}
	
	public void AtualizarCliente()
	{
		
		/*
		 * 	"UPDATE Clientes SET NOME = ?, CIDADE = ?, UF = ?, CEP = ?, TELEFONE = ? , CELULAR = ?, CARGO = ?, SEXO = ?, DATA = ? , EMAIL = ?, TipoEnde = ?, Logradouro = ?, Complemento = ?,
		 *  Numero = ?, CPF = ?, RG = ?, SALARIO = ?, PERIODO = ?, CELULAR = ?, STATUS = ?, ORIGEM = ?, IDUSUARIO = ?  WHERE IDCLIENTE = ?";
		*/
		
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{	
			//UPDATE Clientes SET NOME = ?, CIDADE = ?, UF = ?, CEP = ?, TELEFONE = ? , CELULAR = ?, CARGO = ?, SEXO = ?, DATA = ? , EMAIL = ?, TipoEnde = ?, Logradouro = ?, Complemento = ?, Numero = ?, CPF = ?, RG = ?, SALARIO = ?, PERIODO = ?, CELULAR = ?, STATUS = ?, ORIGEM = ?, IDUSUARIO = ?  WHERE IDCLIENTE = ?
			
			pstm = conexao.prepareStatement(SQL_UPDATE_CLIENTE);
			pstm.setString(1, ManutencaoClienteSiacDAO.getNome());
			pstm.setString(2, ManutencaoClienteSiacDAO.getCidade());
			pstm.setString(3, ManutencaoClienteSiacDAO.getUF());
			pstm.setString(4, ManutencaoClienteSiacDAO.getCEP());
			pstm.setString(5, ManutencaoClienteSiacDAO.getTelefone());
			pstm.setString(6, ManutencaoClienteSiacDAO.getCargo());
			pstm.setString(7, ManutencaoClienteSiacDAO.getSexo());
			pstm.setDate(8, ManutencaoClienteSiacDAO.getData());
			pstm.setString(9, ManutencaoClienteSiacDAO.getEmail());
			pstm.setString(10, ManutencaoClienteSiacDAO.getTipoDeEndereco());
			pstm.setString(11, ManutencaoClienteSiacDAO.getLogradouro());
			pstm.setString(12, ManutencaoClienteSiacDAO.getComplemento());
			pstm.setString(13, ManutencaoClienteSiacDAO.getNumero());
			pstm.setString(14, ManutencaoClienteSiacDAO.getCPF());
			pstm.setString(15, ManutencaoClienteSiacDAO.getRG());
			pstm.setString(16, ManutencaoClienteSiacDAO.getEmpresa());
			pstm.setString(17, ManutencaoClienteSiacDAO.getSalario());
			pstm.setString(18, ManutencaoClienteSiacDAO.getPeriodo());
			pstm.setString(19, ManutencaoClienteSiacDAO.getCelular());
			pstm.setString(20, ManutencaoClienteSiacDAO.getStatus());
			pstm.setString(21, ManutencaoClienteSiacDAO.getOrigem());
			pstm.setInt(22, SenhaDAO.getIDLogado());
			pstm.setInt(23, ManutencaoClienteSiacDAO.getIdCliente());
															
			pstm.executeUpdate();	
		}
		catch (SQLException se)
		  {
			System.out.println("Erro de conexão com o Banco de Dados - ATUALIZAR" + se);
		  }
			finally
			{
			parametroConexao.close(conexao, pstm, null);
			}
	}
	
	
	@Override
	public List<Cliente> findAllClientes() {
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		ResultSet rs = null ;
		SimpleDateFormat formatobrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		
		/*AVISA AO NÂO LOCALIZAR NENHUM CLIENTE*/
		if(!ManutencaoClienteSiacDAO.getBuscarCliente().equals("SELECT * FROM CLIENTES JOIN Usuarios on (IDUSUARIO = ID) WHERE NOME LIKE \'%NENHUM%\'"))
		{
			ManutencaoClienteSiacDAO.setLocalizouCliente(false);
		}
		
		try 
		{
			System.out.println(ManutencaoClienteSiacDAO.getBuscarCliente());
			pstm = conexao.prepareStatement(ManutencaoClienteSiacDAO.getBuscarCliente());
			rs = pstm.executeQuery();
			while(rs.next())
			{
				ManutencaoClienteSiacDAO.setLocalizouCliente(true);
				Cliente clientesCadastrados = new Cliente();
				clientesCadastrados.setIdCliente(rs.getInt("IDCLIENTE"));
								
				clientesCadastrados.setNome(rs.getString("NOME").trim());
				clientesCadastrados.setTipoDeEndereco(rs.getString("TipoEnde").trim());
				clientesCadastrados.setLogradouro(rs.getString("Logradouro").trim());
				clientesCadastrados.setComplemento(rs.getString("Complemento").trim());
				clientesCadastrados.setNumero(rs.getString("Numero").trim());
				clientesCadastrados.setCidade(rs.getString("CIDADE").trim());
				clientesCadastrados.setUF(rs.getString("UF").trim());
				//TRATA CEP RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
				int cont = 0;
				String RecebeCep = rs.getString("CEP").trim();
				String Cep = "";
				for(char c: RecebeCep.toCharArray())
				{
					cont++;
					Cep += c; 
					 if(cont == 5)
					 {
						 Cep += "-";
					 }
				}
				clientesCadastrados.setCEP(Cep);
				clientesCadastrados.setTelefone(rs.getString("TELEFONE").trim());
				clientesCadastrados.setCelular(rs.getString("CELULAR").trim());
				//TRATA CNPJ RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
				cont = 0;
				//TRATA CPF RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
				String recebeCPF = rs.getString("CPF").trim();
				String cpf = "";
				cont = 0;
				for(char c: recebeCPF.toCharArray()){
					cont++;
					if(cont == 4 || cont == 7){	cpf += ".";	}
					if(cont == 10)	{cpf += "-";}
					cpf += c;
				}
				clientesCadastrados.setCPF(cpf);
				clientesCadastrados.setRG(rs.getString("RG").trim());
				clientesCadastrados.setEmail(rs.getString("EMAIL").trim());
				clientesCadastrados.setSexo(rs.getString("SEXO").trim());
				clientesCadastrados.setCargo(rs.getString("CARGO").trim());
				
				String Empresas =  rs.getString("EMPRESA").trim();
				String EmpresaFormatada = "<HTML> " ;
				int qtdCaracter = 0;
				
				for(char c: Empresas.toCharArray())
				{
					EmpresaFormatada += c;
					qtdCaracter++;
					
					if(qtdCaracter % 45 == 0)
					{
						EmpresaFormatada += "<BR>";
					}
				}
				clientesCadastrados.setEmpresa(EmpresaFormatada + " </HTML>");
				
				clientesCadastrados.setSalario(rs.getString("SALARIO").trim());
				clientesCadastrados.setOrigem(rs.getString("ORIGEM").trim());
				clientesCadastrados.setPeriodo(rs.getString("PERIODO").trim());
				if(rs.getString("STATUS").trim().length() == 0)
				{
					clientesCadastrados.setStatus("CLIENTE NÃO TRABALHADO");
				}
				else
				{
					clientesCadastrados.setStatus(rs.getString("STATUS").trim());
				}
				
				clientesCadastrados.setData(rs.getDate("DATA"));
				clientesCadastrados.setDataVisual(formatobrasileiro.format(rs.getDate("DATA")));
				clientesCadastrados.setUsuario(rs.getString("USUARIO").trim());
				
				String Ocorrencias =  rs.getString("OCORRENCIAS").trim();
				String OcorrenciaFormatada = "<HTML> " ;
				qtdCaracter = 0;
				
				for(char c: Ocorrencias.toCharArray())
				{
					OcorrenciaFormatada += c;
					qtdCaracter++;
					
					if(qtdCaracter % 45 == 0)
					{
						OcorrenciaFormatada += "<BR>";
					}
				}
				
				clientesCadastrados.setOcorrencias(OcorrenciaFormatada + " </HTML>");
				
				
				
				clientes.add(clientesCadastrados);
			}
		} 
		catch (SQLException e) 
		{		
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
		System.out.println("Erro ao conectar a Base de Dados" + e);
		}
		return clientes;
	}
			
	public void adicionaNaListaCliente() 
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		try 
		{					
			pstm = conexao.prepareStatement(SQL_PESQUISA_CLIENTES);
			pstm.setInt(1, ManutencaoClienteSiacDAO.getIdCliente());
			rs = pstm.executeQuery();
		
			while(rs.next())
			{
				clientes.setIdCliente(rs.getInt("IDCLIENTE"));
				clientes.setNome(rs.getString("NOME").trim());
				clientes.setTipoDeEndereco(rs.getString("TipoEnde").trim());
				clientes.setLogradouro(rs.getString("Logradouro").trim());
				clientes.setComplemento(rs.getString("Complemento").trim());
				clientes.setNumero(rs.getString("Numero").trim());
				clientes.setCidade(rs.getString("CIDADE").trim());
				clientes.setUF(rs.getString("UF").trim());
		
			//TRATA CEP RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
				int cont = 0;
				String RecebeCep = rs.getString("CEP").trim();
				String Cep = "";
				for(char c: RecebeCep.toCharArray())
				{
					cont++;
					Cep += c; 
					if(cont == 5)
					{
						Cep += "-";
					}
				}
				clientes.setCEP(Cep);
				clientes.setTelefone(rs.getString("TELEFONE").trim());
				//TRATA CNPJ RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
				cont = 0;
				//TRATA CPF RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
				String recebeCPF = rs.getString("CPF").trim();
				String cpf = "";
				cont = 0;
				for(char c: recebeCPF.toCharArray())
				{
					cont++;
					if(cont == 4 || cont == 7){	cpf += ".";	}
					if(cont == 10)	{cpf += "-";}
						cpf += c;
				}
				clientes.setCPF(cpf);
				clientes.setRG(rs.getString("RG").trim());
				clientes.setEmail(rs.getString("EMAIL").trim());
				clientes.setSexo(rs.getString("SEXO").trim());
					
				//VERIFICA A LISTA PARA NÂO HAVER DUPLICIDADE
				boolean clienteNalista = false;
								
				for (int i = 0; i < ManutencaoClienteSiacDAO.clientesPesquisados.size(); i++)
				{
					Cliente cliente =  ManutencaoClienteSiacDAO.clientesPesquisados.get(i);
					if(cliente.getIdCliente() == rs.getInt("IDCLIENTE"))
					{
						clienteNalista = true;
					}
				}
				
				if(clienteNalista == false)
				{
					System.out.println("INCLUIU LISTA");
					clientesPesquisados.add(clientes);
				}			
			}
		}	
		catch (SQLException se)
		{
			System.out.println("Erro de conexão com o Banco de Dados" + se);
		}
		finally
		{
			parametroConexao.close(conexao, pstm, null);
		}
	}
	
	@Override
	public List<Cliente> findAllPesquisaClientes()
	{
		return clientesPesquisados;
	}
}
