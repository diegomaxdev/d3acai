package d3DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ConexaoBancoDados.parametroConexao;
import Entity.Cliente_INCPP;
import Interface.IManutencaoCliente_INCPP;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ManutencaoCliente_INCPPDAO implements IManutencaoCliente_INCPP{
	
	private static int idCliente;
	private static String nome;
	private static String conta;
	private static String valor;
	private static String arquivo;
	private static String pasta;
	private static String local;
	private static java.sql.Date dateInclusao;
	private static String data;
		
	public static int getIdCliente() {return idCliente;}
	public static void setIdCliente(int idCliente) {ManutencaoCliente_INCPPDAO.idCliente = idCliente;}
	public static String getNome() {return nome;}
	public static void setNome(String nome) {ManutencaoCliente_INCPPDAO.nome = nome;}
	public static String getConta() {return conta;}
	public static void setConta(String conta) {ManutencaoCliente_INCPPDAO.conta = conta;}
	public static String getValor() {return valor;}
	public static void setValor(String valor) {ManutencaoCliente_INCPPDAO.valor = valor;}
	public static String getArquivo() {return arquivo;}
	public static void setArquivo(String arquivo) {ManutencaoCliente_INCPPDAO.arquivo = arquivo;}
	public static String getPasta() {return pasta;}
	public static void setPasta(String pasta) {ManutencaoCliente_INCPPDAO.pasta = pasta;}
	public static String getLocal() {return local;}
	public static void setLocal(String local) {ManutencaoCliente_INCPPDAO.local = local;}
	public static java.sql.Date getDateInclusao() {return dateInclusao;}
	public static void setDateInclusao(java.sql.Date dateInclusao) {ManutencaoCliente_INCPPDAO.dateInclusao = dateInclusao;}
	public static String getData() {return data;}
	public static void setData(String data) {ManutencaoCliente_INCPPDAO.data = data;}

	private static String buscarCliente;
	public static String getBuscarCliente() {return buscarCliente;}
	public static void setBuscarCliente(String buscarCliente) {ManutencaoCliente_INCPPDAO.buscarCliente = buscarCliente;}

	public static List<Cliente_INCPP> clientesPesquisados = new ArrayList<Cliente_INCPP>();
	Cliente_INCPP clientes = new Cliente_INCPP();
	
	private static final String SQL_INSERT_CLIENTE = 
			"insert into Clientes_INCPP (NOME, CONTA , VALOR, ARQUIVO, PASTA, LOCAL, INCLUSAO) values (?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_CLIENTE = 
			"UPDATE Clientes_INCPP SET NOME = ?, CONTA = ?, VALOR = ?, ARQUIVO = ?, PASTA = ? , LOCAL = ?, INCLUSAO = ? WHERE IDCLIENTE = ?";

	private static final String SQL_PESQUISA_CLIENTES = 
			"SELECT * FROM Clientes_INCPP WHERE IDCLIENTE = ? ORDER BY IDCLIENTE DESC";
	
	private static final String SQL_CARREGA_CLIENTE = 
			"SELECT * FROM Clientes_INCPP WHERE IDCLIENTE = ? ORDER BY IDCLIENTE DESC";
	
	private static String SQLencontraNomeCliente = " ";
	public static String getSQLencontraNomeCliente(){return SQLencontraNomeCliente;}
	public static void setSQLencontraNomeCliente(String sQLencontraNomeCliente){SQLencontraNomeCliente = sQLencontraNomeCliente;}
	
	private static boolean ClienteValido;
	public static boolean isClienteValido(){return ClienteValido;}
	public static void setClienteValido(boolean clienteValido)	{ClienteValido = clienteValido;	}
	
	SimpleDateFormat formatobrasileiro = new SimpleDateFormat("dd/MM/yyyy");
	
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
		
		ManutencaoCliente_INCPPDAO.setLocal("" + arquivo.getName());
		System.out.println(ManutencaoCliente_INCPPDAO.getLocal());

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
							
				if(coluna1.getContents().trim().toUpperCase().length() == 0)
				{
					continue;
				}
				else
				{
					setNome(coluna1.getContents().toUpperCase().trim()); 
				}

				setConta(coluna2.getContents().toUpperCase().trim() + " "); 
				setValor(coluna3.getContents().toUpperCase().trim() + " "); 
				setArquivo(coluna4.getContents().toUpperCase().trim() + " "); 
				setPasta(coluna5.getContents().toUpperCase().trim() + " "); 
				
			

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
		
	
	/**
	 * CLASSE USADA PARA EMISSÂO DE ARQUIVOS TXT DEVIDO A NECESSIDADE DOS BRANCOS
	 */
	public void CarregaClienteCompleto() 
	{					
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null ;
		
		ManutencaoCliente_INCPPDAO.setClienteValido(false);
		try 
		{
			pstm = conexao.prepareStatement(SQL_CARREGA_CLIENTE);
			pstm.setInt(1, ManutencaoCliente_INCPPDAO.getIdCliente());
			rs = pstm.executeQuery();
			if(rs.next())
			{
				System.out.println("ACHOU");
				ManutencaoCliente_INCPPDAO.setClienteValido(true);
									
					ManutencaoCliente_INCPPDAO.setIdCliente(rs.getInt("IDCLIENTE"));
					ManutencaoCliente_INCPPDAO.setNome(rs.getString("NOME"));
					ManutencaoCliente_INCPPDAO.setConta(rs.getString("CONTA ").trim());
					ManutencaoCliente_INCPPDAO.setValor(rs.getString("VALOR").trim());
					ManutencaoCliente_INCPPDAO.setArquivo(rs.getString("ARQUIVO").trim());
					ManutencaoCliente_INCPPDAO.setPasta(rs.getString("PASTA").trim());
					ManutencaoCliente_INCPPDAO.setLocal(rs.getString("LOCAL").trim());
					ManutencaoCliente_INCPPDAO.setDateInclusao(rs.getDate("INCLUSAO"));
					ManutencaoCliente_INCPPDAO.setData(formatobrasileiro.format(rs.getDate("INCLUSAO")));
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
	
	
	public void IserirCliente()
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{	
			pstm = conexao.prepareStatement(SQL_INSERT_CLIENTE , Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, ManutencaoCliente_INCPPDAO.getNome());
			pstm.setString(2, ManutencaoCliente_INCPPDAO.getConta());
			pstm.setString(3, ManutencaoCliente_INCPPDAO.getValor());
			pstm.setString(4, ManutencaoCliente_INCPPDAO.getArquivo());
			pstm.setString(5, ManutencaoCliente_INCPPDAO.getPasta());
			pstm.setString(6, ManutencaoCliente_INCPPDAO.getLocal());
			
			//CONVERSÃO PARA AS DATAS NO FORMATO SQL APÓS TRATAMENTO
			Date dataAtual = new Date();
			java.sql.Date dataCadastroSQL = new java.sql.Date(dataAtual.getTime()); 
			setDateInclusao(dataCadastroSQL); 
			
			pstm.setDate(7, ManutencaoCliente_INCPPDAO.getDateInclusao());
														
			pstm.executeUpdate();	
			
			 ResultSet rs = pstm.getGeneratedKeys();  
			 if(rs.next())
			 {  
				 System.out.println("GERANDO O ID CLIENTE");
				 //RETORNA O ID DO CLIENTE AO INSERIR PARA O ID ASSINATURA
				 ManutencaoCliente_INCPPDAO.setIdCliente(rs.getInt(1)); 
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
	
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try 
		{	
			//UPDATE Clientes SET NOME = ?, CIDADE = ?, UF = ?, CEP = ?, TELEFONE = ? , CELULAR = ?, CARGO = ?, SEXO = ?, DATA = ? , EMAIL = ?, TipoEnde = ?, Logradouro = ?, Complemento = ?, Numero = ?, CPF = ?, RG = ?, SALARIO = ?, PERIODO = ?, CELULAR = ?, STATUS = ?, ORIGEM = ?, IDUSUARIO = ?  WHERE IDCLIENTE = ?
			
			pstm = conexao.prepareStatement(SQL_UPDATE_CLIENTE);
			pstm.setString(1, ManutencaoCliente_INCPPDAO.getNome());
			pstm.setString(2, ManutencaoCliente_INCPPDAO.getConta());
			pstm.setString(3, ManutencaoCliente_INCPPDAO.getValor());
			pstm.setString(4, ManutencaoCliente_INCPPDAO.getArquivo());
			pstm.setString(5, ManutencaoCliente_INCPPDAO.getPasta());
			pstm.setString(6, ManutencaoCliente_INCPPDAO.getLocal());
			
			Date dataAtual = new Date();
			java.sql.Date dataCadastroSQL = new java.sql.Date(dataAtual.getTime()); 
			setDateInclusao(dataCadastroSQL); 
			
			pstm.setDate(7, ManutencaoCliente_INCPPDAO.getDateInclusao());
			pstm.setInt(8, ManutencaoCliente_INCPPDAO.getIdCliente());
															
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
	public List<Cliente_INCPP> findAllClientes() {
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		List<Cliente_INCPP> clientes = new ArrayList<Cliente_INCPP>();
		ResultSet rs = null ;
		
		
		/*AVISA AO NÂO LOCALIZAR NENHUM CLIENTE*/
		if(!ManutencaoCliente_INCPPDAO.getBuscarCliente().equals("SELECT * FROM Clientes_INCPP WHERE NOME LIKE \'%NENHUM%\'"))
		{
			ManutencaoCliente_INCPPDAO.setClienteValido(false);
		}
		
		try 
		{
			pstm = conexao.prepareStatement(ManutencaoCliente_INCPPDAO.getBuscarCliente());
			rs = pstm.executeQuery();
			while(rs.next())
			{
				ManutencaoCliente_INCPPDAO.setClienteValido(true);
				Cliente_INCPP clientesCadastrados = new Cliente_INCPP();
				clientesCadastrados.setIdCliente(rs.getInt("IDCLIENTE"));
				clientesCadastrados.setNome(rs.getString("NOME").trim());
				clientesCadastrados.setConta(rs.getString("CONTA ").trim());
				clientesCadastrados.setValor(rs.getString("VALOR").trim());
				clientesCadastrados.setArquivo(rs.getString("ARQUIVO").trim());
				clientesCadastrados.setPasta(rs.getString("PASTA").trim());
				clientesCadastrados.setLocal(rs.getString("LOCAL").trim());
				clientesCadastrados.setData(formatobrasileiro.format(rs.getDate("INCLUSAO")));
				
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
		SimpleDateFormat formatobrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		
		try 
		{					
			pstm = conexao.prepareStatement(SQL_PESQUISA_CLIENTES);
			pstm.setInt(1, ManutencaoCliente_INCPPDAO.getIdCliente());
			rs = pstm.executeQuery();
		
			while(rs.next())
			{
				clientes.setIdCliente(rs.getInt("IDCLIENTE"));
				clientes.setNome(rs.getString("NOME").trim());
				clientes.setConta(rs.getString("CONTA ").trim());
				clientes.setValor(rs.getString("VALOR").trim());
				clientes.setArquivo(rs.getString("ARQUIVO").trim());
				clientes.setPasta(rs.getString("PASTA").trim());
				clientes.setLocal(rs.getString("LOCAL").trim());
				clientes.setData(formatobrasileiro.format(rs.getDate("INCLUSAO")));
					
				//VERIFICA A LISTA PARA NÂO HAVER DUPLICIDADE
				boolean clienteNalista = false;
								
				for (int i = 0; i < ManutencaoCliente_INCPPDAO.clientesPesquisados.size(); i++)
				{
					Cliente_INCPP cliente =  ManutencaoCliente_INCPPDAO.clientesPesquisados.get(i);
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
	public List<Cliente_INCPP> findAllPesquisaClientes()
	{
		return clientesPesquisados;
	}
}
