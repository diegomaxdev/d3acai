package d3Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import CellRender.ClienteSiacCellRender;
import Controller.ClienteSiacController;
import Entity.ClienteSiac;
import PadroesDesign.PadroesDesign;
import PrincipalMain.d3Frame;
import d3DAO.ClienteSiacDAO;
import d3DAO.SenhaDAO;
import d3TableModel.ClienteSiacTableModel;
import d3Validacoes.SomenteNumeros;
import d3Validacoes.limiteDeCampoTexto;
import net.miginfocom.swing.MigLayout;


public class ClienteSIACFrame extends JFrame {

	private JPanel contentPane , panelTable;
	private JScrollPane scrollPane;
	private JTable tabelaClientes;
	private List<ClienteSiac> ClienteSiacList;
	private JTextField textNome, textObservacao, textCodigo, textAgencia, textConta;
	private JTextField textNomeOuParte, textTelefoneOuParte, textObservacaoOuParte, textCodigoCliente;
	private JButton btnAlterar, btnIncluir, btnExcluir, btnCancelar, btnLimpar, btnAgendar, btnResumo;
	JDesktopPane desktopPane;
	public static JFormattedTextField textTelefone, textCelular;
	private JComboBox comboBoxBanco, comboBoxUsuarios; 
	private JLabel lblLegendaObrigatorios;
	private JTextField textLegendaObrigatorios;
	private JLabel lblNaoEditaveis;
	private JTextField textNaoEditaveis;
	JCheckBox chckbxExcessao; 
	private JComboBox comboBoxFilial;
	
	/*USADA PARA ATUALIZAR AS FILIAIS NOS AGENDAMENTOS*/
	private int filialAnterior = 0;
	
	public ClienteSIACFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SIAC - CLIENTES");
		setBounds(100, 100, 1024, 718);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/* PADR^ÇAO INICIAL DE BUSCA DO SISTEMA PARA LOCALIZAR OS CLIENTES */
		ClienteSiacDAO.setBuscarCliente("SELECT * FROM Clientes WHERE (1=2)");
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
				
		JLabel lblCodigoCliente = new JLabel("Cód. do Cliente");
		lblCodigoCliente.setForeground(PadroesDesign.CORLABEL);
		lblCodigoCliente.setFont(PadroesDesign.FONTELABEL);
		lblCodigoCliente.setBounds(17, 17, 100, 16);
		desktopPane.add(lblCodigoCliente);
		
		textCodigoCliente = new JTextField();
		textCodigoCliente.setDocument(new SomenteNumeros(10));
		textCodigoCliente.addFocusListener(new CampoComConteudo());
		textCodigoCliente.addMouseListener(new BotaoDireitoCodigo());
		textCodigoCliente.setName("Código do Cliente");
		textCodigoCliente.setForeground(PadroesDesign.CORTEXT);
		textCodigoCliente.setFont(PadroesDesign.FONTETEXT);
		/*ALINHANDO O CONTEUDO NO CENTRO DO CAMPO TEXTO*/
		textCodigoCliente.setHorizontalAlignment(JTextField.CENTER);
		textCodigoCliente.setColumns(10);
		textCodigoCliente.setBounds(17, 33, 90, 28);
		desktopPane.add(textCodigoCliente);
				
		JLabel lblNomeOuParte = new JLabel("Nome ou Parte");
		lblNomeOuParte.setForeground(PadroesDesign.CORLABEL);
		lblNomeOuParte.setFont(PadroesDesign.FONTELABEL);
		lblNomeOuParte.setBounds(117, 17, 160, 16);
		desktopPane.add(lblNomeOuParte);
		
		textNomeOuParte = new JTextField();
		textNomeOuParte.setDocument(new limiteDeCampoTexto(50));
		textNomeOuParte.addFocusListener(new CampoComConteudo());
		textNomeOuParte.addMouseListener(new BotaoDireitoNomeOuParte());
		textNomeOuParte.setName("textNomeOuParte");
		textNomeOuParte.setForeground(PadroesDesign.CORTEXT);
		textNomeOuParte.setFont(PadroesDesign.FONTETEXT);
		textNomeOuParte.setColumns(10);
		textNomeOuParte.setBounds(117, 33, 345, 28);
		desktopPane.add(textNomeOuParte);
		
		JLabel lblTelefoneOuParte = new JLabel("Telefone ou Parte");
		lblTelefoneOuParte.setForeground(PadroesDesign.CORLABEL);
		lblTelefoneOuParte.setFont(PadroesDesign.FONTELABEL);
		lblTelefoneOuParte.setBounds(472, 17, 160, 16);
		desktopPane.add(lblTelefoneOuParte);
		
		textTelefoneOuParte = new JTextField();
		textTelefoneOuParte.setDocument(new limiteDeCampoTexto(50));
		textTelefoneOuParte.addFocusListener(new CampoComConteudo());
		textTelefoneOuParte.addMouseListener(new BotaoDireitoTelefonePesquisa());
		textTelefoneOuParte.setForeground(PadroesDesign.CORTEXT);
		textTelefoneOuParte.setFont(PadroesDesign.FONTETEXT);
		textTelefoneOuParte.setColumns(10);
		textTelefoneOuParte.setBounds(472, 33, 220, 28);
		desktopPane.add(textTelefoneOuParte);
		
		JLabel lblObsOuParte = new JLabel("Observação ou Parte");
		lblObsOuParte.setForeground(PadroesDesign.CORLABEL);
		lblObsOuParte.setFont(PadroesDesign.FONTELABEL);
		lblObsOuParte.setBounds(702, 17, 160, 16);
		desktopPane.add(lblObsOuParte);
		
		textObservacaoOuParte = new JTextField();
		textObservacaoOuParte.setDocument(new limiteDeCampoTexto(50));
		textObservacaoOuParte.addFocusListener(new CampoComConteudo());
		textObservacaoOuParte.setForeground(PadroesDesign.CORTEXT);
		textObservacaoOuParte.setFont(PadroesDesign.FONTETEXT);
		textObservacaoOuParte.setColumns(10);
		textObservacaoOuParte.setBounds(702, 33, 190, 28);
		desktopPane.add(textObservacaoOuParte);
		
		ClassLoader loader = getClass().getClassLoader();
		
