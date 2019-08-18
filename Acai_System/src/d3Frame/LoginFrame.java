package d3Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Informações.Informacoes;
import PadroesDesign.PadroesDesign;
import PrincipalMain.d3Frame;
import PrincipalMain.d3Main;
import d3DAO.SenhaDAO;
import d3Validacoes.LoginDocument;
import d3Validacoes.limiteDeCampoTexto;
import net.java.balloontip.BalloonTip;

public class LoginFrame extends JFrame{

	private JPanel contentPane;
	private JTextField textLogin;
	private JPasswordField passwordField;
	JButton btnEntrar, btnSair, Personalizar;
	String nome, senha;
	SenhaDAO validar =  new SenhaDAO(); 
	JPopupMenu popup;
	BalloonTip balaoAviso;
	JCheckBox chckbxPadrao; 
	JCheckBox chckbxNimbus;
	JCheckBox chckbxMetal; 
	JCheckBox chckbxWindows;
			
	//CONSTRUTOR LOGIN
	public LoginFrame()
	{
		this.inicializarComponentes();
		this.inicializarEventos();
	}
	
	//CRIAÇÃO DE COMPONENTES VIASUAIS
	private void inicializarComponentes() {
		
		setType(Type.NORMAL);
		this.setTitle("Sistema D3 Açaí");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 370, 430);
		
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);

		JDesktopPane desktopPane = new JDesktopPane();
		this.contentPane.add(desktopPane, BorderLayout.CENTER);
		
		ClassLoader loader = getClass().getClassLoader();
				
		JLabel lblimgLogo = new JLabel(new ImageIcon(loader.getResource("img/login.png")));
		lblimgLogo.setForeground(PadroesDesign.CORLABEL);
		lblimgLogo.setFont(PadroesDesign.FONTELABEL);
		lblimgLogo.setBounds(80, 0, 200, 210);
		desktopPane.add(lblimgLogo);
					
		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setForeground(PadroesDesign.CORLABEL);
		lblLogin.setFont(PadroesDesign.FONTELABEL);
		lblLogin.setBounds(22, 207, 65, 30);
		desktopPane.add(lblLogin);
		
		this.textLogin = new JTextField();
		this.textLogin.setDocument(new LoginDocument());
		this.textLogin.addFocusListener(new CampoLogin());
		this.textLogin.setForeground(PadroesDesign.CORTEXT);
		this.textLogin.setFont(PadroesDesign.FONTETEXT);
		this.textLogin.setBounds(68, 210, 255, 30);
		desktopPane.add(textLogin);
		this.textLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha :");
		lblSenha.setForeground(PadroesDesign.CORLABEL);
		lblSenha.setFont(PadroesDesign.FONTELABEL);
		lblSenha.setBounds(22, 247, 65, 30);
		desktopPane.add(lblSenha);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setDocument(new limiteDeCampoTexto(10));
		this.passwordField.addFocusListener(new CampoLogin());
		this.passwordField.setEditable(false);
		this.passwordField.setForeground(PadroesDesign.CORTEXT);
		this.passwordField.setFont(PadroesDesign.FONTETEXT);
		this.passwordField.setBounds(68, 250, 255, 30);
		desktopPane.add(passwordField);
	
		JLabel lblStatus = new JLabel("Visual");
		lblStatus.setForeground(PadroesDesign.CORLABEL);
		lblStatus.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 9));
		lblStatus.setBounds(17, 295, 80, 16);
		desktopPane.add(lblStatus);
		
		JSeparator separador = new JSeparator();  
		separador.setForeground(PadroesDesign.CORLABEL);  
		separador.setBackground(Color.LIGHT_GRAY);  
		separador.setSize(270, 8); 
		separador.setLocation(67,303);
		desktopPane.add(separador);
		
		chckbxPadrao = new JCheckBox("Padrão");
		chckbxPadrao.setForeground(PadroesDesign.CORLABEL);
		chckbxPadrao.addActionListener(new BotaoChekBoxPadrao());
		chckbxPadrao.setFont(PadroesDesign.FONTETEXT);
		chckbxPadrao.setBounds(17, 316, 80, 18);
		desktopPane.add(chckbxPadrao);
	
		/*INICIANDO O LOGO PARA ESTE VISUAL*/
		d3Frame.setLogoUsado("img/fundo.png");

		chckbxNimbus = new JCheckBox("Nimbus");
		chckbxNimbus.setForeground(PadroesDesign.CORLABEL);
		chckbxNimbus.addActionListener(new BotaoChekBoxNimbus());
		chckbxNimbus.setFont(PadroesDesign.FONTETEXT);
		chckbxNimbus.setBounds(97, 316, 80, 18);
		desktopPane.add(chckbxNimbus);
				
		chckbxMetal = new JCheckBox("Metal");
		chckbxMetal.setForeground(PadroesDesign.CORLABEL);
		chckbxMetal.addActionListener(new BotaoChekBoxMetal());
		chckbxMetal.setFont(PadroesDesign.FONTETEXT);
		chckbxMetal.setBounds(177, 316, 80, 18);
		desktopPane.add(chckbxMetal);
		
		chckbxWindows = new JCheckBox("Windows");
		chckbxWindows.setForeground(PadroesDesign.CORLABEL);
		chckbxWindows.addActionListener(new BotaoChekBoxWindows());
		chckbxWindows.setFont(PadroesDesign.FONTETEXT);
		chckbxWindows.setBounds(257, 316, 100, 18);
		desktopPane.add(chckbxWindows);
		
		JSeparator separadorBaixo = new JSeparator();  
		separadorBaixo.setForeground(PadroesDesign.CORLABEL);  
		separadorBaixo.setBackground(Color.LIGHT_GRAY);  
		separadorBaixo.setSize(320, 8); 
		separadorBaixo.setLocation(17,345);
		desktopPane.add(separadorBaixo);
				
		btnEntrar = new JButton(new ImageIcon(loader.getResource("img/ok.png")));
		btnEntrar.setText("  Entrar");
		this.btnEntrar.setForeground(PadroesDesign.CORBOTOES);
		this.btnEntrar.setFont(PadroesDesign.FONTEBOTOES);
		this.btnEntrar.setBounds(22, 353, 150, 35);
		desktopPane.add(btnEntrar);

		this.btnSair = new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		btnSair.setText("  Sair");
		this.btnSair.setForeground(PadroesDesign.CORBOTOES);
		this.btnSair.setFont(PadroesDesign.FONTEBOTOES);
		this.btnSair.setBounds(192, 353, 150, 35);
		desktopPane.add(btnSair);
		
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
		this.setResizable(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		/*COLOCANDO O ENTER COMO O BOTÂO ENTRAR*/
		getRootPane().setDefaultButton(btnEntrar);
		chckbxPadrao.doClick();
		//chckbxWindows.doClick();
		//chckbxNimbus.doClick();
		//chckbxMetal.doClick();
	}
	
	class BotaoChekBoxPadrao implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(chckbxPadrao.isSelected())
			{
				d3Frame.setLogoUsado("img/LogoSIAC.png");
				PadroesDesign.setCORTITULOS(new Color(38,38,38));
				PadroesDesign.setCORLABEL(new Color(38,38,38));
				PadroesDesign.setCOROBSERVACOES(new Color(38,38,38));
				PadroesDesign.setCORTEXT(new Color(58,58,60));
				PadroesDesign.setCORBOTOES(new Color(19,22,65));
				PadroesDesign.setCORTEXTOBSERVACOES(Color.getHSBColor(352,21,96));
				PadroesDesign.setCORTEXTOBRIGATORIO(Color.getHSBColor(352,21,96));
				
				d3Main.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
				chckbxNimbus.setSelected(false);
				chckbxMetal.setSelected(false);
				chckbxWindows.setSelected(false);
			}
		}
	}
	
	class BotaoChekBoxMetal implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(chckbxMetal.isSelected())
			{
				d3Frame.setLogoUsado("img/LogoSIAC.png");
				PadroesDesign.setCORTITULOS(new Color(38,38,38));
				PadroesDesign.setCORLABEL(new Color(38,38,38));
				PadroesDesign.setCOROBSERVACOES(new Color(38,38,38));
				PadroesDesign.setCORTEXT(new Color(58,58,60));
				PadroesDesign.setCORBOTOES(new Color(19,22,65));
				PadroesDesign.setCORTEXTOBSERVACOES(Color.getHSBColor(352,21,96));
				PadroesDesign.setCORTEXTOBRIGATORIO(Color.getHSBColor(352,21,96));
				
				d3Main.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				chckbxNimbus.setSelected(false);
				chckbxPadrao.setSelected(false);
				chckbxWindows.setSelected(false);
			}
		}
	}

	class BotaoChekBoxNimbus implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			d3Frame.setLogoUsado("img/LogoSIACNimbus.png");
			PadroesDesign.setCORTITULOS(new Color(234,235,233));
			PadroesDesign.setCORLABEL(new Color(231,207,72));
			PadroesDesign.setCORTEXT(new Color(58,58,60));
			PadroesDesign.setCORBOTOES(new Color(19,22,65));
			PadroesDesign.setCOROBSERVACOES(new Color(38,38,38));
			PadroesDesign.setCORTEXTOBSERVACOES(Color.getHSBColor(352,21,96));
			PadroesDesign.setCORTEXTOBRIGATORIO(Color.getHSBColor(352,21,96));
			
			if(chckbxNimbus.isSelected())
			{
				d3Main.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				chckbxMetal.setSelected(false);
				chckbxPadrao.setSelected(false);
				chckbxWindows.setSelected(false);
			}
		}
	}
	
	class BotaoChekBoxWindows implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			d3Frame.setLogoUsado("img/LogoSIACClaro.png");
			PadroesDesign.setCORTITULOS(new Color(255,255,255));
			PadroesDesign.setCORLABEL(new Color(255,255,255));
			PadroesDesign.setCOROBSERVACOES(new Color(255,255,255));
			PadroesDesign.setCORTEXTOBSERVACOES(new Color(255,255,255));
			PadroesDesign.setCORTEXTOBRIGATORIO(new Color(255,255,255));
			
			if(chckbxWindows.isSelected())
			{
				d3Main.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				chckbxMetal.setSelected(false);
				chckbxPadrao.setSelected(false);
				chckbxNimbus.setSelected(false);
			}
		}
	}
	
	
	class ClicandoMouse implements MouseListener
	{			
		@Override
		public void mouseClicked(MouseEvent e) 
		{
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) 
		{
			balaoAviso.closeBalloon();
			repaint();
		}
		@Override
		public void mousePressed(MouseEvent e) {

			balaoAviso.closeBalloon();
			repaint();
		}
		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}
	} 
	
	//TRATA TODOS OS EVENTOS QUE VÂO ACONTECER AO CLICARMOS NOS BOTÕES
	private void inicializarEventos() 
	{	
		//TRATAMENTO DO BOTÃO ENTRAR - USA O MÉTODO VALIDAR SENHA - DA CLASSE SENHA - PARA AUTENTICAR NO BANCO
		this.btnEntrar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String loginDigitado = textLogin.getText().trim();
				if(loginDigitado.length() == 0)
				{
					balaoAviso = new BalloonTip(textLogin, "Favor Preencher o Campo Login!");
					balaoAviso.grabFocus();
					balaoAviso.addMouseListener(new ClicandoMouse());
					return;
				}
				
				//GETPASSWORD - INFORME A SENHA EM UMA ARRAY DE CARACTER, DEVIDO A ISSO TEMOS QUE CONVERTER
				String senhaDigitada = new String(passwordField.getPassword());
				if(senhaDigitada.trim().length() == 0)
				{
					balaoAviso = new BalloonTip(passwordField, "Favor Preencher o Campo Senha!");
					balaoAviso.grabFocus();
					balaoAviso.addMouseListener(new ClicandoMouse());
					return;
				}
			
				//TRANSFORMA EM MAIUSCULO O LOGIN E SENHA QUE FORAM DIGITADOS
				loginDigitado = loginDigitado.toUpperCase();
				senhaDigitada = senhaDigitada.toUpperCase();
				
				SenhaDAO validar = new SenhaDAO();
				//SE NÂO FOI RESETADO OU RECÉM CRIADA ATRIBUIMOS ATRVÈS DO SETSENHA O VALOR ATUAL DA SENHA
				validar.setLogin(loginDigitado);
				validar.setSenha(senhaDigitada);

				//INSTACIA METODO VALIDA SENHA DA CLASSE SENHA - RETORNA ATRVÈS DO LOGIN E SENHA ISVALIDO - VARIAVEL BOOLEAN
				try {
					validar.ValidaSenha();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}

				//VERIFICA SE A SENHA FOI RESETADA OU CRIADA COM UM NOVO USUARIO
				if(validar.isLoginValido() == true && validar.isSenhaValida() == true && senhaDigitada.equals("INCPP"))
				{
					//SOLICITA ALTERAÇÃO DA SENHA
					senhaDigitada = "";
					do
					{
						senhaDigitada = JOptionPane.showInputDialog("Digite uma nova senha a ser registrada");
						senhaDigitada = senhaDigitada.toUpperCase();

						if(senhaDigitada.trim().length() <= 3)
						{
							JOptionPane.showMessageDialog(null, "Senha Inválida, a senha deve conter de 4 a 10 caracteres!");
						}
					}
					while(senhaDigitada.trim().length() < 4 );

					validar.setSenha(senhaDigitada);

					//INSTANCIA METODO ATUALIZAR SENHA, PARA DEFINIRMO A NOVA SENHA AO BANCO
					validar.AtualizarSenha();
				}						

				//VERIFICA ESTADO ATUAL DAS VARIAVEIS ISSENHA E ISLOGINVALIDO, SE AMBAS RETORNAREM TRUE, PASSA E ACESSA A CLASSE ASSINATURAS
				if(validar.isLoginValido() == true) 
				{
					if(validar.isSenhaValida() == true)
					{
						JOptionPane.showMessageDialog(null, "Seja bem vindo : "	+ loginDigitado + " !");
						//CARREGA INFORMAÇÔES COMO HORARIO DE ACESSO AO SISTEMA AO ENTRAR
						Informacoes receberInformacoes = new Informacoes();
						receberInformacoes.InformacoesLogin();
					
						validar.CarregaDireitosUsuario();
						/*ALTERANDO O ESTILO NOVAMENTE*/
						try 
						{	
							if(!chckbxPadrao.isSelected() && !chckbxNimbus.isSelected() && !chckbxMetal.isSelected() && !chckbxWindows.isSelected())
							{
								d3Frame.setLogoUsado("img/LogoSIAC.png");
								PadroesDesign.setCORTITULOS(new Color(38,38,38));
								PadroesDesign.setCORLABEL(new Color(38,38,38));
								PadroesDesign.setCOROBSERVACOES(new Color(38,38,38));
								PadroesDesign.setCORTEXT(new Color(58,58,60));
								PadroesDesign.setCORBOTOES(new Color(19,22,65));
								PadroesDesign.setCORTEXTOBSERVACOES(Color.getHSBColor(352,21,96));
								PadroesDesign.setCORTEXTOBRIGATORIO(Color.getHSBColor(352,21,96));
								
								d3Main.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
								chckbxNimbus.setSelected(false);
								chckbxMetal.setSelected(false);
								chckbxWindows.setSelected(false);
							}
							
							UIManager.setLookAndFeel(d3Main.getLookAndFeel());
						} 
						catch (Exception l){l.printStackTrace();}
						new d3Frame();
						dispose();
					}	
					//TRATA INDIVIDUALMENTE O RETORNO CASO FALSE
					else 
					{	
						repaint();
						balaoAviso = new BalloonTip(passwordField, "Senha Inválida para o usuario " + loginDigitado + " !");
						balaoAviso.addMouseListener(new ClicandoMouse());
						return;
					}
				}
				else
				{
					repaint();
					balaoAviso = new BalloonTip(textLogin, "Usuário não Encontrado : : " + loginDigitado + " !");
					balaoAviso.addMouseListener(new ClicandoMouse());
					return;
				}
			}
		});
		
		//EDITA O CAMPO LOGIN E SENHA PARA BRANCO E FECHA O SISTEMA
		this.btnSair.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(null,	"Até logo " + textLogin.getText() + "!");
				textLogin.setText("");
				passwordField.setText("");
				dispose();
				
			}
		});
	
	}
	
	class CampoLogin implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent arg0) {
			
		}

		@Override
		public void focusLost(FocusEvent arg0) 
		{
			if(textLogin.getText().trim().length() > 0)
			{
				passwordField.setEditable(true);
			}	
		}
	}
}
