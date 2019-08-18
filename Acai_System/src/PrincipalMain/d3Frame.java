package PrincipalMain;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ConfiguracoesFrame.SenhaFrame;
import Informações.Informacoes;
import PadroesDesign.PadroesDesign;
import d3DAO.SenhaDAO;
import d3Frame.ClienteSIACFrame;
import d3Frame.NumeroPedidoFrame;
import d3Frame.PesquisaCliente;
import d3Frame.ProdutosFrame;

public class d3Frame extends JFrame {
	
	JDesktopPane desktopPane;
	JMenuItem miSenhas,miUsuarios, miProdutos, 
	miAjustesDataInclusaoAgendamento, miLicençaSistema, miAjusteMailing; 
	JMenu mnConfiguracoes, mnAjustes, mnSistema, mnRelatorios, mnSiacAntigo, mnMailing;
	JMenuItem miSac,miClientes, miTransferencia, miRelatorioAgendamentos, miRelatorioClientesAgendamentos, miConsultaAoSistemaAntigo, miPainelAgendamentos; 
	JMenuBar menuBarIcones , menuBar;
	JMenu btSair;
	
	//SenhaDAO alteracoesLogin;
	JSeparator js ;
	
	//RECEBE HORÁRIO e DATA ATUAL DO SISTEMA
	Calendar CalendarHora; 
	DecimalFormat formato;
	public static JLabel lbInformacoes; 
	public static JLabel lbMensagens; 
	
	boolean fim = false;
	int hh,mm,ss,h; 
	//RECEBE INFORMAÇÕES DO PRIMEIRO ACESSO ATRAVÉS DO INFORMAÇÔES, VALIDADO AO LOGAR 
	public void setJLRotulo(JLabel jLRotulo) 	{	d3Frame.lbInformacoes = jLRotulo;}
	public static JLabel lblimgLogin;
	
	private static String horarioDeAcesso;
	public static String getHorarioDeAcesso() {return horarioDeAcesso;}
	public static void setHorarioDeAcesso(String horarioDeAcesso) {d3Frame.horarioDeAcesso = horarioDeAcesso;}
	
	private static String logoUsado;
	public static String getLogoUsado() {return logoUsado;}
	public static void setLogoUsado(String logoUsado) {d3Frame.logoUsado = logoUsado;}
	
	ClassLoader loader = getClass().getClassLoader();
	
	public d3Frame ()
	{
		this.inicializarComponentes();
	}
		
	private void inicializarComponentes() 
	{
		String cabecalho = "Bem vindo ao D3 Açaí : " + SenhaDAO.getLogin() + "                                   "+ d3Frame.getHorarioDeAcesso()            +"            V.01.00";

		setTitle(cabecalho);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 718);
		getContentPane().setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setLayout(new BorderLayout(0, 0));
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menuBar.setFont(PadroesDesign.FONTELABEL);
		setJMenuBar(menuBar);

		mnConfiguracoes = new JMenu("Configurações");
		mnConfiguracoes.setHorizontalAlignment(SwingConstants.LEFT);
		//mnConfiguracoes.setForeground(PadroesDesign.CORBOTOES);
		mnConfiguracoes.setFont(PadroesDesign.FONTELABEL);
		//mnConfiguracoes.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnConfiguracoes);

		this.miUsuarios = new JMenuItem("Usuarios");
		this.miUsuarios.addActionListener(new MenuOpcaoUsuario());
		this.miUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		this.miUsuarios.setFont(PadroesDesign.FONTELABEL);
		this.mnConfiguracoes.add(miUsuarios);
		
		this.miProdutos = new JMenuItem("Produtos");
		this.miProdutos.addActionListener(new MenuProdutos());
		this.miProdutos.setHorizontalAlignment(SwingConstants.LEFT);
		this.miProdutos.setFont(PadroesDesign.FONTELABEL);
		this.mnConfiguracoes.add(miProdutos);
		
		mnSistema = new JMenu("Sistema");
		mnSistema.setHorizontalAlignment(SwingConstants.LEFT);
		mnSistema.setFont(PadroesDesign.FONTELABEL);
		menuBar.add(mnSistema);
		
		this.miClientes = new JMenuItem("Produção");
		this.miClientes.addActionListener(new MenuItemClientesSIAC());
		this.miClientes.setHorizontalAlignment(SwingConstants.LEFT);
		this.miClientes.setFont(PadroesDesign.FONTELABEL);
		this.mnSistema.add(miClientes);