		JButton btnLocalizarCampos = new JButton(new ImageIcon(loader.getResource("img/Localizar.png")));
		btnLocalizarCampos.addActionListener(new BotaoLocalizar());
		btnLocalizarCampos.setToolTipText("Localizar Cliente de acordo com o preenchimento dos Campos");
		btnLocalizarCampos.setBounds(918, 18, 71, 60);
		desktopPane.add(btnLocalizarCampos);
				
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder(" Clientes "));
		panelTable.setBounds(17, 88, 980, 350);
		
		//CRIAÇÂO DO COMPONENTE TABELA
		tabelaClientes = new JTable();
		/*TAMANHO DA CELULA NA PLANILHA*/
		tabelaClientes.setRowHeight(20);
		tabelaClientes.setPreferredScrollableViewportSize(new Dimension(980, 350));  
		tabelaClientes.setFillsViewportHeight(true);
		tabelaClientes.addMouseListener(new ClicandoMouse());
		tabelaClientes.getTableHeader().setReorderingAllowed(false); 
		tabelaClientes.setAutoCreateRowSorter(true);
		tabelaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		
		scrollPane = new JScrollPane(tabelaClientes); 
		scrollPane.setHorizontalScrollBar(new JScrollBar(0)); 
		scrollPane.setSize(980,350);
		panelTable.add(scrollPane);
		desktopPane.add(panelTable);
				
		/*CRIANDO UM CAMPO PARA ALTERAÇÂO DE INCLUSÂO - THIAGO 01/09/2018*/
		JLabel lblUsuario = new JLabel("Usuário :");
		lblUsuario.setForeground(PadroesDesign.CORLABEL);
		lblUsuario.setFont(PadroesDesign.FONTELABEL);
		lblUsuario.setBounds(625, 438, 70, 28);
		desktopPane.add(lblUsuario);
		
		SenhaDAO carregarUsuarios = new SenhaDAO();
		carregarUsuarios.carregarTodosUsuarios();
		
		comboBoxUsuarios = new JComboBox();
		comboBoxUsuarios.setToolTipText("11000 - Extrair Relatório Agendamento de Outros Usuários");
		comboBoxUsuarios.setFont(PadroesDesign.FONTETEXT);
		comboBoxUsuarios.setModel(carregarUsuarios.opcoesUsuario);
		comboBoxUsuarios.setSelectedItem(SenhaDAO.getLogin());
		comboBoxUsuarios.setForeground(PadroesDesign.CORTEXT);
		comboBoxUsuarios.setBounds(690, 438, 150, 28);
		desktopPane.add(comboBoxUsuarios);
		
		chckbxExcessao = new JCheckBox("Permitir Exceção");
		chckbxExcessao.setForeground(PadroesDesign.CORLABEL);
		chckbxExcessao.setBackground(this.getBackground());
		chckbxExcessao.setToolTipText("Marcar quando o cadastramento contém dados semelhantes aos de outro cliente.");
		chckbxExcessao.addActionListener(new BotaoChekBoxExecessão());
		chckbxExcessao.setFont(PadroesDesign.FONTETEXT);
		chckbxExcessao.setBounds(850, 440, 152, 18);
		desktopPane.add(chckbxExcessao);
		
		JLabel lblClientesSeparador = new JLabel("Cadastro / Alteração de Clientes");
		lblClientesSeparador.setForeground(PadroesDesign.CORLABEL);
		lblClientesSeparador.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
		lblClientesSeparador.setBounds(17, 458, 300, 16);
		desktopPane.add(lblClientesSeparador);
		
		JSeparator separadorClientes = new JSeparator();  
		separadorClientes.setForeground(PadroesDesign.CORLABEL);  
	    separadorClientes.setBackground(Color.BLACK);  
	    separadorClientes.setSize(695, 8); 
		separadorClientes.setLocation(290, 468);
		desktopPane.add(separadorClientes);
				
		JLabel lblCódigo = new JLabel("Cód. do Cliente");
		lblCódigo.setForeground(PadroesDesign.CORLABEL);
		lblCódigo.setFont(PadroesDesign.FONTELABEL);
		lblCódigo.setBounds(17, 498, 100, 16);
		desktopPane.add(lblCódigo);
		
		textCodigo = new JTextField();
		textCodigo.setDocument(new limiteDeCampoTexto(10));
		textCodigo.addFocusListener(new CampoComConteudo());
		textCodigo.setFocusable(false);
		textCodigo.setName("Código do Cliente");
		textCodigo.setForeground(PadroesDesign.CORTEXT);
		textCodigo.setFont(PadroesDesign.FONTETEXT);
		textCodigo.setColumns(10);
		textCodigo.setBounds(17, 514, 90, 28);
		desktopPane.add(textCodigo);
		
		JLabel lblNome= new JLabel("Nome");
		lblNome.setForeground(PadroesDesign.CORLABEL);
		lblNome.setFont(PadroesDesign.FONTELABEL);
		lblNome.setBounds(117, 498, 160, 16);
		desktopPane.add(lblNome);
		
		textNome = new JTextField();
		textNome.setDocument(new limiteDeCampoTexto(50));
		textNome.addFocusListener(new CampoComConteudo());
		textNome.addMouseListener(new BotaoDireitoNome());
		textNome.setName("Nome");
		textNome.setForeground(PadroesDesign.CORTEXT);
		textNome.setFont(PadroesDesign.FONTETEXT);
		textNome.setColumns(10);
		textNome.setBounds(117, 514, 345, 28);
		desktopPane.add(textNome);
		
		JLabel lblTelefone = new JLabel("(DDD) Telefone");
		lblTelefone.setForeground(PadroesDesign.CORLABEL);
		lblTelefone.setFont(PadroesDesign.FONTELABEL);
		lblTelefone.setBounds(472, 498, 160, 16);
		desktopPane.add(lblTelefone);
		
		textTelefone = new JFormattedTextField();
		try 
		{
			textTelefone.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##) ####-####")));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		textTelefone.setDocument(new limiteDeCampoTexto(14));
		textTelefone.addFocusListener(new CampoComConteudo());
		textTelefone.addMouseListener(new BotaoDireitoTelefone());
		textTelefone.setForeground(PadroesDesign.CORTEXT);
		textTelefone.setFont(PadroesDesign.FONTETEXT);
		textTelefone.setColumns(10);
		textTelefone.setBounds(472, 514, 260, 28);
		desktopPane.add(textTelefone);
		
		JLabel lblCelular = new JLabel("(DDD) Celular");
		lblCelular.setForeground(PadroesDesign.CORLABEL);
		lblCelular.setFont(PadroesDesign.FONTELABEL);
		lblCelular.setBounds(742, 498, 160, 16);
		desktopPane.add(lblCelular);
		
		textCelular = new JFormattedTextField();
		try 
		{
			textCelular.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##) #####-####")));
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		textCelular.setDocument(new limiteDeCampoTexto(15));
		textCelular.setForeground(PadroesDesign.CORTEXT);
		textCelular.setFont(PadroesDesign.FONTETEXT);
		textCelular.setColumns(10);
		textCelular.setBounds(742, 514, 250, 28);
		desktopPane.add(textCelular);
		
			
		JLabel lblObs = new JLabel("Observação");
		lblObs.setForeground(PadroesDesign.CORLABEL);
		lblObs.setFont(PadroesDesign.FONTELABEL);
		lblObs.setBounds(197, 545, 160, 16);
		desktopPane.add(lblObs);
		
		textObservacao = new JTextField();
		textObservacao.setDocument(new limiteDeCampoTexto(50));
		textObservacao.addFocusListener(new CampoComConteudo());
		textObservacao.setForeground(PadroesDesign.CORTEXT);
		textObservacao.setFont(PadroesDesign.FONTETEXT);
		textObservacao.setColumns(10);
		textObservacao.setBounds(197, 561, 300, 28);
		desktopPane.add(textObservacao);
			
		JLabel lblAgencia = new JLabel("Agência");
		lblAgencia.setForeground(PadroesDesign.CORLABEL);
		lblAgencia.setFont(PadroesDesign.FONTELABEL);
		lblAgencia.setBounds(707, 545, 130, 16);
		desktopPane.add(lblAgencia);
		
		textAgencia = new JTextField();
		textAgencia.setDocument(new limiteDeCampoTexto(6));
		textAgencia.addFocusListener(new CampoComConteudo());
		textAgencia.setForeground(PadroesDesign.CORTEXT);
		textAgencia.setFont(PadroesDesign.FONTETEXT);
		textAgencia.setColumns(10);
		textAgencia.setBounds(707, 561, 110, 28);
		desktopPane.add(textAgencia);
					
		JLabel lblConta = new JLabel("Conta");
		lblConta.setForeground(PadroesDesign.CORLABEL);
		lblConta.setFont(PadroesDesign.FONTELABEL);
		lblConta.setBounds(827, 545, 160, 16);
		desktopPane.add(lblConta);
		
		textConta = new JTextField();
		textConta.setDocument(new limiteDeCampoTexto(12));
		textConta.addFocusListener(new CampoComConteudo());
		textConta.setForeground(PadroesDesign.CORTEXT);
		textConta.setFont(PadroesDesign.FONTETEXT);
		textConta.setColumns(10);
		textConta.setBounds(827, 561, 130, 28);
		desktopPane.add(textConta);
		
		btnLimpar = new JButton(new ImageIcon(loader.getResource("img/borracha.png")));
		btnLimpar.addActionListener(new BotaoLimpar());
		btnLimpar.setToolTipText("Limpar Todos os Campos");
		btnLimpar.setBounds(962, 561, 28, 28);
		desktopPane.add(btnLimpar);
		
		JSeparator separadorFimClientes = new JSeparator();  
		separadorFimClientes.setForeground(Color.BLACK);  
		separadorFimClientes.setBackground(Color.BLACK);  
		separadorFimClientes.setSize(975, 8); 
		separadorFimClientes.setLocation(17, 608);
		desktopPane.add(separadorFimClientes);
		
		lblLegendaObrigatorios = new JLabel("Campos Obrigatórios");
		lblLegendaObrigatorios.setForeground(PadroesDesign.CORLABEL);
		lblLegendaObrigatorios.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblLegendaObrigatorios.setBounds(40, 615, 282, 16);
		desktopPane.add(lblLegendaObrigatorios);
		
		textLegendaObrigatorios = new JTextField();
		textLegendaObrigatorios.setToolTipText("Campo necessários para cadastro do Cliente.");
		textLegendaObrigatorios.setHorizontalAlignment(SwingConstants.LEFT);
		textLegendaObrigatorios.setBackground(Color.getHSBColor(352,21,96));
		textLegendaObrigatorios.setForeground(PadroesDesign.CORLABEL);
		textLegendaObrigatorios.setFocusable(false);
		textLegendaObrigatorios.setFont(new Font("SansSerif", Font.BOLD, 12));
		textLegendaObrigatorios.setColumns(1);
		textLegendaObrigatorios.setBounds(17, 615, 15, 16);
		desktopPane.add(textLegendaObrigatorios);
		
		lblNaoEditaveis = new JLabel("Campo não editável");
		lblNaoEditaveis.setForeground(PadroesDesign.CORLABEL);
		lblNaoEditaveis.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNaoEditaveis.setBounds(40, 635, 282, 16);
		desktopPane.add(lblNaoEditaveis);
		
		textNaoEditaveis = new JTextField();
		textNaoEditaveis.setBackground(Color.getHSBColor(180,31,82));
		textNaoEditaveis.setToolTipText("Campos carregados automaticamente.");
		textNaoEditaveis.setHorizontalAlignment(SwingConstants.LEFT);
		textNaoEditaveis.setForeground(PadroesDesign.CORLABEL);
		textNaoEditaveis.setFocusable(false);
		textNaoEditaveis.setFont(new Font("SansSerif", Font.BOLD, 12));
		textNaoEditaveis.setColumns(1);
		textNaoEditaveis.setBounds(17, 635, 15, 16);
		desktopPane.add(textNaoEditaveis);
		
		btnIncluir = new JButton(new ImageIcon(loader.getResource("img/save.png")));
		btnIncluir.setText("Incluir");
		btnIncluir.addActionListener(new BotaoIncluir());
		btnIncluir.setForeground(PadroesDesign.CORBOTOES);
		btnIncluir.setToolTipText("3001 - Incluir Cliente");
		btnIncluir.setFont(PadroesDesign.FONTEBOTOES);
		btnIncluir.setBounds(476, 630,  125, 40);
		desktopPane.add(btnIncluir);
			
		btnAlterar = new JButton(new ImageIcon(loader.getResource("img/save.png")));
		btnAlterar.setText("Alterar");
		btnAlterar.addActionListener(new BotaoAlterar());
		btnAlterar.setForeground(PadroesDesign.CORBOTOES);
		btnAlterar.setToolTipText("3002 - Alterar Cliente");
		btnAlterar.setFont(PadroesDesign.FONTEBOTOES);
		btnAlterar.setBounds(606, 630,  125, 40);
		desktopPane.add(btnAlterar);
		
		btnExcluir = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
		btnExcluir.setText("Excluir");
		btnExcluir.addActionListener(new BotaoExcluir());
		btnExcluir.setForeground(PadroesDesign.CORBOTOES);
		btnExcluir.setToolTipText("3006 - Excluir Cliente");
		btnExcluir.setFont(PadroesDesign.FONTEBOTOES);
		btnExcluir.setBounds(736, 630,  125, 40);
		desktopPane.add(btnExcluir);
		
		btnCancelar = new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new BotaoCancelar());
		btnCancelar.setForeground(PadroesDesign.CORBOTOES);
		btnCancelar.setFont(PadroesDesign.FONTEBOTOES);
		btnCancelar.setBounds(866, 630,  125, 40);
		desktopPane.add(btnCancelar);
		
		/*NÂO EDITAVEIS*/
		textCodigo.setBackground(Color.getHSBColor(180,31,82));
		
		/*OBRIGATORIOS*/
		textNome.setBackground(Color.getHSBColor(352,21,96));
		textTelefone.setBackground(Color.getHSBColor(352,21,96));
		textAgencia.setBackground(Color.getHSBColor(352,21,96));
		textConta.setBackground(Color.getHSBColor(352,21,96));
		
		btnIncluir.setEnabled(true);
		btnResumo.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnAgendar.setEnabled(false);
		textCodigo.setText("NOVO");
		
		/*VERIFICANDO O USUARIO A SER EXIBIDO ESTES CAMPOS*/
		if(!SenhaDAO.getLogin().equals("THIAGO") && !SenhaDAO.getLogin().equals("DIEGO"))
		{
			lblUsuario.setVisible(false);
			comboBoxUsuarios.setVisible(false);
		}
						
		refreshTabela();
		
		/*TRATANDO O CLIQUE NO BOTÂO FECHAR*/
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) 
			{
				int n = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema ?" ," Sair do Sistema " ,JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) 
				{
					System.exit(0);	
				}
			}
		});
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.repaint();
		
		/*COLOCANDO O ENTER COMO O BOTÂO ENTRAR*/
		getRootPane().setDefaultButton(btnLocalizarCampos);
	}

	class BotaoChekBoxExecessão implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JPasswordField password = new JPasswordField(6);  
	        password.setEchoChar('*');   
	        JLabel rotulo = new JLabel("Digite a senha :  ");  
	        rotulo.setFont(PadroesDesign.FONTELABEL);
	        rotulo.setForeground(PadroesDesign.CORBOTOES);
	        
	        //CRIA O PAINEL A SER EXIBIDO
	        JPanel entUsuario = new JPanel(); 
	        entUsuario.add(rotulo);  
	        entUsuario.add(password); 
	      
	        /*EXIBE O PAINBEL ATRAVES DE UM PARAMETRO DO JOPTION PANE OU SEJA MONTAMOS UMA NOVA TELA USANDO O JPASSWORDFIELD*/
			ClassLoader loader = getClass().getClassLoader();
	        Icon figura = new ImageIcon(loader.getResource("img/logotipoSIAC.png"));
	        JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito - INCPP", JOptionPane.PLAIN_MESSAGE,figura);  
	        String senha = new String(password.getPassword());;  
	  			
	        if(senha.equals("1901") || senha.equals("36912"))
			{
	        	chckbxExcessao.setSelected(true);
			}
	        else
	        {
	        	chckbxExcessao.setSelected(false);
	        	JOptionPane.showMessageDialog(null, "Senha Incorreta! \n Favor informar ao administrador do sistema!");
	        	return;
	        }
		}
	}
	
	
	class BotaoDireitoTelefone extends MouseAdapter
	{
		
		Component componente[] = desktopPane.getComponents();
				
		@Override
		public void mouseClicked(MouseEvent evt)
		{
			 	if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {  
	              
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {  
	                
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) 
	            { 
	               	//cria o primeiro item do menu e atribui uma ação pra ele
					JMenuItem item1 = new JMenuItem("Copiar");
			   		item1.addActionListener(new ActionListener() 
			   		{
						 
			   			public void actionPerformed(ActionEvent e) 
			   			{
			   				textTelefone.copy();
						}
					});
						 
					//cria o segundo item do menu e atribui uma ação pra ele
					JMenuItem item2 = new JMenuItem("Colar");
					item2.addActionListener(new ActionListener() {
						 
						public void actionPerformed(ActionEvent e) 
						{
							textTelefone.paste();
			   			}
					});
						 
				    //cria o terceiro item do menu e atribui uma ação pra ele
					JMenuItem item3 = new JMenuItem("Recortar");
					item3.addActionListener(new ActionListener() 
					{
					    public void actionPerformed(ActionEvent e) 
					    {
					    	textTelefone.cut();
			   			}
				    });
	               
					  //cria o menu popup e adiciona os 3 itens
				    JPopupMenu popup = new JPopupMenu();
				    popup.add(item1);
				    popup.add(item2);
				    popup.add(item3);
					 
				    //mostra na tela
				    //System.out.println("nome " + textfield.getName());
				    popup.show(textTelefone, 10, 10);
	          }  
		}
	 
		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
		
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BotaoDireitoTelefonePesquisa extends MouseAdapter
	{
		
		Component componente[] = desktopPane.getComponents();
				
		@Override
		public void mouseClicked(MouseEvent evt)
		{
			 	if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {  
	              
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {  
	                
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) 
	            { 
	               	//cria o primeiro item do menu e atribui uma ação pra ele
					JMenuItem item1 = new JMenuItem("Copiar");
			   		item1.addActionListener(new ActionListener() 
			   		{
						 
			   			public void actionPerformed(ActionEvent e) 
			   			{
			   				textTelefoneOuParte.copy();
						}
					});
						 
					//cria o segundo item do menu e atribui uma ação pra ele
					JMenuItem item2 = new JMenuItem("Colar");
					item2.addActionListener(new ActionListener() {
						 
						public void actionPerformed(ActionEvent e) 
						{
							textTelefoneOuParte.paste();
			   			}
					});
						 
				    //cria o terceiro item do menu e atribui uma ação pra ele
					JMenuItem item3 = new JMenuItem("Recortar");
					item3.addActionListener(new ActionListener() 
					{
					    public void actionPerformed(ActionEvent e) 
					    {
					    	textTelefoneOuParte.cut();
			   			}
				    });
	               
					  //cria o menu popup e adiciona os 3 itens
				    JPopupMenu popup = new JPopupMenu();
				    popup.add(item1);
				    popup.add(item2);
				    popup.add(item3);
					 
				    //mostra na tela
				    //System.out.println("nome " + textfield.getName());
				    popup.show(textTelefoneOuParte, 10, 10);
	          }  
		}
	 
		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
		
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BotaoDireitoNome extends MouseAdapter
	{
		
		Component componente[] = desktopPane.getComponents();
				
		@Override
		public void mouseClicked(MouseEvent evt)
		{
			 	if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {  
	              
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {  
	                
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) 
	            { 
	               	//cria o primeiro item do menu e atribui uma ação pra ele
					JMenuItem item1 = new JMenuItem("Copiar");
			   		item1.addActionListener(new ActionListener() 
			   		{
						 
			   			public void actionPerformed(ActionEvent e) 
			   			{
			   				textNome.copy();
						}
					});
						 
					//cria o segundo item do menu e atribui uma ação pra ele
					JMenuItem item2 = new JMenuItem("Colar");
					item2.addActionListener(new ActionListener() {
						 
						public void actionPerformed(ActionEvent e) 
						{
							textNome.paste();
			   			}
					});
						 
				    //cria o terceiro item do menu e atribui uma ação pra ele
					JMenuItem item3 = new JMenuItem("Recortar");
					item3.addActionListener(new ActionListener() 
					{
					    public void actionPerformed(ActionEvent e) 
					    {
					    	textNome.cut();
			   			}
				    });
	               
					  //cria o menu popup e adiciona os 3 itens
				    JPopupMenu popup = new JPopupMenu();
				    popup.add(item1);
				    popup.add(item2);
				    popup.add(item3);
					 
				    //mostra na tela
				    //System.out.println("nome " + textfield.getName());
				    popup.show(textNome, 10, 10);
	          }  
		}
	 
		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
		
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BotaoDireitoCodigo extends MouseAdapter
	{
		
		Component componente[] = desktopPane.getComponents();
				
		@Override
		public void mouseClicked(MouseEvent evt)
		{
			 	if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {  
	              
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {  
	                
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) 
	            { 
	               	//cria o primeiro item do menu e atribui uma ação pra ele
					JMenuItem item1 = new JMenuItem("Copiar");
			   		item1.addActionListener(new ActionListener() 
			   		{
						 
			   			public void actionPerformed(ActionEvent e) 
			   			{
			   				textCodigoCliente.copy();
						}
					});
						 
					//cria o segundo item do menu e atribui uma ação pra ele
					JMenuItem item2 = new JMenuItem("Colar");
					item2.addActionListener(new ActionListener() {
						 
						public void actionPerformed(ActionEvent e) 
						{
							textCodigoCliente.paste();
			   			}
					});
						 
				    //cria o terceiro item do menu e atribui uma ação pra ele
					JMenuItem item3 = new JMenuItem("Recortar");
					item3.addActionListener(new ActionListener() 
					{
					    public void actionPerformed(ActionEvent e) 
					    {
					    	textCodigoCliente.cut();
			   			}
				    });
	               
					  //cria o menu popup e adiciona os 3 itens
				    JPopupMenu popup = new JPopupMenu();
				    popup.add(item1);
				    popup.add(item2);
				    popup.add(item3);
					 
				    //mostra na tela
				    //System.out.println("nome " + textfield.getName());
				    popup.show(textCodigoCliente, 10, 10);
	          }  
		}
	 
		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
		
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
			
	class BotaoDireitoNomeOuParte extends MouseAdapter
	{
		
		Component componente[] = desktopPane.getComponents();
				
		@Override
		public void mouseClicked(MouseEvent evt)
		{
			 	if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {  
	              
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON2_MASK) != 0) {  
	                
	            }  
	            if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) 
	            { 
	               	//cria o primeiro item do menu e atribui uma ação pra ele
					JMenuItem item1 = new JMenuItem("Copiar");
			   		item1.addActionListener(new ActionListener() 
			   		{
						 
			   			public void actionPerformed(ActionEvent e) 
			   			{
			   				textNomeOuParte.copy();
						}
					});
						 
					//cria o segundo item do menu e atribui uma ação pra ele
					JMenuItem item2 = new JMenuItem("Colar");
					item2.addActionListener(new ActionListener() {
						 
						public void actionPerformed(ActionEvent e) 
						{
							textNomeOuParte.paste();
			   			}
					});
						 
				    //cria o terceiro item do menu e atribui uma ação pra ele
					JMenuItem item3 = new JMenuItem("Recortar");
					item3.addActionListener(new ActionListener() 
					{
					    public void actionPerformed(ActionEvent e) 
					    {
					    	textNomeOuParte.cut();
			   			}
				    });
	               
					  //cria o menu popup e adiciona os 3 itens
				    JPopupMenu popup = new JPopupMenu();
				    popup.add(item1);
				    popup.add(item2);
				    popup.add(item3);
					 
				    //mostra na tela
				    //System.out.println("nome " + textfield.getName());
				    popup.show(textNomeOuParte, 10, 10);
	          }  
		}
	 
		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
		
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class BotaoExcluir implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			
			if(ClienteSiacDAO.getIdCliente() != 0 && !textCodigo.getText().trim().equals("NOVO"))
			{
				if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3006"))
				{
					if(ClienteSiacDAO.getOperador().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Este Cliente pertente a um outro operador, favor verificar!");
						return;
					}
					
					int n = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Cliente?" ," Exclusão de Cliente " ,JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) 
					{
						getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
						
						ClienteSiacDAO.setIdCliente(Integer.parseInt(textCodigo.getText().trim()));
						ClienteSiacDAO processar = new ClienteSiacDAO();
						processar.excluirCliente();

						/*ATUALIZANDO TABELA E LIMPANDO CAMPOS PREENCHIDOS*/
						refreshTabela();
						/*ACIONANDO UM CLIQUE NO BOTÂO LIMPAR PARA LIMPAR LISTA E FAZER UM NOVO REFRESH DE TABELA*/
						btnLimpar.doClick();
						
						getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
						JOptionPane.showMessageDialog(null, "O Cliente e seus agendamentos foram excluídos com sucesso!");
					}	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina de 3006 - Excluir Cliente. \n Favor informar ao administrador do sistema!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Favor efetuar a consulta e selecionar o cliente, ates de clicar em consultar!");
			}
		}
	}
	
	class BotaoIncluir implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3001"))
			{
				int n = JOptionPane.showConfirmDialog(null, "Confirma a Inclusão deste Cliente ?" ," Incluir Cliente " ,JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) 
				{
					getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
					
					if(textNome.getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Favor preencher o Campo Nome!");
						textNome.grabFocus();
						return;
					}
					
					if(textAgencia.getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Favor preencher o Campo Agencia");
						textAgencia.grabFocus();
						return;
					}
					
					if(textConta.getText().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Favor preencher o Campo Conta");
						textConta.grabFocus();
						return;
					}
					
					String telefone = textTelefone.getText().trim();
					telefone = telefone.replace("-", "");
					telefone = telefone.replace(" ", "");
					telefone = telefone.replace("(", "");
					telefone = telefone.replace(")", "");
										
					String celular = textCelular.getText().trim();
					celular = celular.replace("-", "");
					celular = celular.replace(" ", "");
					celular = celular.replace("(", "");
					celular = celular.replace(")", "");
												
					/*VALIDANDO O PREENCHIMENTO DE TELEFONE OU CELULAR*/
					if(telefone.trim().length() == 0 && celular.trim().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Favor preencher o Campo Telefone ou o Celular");
						textTelefone.grabFocus();
						return;
					}
					
					/*CAMPOS USADOS PARA A VALIDAÇÂO SE O CLIENTE JÀ EXISTE*/
					ClienteSiacDAO.setCelular(celular);	
					ClienteSiacDAO.setTelefone(telefone);
					/*VALIDAÇÂO CAMPO NOME COM ERRO*/
					if(textNome.getText().contains(" "))
					{
						ClienteSiacDAO.setNome(textNome.getText().substring(0,textNome.getText().indexOf(' ')));
					}
					else
					{
						ClienteSiacDAO.setNome(textNome.getText().trim());
					}
					
					String agencia = textAgencia.getText().trim();
					agencia = agencia.replace(" ", "");
					agencia = agencia.replace("(", "");
					agencia = agencia.replace(")", "");
					agencia = agencia.replace(".", "");
					agencia = agencia.replace(",", "");
										
					String conta = textConta.getText().trim();
					conta = conta.replace(" ", "");
					conta = conta.replace("(", "");
					conta = conta.replace(")", "");
					conta = conta.replace(".", "");
					conta = conta.replace(",", "");
										
					ClienteSiacDAO processar = new ClienteSiacDAO();
					System.out.println("TELEFONE " + telefone);
					
					if(!chckbxExcessao.isSelected())
					{
						if(telefone.length() != 0)
						{
							if(telefone.length() < 10)
							{
								JOptionPane.showMessageDialog(null, "Favor Verificar o Campo Telefone!");
								textTelefone.grabFocus();
								return;
							}
				
							ClienteSiacDAO.setSQLencontraNomeCliente("SELECT * FROM Clientes WHERE Nome LIKE \'%" + ClienteSiacDAO.getNome() + "%\' AND Telefone LIKE \'%"+ telefone +"%\'");
							processar.ValidarCliente();
							
							if(ClienteSiacDAO.isClienteValido())
							{
								getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
								JOptionPane.showMessageDialog(null, "Favor Verificar o Código : " + ClienteSiacDAO.getIdCliente() + " pois o Cliente possui Telefone e Nome semelhantes !");
								textNome.grabFocus();
								return;
							}	
						}
						else
						{
							if(celular.length() < 11)
							{
								JOptionPane.showMessageDialog(null, "Favor Verificar o Campo Celular ou Preencher o campo Telefone!");
								textCelular.grabFocus();
								return;
							}
				
							ClienteSiacDAO.setSQLencontraNomeCliente("SELECT * FROM Clientes WHERE Nome LIKE \'%" + ClienteSiacDAO.getNome() + "%\' AND Celular LIKE \'%"+ celular +"%\'");
							processar.ValidarCliente();
							
							if(ClienteSiacDAO.isClienteValido())
							{
								getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
								JOptionPane.showMessageDialog(null, "Favor Verificar o Código " + ClienteSiacDAO.getIdCliente() + " pois o Cliente possui Celular e Nome semelhantes !");
								textNome.grabFocus();
								return;
							}	
							
						}
						
						/*CASO NÂO TENHA ACHADO O TELEFONE VALIDAREMOS O PRIMEIRO NOME E AGENCIA E CONTA*/
						ClienteSiacDAO.setSQLencontraNomeCliente("SELECT * FROM Clientes WHERE Nome LIKE \'%" + ClienteSiacDAO.getNome() + "%\' AND Agencia =\'"+ agencia +"\' AND Conta =\'"+ conta +"\'");
						processar.ValidarCliente();
							
						if(ClienteSiacDAO.isClienteValido())
						{
							getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
							JOptionPane.showMessageDialog(null, "Favor Verificar o Código " + ClienteSiacDAO.getIdCliente() + "  pois o Cliente possui Agencia, Conta e Nome semelhantes !");
							textNome.grabFocus();
							return;
						}	
		
						/*CASO NÂO TENHA ACHADO OS ANTERIORES VALIDAREMOS O NOME COMPLETO 06/08/2018*/
						ClienteSiacDAO.setSQLencontraNomeCliente("SELECT * FROM Clientes WHERE Nome LIKE \'" + ClienteSiacDAO.getNome() + "%\' AND Nome LIKE \'%" + textNome.getText().trim() + "%\'");
						processar.ValidarCliente();
							
						if(ClienteSiacDAO.isClienteValido())
						{
							getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
							JOptionPane.showMessageDialog(null, "Favor Verificar o Código " + ClienteSiacDAO.getIdCliente() + "  pois o Cliente possui Nome completo semelhante !");
							textNome.grabFocus();
							return;
						}	
					}
					
					
					String idFilial = "" + comboBoxFilial.getSelectedItem();
					/*OBRIGANDO A OPERADORA A SELECIONAR UMA FILIAL PARA REALIZAR O AGENDAMENTO*/
					if(idFilial.trim().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Favor Selecionar uma Filial, antes de clicar em Salvar ");
						comboBoxFilial.grabFocus();
						return;
					}
																	
					ClienteSiacDAO.setNome(textNome.getText().trim());
					ClienteSiacDAO.setObservacao("" + textObservacao.getText().trim());
					ClienteSiacDAO.setBanco("" + comboBoxBanco.getSelectedItem());
					ClienteSiacDAO.setAgencia(agencia);
					ClienteSiacDAO.setConta(conta);
					
					/*USADO PELO THIAGO PARA A CRIAÇÂO DE UM REGISTRO EM NOME DE UMA OPERADORA*/
					ClienteSiacDAO.setOperador("" + comboBoxUsuarios.getSelectedItem());
					processar.IserirCliente();
					
					/*RETORNO DA FUNÇÂO DE INSERIR CLIENTE*/
					if(ClienteSiacDAO.isClienteValido())
					{
						textCodigo.setText("" + ClienteSiacDAO.getIdCliente());
						btnIncluir.setEnabled(false);
						btnAlterar.setEnabled(true);
						btnExcluir.setEnabled(true);
						btnLimpar.setEnabled(true);
						btnAgendar.setEnabled(true);
						btnResumo.setEnabled(true);
						chckbxExcessao.setSelected(false);
						
						/*POSICIONANDO NO CLIENTE PARA CONFIRMAR CADASTRO*/
						ClienteSiacDAO.setBuscarCliente("SELECT * FROM Clientes WHERE Clientes.Nome LIKE \'%" + textNome.getText().trim() + "'");
						refreshTabela();
							
						JOptionPane.showMessageDialog(null, "Cliente: " + ClienteSiacDAO.getIdCliente() + " - " + textNome.getText().trim() + " incluído com sucesso!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Problema na gravação do Cliente", "Verifique no sistema, pois este cliente já existe!", JOptionPane.WARNING_MESSAGE);
					}
					
				}	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina de 3001 - Incluir Cliente. \n Favor informar ao administrador do sistema!");
			}
		}
	}
	
	
	class BotaoAlterar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(ClienteSiacDAO.getIdCliente() != 0 && !textCodigo.getText().trim().equals("NOVO"))
			{
				if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3002"))
				{
					if(ClienteSiacDAO.getOperador().trim().length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Este Cliente pertente a um outro operador, favor verificar!");
						return;
					}
					
					int n = JOptionPane.showConfirmDialog(null, "Confirma a Alteração deste Cliente ?" ," Alterar Cliente " ,JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) 
					{
						/*NÂO FECHAMOS POIS NO REFRESH DE TABELA È EXECUTADO NOVAMENTE*/
						getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));	
						
						if(textAgencia.getText().trim().length() == 0)
						{
							JOptionPane.showMessageDialog(null, "Favor preencher o Campo Agencia");
							textAgencia.grabFocus();
							return;
						}
						
						if(textConta.getText().trim().length() == 0)
						{
							JOptionPane.showMessageDialog(null, "Favor preencher o Campo Conta");
							textConta.grabFocus();
							return;
						}
				
						if(textNome.getText().trim().length() == 0)
						{
							JOptionPane.showMessageDialog(null, "Favor preencher o Campo Nome!");
							textNome.grabFocus();
							return;
						}
						
						if(textTelefone.getText().trim().length() == 0)
						{
							JOptionPane.showMessageDialog(null, "Favor preencher o Campo Telefone!");
							textTelefone.grabFocus();
							return;
						}
						
						String telefone = textTelefone.getText().trim();
						if(telefone.length() > 10)
						{
							telefone = telefone.replace("-", "");
							telefone = telefone.replace(" ", "");
							telefone = telefone.replace("(", "");
							telefone = telefone.replace(")", "");
						}
						
						ClienteSiacDAO.setTelefone(telefone);
						
						ClienteSiacDAO.setNome(textNome.getText().trim());

						String celular = textCelular.getText().trim();
						if(celular.length() > 11)
						{
							celular = celular.replace("-", "");
							celular = celular.replace(" ", "");
							celular = celular.replace("(", "");
							celular = celular.replace(")", "");
						}					
													
						String idFilial = "" + comboBoxFilial.getSelectedItem();
						/*OBRIGANDO A OPERADORA A SELECIONAR UMA FILIAL PARA REALIZAR O AGENDAMENTO*/
						if(idFilial.trim().length() == 0)
						{
							JOptionPane.showMessageDialog(null, "Favor Selecionar uma Filial, antes de clicar em Salvar ");
							comboBoxFilial.grabFocus();
							return;
						}
						
						ClienteSiacDAO.setCelular(celular);											
						ClienteSiacDAO.setObservacao("" + textObservacao.getText().trim());
						ClienteSiacDAO.setIdCliente(Integer.parseInt(textCodigo.getText()));
						ClienteSiacDAO.setBanco("" + comboBoxBanco.getSelectedItem());
						ClienteSiacDAO.setAgencia("" + textAgencia.getText().trim());
						ClienteSiacDAO.setConta("" + textConta.getText().trim());
												
						ClienteSiacDAO processar = new ClienteSiacDAO();
						processar.AtualizarCliente();
						
														
						/*RETORNO DA FUNÇÂO DE INSERIR CLIENTE*/
						if(ClienteSiacDAO.isClienteValido())
						{
							btnIncluir.setEnabled(false);
							btnAlterar.setEnabled(true);
							btnExcluir.setEnabled(true);
							btnLimpar.setEnabled(true);
							btnAgendar.setEnabled(true);
							btnResumo.setEnabled(true);
												
							ClienteSiacDAO.setBuscarCliente("SELECT * FROM Clientes WHERE Clientes.Nome LIKE \'%" + textNome.getText().trim() + "'");
							refreshTabela();
							
							JOptionPane.showMessageDialog(null, "Cliente: " + ClienteSiacDAO.getIdCliente() + " - " + textNome.getText().trim() + " alterado com sucesso!");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Problema na alteração do Cliente", "Verifique no sistema, pois este cliente já existe!", JOptionPane.WARNING_MESSAGE);
						}
					}	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina de 3002 - Alterar Cliente. \n Favor informar ao administrador do sistema!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Favor efetuar a consulta e selecionar um cliente, ates de clicar em Alterar!");
			}
	
		}
	}
	
	class BotaoCancelar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			new d3Frame();
			dispose();
		}
	}
		
	class BotaoLimpar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			textCodigo.setText("NOVO"); 
			textNome.setText("");
			textCelular.setText("");
			textTelefone.setText("");
			textObservacao.setText("");
			textAgencia.setText("");
			textConta.setText("");
						
			/*LIMPANDO TODOS OS VALORES*/
			ClienteSiacDAO.setIdCliente(0); 
			ClienteSiacDAO.setNome(""); 
			ClienteSiacDAO.setCelular(""); 
			ClienteSiacDAO.setTelefone(""); 
			ClienteSiacDAO.setObservacao(""); 
			ClienteSiacDAO.setAgencia(""); 
			ClienteSiacDAO.setConta(""); 
			ClienteSiacDAO.setOperador(""); 
			
			btnAgendar.setEnabled(false);
			btnIncluir.setEnabled(true);
			btnResumo.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnLimpar.setEnabled(false);
		}
	}
	
	
	
	class ClicandoMouse implements MouseListener
	{			
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			int rowIndex = tabelaClientes.convertRowIndexToModel(tabelaClientes.getSelectedRow());
			if (rowIndex != -1)
			{
				//AO CLICAR NO VALOR DA TABELA CARREGA AS VÀRIÀVEIS DA CLASSE DAO< ESTAS SERÂO USADAS PELA TELA DE MANUTENÇÃO	
				ClienteSiac clientesSiac = new ClienteSiacTableModel(ClienteSiacList).get(rowIndex);
	
				ClienteSiacDAO.setIdCliente(clientesSiac.getIdCliente());
				ClienteSiacDAO.setNome(clientesSiac.getNome());
				ClienteSiacDAO.setCelular(clientesSiac.getCelular());
				ClienteSiacDAO.setTelefone(clientesSiac.getTelefone());
				ClienteSiacDAO.setObservacao(clientesSiac.getObs());
				ClienteSiacDAO.setAgencia(clientesSiac.getAgencia());
				ClienteSiacDAO.setConta(clientesSiac.getConta());
				ClienteSiacDAO.setBanco(clientesSiac.getBanco());
				ClienteSiacDAO.setOperador(clientesSiac.getOperador());
				ClienteSiacDAO.setIdFilial(clientesSiac.getIdFilial());
				filialAnterior = clientesSiac.getIdFilial();
							
				textCodigo.setText("" + clientesSiac.getIdCliente()); 
				textNome.setText(clientesSiac.getNome().trim());
				textObservacao.setText(clientesSiac.getObs()); 
				textAgencia.setText(clientesSiac.getAgencia());
				textConta.setText(clientesSiac.getConta());
				comboBoxBanco.setSelectedItem(clientesSiac.getBanco());
				
				/*VOLTANDO O CAMPO A SUA COR DE COMO PREENCHIDO COM O CLIQUE*/
				textNome.setBackground(Color.WHITE);
				textTelefone.setBackground(Color.WHITE);
				textAgencia.setBackground(Color.WHITE);
				textConta.setBackground(Color.WHITE);

				System.out.println(ClienteSiacDAO.getOperador());
				if(ClienteSiacDAO.getOperador().trim().length() != 0)
				{
					if(clientesSiac.getTelefone().trim().length() < 8)
					{
						textTelefone.setText(clientesSiac.getTelefone().trim());
					} 
					else if(clientesSiac.getTelefone().trim().length() == 10)
					{
						textTelefone.setText("(" + clientesSiac.getTelefone().substring(0, 2) + ") " + clientesSiac.getTelefone().substring(2, 6) + "-" + clientesSiac.getTelefone().substring(6, 10));
					}
					else
					{
						textTelefone.setText("(00) " + clientesSiac.getTelefone().trim().substring(0, 4) + "-" + clientesSiac.getTelefone().trim().substring(4, 8));
					}
							
					if(clientesSiac.getCelular().trim().length() == 11)
					{
						textCelular.setText("(" + clientesSiac.getCelular().substring(0, 2) + ") " + clientesSiac.getCelular().substring(2, 7) + "-" + clientesSiac.getCelular().substring(7, 11));
					}
					else
					{
						if(clientesSiac.getCelular().trim().length() < 9)
						{
							textCelular.setText(clientesSiac.getCelular());
						}
						else
						{
							textCelular.setText("(00) " + clientesSiac.getCelular().trim().substring(0, 5) + "-" + clientesSiac.getCelular().trim().substring(5, 9));
						}
					}
								
					btnAgendar.setEnabled(true);
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					btnResumo.setEnabled(true);
				}
				else
				{
					textTelefone.setText("OCULTO");
					textCelular.setText("OCULTO");
					
					btnAgendar.setEnabled(false);
					btnAlterar.setEnabled(false);
					btnExcluir.setEnabled(false);
					btnResumo.setEnabled(false);
					
				}
				
				btnIncluir.setEnabled(false);
				btnLimpar.setEnabled(true);
			
				
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}
	} 
		
	class BotaoLocalizar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			/*ACIONANDO UM CLIQUE NO BOTÂO LIMPAR PARA LIMPAR LISTA E FAZER UM NOVO REFRESH DE TABELA*/
			btnLimpar.doClick();
			
			String localizar = ""; 
			
			/* PADR^ÇAO INICIAL DE BUSCA DO SISTEMA PARA LOCALIZAR OS CLIENTES */
			localizar += "SELECT * FROM Clientes WHERE (1=1)";
			
			if(textCodigoCliente.getText().trim().length() > 0 )
			{
				localizar += " AND Clientes.IDCliente =" + textCodigoCliente.getText().trim();
			}
			
			if(textNomeOuParte.getText().trim().length() > 0 )
			{
				localizar += " AND Clientes.Nome LIKE \'%" + textNomeOuParte.getText().trim().toUpperCase() + "%\'";
			}
			
			if(textTelefoneOuParte.getText().trim().length() > 0)
			{
				/*REMOVENDO O CARACTER DE SEPARAÇÂO DO NUMERO*/
				String telefone = textTelefoneOuParte.getText().trim().replace("-","");
				localizar +=  " AND (Clientes.Telefone LIKE \'%"+ telefone +"%\' OR Clientes.Celular LIKE \'%"+ telefone +"%\')";
			}
			
			if(textObservacaoOuParte.getText().trim().length() > 0 )
			{
				localizar += " AND Clientes.Obs LIKE \'%" + textObservacaoOuParte.getText().trim().toUpperCase() + "%\'";
			}
			
			localizar += " ORDER BY Clientes.Nome ";
					
			//DEFINE O VALOR NA VARIÀVEL USADA PARA CARREGAR A LISTA NA CLASSE DAO
			ClienteSiacDAO.setBuscarCliente(localizar);
			refreshTabela();
					
			if(!ClienteSiacDAO.isLocalizouCliente())
			{
				JOptionPane.showMessageDialog(null, "Não foi localizado nenhum cliente seguindo os critérios acima, favor verificar !");
			}
		}
	}	
			
	public void refreshTabela()
	{
		
		getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));		

		//CARREGA A FUNÇÂO FINDCLIENTES DA CLASSE CONTROLLER	
		ClienteSiacList = new ClienteSiacController().findClienteSiac();
		if (ClienteSiacList != null){
			tabelaClientes.setModel(new ClienteSiacTableModel(ClienteSiacList));
			tabelaClientes.setDefaultRenderer(Object.class, new ClienteSiacCellRender());
		}

		if(ClienteSiacList.size() == 0)
		{
			tabelaClientes.setAutoResizeMode (JTable.WIDTH); 
		}
		else
		{
			tabelaClientes.setAutoResizeMode (JTable.AUTO_RESIZE_OFF); 
		}
		
		getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
	}
	
	class CampoComConteudo implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0) {
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
						
			if(textNomeOuParte.getText().trim().length() > 0)
			{
				textNomeOuParte.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				textNomeOuParte.setBackground(Color.WHITE);
			}
					
			if(textTelefoneOuParte.getText().trim().length() > 0)
			{
				textTelefoneOuParte.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				textTelefoneOuParte.setBackground(Color.WHITE);
			}
			
			if(textObservacaoOuParte.getText().trim().length() > 0)
			{
				textObservacaoOuParte.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				textObservacaoOuParte.setBackground(Color.WHITE);
			}
			
			if(textNome.getText().trim().length() > 0 )
			{
				textNome.setBackground(Color.WHITE);
			}
			else
			{
				textNome.setBackground(Color.getHSBColor(352,21,96));
			}
			
			if(textTelefone.getText().trim().length() > 0 )
			{
				textTelefone.setBackground(Color.WHITE);
			}
			else
			{
				textTelefone.setBackground(Color.getHSBColor(352,21,96));
			}
			
			if(textAgencia.getText().trim().length() > 0 )
			{
				textAgencia.setBackground(Color.WHITE);
			}
			else
			{
				textAgencia.setBackground(Color.getHSBColor(352,21,96));
			}
			
			if(textConta.getText().trim().length() > 0 )
			{
				textConta.setBackground(Color.WHITE);
			}
			else
			{
				textConta.setBackground(Color.getHSBColor(352,21,96));
			}
		}
	}
}
