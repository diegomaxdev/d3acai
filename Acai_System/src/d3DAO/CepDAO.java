package d3DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConexaoBancoDados.parametroConexao;
import Entity.Cep;
import Interface.ICep;


public class CepDAO implements ICep {

		private static String CepDigitado;
		private boolean CepValido;
		private static String codigoPostal;
		private static String tipo;
		private static String logradouro;
		private static String complemento;
		private static String bairro;
		private static String cidade;
		private static String estado;
		private static String consultaCEP;
				
		public static String getConsultaCEP() {return consultaCEP;}
		public static void setConsultaCEP(String consultaCEP) {CepDAO.consultaCEP = consultaCEP;}
		public static String getCodigoPostal() {return codigoPostal;}
		public static void setCodigoPostal(String codigoPostal) {CepDAO.codigoPostal = codigoPostal;}
		public static String getTipo() {return tipo;}
		public static void setTipo(String tipo) {CepDAO.tipo = tipo;}		
		public static String getLogradouro() {return logradouro;}
		public static void setLogradouro(String logradouro) {CepDAO.logradouro = logradouro;}
		public static String getComplemento() {return complemento;}
		public static void setComplemento(String complemento) {CepDAO.complemento = complemento;}
		public static String getBairro() {return bairro;}
		public static void setBairro(String bairro) {CepDAO.bairro = bairro;}
		public static String getCidade() {return cidade;}
		public static void setCidade(String cidade) {CepDAO.cidade = cidade;}
		public static String getEstado() {return estado;}
		public static void setEstado(String estado) {CepDAO.estado = estado;}
		public boolean getCEPValido() {return CepValido;}
		public void setCEPValido(boolean CepValido) {this.CepValido = CepValido;}
		public static String getCepDigitado() {return CepDigitado;}
		public static void setCepDigitado(String cepDigitado) {CepDigitado = cepDigitado;}
		
		private static File arquivoSelecionado;
		public static File getArquivoSelecionado(){	return arquivoSelecionado;}
		public static void setArquivoSelecionado(File arquivoSelecionado){CepDAO.arquivoSelecionado = arquivoSelecionado;	}
				
		private static final String SQL_CONSULTA_CEP = 
				"SELECT * FROM Cep WHERE Cep = ?";
		
		private static final String SQL_INSERT_CEP = 
				"insert into Cep (Estado , Cep , Cidade, Bairro, Tipo, Logradouro, Complemento) values (?,?,?,?,?,?,?)";
		
		private static final String SQL_DELETE_LIMPEZABASE_CEP = 
				"DELETE FROM Cep";
		
		public void LimpaBaseOnLine() 
		{
			Connection conexao = parametroConexao.getConnection_Inicial();
			PreparedStatement pstm = null;
			try 
			{
				pstm = conexao.prepareStatement(SQL_DELETE_LIMPEZABASE_CEP);
				pstm.executeUpdate();
			}
			catch (SQLException se)
			{
				System.out.println("Erro de conexão com o Banco de Dados " + se);
			}
			finally
			{
				parametroConexao.close(conexao, pstm, null);
			}
		}
		
