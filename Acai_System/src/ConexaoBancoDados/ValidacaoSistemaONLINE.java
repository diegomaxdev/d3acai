package ConexaoBancoDados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class ValidacaoSistemaONLINE
{
	private static String empresa;
	private static Date DataValidade;
	private static int statusSistema;
	private static boolean sistemaHabilitado;
		
	public static Date getDataValidade()	{	return DataValidade;	}
	public static void setDataValidade(Date dataAtual){	DataValidade = dataAtual;	}
	public static int getStatusSistema(){	return statusSistema;}
	public static void setStatusSistema(int statusSistema){	ValidacaoSistemaONLINE.statusSistema = statusSistema;}
	public static boolean isSistemaHabilitado(){return sistemaHabilitado;	}
	public static void setSistemaHabilitado(boolean sistemaHabilitado)	{	ValidacaoSistemaONLINE.sistemaHabilitado = sistemaHabilitado;}
	public static String getEmpresa(){	return empresa;	}
	public static void setEmpresa(String empresa){	ValidacaoSistemaONLINE.empresa = empresa;	}
	
	private static final String SQL_VALIDA_SISTEMA = 
			"Select * from Validacao where IDCliente = 1";

	public void ValidaLicenca()
	{					
		Connection conexaoOnLine = parametroConexao.getConnection_Validacao();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try 
		{
			pstm = conexaoOnLine.prepareStatement(SQL_VALIDA_SISTEMA);
			//VALIDA APENAS UM USUARIO DO RETORNO IF, ESTE VEM ATRAVÈS DO RESULT SET DA QUERY
			rs = pstm.executeQuery(); 
			if(rs.next()) 
			{
				System.out.println("BUSCANDO VALIDAÇÂO INTERNA");
				ValidacaoSistemaONLINE.setDataValidade(rs.getDate("DataInclusao"));
			
				SimpleDateFormat formatobrasileiro = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date dataAtual = new java.util.Date();
				java.sql.Date dataAtualSQL = new java.sql.Date(dataAtual.getTime());  
					
				if(ValidacaoSistemaONLINE.getDataValidade().after(dataAtualSQL) || formatobrasileiro.format(ValidacaoSistemaONLINE.getDataValidade()).equals(formatobrasileiro.format(dataAtualSQL)))
				{
					ValidacaoSistemaONLINE.setSistemaHabilitado(true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "\n Este programa Expirou na Data de : " + formatobrasileiro.format(ValidacaoSistemaONLINE.getDataValidade()) + "\n Favor Contactar o Administrador do Sistema" );
					return;
				}
			}
			else
			{
				ValidacaoSistemaONLINE.setSistemaHabilitado(false);
				JOptionPane.showMessageDialog(null," Inconsistência de Empresa no Sistema ");
				return;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Erro de conexão com o Banco de Dados");
		}
		
		finally
		{
			parametroConexao.close(conexaoOnLine, pstm, null);
		}
	}
}
