package d3DAO;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ConexaoBancoDados.parametroConexao;
import Entity.Produto;
import Interface.IProduto;

public class ProdutosDAO implements IProduto {

	private static String descricao, categoria;
	private static BigDecimal valor;
	private static int idProduto, ativo;
	private static float quantidade;
	private static Date dataInclusao;

	public static String getDescricao() {return descricao;}
	public static void setDescricao(String descricao) {ProdutosDAO.descricao = descricao;}
	public static String getCategoria() {return categoria;}
	public static void setCategoria(String categoria) {ProdutosDAO.categoria = categoria;}
	public static BigDecimal getValor() {return valor;}
	public static void setValor(BigDecimal valor) {ProdutosDAO.valor = valor;}
	public static int getIdProduto() {return idProduto;}
	public static void setIdProduto(int idProduto) {ProdutosDAO.idProduto = idProduto;}
	public static int getAtivo() {return ativo;}
	public static void setAtivo(int ativo) {ProdutosDAO.ativo = ativo;}
	public static float getQuantidade() {return quantidade;}
	public static void setQuantidade(float quantidade) {ProdutosDAO.quantidade = quantidade;}
	public static Date getDataInclusao() {return dataInclusao;}
	public static void setDataInclusao(Date dataInclusao) {ProdutosDAO.dataInclusao = dataInclusao;}
	
	private static String buscarCliente;
	public static String getBuscarProdutos() {return buscarCliente;}
	public static void setBuscarCliente(String buscarCliente) {ProdutosDAO.buscarCliente = buscarCliente;}

	public static List<Produto> produtosLista = new ArrayList<Produto>();
	Produto produto = new Produto();

	private static final String SQL_INSERT_PRODUTO = "insert into Produtos (Descricao, Categoria, Valor, Ativo, Quantidade, data) values (?,?,?,?,?,?)";

	private static final String SQL_UPDATE_PRODUTO = "UPDATE Produtos SET  Descricao = ?, Categoria = ?, Valor = ?, Ativo = ?, Quantidade = ? WHERE idProduto = ?";

	private static final String SQL_CARREGA_PRODUTO = "SELECT * FROM Produtos WHERE idProduto = ? ORDER BY Descricao DESC";

	private static final String SQL_DELETE_PRODUTO = "DELETE FROM Produtos WHERE idProduto = ?";
	
	private static final String SQL_UPDATE_PRODUTO_ESTOQUE = "UPDATE Produtos SET Quantidade = ? WHERE idProduto = ?";

	private static boolean ProdutoValido;
	public static boolean isProdutoValido() {return ProdutoValido;}
	public static void setProdutoValido(boolean produtoValido) {ProdutoValido = produtoValido;}
	
	private static boolean arquivoGerado;
	public static boolean isArquivoGerado() {return arquivoGerado;}
	public static void setArquivoGerado(boolean arquivoGerado) {ProdutosDAO.arquivoGerado = arquivoGerado;}

