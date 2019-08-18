package d3Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Informações.Informacoes;
import PadroesDesign.PadroesDesign;
import PrincipalMain.d3Frame;
import d3DAO.SenhaDAO;

public class NumeroPedidoFrame extends JFrame {
	
	private JPanel contentPane;
	JMenuItem miVoltar; 
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
	public void setJLRotulo(JLabel jLRotulo) 	{	NumeroPedidoFrame.lbInformacoes = jLRotulo;}
	public static JLabel lblAtual, lblAnterior, lblNumeroAtual, lblNomeAnterior , lblNome, lblNumeroAnterior, lblPedidoAtual, lblPedidoAnterior;
	
	private static String horarioDeAcesso;
	public static String getHorarioDeAcesso() {return horarioDeAcesso;}
	public static void setHorarioDeAcesso(String horarioDeAcesso) {NumeroPedidoFrame.horarioDeAcesso = horarioDeAcesso;}
	
	private static String logoUsado;
	public static String getLogoUsado() {return logoUsado;}
	public static void setLogoUsado(String logoUsado) {NumeroPedidoFrame.logoUsado = logoUsado;}
	
	ClassLoader loader = getClass().getClassLoader();
	
	public NumeroPedidoFrame ()
	{
		this.inicializarComponentes();
	}
		
	private void inicializarComponentes() 
	{
		String cabecalho = "Bem vindo ao D3 Açaí : " + SenhaDAO.getLogin() + "                                   "+ NumeroPedidoFrame.getHorarioDeAcesso()            +"            V.01.00";

		setTitle(cabecalho);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 718);
		getContentPane().setLayout(new BorderLayout(0, 0));

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		this.contentPane.add(desktopPane, BorderLayout.CENTER);

		menuBar = new JMenuBar();
		menuBar.setFont(PadroesDesign.FONTELABEL);
		setJMenuBar(menuBar);

		this.miVoltar = new JMenuItem("Voltar");
		this.miVoltar.addActionListener(new MenuSair());
		this.miVoltar.setHorizontalAlignment(SwingConstants.LEFT);
		this.miVoltar.setFont(PadroesDesign.FONTELABEL);
		this.menuBar.add(miVoltar);
		
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
		
		JSeparator separador = new JSeparator();  
		separador.setForeground(PadroesDesign.CORLABEL);  
		separador.setBackground(Color.LIGHT_GRAY);  
		separador.setOrientation(SwingConstants.VERTICAL);
		separador.setSize(8, 718); 
		separador.setLocation(424,0);
		desktopPane.add(separador);
		
		JSeparator separadorDir = new JSeparator();  
		separadorDir.setForeground(PadroesDesign.CORLABEL);  
		separadorDir.setBackground(Color.LIGHT_GRAY);  
		separadorDir.setOrientation(SwingConstants.VERTICAL);
		separadorDir.setSize(8, 718); 
		separadorDir.setLocation(100,0);
		desktopPane.add(separadorDir);
		
		JSeparator separadorEsq = new JSeparator();  
		separadorEsq.setForeground(PadroesDesign.CORLABEL);  
		separadorEsq.setBackground(Color.LIGHT_GRAY);  
		separadorEsq.setOrientation(SwingConstants.VERTICAL);
		separadorEsq.setSize(8, 718); 
		separadorEsq.setLocation(924,0);
		desktopPane.add(separadorEsq);
		
		JLabel lblimgLateralDireita = new JLabel(new ImageIcon(loader.getResource("img/barraLateral.png")));
		lblimgLateralDireita.setForeground(PadroesDesign.CORLABEL);
		lblimgLateralDireita.setFont(PadroesDesign.FONTELABEL);
		lblimgLateralDireita.setBounds(0, 0, 100, 710);
		desktopPane.add(lblimgLateralDireita);
		
		
		JLabel lblimgLateralEsquerda = new JLabel(new ImageIcon(loader.getResource("img/barraLateral.png")));
		lblimgLateralEsquerda.setForeground(PadroesDesign.CORLABEL);
		lblimgLateralEsquerda.setFont(PadroesDesign.FONTELABEL);
		lblimgLateralEsquerda.setBounds(924, 0, 100, 710);
		desktopPane.add(lblimgLateralEsquerda);
		
		
		//JTabbedPane Painel_Abas = new JTabbedPane(JTabbedPane.NORTH);
		//desktopPane.add(Painel_Abas, BorderLayout.CENTER);
		
		//lblimgLogin = new JLabel(new ImageIcon(loader.getResource("img/fundo.png")));
		lblNumeroAnterior = new JLabel("Anterior");
		lblNumeroAnterior.setVisible(true);
		lblNumeroAnterior.setHorizontalAlignment(JLabel.CENTER);
		lblNumeroAnterior.setVerticalAlignment(JLabel.CENTER);
		lblNumeroAnterior.setFont(PadroesDesign.FONTEPAINEL);
		lblNumeroAnterior.setBackground(Color.WHITE);
		lblNumeroAnterior.setForeground(new Color(74, 45, 86));
		lblNumeroAnterior.setOpaque(false);
		//lblNumeroAnterior.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		lblNumeroAnterior.setBounds(100, 0, 324, 150);
		desktopPane.add(lblNumeroAnterior);
		