	JMenuBar mnBarIcones = new JMenuBar();
	mnBarIcones.setSize(600, 40);
	mnBarIcones.setForeground(PadroesDesign.CORBOTOES);
	mnBarIcones.setFont(PadroesDesign.FONTELABEL);
	getContentPane().add(mnBarIcones, BorderLayout.NORTH);
	
	JButton btnVenda = new JButton();
	ImageIcon img_Venda = new ImageIcon(loader.getResource("imgIcones/vendas.png"));  
	btnVenda.setIcon(img_Venda);
	btnVenda.addActionListener(new MenuItemClientesSIAC());
	btnVenda.setToolTipText("Ibserir Pedido de Venda ");
	btnVenda.setHorizontalAlignment(SwingConstants.CENTER);
	mnBarIcones.add( btnVenda);
	
	JButton btnProducao = new JButton();
	ImageIcon img_Producao = new ImageIcon(loader.getResource("imgIcones/producao.png"));  
	btnProducao.setIcon(img_Producao);
	btnProducao.addActionListener(new MenuItemClientesSIAC());
	btnProducao.setToolTipText("Verificar o que está sendo produzido ");
	btnProducao.setHorizontalAlignment(SwingConstants.CENTER);
	mnBarIcones.add(btnProducao);
	
	JButton btnPainel = new JButton();
	ImageIcon img_Painel = new ImageIcon(loader.getResource("imgIcones/painel.png"));  
	btnPainel.setIcon(img_Painel);
	btnPainel.addActionListener(new MenuItemPainel());
	btnPainel.setToolTipText("Verificar o Numero Liberado ");
	btnPainel.setHorizontalAlignment(SwingConstants.CENTER);
	mnBarIcones.add(btnPainel);
	
	JButton btPedidoPronto = new JButton();
	ImageIcon img_PedidoPronto = new ImageIcon(loader.getResource("imgIcones/pedidoPronto.png"));  
	btPedidoPronto.setIcon(img_PedidoPronto);
	btPedidoPronto.addActionListener(new MenuItemClientesSIAC());
	btPedidoPronto.setToolTipText("Pedidos que já foram produzidos ");
	btPedidoPronto.setHorizontalAlignment(SwingConstants.CENTER);
	mnBarIcones.add(btPedidoPronto);
	
	JButton btWhatsApp = new JButton();
	ImageIcon img_WhatsApp = new ImageIcon(loader.getResource("imgIcones/WhatsApp.png"));  
	btWhatsApp.setIcon(img_WhatsApp);
	btWhatsApp.addActionListener(new BotaoWhatsAppListener());
	btWhatsApp.setToolTipText("Pedidos que já foram produzidos ");
	btWhatsApp.setHorizontalAlignment(SwingConstants.CENTER);
	mnBarIcones.add(btWhatsApp);

	JButton menu_Sair = new JButton();    
	ImageIcon img_Sair = new ImageIcon(loader.getResource("imgIcones/Sair.png"));    
	menu_Sair.setIcon(img_Sair);  
	menu_Sair.setToolTipText("Encerrar o Sistema");
	menu_Sair.addActionListener(new MenuSair());
	mnBarIcones.add(menu_Sair);   
		
	JButton menu_Vazio = new JButton();    
	ImageIcon img_Vazio = new ImageIcon(loader.getResource("imgIcones/Vazio.png")); 
	menu_Vazio.setEnabled(false);;
	menu_Vazio.setIcon(img_Vazio);  
	mnBarIcones.add(menu_Vazio); 
	
	JLabel lbEspaço = new JLabel("                                                                                                            ");
	lbEspaço.setFont(PadroesDesign.FONTELABEL);
	menuBar.add(lbEspaço);
	
	lbInformacoes = new JLabel("");
	lbInformacoes.setFont(PadroesDesign.FONTELABEL);
	menuBar.add(lbInformacoes);
	//Atualiza o texto da Label Hora através da Classe Informações - Usando a Classe Calendar
	ActionListener tarefa = (new ActionListener()
	{ 
		public void actionPerformed(ActionEvent e)
		{ 				
			Informacoes receberInformacoes = new Informacoes(); 
			receberInformacoes.DATA();
			receberInformacoes.HORAS();
		} 
	}); 

	//Realiza determinadas tarefas em um certo tempo, recebe um action Listenner para executar
		javax.swing.Timer time = new javax.swing.Timer(1000,tarefa); 
		time.start(); 
		