	public void ValidarProduto() {
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ProdutosDAO.setProdutoValido(true);
		
		try 
		{
			pstm = conexao.prepareStatement("SELECT * FROM Produtos WHERE Descricao = ?");
			pstm.setString(1, ProdutosDAO.getDescricao());
			rs = pstm.executeQuery();
			if (rs.next()) 
			{
				ProdutosDAO.setProdutoValido(false);
				ProdutosDAO.setIdProduto(rs.getInt("IDCliente"));
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão com o Banco de Dados " + e);
		}

		finally 
		{
			parametroConexao.close(conexao, pstm, rs);
		}
	}

	public void gerarArquivoXLS() {
		String caminho = null;
		JFileChooser chooser = new JFileChooser();
		setArquivoGerado(false);

		int retorno = chooser.showSaveDialog(null);
		if (retorno == JFileChooser.APPROVE_OPTION) {
			setArquivoGerado(true);
			caminho = chooser.getSelectedFile().getAbsolutePath() + ".xls";

			try {
				// CRIAÇÂO DO ARQUIVO
				HSSFWorkbook wb = new HSSFWorkbook();

				// CRIAÇÂO DA PLANILHA ABA
				HSSFSheet sheet = wb.createSheet("Relatório de Produtos ");

				// CRIA A PRIMEIRA LINHA DA TABELA CABEÇALHO
				HSSFRow titulo = sheet.createRow((short) 0);
				// Cria as células na linha
				titulo.createCell(0).setCellValue("Relatórios de Produtos - D3");

				// CRIA A PRIMEIRA LINHA DA TABELA CABEÇALHO
				HSSFRow cabecalho = sheet.createRow((short) 2);
				// Cria as células na linha
				cabecalho.createCell(0).setCellValue("idProduto");
				cabecalho.createCell(1).setCellValue("Descrição");
				cabecalho.createCell(2).setCellValue("Categoria");
				cabecalho.createCell(3).setCellValue("Valor");
				cabecalho.createCell(4).setCellValue("Ativo");
				cabecalho.createCell(5).setCellValue("Quantidade");
				cabecalho.createCell(6).setCellValue("data");


				SimpleDateFormat formatobrasileiro = new SimpleDateFormat("dd/MM/yyyy");

				for (int i = 0; i < ProdutosDAO.produtosLista.size(); i++) {
					Produto linhasArquivo = ProdutosDAO.produtosLista.get(i);
					HSSFRow dados = sheet.createRow((short) (i + 3));
					// Nas células a seguir vc substitui pelos valores das notas
					dados.createCell(0).setCellValue(linhasArquivo.getIdProduto());
					dados.createCell(1).setCellValue(linhasArquivo.getDescricao());
					dados.createCell(2).setCellValue(linhasArquivo.getCategoria());
					dados.createCell(3).setCellValue(NumberFormat.getCurrencyInstance().format(linhasArquivo.getValor()));
					dados.createCell(4).setCellValue(linhasArquivo.getAtivo());
					dados.createCell(5).setCellValue(linhasArquivo.getQuantidade());
					dados.createCell(6).setCellValue(formatobrasileiro.format(linhasArquivo.getDataInclusao()));
			
					try (FileOutputStream fileOut = new FileOutputStream(caminho)) {
						wb.write(fileOut);
					}
				}

				try (FileOutputStream fileOut = new FileOutputStream(caminho)) {
					wb.write(fileOut);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	public void excluirProduto() {
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try {
			pstm = conexao.prepareStatement(SQL_DELETE_PRODUTO);
			pstm.setInt(1, getIdProduto());
			pstm.executeUpdate();
		} catch (SQLException se) {
			System.out.println("Erro de conexão com o Banco de Dados " + se);
			se.printStackTrace();
		} finally {
			parametroConexao.close(conexao, pstm, null);
		}
	}

	//"insert into Produtos (Descricao, Categoria, Valor, Ativo, Quantidade, data) values (?,?,?,?,?,?)";
	public void IserirProduto() 
	{
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try {
			pstm = conexao.prepareStatement(SQL_INSERT_PRODUTO, Statement.RETURN_GENERATED_KEYS);
			pstm.setString(1, ProdutosDAO.getDescricao());
			pstm.setString(2, ProdutosDAO.getCategoria());
			pstm.setBigDecimal(3, ProdutosDAO.getValor());
			pstm.setInt(4, ProdutosDAO.getAtivo());
			pstm.setFloat(5, ProdutosDAO.getQuantidade());
			java.util.Date data = new java.util.Date();
			Date dataAtual = new Date(data.getTime());
			ProdutosDAO.setDataInclusao(dataAtual);
			pstm.setDate(6, ProdutosDAO.getDataInclusao());
			pstm.executeUpdate();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				// RETORNA O ID DO CLIENTE AO INSERIR PARA O ID ASSINATURA
				ProdutosDAO.setIdProduto(rs.getInt(1));
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

	//"UPDATE Produtos SET  Descricao = ?, Categoria = ?, Valor = ?, Ativo = ?, Quantidade = ? WHERE idProduto = ?";
	public void AtualizarProduto() {
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		try {
			pstm = conexao.prepareStatement(SQL_UPDATE_PRODUTO);
			pstm.setString(1, ProdutosDAO.getDescricao());
			pstm.setString(2, ProdutosDAO.getCategoria());
			pstm.setBigDecimal(3, ProdutosDAO.getValor());
			pstm.setInt(4, ProdutosDAO.getAtivo());
			pstm.setFloat(5, ProdutosDAO.getQuantidade());
			pstm.setInt(6, ProdutosDAO.getIdProduto());


			pstm.executeUpdate();

		} 
		catch (SQLException se) 
		{
			System.out.println("Erro de conexão com o Banco de Dados - ATUALIZAR" + se);
		} finally {
			parametroConexao.close(conexao, pstm, null);
		}
	}

	@Override
	public List<Produto> findAllPesquisaProduto() {
		Connection conexao = parametroConexao.getConnection_Inicial();
		PreparedStatement pstm = null;
		List<Produto> produtos = new ArrayList<Produto>();
		ResultSet rs = null;
		produtosLista.clear();

		try 
		{

			System.out.println("ANTES DO DAO " + ProdutosDAO.getBuscarProdutos());
			pstm = conexao.prepareStatement(ProdutosDAO.getBuscarProdutos());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Produto produtoCadastrados = new Produto();
				produtoCadastrados.setIdProduto(rs.getInt("idProduto"));
				produtoCadastrados.setDescricao(rs.getString("Descricao").trim());
				produtoCadastrados.setDataInclusao(rs.getDate("data"));
				produtoCadastrados.setValor(rs.getBigDecimal("Valor"));
				produtoCadastrados.setAtivo(rs.getInt("Ativo"));
				produtoCadastrados.setQuantidade(rs.getInt("Quantidade"));
				produtoCadastrados.setCategoria(rs.getString("Categoria").trim());

				produtosLista.add(produtoCadastrados);
				produtos.add(produtoCadastrados);
			}
		} catch (SQLException e) {
			try {
				if (conexao != null) {
					// RETORNA O BANCO AO ESTADO ANTERIOR
					conexao.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				parametroConexao.close(conexao, pstm, rs);
			}
			System.out.println("Erro ao conectar a Base de Dados" + e);
		}
		return produtos;
	}

	@Override
	public List<Produto> findAllProduto() {
		return produtosLista;
	}
}