		lblAnterior = new JLabel("200");
		lblAnterior.setVisible(true);
		lblAnterior.setHorizontalAlignment(JLabel.CENTER);
		lblAnterior.setVerticalAlignment(JLabel.CENTER);
		lblAnterior.setFont(PadroesDesign.FONTENUMEROPAINEL);
		lblAnterior.setForeground(Color.WHITE);
		lblAnterior.setBackground(Color.getHSBColor(274,63,38));	
		lblAnterior.setBounds(100, 150, 324, 150);
		desktopPane.add(lblAnterior);
		
		lblPedidoAnterior = new JLabel("Descrição");
		lblPedidoAnterior.setVisible(true);
		lblPedidoAnterior.setHorizontalAlignment(JLabel.CENTER);
		lblPedidoAnterior.setVerticalAlignment(JLabel.CENTER);
		lblPedidoAnterior.setFont(PadroesDesign.FONTEPAINELDESCRICAOANTERIOR);
		lblPedidoAnterior.setForeground(PadroesDesign.CORTITULOS);
		lblPedidoAnterior.setBackground(Color.getHSBColor(274,63,38));	
		lblPedidoAnterior.setBounds(100, 300, 324, 70);
		desktopPane.add(lblPedidoAnterior);
		
		lblNomeAnterior = new JLabel("Maria");
		lblNomeAnterior.setVisible(true);
		lblNomeAnterior.setHorizontalAlignment(JLabel.CENTER);
		lblNomeAnterior.setVerticalAlignment(JLabel.CENTER);
		lblNomeAnterior.setFont(PadroesDesign.FONTEPAINEL);
		lblNomeAnterior.setForeground(new Color(74, 45, 86));
		lblNomeAnterior.setBackground(Color.getHSBColor(274,63,38));	
		lblNomeAnterior.setBounds(100, 370, 324, 80);
		desktopPane.add(lblNomeAnterior);
		
		JLabel lblimgLogo = new JLabel(new ImageIcon(loader.getResource("img/login.png")));
		lblimgLogo.setForeground(PadroesDesign.CORLABEL);
		lblimgLogo.setFont(PadroesDesign.FONTELABEL);
		lblimgLogo.setBounds(100, 450, 324, 200);
		desktopPane.add(lblimgLogo);
			
		lblNumeroAtual = new JLabel("Número Atual");
		lblNumeroAtual.setHorizontalAlignment(JLabel.CENTER);
		lblNumeroAtual.setVerticalAlignment(JLabel.CENTER);
		lblNumeroAtual.setVisible(true);
		lblNumeroAtual.setFont(PadroesDesign.FONTEPAINEL);
		lblNumeroAtual.setForeground(new Color(74, 45, 86));
		lblNumeroAtual.setBounds(424, 0, 500, 150);
		desktopPane.add(lblNumeroAtual);
				
		lblAtual = new JLabel("300");
		lblAtual.setVisible(true);
		lblAtual.setHorizontalAlignment(JLabel.CENTER);
		lblAtual.setVerticalAlignment(JLabel.CENTER);
		lblAtual.setFont(PadroesDesign.FONTENUMEROPAINELATUAL);
		lblAtual.setForeground(Color.WHITE);
		lblAtual.setBounds(424, 150, 500, 300);
		desktopPane.add(lblAtual);
		
		lblPedidoAtual = new JLabel("Descrição");
		lblPedidoAtual.setVisible(true);
		lblPedidoAtual.setHorizontalAlignment(JLabel.CENTER);
		lblPedidoAtual.setVerticalAlignment(JLabel.CENTER);
		lblPedidoAtual.setFont(PadroesDesign.FONTEPAINELDESCRICAOATUAL);
		lblPedidoAtual.setForeground(PadroesDesign.CORTITULOS);
		lblPedidoAtual.setBounds(424, 450, 500, 100);
		desktopPane.add(lblPedidoAtual);
		
		lblNome = new JLabel("Debora Dênis");
		lblNome.setHorizontalAlignment(JLabel.CENTER);
		lblNome.setVerticalAlignment(JLabel.CENTER);
		lblNome.setVisible(true);
		lblNome.setFont(PadroesDesign.FONTEPAINEL);
		lblNome.setForeground(new Color(74, 45, 86));
		lblNome.setBounds(424, 550, 500, 100);
		desktopPane.add(lblNome);

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
		
		JLabel lblimgFundoDir = new JLabel(new ImageIcon(loader.getResource("img/fundoPainel.png")));
		lblimgFundoDir.setForeground(PadroesDesign.CORLABEL);
		lblimgFundoDir.setFont(PadroesDesign.FONTELABEL);
		lblimgFundoDir.setBounds(100, 150, 324, 150);
		desktopPane.add(lblimgFundoDir);
		
		JLabel lblimgFundo = new JLabel(new ImageIcon(loader.getResource("img/fundoPainel.png")));
		lblimgFundo.setForeground(PadroesDesign.CORLABEL);
		lblimgFundo.setFont(PadroesDesign.FONTELABEL);
		lblimgFundo.setBounds(424, 150, 500, 300);
		desktopPane.add(lblimgFundo);
		
		this.getContentPane().setBackground(new Color(74, 45, 86));
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(true);
	}

	class MenuSair implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			dispose();
			new d3Frame();
		}
	}
}