		public void IserirCep()
		{
			Connection conexao = parametroConexao.getConnection_Inicial();
			PreparedStatement pstm = null;
			try 
			{		// Criando uma conexão com o banco - através do parâmetro passado pela classe parametroConexao
				pstm = conexao.prepareStatement(SQL_INSERT_CEP);
				pstm.setString(1, getEstado());
				pstm.setString(2, getCepDigitado());
				pstm.setString(3, getCidade());
				pstm.setString(4, getBairro());
				pstm.setString(5, getTipo());
				pstm.setString(6, getLogradouro());
				pstm.setString(7, getComplemento());
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
		
		public void CEPExiste() 
		{					
			Connection conexao = parametroConexao.getConnection_Inicial();
			PreparedStatement pstm = null;
			ResultSet rs = null ;
			
			this.setCEPValido(false);
			try 
			{
				pstm = conexao.prepareStatement(SQL_CONSULTA_CEP);
				pstm.setString(1, getCepDigitado().trim());
				rs = pstm.executeQuery();
				if(rs.next())
				{
					this.setCEPValido(true);
					setCodigoPostal(rs.getString("Cep").trim());
					setTipo(rs.getString("Tipo").trim());
					setLogradouro(rs.getString("Logradouro").trim());
					setComplemento(rs.getString("Complemento").trim());
					setBairro(rs.getString("Bairro").trim());
					setCidade(rs.getString("Cidade").trim());
					setEstado(rs.getString("Estado").trim());
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
		
		
		public void LocalizarCEP() 
		{					
			Connection conexao = parametroConexao.getConnection_Inicial();
			PreparedStatement pstm = null;
			ResultSet rs = null ;
			
			this.setCEPValido(false);
			try 
			{
				pstm = conexao.prepareStatement(SQL_CONSULTA_CEP);
				pstm.setString(1, getCepDigitado().trim());
				rs = pstm.executeQuery();
				if(rs.next())
				{
					this.setCEPValido(true);
					setCodigoPostal(rs.getString("Cep").trim());
					setTipo(rs.getString("Tipo").trim());
					setLogradouro(rs.getString("Logradouro").trim());
					setComplemento(rs.getString("Complemento").trim());
					setBairro(rs.getString("Bairro").trim());
					setCidade(rs.getString("Cidade").trim());
					setEstado(rs.getString("Estado").trim());
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
		
		
		@Override
		public List<Cep> findAllCep() {
			Connection conexao = parametroConexao.getConnection_Inicial();
			PreparedStatement pstm = null;
			 List<Cep> EndereçoCorreio = new ArrayList<Cep>();
			ResultSet rs = null ;
			
			if(CepDAO.getConsultaCEP() == null)
			{
				CepDAO.setConsultaCEP("SELECT * FROM Cep WHERE Cep = 00000000");
			}
			
			try 
			{
				pstm = conexao.prepareStatement(CepDAO.getConsultaCEP().trim());
				rs = pstm.executeQuery();
				while(rs.next())
				{
					Cep enderecos = new Cep();
					enderecos.setCodigo(rs.getString("Cep").trim());
					enderecos.setTipo(rs.getString("Tipo").trim());
					enderecos.setLogradouro(rs.getString("Logradouro").trim());
					enderecos.setComplemento(rs.getString("Complemento").trim());
					enderecos.setBairro(rs.getString("Bairro").trim());
					enderecos.setCidade(rs.getString("Cidade").trim());
					enderecos.setEstado(rs.getString("Estado").trim());
			
					EndereçoCorreio.add(enderecos);
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
			System.out.println("Erro ao conectar a Base de Dados");
			}
			return EndereçoCorreio;
		}

		public void AtualizarBaseCEP() throws IOException 
		{	
			/*LIMPA A BASE DE CEP*/
			LimpaBaseOnLine();
		
			//VARRE ARQUIVOS DOS ESTADOS
			ArquivosEstados();
			
			//VARRE ARQUIVOS DOS GRANDES USUARIOS
			ArquivoGrandesUsuarios();
						
			//VARRE ARQUIVOS DAS GRANDES LOCALIZADES
			ArquivoLocalizades();
			
			//ARQUIVO DE UNIDADES OPERACIONAIS
			ArquivoUnidadesOperacionais();
			
			//ARQUIVO DE UNIDADES OPERACIONAIS
			ArquivoCaixasPostais();
		}
		
		public void ArquivosEstados() throws IOException 
		{		
			/*CRIA_SE UMA RAAY COM TODOS OS ESTADOS*/
			String[] estados = new String[27];
			estados[0] = "AC";
			estados[1] = "AL";
			estados[2] = "AP";
			estados[3] = "AM";
			estados[4] = "BA";
			estados[5] = "CE";
			estados[6] = "DF";
			estados[7] = "ES";
			estados[8] = "GO";
			estados[9] = "MA";
			estados[10] = "MT";
			estados[11] = "MS";
			estados[12] = "MG";
			estados[13] = "PA";
			estados[14] = "PB";
			estados[15] = "PR";
			estados[16] = "PE";
			estados[17] = "PI";
			estados[18] = "RJ";
			estados[19] = "RN";
			estados[20] = "RS";
			estados[21] = "RO";
			estados[22] = "RR";
			estados[23] = "SC";
			estados[24] = "SP";
			estados[25] = "SE";
			estados[26] = "TO";

		    for (int i = 0; i < estados.length; i++)
			{
		    	String estado = estados[i];
		    	 // CepDAO.setArquivoSelecionado(abertura.getSelectedFile()); 
		  			
				FileInputStream stream = new FileInputStream("\\\\192.168.0.24\\Sistemas\\Correios\\Fixo\\DNE_GU_" + estado + "_LOGRADOUROS.TXT");
				InputStreamReader reader = new InputStreamReader(stream);
				BufferedReader br = new BufferedReader(reader);
				String linha = br.readLine();
											
				while(linha != null) 
				{	
					System.out.println("LENDO O ARQUIVO");
					if(!linha.substring(0,1).equals("C") && !linha.substring(0,1).equals("#"))
					{	
						//VALIDANDO AS 10 POSIçõES DE CONTROLE
						String SiglaUF = linha.substring(1, 3);
						String NomeLocalidade = linha.substring(17, 89);
						String Bairro = linha.substring(102, 174);
						String TipoLogradouro = linha.substring(259, 285);
						String Preposição = linha.substring(285, 288);
						String titulo = linha.substring(288, 360);
						
						String Logradouro = "";
						if(titulo.trim().length() > 0)
						{
							Logradouro = (Preposição.trim() + " "+ titulo.trim() + " " + linha.substring(374, 446).trim()).trim();
						}
						else
						{
							Logradouro = (Preposição.trim() + " " + linha.substring(374, 446).trim()).trim();
						}
					
						String informacaoAdicional = linha.substring(482, 518);
						String CEP = linha.substring(518, 526);
															
						System.out.println(CEP);
						System.out.println(Logradouro);
						System.out.println(SiglaUF);
								
						setEstado(SiglaUF.trim());
						setCepDigitado(CEP.trim());
						setCidade(NomeLocalidade.trim());
						setBairro(Bairro.trim());
						setTipo(TipoLogradouro.trim());
						setLogradouro(Logradouro);
						setComplemento(informacaoAdicional.trim() + " ");
						/*ROTINA DE INSERÇÂO APOS LEITURA*/
						IserirCep();
					}

					linha = br.readLine();
				}
						
			br.close();
			stream.close();
		}	
	}
	
	public void ArquivoGrandesUsuarios() throws IOException 
	{	
	    //ARQUIVO DE GRANDES USUÁRIOS
	    FileInputStream stream = new FileInputStream("\\\\192.168.0.24\\Sistemas\\Correios\\Fixo\\DNE_GU_GRANDES_USUARIOS.TXT");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
	
		while(linha != null) 
		{	
			System.out.println("LENDO O ARQUIVO GRANDES USUARIOS");
			String Complemento = "";
			if(!linha.substring(0,1).equals("C") && !linha.substring(0,1).equals("E") && !linha.substring(0,1).equals("#"))
			{	
				//VALIDANDO AS 10 POSIçõES DE CONTROLE
				String SiglaUF = linha.substring(1, 3);
				String Cidade = linha.substring(17, 89);
				String Bairro = linha.substring(102, 174);
				String NomeGrandeUsuario = linha.substring(188, 260);
				String CEP = linha.substring(260, 268);
				/*INICIA O COMPLEMENTO COM O NOME DA EMPRESA*/
				Complemento = NomeGrandeUsuario.trim();
										
				setEstado(SiglaUF.trim());
				setCidade(Cidade.trim());
				setCepDigitado(CEP.trim());
				setBairro(Bairro);
			}
			else if(linha.substring(0,1).equals("E"))
			{
				String TipoLogradouro = linha.substring(15, 87);
				String Preposição = linha.substring(87, 90);
				String titulo = linha.substring(90, 162);
				String nomeLogradouro = linha.substring(176, 248);
				/*COMPLETA O COMPLEMENTO ATRAVES DA LINHA DE INFORMAÇÂO INICIADA COM A LETRA E*/
				Complemento += linha.substring(259, 295).trim() + " " + linha.substring(295, 306).trim() + " " + linha.substring(306, 342).trim() + " " + linha.substring(342, 353).trim() + " " + linha.substring(353, 389).trim() + " " + linha.substring(389, 425).trim();
			
				setTipo(TipoLogradouro.trim());
				if(titulo.trim().length() > 0)
				{
					setLogradouro((Preposição.trim() + " " + titulo.trim() + " " + nomeLogradouro).trim());
				}
				else
				{
					setLogradouro((Preposição.trim() + " " + nomeLogradouro).trim());
				}
				setComplemento(Complemento);
				/*ROTINA DE INSERÇÂO APOS LEITURA*/
				IserirCep();
			}

			linha = br.readLine();
		//FIM WHILE DOS GRANDES USUARIOS
		}
			
		br.close();
		stream.close();
	}	
	
	public void ArquivoLocalizades() throws IOException 
	{				
		//ARQUIVO DE GRANDES USUÁRIOS
		FileInputStream stream = new FileInputStream("\\\\192.168.0.24\\Sistemas\\Correios\\Fixo\\DNE_GU_LOCALIDADES.TXT");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
							
		while(linha != null) 
		{	
			System.out.println("LENDO O ARQUIVO LOCALIDADES");
			String Complemento = " ";
			if(linha.substring(0,1).equals("D"))
			{	
				//VALIDANDO AS 10 POSIçõES DE CONTROLE
				String SiglaUF = linha.substring(3, 5).trim();
				String Cidade = linha.substring(19, 91).trim();
				String Bairro = " ";
				String CEP = linha.substring(91, 99);
									
				setEstado(SiglaUF.trim());
				setCidade(Cidade.trim());
				setCepDigitado(CEP.trim());
				setBairro(Bairro);
			
				String TipoLogradouro = " ";
				String nomeLogradouro = " ";
				String tipoRegiao = linha.substring(135, 136);;
				switch (tipoRegiao)
				{
				case "D":
					tipoRegiao = "Distrito";
					break;
				case "P":
					tipoRegiao = "Povoado";
					break;
				case "R":
					tipoRegiao = "Região";
					break;
				case "A":
					tipoRegiao = "Administrativa";
					break;	

				default:
					tipoRegiao = " ";
					break;
				}
				
				String NumeroIBGE = linha.substring(154, 161);
				/*COMPLETA O COMPLEMENTO ATRAVES DA LINHA DE INFORMAÇÂO INICIADA COM A LETRA E*/
				Complemento = tipoRegiao.trim();
				
				if(NumeroIBGE.trim().length() > 0)
				{
					Complemento += ("Código IBGE do Município " + NumeroIBGE).trim();
				}
				
				setTipo(TipoLogradouro);
				setLogradouro(nomeLogradouro);
				setComplemento(Complemento);
				/*ROTINA DE INSERÇÂO APOS LEITURA*/
				IserirCep();
			}
			linha = br.readLine();
		//FIM DO ARQUIVO DE LOCALIZADES	
		}		
	
		br.close();
		stream.close();
	}
	
	public void ArquivoUnidadesOperacionais() throws IOException 
	{				
		//ARQUIVO DE UNIDADES OPERACIONAIS
		FileInputStream stream = new FileInputStream("\\\\192.168.0.24\\Sistemas\\Correios\\Fixo\\DNE_GU_UNIDADES_OPERACIONAIS.TXT");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
	
		while(linha != null) 
		{	
			System.out.println("LENDO O ARQUIVO UNIDADES OPERACIONAIS");
			String Complemento = "";
			if(!linha.substring(0,1).equals("C") && !linha.substring(0,1).equals("E") && !linha.substring(0,1).equals("#"))
			{	
				//VALIDANDO AS 10 POSIçõES DE CONTROLE
				String SiglaUF = linha.substring(1, 3);
				String Cidade = linha.substring(17, 89);
				String Bairro = linha.substring(102, 174);
				String NomeUnidadeOperacional = linha.substring(268, 340).trim();
				String CEP = linha.substring(246, 254);
				/*INICIA O COMPLEMENTO COM O NOME DA EMPRESA*/
				Complemento = NomeUnidadeOperacional;
										
				setEstado(SiglaUF.trim());
				setCidade(Cidade.trim());
				setCepDigitado(CEP.trim());
				setBairro(Bairro);
			}
			else if(linha.substring(0,1).equals("E"))
			{
				String TipoLogradouro = linha.substring(15, 87);
				String Preposição = linha.substring(87, 90);
				String titulo = linha.substring(90, 162);
				String nomeLogradouro = linha.substring(176, 248);
				
				/*COMPLETA O COMPLEMENTO ATRAVES DA LINHA DE INFORMAÇÂO INICIADA COM A LETRA E*/
				Complemento += linha.substring(259, 295).trim() + " " + linha.substring(295, 306).trim() + " " + linha.substring(306, 342).trim() + " " + linha.substring(342, 353).trim() + " " + linha.substring(353, 389).trim() + " " + linha.substring(389, 425).trim();
			
				setTipo(TipoLogradouro.trim());
				if(titulo.trim().length() > 0)
				{
					setLogradouro((Preposição.trim() + " " + titulo.trim() + " " + nomeLogradouro).trim());
				}
				else
				{
					setLogradouro((Preposição.trim() + " " + nomeLogradouro).trim());
				}
				setComplemento(Complemento.trim());
				/*ROTINA DE INSERÇÂO APOS LEITURA*/
				IserirCep();
			}

			linha = br.readLine();
		//FIM WHILE DOS GRANDES USUARIOS
		}
			
		br.close();
		stream.close();
			
	}
	
	public void ArquivoCaixasPostais() throws IOException 
	{				
		//ARQUIVO DE UNIDADES OPERACIONAIS
		FileInputStream stream = new FileInputStream("\\\\192.168.0.24\\Sistemas\\Correios\\Fixo\\DNE_GU_CAIXAS_POSTAIS_COMUNITA.TXT");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
	
		while(linha != null) 
		{	
			System.out.println("LENDO O ARQUIVO CAIXAS POSTAIS");
			if(linha.substring(0,1).equals("D"))
			{	
				//VALIDANDO AS 10 POSIçõES DE CONTROLE
				String SiglaUF = linha.substring(1, 3);
				String Cidade = linha.substring(17, 89);
				String Bairro = " ";
				String NomePontoaixaPostal = linha.substring(97, 169).trim();
				String CEP = linha.substring(89, 97);
				String TipoLogradouro = "Cx. Postal";
				String nomeLogradouro = linha.substring(177, 249);
											
				setEstado(SiglaUF.trim());
				setCidade(Cidade.trim());
				setCepDigitado(CEP.trim());
				setBairro(Bairro);
				setTipo(TipoLogradouro.trim());
				setLogradouro(nomeLogradouro.trim());
				setComplemento(NomePontoaixaPostal.trim());
				/*ROTINA DE INSERÇÂO APOS LEITURA*/
				IserirCep();
			}

			linha = br.readLine();
		//FIM WHILE DOS GRANDES USUARIOS
		}
			
		br.close();
		stream.close();
			
	}

}
