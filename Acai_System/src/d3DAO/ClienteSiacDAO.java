package d3DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ConexaoBancoDados.parametroConexao;
import Entity.Cliente;
import Entity.ClienteSiac;
import Interface.IManutencaoClienteSiac;
import d3Validacoes.ValidaDataVencimento;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ClienteSiacDAO implements IManutencaoClienteSiac{
	
	private static String  nome, celular, observacao, agencia, conta, telefone, Banco, operador, dataIncial, dataFinal;
	private static int idCliente, idFilial;
	private static Date dataInclusao;
	private static int quantidadeAgendamentos;
			
	public static String getNome() {return nome;}
	public static void setNome(String nome) {ClienteSiacDAO.nome = nome;}
	public static String getObservacao() {return observacao;}
	public static void setObservacao(String observacao) {ClienteSiacDAO.observacao = observacao;}
	public static String getAgencia() {return agencia;}
	public static void setAgencia(String agencia) {ClienteSiacDAO.agencia = agencia;}
	public static String getConta() {return conta;}
	public static void setConta(String conta) {ClienteSiacDAO.conta = conta;}
	public static String getTelefone() {return telefone;}
	public static void setTelefone(String telefone) {ClienteSiacDAO.telefone = telefone;}
	public static int getIdCliente() {return idCliente;}
	public static void setIdCliente(int idCliente) {ClienteSiacDAO.idCliente = idCliente;}
	public static String getBanco() {return Banco;}
	public static void setBanco(String banco) {Banco = banco;}
	public static String getOperador() {return operador;}
	public static void setOperador(String operador) {ClienteSiacDAO.operador = operador;}
	public static Date getDataInclusao() {return dataInclusao;}
	public static void setDataInclusao(Date dataInclusao) {ClienteSiacDAO.dataInclusao = dataInclusao;}
	public static String getDataIncial() {return dataIncial;}
	public static void setDataIncial(String dataIncial) {ClienteSiacDAO.dataIncial = dataIncial;}
	public static String getDataFinal() {return dataFinal;}
	public static void setDataFinal(String dataFinal) {ClienteSiacDAO.dataFinal = dataFinal;}
	public static String getCelular() {return celular;}
	public static void setCelular(String celular) {ClienteSiacDAO.celular = celular;}
	public static int getIdFilial() {return idFilial;}
	public static void setIdFilial(int idFilial) {ClienteSiacDAO.idFilial = idFilial;}

	private static String buscarCliente;
	public static String getBuscarCliente() {return buscarCliente;}
	public static void setBuscarCliente(String buscarCliente) {ClienteSiacDAO.buscarCliente = buscarCliente;}

	public static List<ClienteSiac> clientesLista = new ArrayList<ClienteSiac>();
	Cliente clientes = new Cliente();
	
	private static final String SQL_INSERT_CLIENTE = 
			"insert into Clientes (Nome, Telefone, Celular, Obs, Agencia, Conta, Banco, DataInclusao, Operador, IDFilial) values (?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_CLIENTE = 
			"UPDATE Clientes SET  Nome = ?, Telefone = ?, Celular = ?, Obs = ?, Agencia = ?, Conta = ?, Banco = ?, IDFilial=? WHERE IDCliente = ?";

	private static final String SQL_CARREGA_CLIENTE = 
			"SELECT * FROM Clientes WHERE IDCliente = ? ORDER BY Nome DESC";
	
	private static final String SQL_DELETE_CLIENTE = 
			"DELETE FROM Clientes WHERE IDCliente = ?";

	private static boolean ClienteValido;
	public static boolean isClienteValido(){return ClienteValido;}
	public static void setClienteValido(boolean clienteValido)	{ClienteValido = clienteValido;	}
	
	private static boolean LocalizouCliente;
	public static boolean isLocalizouCliente(){return LocalizouCliente;}
	public static void setLocalizouCliente(boolean localizouCliente){LocalizouCliente = localizouCliente;}
	
	private static String SQLencontraNomeCliente = " ";
	public static String getSQLencontraNomeCliente(){return SQLencontraNomeCliente;}
	public static void setSQLencontraNomeCliente(String sQLencontraNomeCliente){SQLencontraNomeCliente = sQLencontraNomeCliente;}
	
	private static boolean arquivoGerado;
	public static boolean isArquivoGerado() {return arquivoGerado;}
	public static void setArquivoGerado(boolean arquivoGerado) {ClienteSiacDAO.arquivoGerado = arquivoGerado;}
	
	private static final String SQL_UPDATE_CLIENTE_AGENDAMENTO = 
			"UPDATE Clientes SET DataInclusao = ?, Operador = ? WHERE IDCliente = ?";
	
	public void ValidarCliente() 
	{					
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		
		ClienteSiacDAO.setClienteValido(false);
		
		try 
		{
			pstm = conexao.prepareStatement(ClienteSiacDAO.getSQLencontraNomeCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				ClienteSiacDAO.setClienteValido(true);
				ClienteSiacDAO.setIdCliente(rs.getInt("IDCliente"));
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
	
	public void gerarArquivoXLS() 
	{
		String caminho = null;
	    JFileChooser chooser = new JFileChooser();
	    setArquivoGerado(false);	    

	    int retorno = chooser.showSaveDialog(null);
	    if (retorno==JFileChooser.APPROVE_OPTION)
	    {
	    	setArquivoGerado(true);	
	    	caminho = chooser.getSelectedFile().getAbsolutePath()+".xls"; 

	       try {
	   	 		//CRIAÇÂO DO ARQUIVO
	   	   		HSSFWorkbook wb = new HSSFWorkbook();  

	   	   		//CRIAÇÂO DA PLANILHA ABA
	  	   		HSSFSheet sheet = wb.createSheet("Relatório de Clientes ");   
	  	   		
	  	   	//CRIA A PRIMEIRA LINHA DA TABELA CABEÇALHO
	            HSSFRow titulo = sheet.createRow((short)0);
	            //Cria as células na linha
	            titulo.createCell(0).setCellValue("Relatórios de Clientes - SIAC");
	            
	            HSSFRow parametros = sheet.createRow((short)1);
	            //Cria as células na linha
	            parametros.createCell(0).setCellValue("Usuário");
	            parametros.createCell(1).setCellValue(ClienteSiacDAO.getOperador());
	            parametros.createCell(3).setCellValue("Data Inicial");
	            parametros.createCell(4).setCellValue(ClienteSiacDAO.getDataIncial());
	            parametros.createCell(6).setCellValue("Data Final");
	            parametros.createCell(7).setCellValue(ClienteSiacDAO.getDataFinal());
  	                		                
	  	   		//CRIA A PRIMEIRA LINHA DA TABELA CABEÇALHO
	            HSSFRow cabecalho = sheet.createRow((short)3);
	            //Cria as células na linha
	            cabecalho.createCell(0).setCellValue("Código");
	            cabecalho.createCell(1).setCellValue("Nome");
	            cabecalho.createCell(2).setCellValue("Telefone");
	            cabecalho.createCell(3).setCellValue("Celular");
	            cabecalho.createCell(4).setCellValue("Observação");
	            cabecalho.createCell(5).setCellValue("Operador");
	            cabecalho.createCell(6).setCellValue("Banco");
	            cabecalho.createCell(7).setCellValue("Agencia");
	            cabecalho.createCell(8).setCellValue("Conta");
	            cabecalho.createCell(9).setCellValue("Data Inclusao");
	            cabecalho.createCell(10).setCellValue("Filial");
	            
	        	SimpleDateFormat formatobrasileiro = new SimpleDateFormat("dd/MM/yyyy");
	        	               	                
	            for (int i = 0; i < ClienteSiacDAO.clientesLista.size(); i++)
	         	{
	                ClienteSiac linhasArquivo =  ClienteSiacDAO.clientesLista.get(i);         
	            	HSSFRow dados = sheet.createRow((short)(i + 5));
	    			//Nas células a seguir vc substitui pelos valores das notas
	    			dados.createCell(0).setCellValue(linhasArquivo.getIdCliente());
	    			dados.createCell(1).setCellValue(linhasArquivo.getNome());
	    			dados.createCell(2).setCellValue(linhasArquivo.getTelefone());
	    			dados.createCell(3).setCellValue(linhasArquivo.getCelular());
	    			dados.createCell(4).setCellValue(linhasArquivo.getObs());
	    			dados.createCell(5).setCellValue(linhasArquivo.getOperador());
	    			dados.createCell(6).setCellValue(linhasArquivo.getBanco());
	    			dados.createCell(7).setCellValue(linhasArquivo.getAgencia());
	    			dados.createCell(8).setCellValue(linhasArquivo.getConta());
	    			dados.createCell(9).setCellValue(formatobrasileiro.format(linhasArquivo.getDataInclusao()));
	    			dados.createCell(8).setCellValue(linhasArquivo.getIdFilial());    					    				
	    			
	    			try (FileOutputStream fileOut = new FileOutputStream(caminho)) 
	               	{
	                   	wb.write(fileOut);
	              	}  
	    		}  
	                
	            HSSFRow dados = sheet.createRow((short)(ClienteSiacDAO.clientesLista.size() + 5));
    			//Nas células a seguir vc substitui pelos valores das notas
    			dados.createCell(0).setCellValue("Quantidade Clientes :");
    			dados.createCell(1).setCellValue(quantidadeAgendamentos);

    			   				
    			try (FileOutputStream fileOut = new FileOutputStream(caminho)) 
               	{
                   	wb.write(fileOut);
               	}  
 	       } 
	       catch (Exception e) 
	       {
	       		e.printStackTrace();
	           throw new RuntimeException();
	       }
	    }
	}
	
	public void excluirCliente() 
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{	
			pstm = conexao.prepareStatement(SQL_DELETE_CLIENTE);
			pstm.setInt(1, getIdCliente());
			pstm.executeUpdate();	
		}
		catch (SQLException se)
		  {
			System.out.println("Erro de conexão com o Banco de Dados " + se);
			se.printStackTrace();
		  }
			finally
			{
			parametroConexao.close(conexao, pstm, null);
			}
	}
	
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
				//Cell coluna2 = posicao.getCell(1, i);
				//Cell coluna3 = posicao.getCell(2, i);
				//Cell coluna4 = posicao.getCell(3, i);  
				Cell coluna5 = posicao.getCell(4, i);
				Cell coluna6 = posicao.getCell(5, i);
				Cell coluna7 = posicao.getCell(6, i);  
				Cell coluna8 = posicao.getCell(7, i);
				Cell coluna9 = posicao.getCell(8, i);
			
				if(coluna1.getContents().trim().toUpperCase().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "Erro na Linha " + i + ". Este Erro encontra-se na Coluna Nome, que está em Branco");
					continue;
				}
				else
				{
					setNome(coluna1.getContents().toUpperCase().trim()); 
				}

				setTelefone(coluna5.getContents().toUpperCase().trim() + " "); 
				setObservacao(coluna6.getContents().toUpperCase().trim() + " "); 
				setBanco(coluna7.getContents().toUpperCase().trim() + " "); 
				setAgencia(coluna8.getContents().toUpperCase().trim() + " "); 
				setConta(coluna9.getContents().toUpperCase().trim() + " "); 
				
				/*CONVERSÂO DE DATA PARA GRAVAR CLIENTE*/
				java.util.Date data = new java.util.Date();
				Date dataAtual = new Date(data.getTime());
				setDataInclusao(dataAtual);
				setOperador("IMPORTAÇÂO");

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
	
	public void CarregaClienteSemOperador() 
	{					
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		
		ClienteSiacDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(SQL_CARREGA_CLIENTE);
			pstm.setInt(1, ClienteSiacDAO.getIdCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				ClienteSiacDAO.setClienteValido(true);
				ClienteSiacDAO.setNome(rs.getString("Nome").trim());
				ClienteSiacDAO.setTelefone(" ");
				ClienteSiacDAO.setCelular(" ");
				ClienteSiacDAO.setObservacao(rs.getString("Obs").trim());
				ClienteSiacDAO.setAgencia(rs.getString("Agencia").trim());
				ClienteSiacDAO.setConta(rs.getString("Conta").trim());
				ClienteSiacDAO.setBanco(rs.getString("Banco").trim());
				ClienteSiacDAO.setIdFilial(rs.getInt("IDFilial"));
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
		
	public void CarregaCliente() 
	{					
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		
		ClienteSiacDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(SQL_CARREGA_CLIENTE);
			pstm.setInt(1, ClienteSiacDAO.getIdCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				ClienteSiacDAO.setClienteValido(true);
				ClienteSiacDAO.setNome(rs.getString("Nome").trim());
				
				/*TRATAMENTO TELEFONE PADROA NOVO*/
				if(rs.getString("Telefone").trim().length() == 11)
				{
					ClienteSiacDAO.setTelefone( "(" +rs.getString("Telefone").trim().substring(0, 2) + ") " + rs.getString("Telefone").trim().substring(2, 7) + "-" + rs.getString("Telefone").trim().substring(7, 11));
				}
				else if(rs.getString("Telefone").trim().length() == 10)
				{
					ClienteSiacDAO.setTelefone( "(" +rs.getString("Telefone").trim().substring(0, 2) + ") " + rs.getString("Telefone").trim().substring(2, 6) + "-" + rs.getString("Telefone").trim().substring(6, 10));
				}
				else if(rs.getString("Telefone").trim().length() == 8)
				{
					ClienteSiacDAO.setTelefone(rs.getString("Telefone").trim().substring(0, 4) + "-" + rs.getString("Telefone").trim().substring(4, 8));
				}
				else
				{
					ClienteSiacDAO.setTelefone(rs.getString("Telefone").trim());
				}
				
				/*TRATANDO CAMPO CELULAR PARA RECEBER ERROS*/
				if(rs.getString("Celular").trim().length() == 11)
				{
					ClienteSiacDAO.setCelular( "(" +rs.getString("Celular").trim().substring(0, 2) + ") " + rs.getString("Celular").trim().substring(2, 7) + "-" + rs.getString("Celular").trim().substring(7, 11));
				}
				else if(rs.getString("Celular").trim().length() == 10)
				{
					ClienteSiacDAO.setCelular( "(" +rs.getString("Celular").trim().substring(0, 2) + ") " + rs.getString("Celular").trim().substring(2, 6) + "-" + rs.getString("Celular").trim().substring(6, 10));
				}
				else if(rs.getString("Celular").trim().length() == 8)
				{
					ClienteSiacDAO.setCelular(rs.getString("Celular").trim().substring(0, 4) + "-" + rs.getString("Celular").trim().substring(4, 8));
				}
				else
				{
					ClienteSiacDAO.setCelular(rs.getString("Celular").trim());
				}

				ClienteSiacDAO.setObservacao(rs.getString("Obs").trim());
				ClienteSiacDAO.setAgencia(rs.getString("Agencia").trim());
				ClienteSiacDAO.setConta(rs.getString("Conta").trim());
				ClienteSiacDAO.setBanco(rs.getString("Banco").trim());
				ClienteSiacDAO.setIdFilial(rs.getInt("IDFilial"));
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
	
	public void IserirCliente()
	{
		ClienteSiacDAO.setClienteValido(true);		
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{	
			pstm = conexao.prepareStatement(SQL_INSERT_CLIENTE , Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, ClienteSiacDAO.getNome());
			pstm.setString(2, ClienteSiacDAO.getTelefone());
			pstm.setString(3, ClienteSiacDAO.getCelular());
			pstm.setString(4, ClienteSiacDAO.getObservacao());
			pstm.setString(5, ClienteSiacDAO.getAgencia());
			pstm.setString(6, ClienteSiacDAO.getConta());
			pstm.setString(7, ClienteSiacDAO.getBanco());
			
			java.util.Date data = new java.util.Date();
			Date dataAtual = new Date(data.getTime());
			ClienteSiacDAO.setDataInclusao(dataAtual);
			pstm.setDate(8, ClienteSiacDAO.getDataInclusao());
			pstm.setString(9, ClienteSiacDAO.getOperador());
			pstm.setInt(10, ClienteSiacDAO.getIdFilial());
		
	
			pstm.executeUpdate();	
			
			 ResultSet rs = pstm.getGeneratedKeys();  
			 if(rs.next())
			 {  
				 //RETORNA O ID DO CLIENTE AO INSERIR PARA O ID ASSINATURA
				 ClienteSiacDAO.setIdCliente(rs.getInt(1)); 
				 System.out.println("RECEBEU O ID " + rs.getInt(1));
			 }   
			 else
			 {
				 System.out.println("GERANDO O ID CLIENTE ERRADO");
			 }
		}
		catch (SQLException se)
		{
			ClienteSiacDAO.setClienteValido(false);
			System.out.println("Erro de conexão com o Banco de Dados" + se);
		}
		finally
		{
			parametroConexao.close(conexao, pstm, null);
		}
	}
	
	public void AtualizarCliente()
	{
		ClienteSiacDAO.setClienteValido(true);
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{	
			pstm = conexao.prepareStatement(SQL_UPDATE_CLIENTE);
			pstm.setString(1, ClienteSiacDAO.getNome());
			pstm.setString(2, ClienteSiacDAO.getTelefone());
			pstm.setString(3, ClienteSiacDAO.getCelular());
			pstm.setString(4, ClienteSiacDAO.getObservacao());
			pstm.setString(5, ClienteSiacDAO.getAgencia());
			pstm.setString(6, ClienteSiacDAO.getConta());
			pstm.setString(7, ClienteSiacDAO.getBanco());
			pstm.setInt(8, ClienteSiacDAO.getIdFilial());
			pstm.setInt(9, ClienteSiacDAO.getIdCliente());
															
			pstm.executeUpdate();
			
		}
		catch (SQLException se)
		  {
			ClienteSiacDAO.setClienteValido(false);
			System.out.println("Erro de conexão com o Banco de Dados - ATUALIZAR" + se);
		  }
			finally
			{
			parametroConexao.close(conexao, pstm, null);
			}
	}
	
	
	@Override
	public List<ClienteSiac> findAllClienteSiac() {
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		List<ClienteSiac> clientes = new ArrayList<ClienteSiac>();
		ResultSet rs = null ;
		ClienteSiacDAO.setLocalizouCliente(false);
		quantidadeAgendamentos= 0;
		clientesLista.clear();

		SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dataAtual = new java.util.Date();
		String dataFinal = formatoSQL.format(dataAtual.getTime());
		String ano = dataFinal.substring(0,4);
		String mes = dataFinal.substring(5,7);
		String dia = dataFinal.substring(8,10);;
										
		ValidaDataVencimento validar = new ValidaDataVencimento();
		
		validar.setAno(Integer.parseInt(ano));
		validar.setDia(Integer.parseInt(dia));
		validar.setMes(Integer.parseInt(mes)-1);
		validar.validaData();
			
		try 
		{
			
			System.out.println("ANTES DO DAO " + ClienteSiacDAO.getBuscarCliente());
			pstm = conexao.prepareStatement(ClienteSiacDAO.getBuscarCliente());
			rs = pstm.executeQuery();
			while(rs.next())
			{
				ClienteSiacDAO.setLocalizouCliente(true);
				ClienteSiac clientesCadastrados = new ClienteSiac();
				clientesCadastrados.setIdCliente(rs.getInt("IDCliente"));
				clientesCadastrados.setNome(rs.getString("Nome").trim());
				clientesCadastrados.setDataInclusao(rs.getDate("DataInclusao"));
				
				if(!SenhaDAO.getDireitosAtribuidos().contains("2200") && !SenhaDAO.getLogin().equals(rs.getString("Operador").trim()) && !rs.getString("Operador").trim().equals("NENHUM"))
				{
					if(clientesCadastrados.getDataInclusao().before(validar.getDataCorrigida()))
					{
						clientesCadastrados.setCelular(rs.getString("Celular").trim());
						clientesCadastrados.setTelefone(rs.getString("Telefone").trim());
						clientesCadastrados.setOperador(rs.getString("Operador").trim());
					}
					else
					{
						/*TRATAMENTO TELEFONE PADROA NOVO*/
						clientesCadastrados.setCelular("");
						clientesCadastrados.setTelefone("");
						clientesCadastrados.setOperador("");
					}
				}
				else
				{
					clientesCadastrados.setCelular(rs.getString("Celular").trim());
					clientesCadastrados.setTelefone(rs.getString("Telefone").trim());
					clientesCadastrados.setOperador(rs.getString("Operador").trim());
				}
				
				clientesCadastrados.setObs(rs.getString("Obs").trim());
				clientesCadastrados.setAgencia(rs.getString("Agencia").trim());
				clientesCadastrados.setConta(rs.getString("Conta").trim());
				clientesCadastrados.setBanco(rs.getString("Banco").trim());
				clientesCadastrados.setIdFilial(rs.getInt("IDFilial"));
						
				quantidadeAgendamentos += 1;
								
				clientesLista.add(clientesCadastrados);
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
			} catch (SQLException e1) 
			{
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
			
		
	@Override
	public List<ClienteSiac> findAllPesquisaClienteSiac()
	{
		return clientesLista;
	}
}
