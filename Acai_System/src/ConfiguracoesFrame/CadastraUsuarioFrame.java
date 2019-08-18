package ConfiguracoesFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import PadroesDesign.PadroesDesign;
import d3DAO.SenhaDAO;
import d3Validacoes.LoginDocument;
import d3Validacoes.Validar;
import d3Validacoes.textEmail;

public class CadastraUsuarioFrame extends JFrame  {

	private JPanel contentPane;
	private JTextField textLogin, textEmail;
	SenhaDAO validar;
	JButton btnSalvar, btnCancela;
	
	private static int rotinaExecutada; 
	public int getRotinaExecutada() {return rotinaExecutada;}
	public static void setRotinaExecutada(int rotinaExecutada) {CadastraUsuarioFrame.rotinaExecutada = rotinaExecutada;	}
	
	public CadastraUsuarioFrame()  
	{
		this.inicializarComponentes();
		this.inicializarEventos();
	}	
	
	public void inicializarComponentes() 
	{
		this.setTitle("CADASTRO DE USUARIO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setBounds(100, 100, 405, 188);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		this.contentPane.add(desktopPane, BorderLayout.CENTER);
			
		ClassLoader loader = getClass().getClassLoader();
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setForeground(PadroesDesign.CORLABEL);
		lblUsurio.setFont(PadroesDesign.FONTELABEL);
		lblUsurio.setBounds(17, 17, 77, 23);
		desktopPane.add(lblUsurio);
		
		this.textLogin = new JTextField();
		this.textLogin.setDocument(new LoginDocument());
		this.textLogin.setBounds(73, 15, 150, 28);
		this.textLogin.setFont(PadroesDesign.FONTETEXT);
		this.textLogin.setForeground(PadroesDesign.CORTEXT);
		desktopPane.add(textLogin);
		this.textLogin.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(PadroesDesign.CORLABEL);
		lblEmail.setFont(PadroesDesign.FONTELABEL);
		lblEmail.setBounds(17, 52, 77, 23);
		desktopPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setDocument(new textEmail(50));
		textEmail.addFocusListener(new CampoEmail());
		textEmail.setToolTipText("Campo para o e-mail do Funcionário receber seus Agendamentos.");
		textEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textEmail.setForeground(Color.DARK_GRAY);
		textEmail.setFont(PadroesDesign.FONTETEXT);
		textEmail.setColumns(1);
		textEmail.setBounds(73, 50, 290, 28);
		desktopPane.add(textEmail);
		
		this.btnSalvar =  new JButton(new ImageIcon(loader.getResource("img/save.png")));
		this.btnSalvar.setText("Salvar");
		this.btnSalvar.setForeground(PadroesDesign.CORBOTOES);
		this.btnSalvar.setFont(PadroesDesign.FONTEBOTOES);
		this.btnSalvar.setBounds(59, 90, 135, 40);
		desktopPane.add(btnSalvar);
		
		this.btnCancela =new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		this.btnCancela.setText("Cancelar");
		this.btnCancela.setForeground(PadroesDesign.CORBOTOES);
		this.btnCancela.setFont(PadroesDesign.FONTEBOTOES);
		this.btnCancela.setBounds(204, 90, 135, 40);
		desktopPane.add(btnCancela);
		
		if(rotinaExecutada == 2)
		{
			textLogin.setText(SenhaDAO.getLoginSelecionado());
			textEmail.setText(SenhaDAO.getEmail());
		}
		
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); 
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
	
	public void inicializarEventos() 
	{
		
		this.btnSalvar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				//RECEBE VALOR DIGITADO NO TEXT LOGIN e LIMPA OS BRANCOS - VALIDA SE ALGO FOI DIGITADO
				String loginDigitado = textLogin.getText();
				if(loginDigitado.trim().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Favor preencher o campo para a criação de um novo Usuário");
					textLogin.grabFocus();
					return;
				}
				
				loginDigitado = loginDigitado.toUpperCase().trim();
				
				//SET LOGIN EM SENHA PARA CADASTRO NO BANCO
				SenhaDAO validar = new SenhaDAO();
				SenhaDAO.setLoginSelecionado(loginDigitado);
				SenhaDAO.setEmail(textEmail.getText().trim());
						
				if(rotinaExecutada == 1)
				{
					//VERIFICA SE O LOGIN EXISTE EM NOSSO BANCO
					validar.usuarioExiste();
					
					if(validar.isLoginValido() == false)
					{
						//SE FOR FALSO, ELE NÂO EXISTE - ATRIBUIMOS A SENHA PADRÂO SEGMENTO E CADASTRAMOS O USUARIO ATRAVES DO ISERIR LOGIN
						validar.setSenha("BOTTALLO");
						int n = JOptionPane.showConfirmDialog(null, "Confirma a inclusão do usuário " + loginDigitado + " ?" ,"Confirmação de criação usuário " ,JOptionPane.YES_NO_OPTION);
							if (n == JOptionPane.YES_OPTION) {
								validar.IserirUsuario();
								JOptionPane.showMessageDialog(null, " \n \r Login "+ loginDigitado + " registrado com sucesso ! \n \r Senha definida : BOTTALLO \n \r Favor solicitar ao usuário que altere esta senha no proximo Login  ");
						}
							
						new SenhaFrame();
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Não foi possivel adicionar este usuário, pois já existe !");
						textLogin.setText(null);
				        textLogin.grabFocus();  
					}
				}
				else
				{
					int n = JOptionPane.showConfirmDialog(null, "Confirma a Alteração do usuário " + loginDigitado + " ?" ,"Confirmação de Alteração de usuário " ,JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) 
					{
						validar.AlterarUsuario();
						JOptionPane.showMessageDialog(null, " \n \r Login "+ loginDigitado + " alterado com sucesso !");
					}
					
					new SenhaFrame();
					dispose();
					
				}
			}	
		});	
		
		this.btnCancela.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				SenhaDAO.setLoginSelecionado("");
				new SenhaFrame();
				dispose();
			}
		});
	}
}