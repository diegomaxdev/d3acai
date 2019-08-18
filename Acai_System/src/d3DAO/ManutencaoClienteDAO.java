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
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ManutencaoClienteDAO implements IManutencaoCliente{
	
	private static String  nome, tipoDeEndereco, logradouro,
	 complemento, numero, cidade, UF, CEP, telefone, celular, CPF, RG, email, sexo, empresa, salario, periodo, cargo, origem, status;
	private static java.sql.Date data;
	private static int idCliente, idUsuario;
	private static String buscarCliente;
	private static String ClienteEncontrado;
	
	public static String getClienteEncontrado() {		return ClienteEncontrado;	}
	public static void setClienteEncontrado(String clienteEncontrado) {		ClienteEncontrado = clienteEncontrado;	}
	public static String getBuscarCliente() {		return buscarCliente;	}
	public static void setBuscarCliente(String buscarCliente) {		ManutencaoClienteDAO.buscarCliente = buscarCliente;	}
	public static int getIdCliente() {		return idCliente;	}
	public static void setIdCliente(int idCliente) {		ManutencaoClienteDAO.idCliente = idCliente;	}
	public static String getNome() {		return nome;	}
	public static void setNome(String nome) {		ManutencaoClienteDAO.nome = nome;	}
	public static String getTipoDeEndereco() {		return tipoDeEndereco;	}
	public static void setTipoDeEndereco(String tipoDeEndereco) {		ManutencaoClienteDAO.tipoDeEndereco = tipoDeEndereco;	}
	public static String getLogradouro() {		return logradouro;	}
	public static void setLogradouro(String logradouro) {		ManutencaoClienteDAO.logradouro = logradouro;	}
	public static String getComplemento() {		return complemento;	}
	public static void setComplemento(String complemento) {		ManutencaoClienteDAO.complemento = complemento;	}
	public static String getNumero() {		return numero;	}
	public static void setNumero(String numero) {		ManutencaoClienteDAO.numero = numero;	}
	public static String getCidade() {		return cidade;	}
	public static void setCidade(String cidade) {		ManutencaoClienteDAO.cidade = cidade;	}
	public static String getUF() {		return UF;	}
	public static void setUF(String uF) {		UF = uF;	}
	public static String getCEP() {		return CEP;	}
	public static void setCEP(String cEP) {		CEP = cEP;	}
	public static String getTelefone() {		return telefone;	}
	public static void setTelefone(String telefone) {		ManutencaoClienteDAO.telefone = telefone;	}
	public static String getCPF() {		return CPF;	}
	public static void setCPF(String cPF) {		CPF = cPF;	}
	public static String getRG() {		return RG;	}
	public static void setRG(String rG) {		RG = rG;	}
	public static String getEmail() {		return email;	}
	public static void setEmail(String email) {		ManutencaoClienteDAO.email = email;	}
	public static String getSexo() {		return sexo;	}
	public static void setSexo(String sexo) {		ManutencaoClienteDAO.sexo = sexo;	}
	public static String getCelular() {return celular;}
	public static void setCelular(String celular) {ManutencaoClienteDAO.celular = celular;}
	public static String getEmpresa() {return empresa;}
	public static void setEmpresa(String empresa) {ManutencaoClienteDAO.empresa = empresa;}
	public static String getSalario() {return salario;}
	public static void setSalario(String salario) {ManutencaoClienteDAO.salario = salario;}
	public static String getPeriodo() {	return periodo;}
	public static void setPeriodo(String periodo) {ManutencaoClienteDAO.periodo = periodo;}
	public static String getCargo() {return cargo;}
	public static void setCargo(String cargo) {ManutencaoClienteDAO.cargo = cargo;}
	public static String getOrigem() {return origem;}
	public static void setOrigem(String origem) {ManutencaoClienteDAO.origem = origem;}
	public static java.sql.Date getData() {return data;}
	public static void setData(java.sql.Date data) {ManutencaoClienteDAO.data = data;}
	public static int getIdUsuario() {return idUsuario;}
	public static void setIdUsuario(int idUsuario) {ManutencaoClienteDAO.idUsuario = idUsuario;}
	public static String getStatus() {return status;}
	public static void setStatus(String status) {ManutencaoClienteDAO.status = status;}

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
	public static void setCpfValido(boolean cpfValido) {ManutencaoClienteDAO.cpfValido = cpfValido;	}

	public static boolean isCnpjValido() {return cnpjValido;}
	public static void setCnpjValido(boolean cnpjValido) {ManutencaoClienteDAO.cnpjValido = cnpjValido;	}

	public static boolean isEmailValido() {	return emailValido;	}
	public static void setEmailValido(boolean emailValido) {ManutencaoClienteDAO.emailValido = emailValido;	}

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

			pstm.setString(1, ManutencaoClienteDAO.getStatus());
			pstm.setInt(2, ManutencaoClienteDAO.getIdCliente());

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
		
		ManutencaoClienteDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(getSQLencontraNomeCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{								
				ManutencaoClienteDAO.setIdCliente(rs.getInt("IDCLIENTE"));
				ManutencaoClienteDAO.setNome(rs.getString("NOME").trim());
				
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
		
		ManutencaoClienteDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(SQL_CARREGA_CLIENTE);
			pstm.setInt(1, ManutencaoClienteDAO.getIdCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				System.out.println("ACHOU");
				ManutencaoClienteDAO.setClienteValido(true);
									
					ManutencaoClienteDAO.setIdCliente(rs.getInt("IDCLIENTE"));
					ManutencaoClienteDAO.setNome(rs.getString("NOME"));
					ManutencaoClienteDAO.setTipoDeEndereco(rs.getString("TipoEnde"));
					ManutencaoClienteDAO.setLogradouro(rs.getString("Logradouro"));
					ManutencaoClienteDAO.setComplemento(rs.getString("Complemento"));
					ManutencaoClienteDAO.setNumero(rs.getString("Numero"));
					ManutencaoClienteDAO.setCidade(rs.getString("CIDADE"));
					ManutencaoClienteDAO.setUF(rs.getString("UF"));
					//TRATA CEP RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
					ManutencaoClienteDAO.setCEP(rs.getString("CEP"));
					ManutencaoClienteDAO.setTelefone(rs.getString("TELEFONE"));
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
					ManutencaoClienteDAO.setCPF(cpf);
					ManutencaoClienteDAO.setRG(rs.getString("RG"));
					ManutencaoClienteDAO.setEmail(rs.getString("EMAIL"));
					ManutencaoClienteDAO.setSexo(rs.getString("SEXO"));
					
					ManutencaoClienteDAO.setLocalizouCliente(true);
					ManutencaoClienteDAO.setCargo(rs.getString("CARGO").trim());
					ManutencaoClienteDAO.setEmpresa(rs.getString("EMPRESA").trim());
					ManutencaoClienteDAO.setSalario(rs.getString("SALARIO").trim());
					ManutencaoClienteDAO.setOrigem(rs.getString("ORIGEM").trim());
					ManutencaoClienteDAO.setPeriodo(rs.getString("PERIODO").trim());
					if(rs.getString("STATUS").trim().length() == 0)
					{
						ManutencaoClienteDAO.setStatus("CLIENTE NÃO TRABALHADO");
					}
					else
					{
						ManutencaoClienteDAO.setStatus(rs.getString("STATUS").trim());
					}
					ManutencaoClienteDAO.setCelular(rs.getString("CELULAR").trim());
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
		
		ManutencaoClienteDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(SQL_CARREGA_CLIENTE);
			pstm.setInt(1, ManutencaoClienteDAO.getIdCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				ManutencaoClienteDAO.setClienteValido(true);
									
					ManutencaoClienteDAO.setIdCliente(rs.getInt("IDCLIENTE"));
					ManutencaoClienteDAO.setNome(rs.getString("NOME").trim());
					ManutencaoClienteDAO.setTipoDeEndereco(rs.getString("TipoEnde").trim());
					ManutencaoClienteDAO.setLogradouro(rs.getString("Logradouro").trim());
					ManutencaoClienteDAO.setComplemento(rs.getString("Complemento").trim());
					ManutencaoClienteDAO.setNumero(rs.getString("Numero").trim());
					ManutencaoClienteDAO.setCidade(rs.getString("CIDADE").trim());
					ManutencaoClienteDAO.setUF(rs.getString("UF").trim());
					//TRATA CEP RECEBIDO SEM O HIFEM DO BANCO DE DADOS E INSERE APÒS A 5º LETRA
					ManutencaoClienteDAO.setCEP(rs.getString("CEP").substring(0,5) + "-" +  rs.getString("CEP").substring(5,8));
					ManutencaoClienteDAO.setTelefone(rs.getString("TELEFONE").trim());
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
										
					ManutencaoClienteDAO.setCPF(cpf);
					ManutencaoClienteDAO.setRG(rs.getString("RG").trim());
					ManutencaoClienteDAO.setEmail(rs.getString("EMAIL").trim());
					ManutencaoClienteDAO.setSexo(rs.getString("SEXO").trim());
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
			pstm.setString(1, ManutencaoClienteDAO.getEmail().toLowerCase().trim());
			pstm.setString(2, ManutencaoClienteDAO.getCPF());
			resultado = pstm.executeQuery(); 
			ManutencaoClienteDAO.setEmailValido(true);
			if(resultado.next()) 
			{
				ManutencaoClienteDAO.setEmailValido(false);
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
			pstm.setString(1, ManutencaoClienteDAO.getEmail().toLowerCase().trim());
			resultado = pstm.executeQuery(); 
			ManutencaoClienteDAO.setEmailValido(true);
			if(resultado.next()) 
			{
				System.out.println("NOME DO CLIENTE ACHADO + " + resultado.getString("NOME").trim());
				ManutencaoClienteDAO.setEmailValido(false);
				emailExiste = (resultado.getInt("IDCLIENTE")  +" - "+  resultado.getString("NOME").trim());
				ManutencaoClienteDAO.setClienteEncontrado(emailExiste);
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
			pstm.setString(1, ManutencaoClienteDAO.getCPF().toLowerCase().trim());
			resultado = pstm.executeQuery(); 
			ManutencaoClienteDAO.setCpfValido(true);
			if(resultado.next()) 
			{
				ManutencaoClienteDAO.setCpfValido(false);
				CPFExiste = resultado.getInt("IDCLIENTE") +" - " + resultado.getString("NOME").trim();
				ManutencaoClienteDAO.setIdCliente(resultado.getInt("IDCLIENTE"));
				ManutencaoClienteDAO.setEmail(resultado.getString("EMAIL"));
				ManutencaoClienteDAO.setCEP(resultado.getString("CEP"));
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
			pstm.setString(1, ManutencaoClienteDAO.getNome());
			pstm.setString(2, ManutencaoClienteDAO.getCidade());
			pstm.setString(3, ManutencaoClienteDAO.getUF());
			pstm.setString(4, ManutencaoClienteDAO.getCEP());
			pstm.setString(5, ManutencaoClienteDAO.getTelefone());
			pstm.setString(6, ManutencaoClienteDAO.getCargo());
			pstm.setString(7, ManutencaoClienteDAO.getSexo());
			pstm.setDate(8, ManutencaoClienteDAO.getData());
			pstm.setString(9, ManutencaoClienteDAO.getEmail());
			pstm.setString(10, ManutencaoClienteDAO.getTipoDeEndereco());
			pstm.setString(11, ManutencaoClienteDAO.getLogradouro());
			pstm.setString(12, ManutencaoClienteDAO.getComplemento());
			pstm.setString(13, ManutencaoClienteDAO.getNumero());
			pstm.setString(14, ManutencaoClienteDAO.getCPF());
			pstm.setString(15, ManutencaoClienteDAO.getRG());
			pstm.setString(16, ManutencaoClienteDAO.getEmpresa());
			pstm.setString(17, ManutencaoClienteDAO.getSalario());
			pstm.setString(18, ManutencaoClienteDAO.getPeriodo());
			pstm.setString(19, ManutencaoClienteDAO.getCelular());
			pstm.setString(20, ManutencaoClienteDAO.getStatus());
			pstm.setString(21, ManutencaoClienteDAO.getOrigem());
			pstm.setInt(22, SenhaDAO.getIDLogado());
			
			
												
			pstm.executeUpdate();	
			
			 ResultSet rs = pstm.getGeneratedKeys();  
			 if(rs.next())
			 {  
				 System.out.println("GERANDO O ID CLIENTE");
				 //RETORNA O ID DO CLIENTE AO INSERIR PARA O ID ASSINATURA
				 ManutencaoClienteDAO.setIdCliente(rs.getInt(1)); 
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
			pstm.setString(1, ManutencaoClienteDAO.getNome());
			pstm.setString(2, ManutencaoClienteDAO.getCidade());
			pstm.setString(3, ManutencaoClienteDAO.getUF());
			pstm.setString(4, ManutencaoClienteDAO.getCEP());
			pstm.setString(5, ManutencaoClienteDAO.getTelefone());
			pstm.setString(6, ManutencaoClienteDAO.getCargo());
			pstm.setString(7, ManutencaoClienteDAO.getSexo());
			pstm.setDate(8, ManutencaoClienteDAO.getData());
			pstm.setString(9, ManutencaoClienteDAO.getEmail());
			pstm.setString(10, ManutencaoClienteDAO.getTipoDeEndereco());
			pstm.setString(11, ManutencaoClienteDAO.getLogradouro());
			pstm.setString(12, ManutencaoClienteDAO.getComplemento());
			pstm.setString(13, ManutencaoClienteDAO.getNumero());
			pstm.setString(14, ManutencaoClienteDAO.getCPF());
			pstm.setString(15, ManutencaoClienteDAO.getRG());
			pstm.setString(16, ManutencaoClienteDAO.getEmpresa());
			pstm.setString(17, ManutencaoClienteDAO.getSalario());
			pstm.setString(18, ManutencaoClienteDAO.getPeriodo());
			pstm.setString(19, ManutencaoClienteDAO.getCelular());
			pstm.setString(20, ManutencaoClienteDAO.getStatus());
			pstm.setString(21, ManutencaoClienteDAO.getOrigem());
			pstm.setInt(22, SenhaDAO.getIDLogado());
			pstm.setInt(23, ManutencaoClienteDAO.getIdCliente());
															
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
		if(!ManutencaoClienteDAO.getBuscarCliente().equals("SELECT * FROM CLIENTES JOIN Usuarios on (IDUSUARIO = ID) WHERE NOME LIKE \'%NENHUM%\'"))
		{
			ManutencaoClienteDAO.setLocalizouCliente(false);
		}
		
		try 
		{
			System.out.println(ManutencaoClienteDAO.getBuscarCliente());
			pstm = conexao.prepareStatement(ManutencaoClienteDAO.getBuscarCliente());
			rs = pstm.executeQuery();
			while(rs.next())
			{
				ManutencaoClienteDAO.setLocalizouCliente(true);
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
			pstm.setInt(1, ManutencaoClienteDAO.getIdCliente());
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
								
				for (int i = 0; i < ManutencaoClienteDAO.clientesPesquisados.size(); i++)
				{
					Cliente cliente =  ManutencaoClienteDAO.clientesPesquisados.get(i);
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
