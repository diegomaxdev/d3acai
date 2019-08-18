package d3Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import PadroesDesign.PadroesDesign;
import d3DAO.CepDAO;
import d3DAO.ManutencaoClienteDAO;
import d3DAO.SenhaDAO;
import d3Validacoes.CPFDocument;
import d3Validacoes.SomenteNumeros;
import d3Validacoes.Validar;
import d3Validacoes.limiteDeCampoTexto;
import d3Validacoes.textEmail;

public class ManutencaoClienteFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textNome;
	public static JTextField textTipoEndereco;
	private JLabel lblTipoDeEndereco;
	public static JTextField textLogradouro;
	private JLabel lblLogradouro;
	private JLabel lblNmero;
	private JTextField textNumero;
	private JLabel lblComplemento;
	public static JTextField textComplemento;
	private JLabel lblCidade;
	public static JTextField textCidade;
	private JLabel lblUf;
	public static JTextField textUF;
	private JLabel lblCelular;
	private JTextField textCelular;
	public static JFormattedTextField textCPF;
	public static JFormattedTextField textCEP;
	private JLabel lblCpf;
	private JLabel lblTelefone;
	private JTextField textTelefone;
	private JLabel lblEmail;
	private JTextField textEmail;
	private JLabel lblSexo;
	private JLabel lblPeriodo;
	private JLabel lblCargo;
	public static JTextField textCargo;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnClienteInternacional;
	private JDateChooser dateInclusao;
	private JComboBox comboBoxSexo;
	private JLabel lblRg;
	private JTextField textRG ;
	Date DataCadastro;
	private static String CPF, CEP;
	private static String CPFEntrada, emailEntrada;
	private JLabel lblLegendaObrigatorios;
	private JTextField textLegendaObrigatorios;
	private JLabel lblNaoEditaveis;
	private JTextField textNaoEditaveis;
	private JButton btnLiberarEndereco;
	private JLabel lblEmpresa, lblSalario, lblOrigem;
	private JTextField textPeriodo, textEmpresa, textSalario, textOrigem;

	private static int rotinaExecutada;
	public int getRotinaExecutada() {return rotinaExecutada;}
	public static void setRotinaExecutada(int rotinaExecutada) {ManutencaoClienteFrame.rotinaExecutada = rotinaExecutada;}

	public ManutencaoClienteFrame() 
	{
		setType(Type.POPUP);
		setTitle("MANUTEN\u00C7\u00C2O DE CLIENTE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		ClassLoader loader = getClass().getClassLoader();
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setForeground(PadroesDesign.CORLABEL);
		lblCdigo.setFont(PadroesDesign.FONTELABEL);
		lblCdigo.setBounds(17, 21, 55, 16);
		desktopPane.add(lblCdigo);
		
		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setToolTipText("Automático - Campo de Identifica\u00E7\u00E3o do Cliente.");
		textCodigo.setForeground(Color.DARK_GRAY);
		textCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		textCodigo.setFont(PadroesDesign.FONTETEXT);
		textCodigo.setBounds(17, 41, 91, 28);
		desktopPane.add(textCodigo);
		textCodigo.setColumns(1);
	
		JLabel lblNome = new JLabel("Nome");
		lblNome.setForeground(PadroesDesign.CORLABEL);
		lblNome.setFont(PadroesDesign.FONTELABEL);
		lblNome.setBounds(120, 21, 55, 16);
		desktopPane.add(lblNome);
		
		textNome = new JTextField();
		textNome.setDocument(new limiteDeCampoTexto(50));
		textNome.addFocusListener(new CamposObrigatorios());
		textNome.setToolTipText("Campo para o nome do Cliente.");
		textNome.setHorizontalAlignment(SwingConstants.LEFT);
		textNome.setForeground(Color.DARK_GRAY);
		textNome.setFont(PadroesDesign.FONTETEXT);
		textNome.setColumns(1);
		textNome.setBounds(120, 41, 476, 28);
		desktopPane.add(textNome);
		
		lblSexo = new JLabel("Sexo");
		lblSexo.setForeground(PadroesDesign.CORLABEL);
		lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblSexo.setBounds(606, 21, 140, 16);
		desktopPane.add(lblSexo);
		
		comboBoxSexo = new JComboBox();
		comboBoxSexo.setEnabled(true);
		comboBoxSexo.setForeground(Color.DARK_GRAY);
		comboBoxSexo.setToolTipText("Sexo do Cliente");
		comboBoxSexo.setFont(PadroesDesign.FONTELABEL);
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino",""}));
		comboBoxSexo.setBounds(606, 41, 150, 28);
		desktopPane.add(comboBoxSexo);
		
		JLabel lblDataModifica = new JLabel("Data Inclus\u00E3o");
		lblDataModifica.setForeground(PadroesDesign.CORLABEL);
		lblDataModifica.setFont(PadroesDesign.FONTELABEL);
		lblDataModifica.setBounds(766, 21, 90, 16);
		desktopPane.add(lblDataModifica);
		
		Date dataAtual = new Date();
		dateInclusao = new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
		dateInclusao.setDate(dataAtual);
		dateInclusao.setFocusable(false);
		dateInclusao.setEnabled(false);
		dateInclusao.setBounds(766, 41, 142, 28);
		desktopPane.add(dateInclusao);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(PadroesDesign.CORLABEL);
		lblEmail.setFont(PadroesDesign.FONTELABEL);
		lblEmail.setBounds(17, 71, 74, 16);
		desktopPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setDocument(new textEmail(50));
		textEmail.addFocusListener(new CampoEmail());
		textEmail.addFocusListener(new CamposObrigatorios());
		textEmail.setToolTipText("Campo para o e-mail do cliente, usado na busca ao receber e-mails.");
		textEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textEmail.setForeground(Color.DARK_GRAY);
		textEmail.setFont(PadroesDesign.FONTETEXT);
		textEmail.setColumns(1);
		textEmail.setBounds(17, 91, 463, 28);
		desktopPane.add(textEmail);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setForeground(PadroesDesign.CORLABEL);
		lblCpf.setFont(PadroesDesign.FONTELABEL);
		lblCpf.setBounds(490, 71, 129, 16);
		desktopPane.add(lblCpf);
				
		textCPF = new JFormattedTextField();  
		try 
		{
			textCPF.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		textCPF.setEnabled(true);
		textCPF.setDocument(new CPFDocument(14));
		textCPF.addFocusListener(new CampoCPF());
		textCPF.addFocusListener(new CamposObrigatorios());
		textCPF.setToolTipText("Campo CPF - obrigatório e unico para a identificação deste cliente");
		textCPF.setHorizontalAlignment(SwingConstants.LEFT);
		textCPF.setForeground(Color.DARK_GRAY);
		textCPF.setFont(PadroesDesign.FONTETEXT);
		textCPF.setColumns(1);
		textCPF.setBounds(490, 91, 200, 28);
		desktopPane.add(textCPF);
		
		lblRg = new JLabel("RG");
		lblRg.setForeground(PadroesDesign.CORLABEL);
		lblRg.setFont(PadroesDesign.FONTELABEL);
		lblRg.setBounds(700, 71, 129, 16);
		desktopPane.add(lblRg);
		
		textRG = new JTextField();
		textRG.setEnabled(true);
		textRG.setDocument(new limiteDeCampoTexto(12));
		textRG.setToolTipText("Campo para o RG - este registro é estadual e sua quantidade de caracteres podem variar");
		textRG.setHorizontalAlignment(SwingConstants.LEFT);
		textRG.setForeground(Color.DARK_GRAY);
		textRG.setFont(PadroesDesign.FONTETEXT);
		textRG.setColumns(1);
		textRG.setBounds(700, 91, 212, 28);
		desktopPane.add(textRG);
									
		JLabel lblCep = new JLabel("CEP");
		lblCep.setForeground(PadroesDesign.CORLABEL);
		lblCep.setFont(PadroesDesign.FONTELABEL);
		lblCep.setBounds(17, 121, 91, 16);
		desktopPane.add(lblCep);
		
		textCEP = new JFormattedTextField();  
		//USADO PARA CAMPOS QUE JÀ VEM PREENCHIDOS, CASO NÂO FAÇA ESTA CONDIÇÂO ELE IRIA APLICAR A MASCARA EM TODOS E APAGARIA O CONTEÙDO RECEBIDO
		if(rotinaExecutada == 1 || textCEP.getText().length()== 0){		
			try {
				textCEP.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#####-###")));
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//textCEP.setDocument(new CEPDocument(9));
		//DESABILITA O USO DO TAB DO CAMPO CEP
		//textCEP.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET); 
		textCEP.addFocusListener(new CampoCEP());
		textCEP.addFocusListener(new CamposObrigatorios());
		textCEP.setToolTipText("Campo para preenchimento do CEP");
		textCEP.setHorizontalAlignment(SwingConstants.LEFT);
		textCEP.setForeground(Color.DARK_GRAY);
		textCEP.setFont(PadroesDesign.FONTETEXT);
		textCEP.setColumns(1);
		textCEP.setBounds(17, 141, 123, 28);
		desktopPane.add(textCEP);
		
		JButton btnConfirma = new JButton(new ImageIcon(loader.getResource("img/ok.png")));
		btnConfirma.addActionListener(new BotaoCarregarCEPListener());
		btnConfirma.setToolTipText("Carregar o Endereço de Acordo com o CEP Digitado");
		btnConfirma.setBounds(145, 141, 28, 28);
		desktopPane.add(btnConfirma);
			
		lblTipoDeEndereco = new JLabel("Tipo de Endere\u00E7o");
		lblTipoDeEndereco.setForeground(PadroesDesign.CORLABEL);
		lblTipoDeEndereco.setFont(PadroesDesign.FONTELABEL);
		lblTipoDeEndereco.setBounds(183, 121, 98, 16);
		desktopPane.add(lblTipoDeEndereco);
				
		textTipoEndereco = new JTextField();
		textTipoEndereco.setEditable(false);
		textTipoEndereco.setDocument(new limiteDeCampoTexto(20));
		textTipoEndereco.setToolTipText("Campo para o o endereço atual do cliente segundo os correios - preenchido através do CEP.");
		textTipoEndereco.setHorizontalAlignment(SwingConstants.LEFT);
		textTipoEndereco.setForeground(Color.DARK_GRAY);
		textTipoEndereco.setFont(PadroesDesign.FONTETEXT);
		textTipoEndereco.setColumns(1);
		textTipoEndereco.setBounds(183, 141, 177, 28);
		desktopPane.add(textTipoEndereco);
				
		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setForeground(PadroesDesign.CORLABEL);
		lblLogradouro.setFont(PadroesDesign.FONTELABEL);
		lblLogradouro.setBounds(370, 121, 91, 16);
		desktopPane.add(lblLogradouro);
		
		textLogradouro = new JTextField();
		textLogradouro.setEditable(false);
		textLogradouro.setDocument(new limiteDeCampoTexto(50));
		textLogradouro.setToolTipText("Campo para refer\u00EAncia na entrega - preenchido através do CEP");
		textLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
		textLogradouro.setForeground(Color.DARK_GRAY);
		textLogradouro.setFont(PadroesDesign.FONTETEXT);
		textLogradouro.setColumns(1);
		textLogradouro.setBounds(370, 141, 430, 28);
		desktopPane.add(textLogradouro);
		
		JButton btnEnderecoInvalido = new JButton(new ImageIcon(loader.getResource("img/cancel.png")));
		btnEnderecoInvalido.addActionListener(new BotaoLogradouroInvalido());
		btnEnderecoInvalido.setToolTipText("Desbloquear Campos de Endereço para Digitar Manualmente ");
		btnEnderecoInvalido.setBounds(810, 141, 28, 28);
		desktopPane.add(btnEnderecoInvalido);
				
		lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setForeground(PadroesDesign.CORLABEL);
		lblNmero.setFont(PadroesDesign.FONTELABEL);
		lblNmero.setBounds(848, 121, 61, 16);
		desktopPane.add(lblNmero);
		
		textNumero = new JTextField();
		textNumero.setDocument(new SomenteNumeros(8));
		textNumero.addFocusListener(new CamposObrigatorios());
		textNumero.setToolTipText("Campo para o número da casa do Cliente.");
		textNumero.setHorizontalAlignment(SwingConstants.LEFT);
		textNumero.setForeground(Color.DARK_GRAY);
		textNumero.setFont(PadroesDesign.FONTETEXT);
		textNumero.setColumns(1);
		textNumero.setBounds(848, 141, 61, 28);
		desktopPane.add(textNumero);
		
		lblComplemento = new JLabel("Complemento");
		lblComplemento.setForeground(PadroesDesign.CORLABEL);
		lblComplemento.setFont(PadroesDesign.FONTELABEL);
		lblComplemento.setBounds(17, 171, 124, 16);
		desktopPane.add(lblComplemento);
		
		textComplemento = new JTextField();
		textComplemento.setDocument(new limiteDeCampoTexto(20));
		textComplemento.setToolTipText("Campo para o complemento Ex: nº casa.");
		textComplemento.setHorizontalAlignment(SwingConstants.LEFT);
		textComplemento.setForeground(Color.DARK_GRAY);
		textComplemento.setFont(PadroesDesign.FONTETEXT);
		textComplemento.setColumns(1);
		textComplemento.setBounds(17, 191, 313, 28);
		desktopPane.add(textComplemento);
			
		lblCidade = new JLabel("Cidade");
		lblCidade.setForeground(PadroesDesign.CORLABEL);
		lblCidade.setFont(PadroesDesign.FONTELABEL);
		lblCidade.setBounds(340, 171, 74, 16);
		desktopPane.add(lblCidade);
		
		textCidade = new JTextField();
		textCidade.setEditable(false);
		textCidade.setDocument(new limiteDeCampoTexto(30));
		textCidade.setToolTipText("Campo para a cidade - preenchido automático com o CEP");
		textCidade.setHorizontalAlignment(SwingConstants.LEFT);
		textCidade.setForeground(Color.DARK_GRAY);
		textCidade.setFont(PadroesDesign.FONTETEXT);
		textCidade.setColumns(1);
		textCidade.setBounds(340, 191, 200, 28);
		desktopPane.add(textCidade);
		
		lblUf = new JLabel("UF");
		lblUf.setForeground(PadroesDesign.CORLABEL);
		lblUf.setFont(PadroesDesign.FONTELABEL);
		lblUf.setBounds(550, 171, 33, 16);
		desktopPane.add(lblUf);
		
		textUF = new JTextField();
		textUF.setEditable(false);
		textUF.setDocument(new limiteDeCampoTexto(2));
		textUF.setToolTipText("Campo para o Estado - preenchido automático com o CEP");
		textUF.setHorizontalAlignment(SwingConstants.LEFT);
		textUF.setForeground(Color.DARK_GRAY);
		textUF.setFont(PadroesDesign.FONTETEXT);
		textUF.setColumns(1);
		textUF.setBounds(550, 191, 50, 28);
		desktopPane.add(textUF);
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setForeground(PadroesDesign.CORLABEL);
		lblTelefone.setFont(PadroesDesign.FONTELABEL);
		lblTelefone.setBounds(610, 171, 74, 16);
		desktopPane.add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setDocument(new limiteDeCampoTexto(30));
		textTelefone.setToolTipText("Campo para os telefones deste cliente");
		textTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		textTelefone.setForeground(Color.DARK_GRAY);
		textTelefone.setFont(PadroesDesign.FONTETEXT);
		textTelefone.setColumns(1);
		textTelefone.setBounds(610, 191, 301, 28);
		desktopPane.add(textTelefone);
				
		lblCelular = new JLabel("Celular");
		lblCelular.setForeground(PadroesDesign.CORLABEL);
		lblCelular.setFont(PadroesDesign.FONTELABEL);
		lblCelular.setBounds(17, 221, 129, 16);
		desktopPane.add(lblCelular);
		
		textCelular = new JTextField();
		textCelular.setDocument(new limiteDeCampoTexto(30));
		textCelular.setToolTipText("Numero do fax - caso necessário o envio");
		textCelular.setHorizontalAlignment(SwingConstants.LEFT);
		textCelular.setForeground(Color.DARK_GRAY);
		textCelular.setFont(PadroesDesign.FONTETEXT);
		textCelular.setColumns(1);
		textCelular.setBounds(17, 241, 253, 28);
		desktopPane.add(textCelular);
			
		lblCargo = new JLabel("Cargo");
		lblCargo.setForeground(PadroesDesign.CORLABEL);
		lblCargo.setFont(PadroesDesign.FONTELABEL);
		lblCargo.setBounds(280, 221, 282, 16);
		desktopPane.add(lblCargo);
		
		textCargo = new JTextField();
		textCargo.setDocument(new limiteDeCampoTexto(30));
		textCargo.setToolTipText("Campo para o indicarmos o Cargo ocupado Pelo Cliente.");
		textCargo.setHorizontalAlignment(SwingConstants.LEFT);
		textCargo.addFocusListener(new CamposObrigatorios());
		textCargo.setForeground(Color.DARK_GRAY);
		textCargo.setFont(PadroesDesign.FONTETEXT);
		textCargo.setColumns(1);
		textCargo.setBounds(280, 241, 320, 28);
		desktopPane.add(textCargo);
		
		lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setForeground(PadroesDesign.CORLABEL);
		lblPeriodo.setFont(PadroesDesign.FONTELABEL);
		lblPeriodo.setBounds(610, 221, 282, 16);
		desktopPane.add(lblPeriodo);
		
		textPeriodo = new JTextField();
		//textCargo.setEditable(false);
		textPeriodo.setToolTipText("Campo para o indicarmos o Cargo ocupado Pelo Cliente.");
		textPeriodo.setHorizontalAlignment(SwingConstants.LEFT);
		textPeriodo.setDocument(new limiteDeCampoTexto(30));
		textPeriodo.addFocusListener(new CamposObrigatorios());
		textPeriodo.setForeground(Color.DARK_GRAY);
		textPeriodo.setFont(PadroesDesign.FONTETEXT);
		textPeriodo.setColumns(1);
		textPeriodo.setBounds(610, 241, 300, 28);
		desktopPane.add(textPeriodo);
		
		lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setForeground(PadroesDesign.CORLABEL);
		lblEmpresa.setFont(PadroesDesign.FONTELABEL);
		lblEmpresa.setBounds(17, 271, 282, 16);
		desktopPane.add(lblEmpresa);
		
		textEmpresa = new JTextField();
		textEmpresa.setDocument(new limiteDeCampoTexto(50));
		textEmpresa.setToolTipText("Campo para o indicarmos o Cargo ocupado Pelo Cliente.");
		textEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
		textEmpresa.addFocusListener(new CamposObrigatorios());
		textEmpresa.setForeground(Color.DARK_GRAY);
		textEmpresa.setFont(PadroesDesign.FONTETEXT);
		textEmpresa.setColumns(1);
		textEmpresa.setBounds(17, 291, 373, 28);
		desktopPane.add(textEmpresa);
		
		lblSalario = new JLabel("Salário");
		lblSalario.setForeground(PadroesDesign.CORLABEL);
		lblSalario.setFont(PadroesDesign.FONTELABEL);
		lblSalario.setBounds(400, 271, 170, 16);
		desktopPane.add(lblSalario);
		
		textSalario = new JTextField();
		textSalario.setToolTipText("Campo para o indicarmos o Cargo ocupado Pelo Cliente.");
		textSalario.setHorizontalAlignment(SwingConstants.LEFT);
		textSalario.addFocusListener(new CamposObrigatorios());
		textSalario.addFocusListener(new CampoSalario()); 
		textSalario.setForeground(Color.DARK_GRAY);
		textSalario.setFont(PadroesDesign.FONTETEXT);
		textSalario.setColumns(1);
		textSalario.setBounds(400, 291, 170, 28);
		desktopPane.add(textSalario);
		
		lblOrigem = new JLabel("Origem");
		lblOrigem.setForeground(PadroesDesign.CORLABEL);
		lblOrigem.setFont(PadroesDesign.FONTELABEL);
		lblOrigem.setBounds(580, 271, 170, 16);
		desktopPane.add(lblOrigem);
		
		textOrigem = new JTextField();
		textOrigem.setDocument(new limiteDeCampoTexto(30));
		textOrigem.setToolTipText("Campo para o indicarmos o Cargo ocupado Pelo Cliente.");
		textOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		textOrigem.addFocusListener(new CamposObrigatorios());
		textOrigem.setForeground(Color.DARK_GRAY);
		textOrigem.setFont(PadroesDesign.FONTETEXT);
		textOrigem.setColumns(1);
		textOrigem.setBounds(580, 291, 330, 28);
		desktopPane.add(textOrigem);
					
		btnLiberarEndereco = new JButton(new ImageIcon(loader.getResource("img/Correios.png")));
		btnLiberarEndereco.setText("<HTML><center>Liberar </br> Endereço </center><HTML>");
		btnLiberarEndereco.setToolTipText("6101 - Incluir CEP Novo");
		btnLiberarEndereco.addActionListener(new BotaoCepNovo());
		btnLiberarEndereco.setForeground(Color.DARK_GRAY);
		btnLiberarEndereco.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLiberarEndereco.setBounds(514, 340,  125, 40);
		desktopPane.add(btnLiberarEndereco);
					
		btnSalvar = new JButton(new ImageIcon(loader.getResource("img/save.png")));
		btnSalvar.setText("Salvar");
		btnSalvar.setToolTipText("Salvar alterações do cadastro / cadastrar novo cliente");
		btnSalvar.addActionListener(new BotaoSalvar());
		btnSalvar.setForeground(Color.DARK_GRAY);
		btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSalvar.setBounds(651, 340,  125, 40);
		desktopPane.add(btnSalvar);
					
		btnCancelar = new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		btnCancelar.setToolTipText("Cancelar inclusão do Preço");
		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new BotaoCancelar());
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnCancelar.setBounds(786, 340,  125, 40);
		desktopPane.add(btnCancelar);
			
		System.out.println("ROTINA " + rotinaExecutada);
		//ROTINA 2 - ALTERAÇÃO  E ROTINA 4 - CONSULTA - 5 - GERENCIAMENTO DE CONTRATOS - 8 - COSULTA IPAD
		if(rotinaExecutada == 2 || rotinaExecutada == 4 || rotinaExecutada == 5 || rotinaExecutada == 6 || rotinaExecutada == 7 || rotinaExecutada == 8 )
		{
			textCodigo.setText("" + ManutencaoClienteDAO.getIdCliente());
			textNome.setText(ManutencaoClienteDAO.getNome().trim());
			//VALOR A SER COMPARADO NA ALTERAÇÃO
			emailEntrada =  ManutencaoClienteDAO.getEmail().trim();
			textEmail.setText(ManutencaoClienteDAO.getEmail().trim());
			textTipoEndereco.setText(ManutencaoClienteDAO.getTipoDeEndereco().trim());
			textLogradouro.setText(ManutencaoClienteDAO.getLogradouro().trim());
			textComplemento.setText(ManutencaoClienteDAO.getComplemento().trim());
			textNumero.setText(ManutencaoClienteDAO.getNumero().trim());
			textCidade.setText(ManutencaoClienteDAO.getCidade().trim());
			textUF.setText(ManutencaoClienteDAO.getUF().trim());
			textCEP.setText(ManutencaoClienteDAO.getCEP());
			textTelefone.setText(ManutencaoClienteDAO.getTelefone().trim());
			textCelular.setText(ManutencaoClienteDAO.getCelular().trim());
			textCargo.setText(ManutencaoClienteDAO.getCargo().trim());
			textPeriodo.setText(ManutencaoClienteDAO.getPeriodo().trim());
			textEmpresa.setText(ManutencaoClienteDAO.getEmpresa().trim());
			textSalario.setText(ManutencaoClienteDAO.getSalario().trim());
			textOrigem.setText(ManutencaoClienteDAO.getOrigem().trim());
			CPFEntrada = ManutencaoClienteDAO.getCPF().trim();
			textCPF.setText(ManutencaoClienteDAO.getCPF());
			textRG.setText(ManutencaoClienteDAO.getRG().trim());
	
			//CONVERSÃO PARA AS DATAS NO FORMATO SQL APÓS TRATAMENTO
			SimpleDateFormat formatobrasileiro = new SimpleDateFormat("yyyy-MM-dd");
			//BANCO ANTIGO UTILIZA F E M PARA OS VALORES DA TABELA, COMBO UTILIZA 0 E 1 - FAZER UM UPDATE
			if(ManutencaoClienteDAO.getSexo().contains("F"))
			{
				comboBoxSexo.setSelectedIndex(1);	
			}
			else
			{
				comboBoxSexo.setSelectedIndex(0);
			}
			
			if(rotinaExecutada == 4 || rotinaExecutada == 8 || rotinaExecutada == 7)
			{
				btnLiberarEndereco.setVisible(false);
				
				textCodigo.setEditable(false);
				textNome.setEditable(false);
				dateInclusao.setEnabled(false);
				textTipoEndereco.setEditable(false);
				textLogradouro.setEditable(false);
				textComplemento.setEditable(false);
				textNumero.setEditable(false);
				textCidade.setEditable(false);
				textUF.setEditable(false);
				textCEP.setEditable(false);
				textTelefone.setEditable(false);
				textCelular.setEditable(false);
				textCPF.setEditable(false);
				textRG.setEditable(false);
				textEmail.setEditable(false);
				comboBoxSexo.setEnabled(false);
				textCargo.setEditable(false);
				btnSalvar.setVisible(false);
				btnConfirma.setEnabled(false);
				
				//POSIÇÂO DO BOTÂO ASINATURA< POIS TIRAMOS O SALVAR EM UMA CONSULTA - ALTERAÇÂO DO TITULO PARA CONSULTA
				setTitle("CONSULTA DE CLIENTE");
			}	
		}
		else
		{
			lblLegendaObrigatorios = new JLabel("Campos Obrigatórios para a inclusão do Cliente");
			lblLegendaObrigatorios.setForeground(Color.DARK_GRAY);
			lblLegendaObrigatorios.setFont(new Font("Segoe UI", Font.BOLD, 11));
			lblLegendaObrigatorios.setBounds(40, 340, 282, 16);
			desktopPane.add(lblLegendaObrigatorios);
			
			textLegendaObrigatorios = new JTextField();
			//textLegendaObrigatorios.setEditable(false);
			textLegendaObrigatorios.setToolTipText("Campo para o indicarmos o Cargo ocupado Pelo Cliente.");
			textLegendaObrigatorios.setHorizontalAlignment(SwingConstants.LEFT);
			textLegendaObrigatorios.setBackground(Color.getHSBColor(352,21,96));
			textLegendaObrigatorios.setForeground(Color.DARK_GRAY);
			textLegendaObrigatorios.setFont(new Font("SansSerif", Font.BOLD, 12));
			textLegendaObrigatorios.setColumns(1);
			textLegendaObrigatorios.setBounds(17, 340, 15, 16);
			desktopPane.add(textLegendaObrigatorios);
			
			lblNaoEditaveis = new JLabel("Campos não editáveis ou Automáticos");
			lblNaoEditaveis.setForeground(Color.DARK_GRAY);
			lblNaoEditaveis.setFont(new Font("Segoe UI", Font.BOLD, 11));
			lblNaoEditaveis.setBounds(40, 360, 282, 16);
			desktopPane.add(lblNaoEditaveis);
			
			textNaoEditaveis = new JTextField();
			textNaoEditaveis.setBackground(Color.getHSBColor(180,31,82));
			//textNaoEditaveis.setEditable(false);
			textNaoEditaveis.setToolTipText("Campo para o indicarmos o Cargo ocupado Pelo Cliente.");
			textNaoEditaveis.setHorizontalAlignment(SwingConstants.LEFT);
			textNaoEditaveis.setForeground(Color.DARK_GRAY);
			textNaoEditaveis.setFont(new Font("SansSerif", Font.BOLD, 12));
			textNaoEditaveis.setColumns(1);
			textNaoEditaveis.setBounds(17, 360, 15, 16);
			desktopPane.add(textNaoEditaveis);
						
			textCodigo.setBackground(Color.getHSBColor(180,31,82));
			textTipoEndereco.setBackground(Color.getHSBColor(180,31,82));
			textLogradouro.setBackground(Color.getHSBColor(180,31,82));
			textCidade.setBackground(Color.getHSBColor(180,31,82));
			textUF.setBackground(Color.getHSBColor(180,31,82));
						
			textNome.setBackground(Color.getHSBColor(352,21,96));
			//EXIBE O NOME AO SER REPETIDO
			textNome.addFocusListener(new CampoNome());
			comboBoxSexo.setSelectedIndex(0);
		}
				
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); 
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(true);
	}
	
	
	
	class CampoSalario implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0) 
		{

		}

		@Override
		public void focusLost(FocusEvent arg0) 
		{
			//TRATAMENTO CASO RECEBA , NO CAMPO SEM ESTE DARIA ERRO NA CONVERSÂO
			String valor = textSalario.getText();
			if(valor.contains(",") || valor.contains("R") || valor.contains("$") || valor.contains(" "))
			{
				valor = valor.replace(",", ".");
				valor = valor.replace("R", "");
				valor = valor.replace("$", "");
				valor = valor.replace(" ", "");
			}

			if(valor.trim().length() == 0)
			{
				textSalario.setText(NumberFormat.getCurrencyInstance().format(0));
			}
			else
			{

				textSalario.setText(NumberFormat.getCurrencyInstance().format(Float.parseFloat(valor)));
			}
		}
	}	
		
	class BotaoLogradouroInvalido implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
				textTipoEndereco.setEditable(true);
				textLogradouro.setEditable(true);
				textComplemento.setEditable(true);
				textNumero.setEditable(true);
				textCidade.setEditable(true);
				textUF.setEditable(true);
		}
	}
	
	class BotaoCepNovo implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
				textTipoEndereco.setEditable(true);
				textLogradouro.setEditable(true);
				textComplemento.setEditable(true);
				textNumero.setEditable(true);
				textCidade.setEditable(true);
				textUF.setEditable(true);
				textCidade.setBackground(Color.getHSBColor(352,21,96));
				textCEP.setBackground(Color.getHSBColor(352,21,96));
				textUF.setBackground(Color.getHSBColor(352,21,96));
				textLogradouro.setBackground(Color.getHSBColor(352,21,96));
				textCidade.setBackground(Color.getHSBColor(352,21,96));
		}
	}
			
	class BotaoCarregarCEPListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			CepDAO validar = new CepDAO();
			if(!textCEP.getText().trim().equals(""))
			{
				CEP = textCEP.getText().trim();
				if(CEP.contains("-"))
				{
					CEP = CEP.replace("-", "");
				}				
				
				CepDAO.setCepDigitado(CEP);
				validar.CEPExiste();
				if(validar.getCEPValido() == true)
				{					
					textTipoEndereco.setText(CepDAO.getTipo());
					textLogradouro.setText(CepDAO.getLogradouro());
					textComplemento.setText(CepDAO.getComplemento());
					textCidade.setText(CepDAO.getCidade());
					textUF.setText(CepDAO.getEstado());
					if(textLogradouro.getText().trim().length() == 0)
					{
						textTipoEndereco.setEditable(true);
						textLogradouro.setEditable(true);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Este Cep não foi localizado, favor consultar no botão ao lado!");
				}
			}
		}
	}	
		
	class BotaoCancelar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			ManutencaoClienteDAO.setIdCliente(0);
			ManutencaoClienteDAO.setNome("");
			ManutencaoClienteDAO.setCargo("");
			ManutencaoClienteDAO.setPeriodo("");
			ManutencaoClienteDAO.setEmpresa("");
			ManutencaoClienteDAO.setSalario("");
			ManutencaoClienteDAO.setTelefone("");
			ManutencaoClienteDAO.setCelular("");
			ManutencaoClienteDAO.setData(null);
			ManutencaoClienteDAO.setStatus("");
			ManutencaoClienteDAO.setCPF("");
			ManutencaoClienteDAO.setRG("");
			ManutencaoClienteDAO.setCEP("");

			ManutencaoClienteDAO.setTipoDeEndereco("");
			ManutencaoClienteDAO.setLogradouro("");
			ManutencaoClienteDAO.setComplemento("");
			ManutencaoClienteDAO.setNumero("");
			ManutencaoClienteDAO.setCidade("");
			ManutencaoClienteDAO.setUF("");

			ManutencaoClienteDAO.setOrigem("");
			ManutencaoClienteDAO.setIdUsuario(0);

			ManutencaoClienteDAO.setEmail("");
			ManutencaoClienteDAO.setSexo("");
			dispose();
		}	
	}
	
	class BotaoSalvar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(!SenhaDAO.getDireitosAtribuidos().contains("3000") && !SenhaDAO.getDireitosAtribuidos().contains("3001") && !SenhaDAO.getDireitosAtribuidos().contains("3002"))
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina de 3000 - Alterar Cliente. \n Favor informar ao administrador do sistema!");
				return;
			}
			
			ManutencaoClienteDAO validar = new ManutencaoClienteDAO();
			if(textNome.getText().trim().length() == 0)
			{
				JOptionPane.showMessageDialog(null, "Favor preencher o campo Nome deste Cliente");
				textNome.grabFocus();
				return;
			}

			Validar validarDados = new Validar();

			CPF = textCPF.getText().trim();
			if(CPF.contains("."))
			{
				CPF = CPF.replace(".", "");
				CPF = CPF.replace("-", "");
			}

			if(CPF.trim().length() == 0)
			{
				ManutencaoClienteDAO.setCPF(" ");
			}
			else if(textCPF.getText().equals("000.000.000-00"))
			{
				ManutencaoClienteDAO.setCPF(CPF);
			}
			else
			{			
				if(textCPF.getText().equals("000.000.000-00"))
				{
					JOptionPane.showMessageDialog(null, "CPF Inválido");
					textCPF.setText("");
					textCPF.grabFocus();
					return;
				}

				if(CPF.trim().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "Favor preencher o CPF");
					textCPF.grabFocus();
					return;
				}

				ManutencaoClienteDAO.setCPF(CPF);
				if(CPF.length() != 0 )
				{	//CASO RECEBA SÓ A MASCARA
					if(CPF.length() == 11)
					{
						validarDados.validaCPF(CPF);
						if(validarDados.isCpfValido() == true)
						{
							ManutencaoClienteDAO.setCPF(CPF);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Este CPF está incorreto, ou não existe, favor verificar!");
							textCPF.grabFocus();
							return;
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Favor preencher o campo CPF com 11 números, este CPF está incorreto !");
						textCPF.grabFocus();
						return;
					}
				}
			}

			//PERMISSÂO PARA INCLUSÔA DE E_MAIL COM DUPLICIDADE
			if(textEmail.getText().trim().length() > 0)
			{
				Validar validarEmail = new Validar();
				System.out.println(textEmail.getText().trim());
				validarEmail.validaEmail(textEmail.getText().trim());

				if(!validarEmail.isEmailValido())
				{
					JOptionPane.showMessageDialog(null, "E-mail Inválido, Favor preencher ou deixá-lo em branco caso tenha permissão!");
					textEmail.grabFocus();
					return;
				}	
				else
				{
					ManutencaoClienteDAO.setEmail(textEmail.getText().trim());
				}
			}
			else
			{
				ManutencaoClienteDAO.setEmail(" ");
			}

			SimpleDateFormat formatobrasileiro = new SimpleDateFormat("yyyy-MM-dd");
			//CONVERSÃO PARA AS DATAS NO FORMATO SQL APÓS TRATAMENTO
			try 
			{
				DataCadastro = formatobrasileiro. parse ( formatobrasileiro.format(dateInclusao.getDate()) );
			} 
			catch (ParseException e2) 
			{
				e2.printStackTrace();
			}	

			java.sql.Date dataCadastroSQL = new java.sql.Date(DataCadastro.getTime()); 
			ManutencaoClienteDAO.setData(dataCadastroSQL);

			//RECEBE UM VALOR DE UM CAMPO TEXTO E CONVERTE PARA INTEIRO PARA A GRAVAÇÂO DO BANCO
			ManutencaoClienteDAO.setNome(textNome.getText());
			ManutencaoClienteDAO.setTipoDeEndereco(textTipoEndereco.getText() + " ");
			ManutencaoClienteDAO.setLogradouro(textLogradouro.getText()+ " ");
			ManutencaoClienteDAO.setComplemento(textComplemento.getText()+ " ");
			ManutencaoClienteDAO.setNumero(textNumero.getText()+ " ");
			ManutencaoClienteDAO.setCidade(textCidade.getText()+ " ");
			ManutencaoClienteDAO.setUF(textUF.getText()+ " ");
			ManutencaoClienteDAO.setCargo(textCargo.getText()+ " ");
			ManutencaoClienteDAO.setEmpresa(textEmpresa.getText()+ " ");
			ManutencaoClienteDAO.setSalario(textSalario.getText().trim()+ " ");
			ManutencaoClienteDAO.setPeriodo(textPeriodo.getText()+ " ");
			ManutencaoClienteDAO.setOrigem(textOrigem.getText()+ " ");
			
			if(comboBoxSexo.getSelectedItem().equals("Masculino"))
			{
				ManutencaoClienteDAO.setSexo("M");
			}
			else
			{
				ManutencaoClienteDAO.setSexo("F");
			}
			
			System.out.println(ManutencaoClienteDAO.getSexo());
			//REMOVE PONTUAÇÂO COLOCADA PELO CAMPO
			CEP = textCEP.getText().trim();
			if( textCEP.getText().trim().length() > 1)
			{
				if(textCEP.getText().equals("00000-000") )
				{
					JOptionPane.showMessageDialog(null, "CEP Inválido");
					textCEP.setText("");
					return;
				}

				if(CEP.contains("-"))
				{
					CEP = CEP.replace("-", "");
					CEP = CEP.replace(" ", "");
				}

				if(CEP.length() == 8)
				{									
					CepDAO validarCEP = new CepDAO();
					CepDAO.setCepDigitado(CEP);
					validarCEP.CEPExiste();

					if(validarCEP.getCEPValido() == true)
					{
						ManutencaoClienteDAO.setCEP(CEP);
					}
					else
					{						
						JOptionPane.showMessageDialog(null, "Favor consultar o CEP no botão ao lado");
						textCEP.grabFocus();
						return;
					}
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "CEP incorreto !");
					textCEP.grabFocus();
					return;
				}
			}
			else
			{
				if(CEP.contains("-"))
				{
					CEP = CEP.replace("-", "");
					CEP = CEP.replace(" ", "");
				}

				ManutencaoClienteDAO.setCEP(CEP);
			}

			ManutencaoClienteDAO.setTelefone(textTelefone.getText()+ " ");
			ManutencaoClienteDAO.setCelular(textCelular.getText()+ " ");

			/**EXCESSÂO USADA PARA DAR PERMISSÂO A UTILIZAÇÂO DO RECURSO DE ZERAR O CAMPO EM CASO DE PESSOA FISICA*/
			ManutencaoClienteDAO.setRG(textRG.getText());
		
			//TRATA O CAMPO SEXO QUE PASSA UM INTEIRO ATRAVÈS DE UM COMBOBOX E GRAVA UM CHAR
			String sexo;
			if(comboBoxSexo.getSelectedIndex() == 0)
			{
				sexo = "M";
			}
			else
			{
				sexo = "F";
			}
			ManutencaoClienteDAO.setSexo(sexo);

			//ALTERAÇÂO 
			if(rotinaExecutada == 2 || rotinaExecutada == 5 || rotinaExecutada == 6)
			{ 				
				validar.AtualizarCliente();
				JOptionPane.showMessageDialog(null, "Cliente : " + ManutencaoClienteDAO.getNome() + " alterado com sucesso" );
			}
			else
			{	
				validar.IserirCliente();
				JOptionPane.showMessageDialog(null, "Código / Cliente  : " + ManutencaoClienteDAO.getIdCliente() + " / " + ManutencaoClienteDAO.getNome() + "\n incluído com sucesso" );
				textCodigo.setText(""+ManutencaoClienteDAO.getIdCliente());
			}
			
			new PesquisaCliente();
			dispose();
			
		}
	}
	

	class CampoNome implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0) 
		{
			
		}

		@Override
		public void focusLost(FocusEvent arg0) 
		{
			if(textNome.getText().trim().length() > 0)
			{
				ManutencaoClienteDAO validarNome = new ManutencaoClienteDAO();
				ManutencaoClienteDAO.setSQLencontraNomeCliente("SELECT * FROM Clientes WHERE NOME LIKE \'" + textNome.getText().trim() + "%\'");
				validarNome.CarregaNomeClienteInclusao();
			}	
		}
	}
	
	class CamposObrigatorios implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0) 
		{
			
		}

		@Override
		public void focusLost(FocusEvent arg0) 
		{
			if(textNome.getText().trim().length() > 0 )
			{
				textNome.setBackground(Color.WHITE);
			}
			else
			{
				textNome.setBackground(Color.getHSBColor(352,21,96));
			}
		}
	}
	
	class CampoCEP implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0) 
		{
			
		}

		@Override
		public void focusLost(FocusEvent arg0) 
		{
		
			CEP = textCEP.getText().trim();
			//RECEBE UM DEVIDO A MASCARA CRIADA NO CAMPO COM O VALOR DO -
			if( CEP.trim().length() > 1)
			{
				if(CEP.equals("00000-000"))
				{
					JOptionPane.showMessageDialog(null, "CEP Inválido");
					return;
				}
				
				if(CEP.contains("-"))
				{
					CEP = CEP.replace("-", "");
					CEP = CEP.replace(" ", "");
				}
				
				if(CEP.length() == 8)
				{						
					CepDAO validar = new CepDAO();
					CepDAO.setCepDigitado(CEP);
					validar.CEPExiste();
			
					textLogradouro.setText(CepDAO.getLogradouro());
					if(validar.getCEPValido() == true)
					{
						if(CepDAO.getLogradouro().trim().equals("") || textLogradouro.getText().trim().length() == 0)
						{
							textTipoEndereco.setEditable(true);
							textLogradouro.setEditable(true);
						}
						else
						{
							textLogradouro.setText(CepDAO.getLogradouro());
						}	
						
						textTipoEndereco.setText(CepDAO.getTipo());
						textComplemento.setText(CepDAO.getComplemento());
						textCidade.setText(CepDAO.getCidade());
						textUF.setText(CepDAO.getEstado());
						textNumero.grabFocus();
					}
					else
					{						
						JOptionPane.showMessageDialog(null, "Favor consultar o CEP no botão ao lado");
						textCEP.grabFocus();
						return;
					}
					
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "CEP incorreto !");
					textCEP.grabFocus();
					return;
				}
			}
		}
	}
	
	class CampoCPF implements FocusListener
	{	
		@Override
		public void focusGained(FocusEvent arg0) {
		}

		@Override
		public void focusLost(FocusEvent arg0) {

			Validar validar = new Validar();
			if(textCPF.getText().trim().length() != 0)
			{
				if(textCPF.getText().equals("000.000.000-00") && !SenhaDAO.getDireitosAtribuidos().contains("5301") )
				{

					if(textCPF.getText().equals("000.000.000-00") )
					{
						JOptionPane.showMessageDialog(null, "CPF Inválido, , Favor Consultar o Administrador para a rotina 5301 - Permissão CPF inválido");
						textCPF.setText("");
						return;
					}

					CPF = textCPF.getText().trim();
					if(CPF.contains("."))
					{
						CPF = CPF.replace(".", "");
						CPF = CPF.replace("-", "");
					}
					//CASO RECEBA SÓ A MASCARA
					if(CPF.trim().length() == 0)
					{
						textCPF.setText("");
						textRG.grabFocus();
						return;
					}

					if(CPF.length() == 11)
					{
						validar.validaCPF(CPF);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Favor preencher o campo CPF com 11 números, este CPF está incorreto !");
						textCPF.grabFocus();
						return;
					}
					if(validar.isCpfValido() == false)
					{
						JOptionPane.showMessageDialog(null, "Este CPF está incorreto, ou não existe, favor verificar!");
						textCPF.grabFocus();
						return;
					}
				}
			}	
		}
	}	

	class CampoEmail implements FocusListener
	{	
		Validar validar = new Validar();
		@Override
		public void focusGained(FocusEvent arg0) {
				
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			
			if(textEmail.getText().trim().length() > 0)
			{	
				validar.validaEmail(textEmail.getText());
				System.out.println(validar.isEmailValido());
				if(!validar.isEmailValido())
				{
					JOptionPane.showMessageDialog(null, "E-mail Inválido!");
					textEmail.grabFocus();
					return;
				}
							
			}
		}
	}

	public void LimpaTela() 
	{	
		Date dataAtual = new Date();
		textCodigo.setText("");
		textNome.setText("");
		dateInclusao.setDate(dataAtual);
		textTipoEndereco.setText("");
		textLogradouro.setText("");
		textComplemento.setText("");
		textNumero.setText("");
		textCidade.setText("");
		textUF.setText("");
		textCEP.setText("");
		textTelefone.setText("");
		textCelular.setText("");
		textCPF.setText("");
		textRG.setText("");
		textEmail.setText("");
		comboBoxSexo.setSelectedIndex(0);	
		textCargo.setText("");
	}
}