		//JTabbedPane Painel_Abas = new JTabbedPane(JTabbedPane.NORTH);
		//desktopPane.add(Painel_Abas, BorderLayout.CENTER);
		
		lblimgLogin = new JLabel(new ImageIcon(loader.getResource("img/fundo.png")));
		lblimgLogin.setVisible(true);
		lblimgLogin.setFont(PadroesDesign.FONTELABEL);
		lblimgLogin.setForeground(PadroesDesign.CORTITULOS);
		lblimgLogin.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		lblimgLogin.setBounds(0, 0, 1024, 650);
		desktopPane.add(lblimgLogin);

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
	}

	class BotaoWhatsAppListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			Desktop desktop = null;
			URI uri = null;
					
			try
			{
				if (!Desktop.isDesktopSupported()) 
				throw new IllegalStateException("Desktop resources not supported!");
				
				desktop = Desktop.getDesktop();
				
				if (!desktop.isSupported(Desktop.Action.BROWSE))
			    throw new IllegalStateException("No default browser set!");
				
				uri = new URI("https://web.whatsapp.com/");
				desktop.browse(uri);
			} 
			catch (URISyntaxException e)
			{
				System.out.println(e);
				e.printStackTrace();
			} 
			catch (IOException e)
			{
				System.out.println(e);
				e.printStackTrace();
			}
		}
	}	
	
	/*PESQUISA CLIENTE DE ASSINATURAS*/
	class MenuItemPainel implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3001") || SenhaDAO.getDireitosAtribuidos().contains("3002") || SenhaDAO.getDireitosAtribuidos().contains("3003")
					|| SenhaDAO.getDireitosAtribuidos().contains("3004") || SenhaDAO.getDireitosAtribuidos().contains("3005") || SenhaDAO.getDireitosAtribuidos().contains("3006"))
			{
				new NumeroPedidoFrame();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 3000 - Acesso Total ao Cadastro de Cliente. \n Favor informar ao administrador do sistema!");
			}
		}
	}
	
	
	/*PESQUISA CLIENTE DE ASSINATURAS*/
	class MenuItemClientesSIAC implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3001") || SenhaDAO.getDireitosAtribuidos().contains("3002") || SenhaDAO.getDireitosAtribuidos().contains("3003")
					|| SenhaDAO.getDireitosAtribuidos().contains("3004") || SenhaDAO.getDireitosAtribuidos().contains("3005") || SenhaDAO.getDireitosAtribuidos().contains("3006"))
			{
				new ClienteSIACFrame();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 3000 - Acesso Total ao Cadastro de Cliente. \n Favor informar ao administrador do sistema!");
			}
		}
	}
	
	/*PESQUISA CLIENTE DE ASSINATURAS*/
	class MenuItemPesquisaCliente implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(SenhaDAO.getDireitosAtribuidos().contains("2605"))
			{
				new PesquisaCliente();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 2605 - Relatório de Clientes. \n Favor informar ao administrador do sistema!");
			}
		}
	}
	
	
		
	class MenuProdutos implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(SenhaDAO.getDireitosAtribuidos().contains("1000") || SenhaDAO.getDireitosAtribuidos().contains("1001") || SenhaDAO.getDireitosAtribuidos().contains("1002") || 
			SenhaDAO.getDireitosAtribuidos().contains("1003") || SenhaDAO.getDireitosAtribuidos().contains("1004") || SenhaDAO.getDireitosAtribuidos().contains("1005")	)
			{
				new ProdutosFrame();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 1000 - Senhas. \n Favor informar ao administrador do sistema!");
			}
		}
	}		
	
	//CLASSE INDIVIDUAL PARA AÇÂO DO MENU USUARIO
	class MenuOpcaoUsuario implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(SenhaDAO.getDireitosAtribuidos().contains("1000") || SenhaDAO.getDireitosAtribuidos().contains("1001") || SenhaDAO.getDireitosAtribuidos().contains("1002") || 
			   SenhaDAO.getDireitosAtribuidos().contains("1003") || SenhaDAO.getDireitosAtribuidos().contains("1004") || SenhaDAO.getDireitosAtribuidos().contains("1005")	)
			{
				new SenhaFrame();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 1000 - Senhas. \n Favor informar ao administrador do sistema!");
			}
		}
	}
		
	class MenuSair implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			
			System.out.println("ENTROU EM SAIR");
			int n = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema ?" ,"Confirmação de saída " ,JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) 
			{
				System.exit(0);
			}
		}
	}
}

