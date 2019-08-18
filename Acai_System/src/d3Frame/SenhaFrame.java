package d3Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CellRender.UsuarioCellRender;
import Controller.UsuarioController;
import Entity.Usuario;
import PadroesDesign.PadroesDesign;
import PrincipalMain.d3Frame;
import d3DAO.SenhaDAO;
import d3TableModel.UsuarioTableModel;
import net.miginfocom.swing.MigLayout;

public class SenhaFrame extends JFrame 
{

	private JPanel contentPane , panelTable;
	SenhaDAO validar = new SenhaDAO();
	private JTextField textLogin;
	private JTable tabela;
	Usuario umUsuario = null;
	private List<Usuario> UsuarioList;
	private JScrollPane scrollPane;
	DireitosUsuarioFrame textLabelUsuario;
	
	public SenhaFrame() 
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 640, 474);
		setTitle("USUÁRIOS E PERMISSÕES");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JLabel lbUsuarios = new JLabel("Usu\u00E1rio Selecionado : ");
		lbUsuarios.setBounds(18,9, 194, 22);
		desktopPane.add(lbUsuarios);
		lbUsuarios.setForeground(PadroesDesign.CORLABEL);
		lbUsuarios.setFont(PadroesDesign.FONTELABEL);
				
		textLogin = new JTextField();
		textLogin.setBounds(142, 9, 463, 28);
		textLogin.setForeground(PadroesDesign.CORTEXT);
		textLogin.setFont(PadroesDesign.FONTETEXT);
		textLogin.setEditable(false);
		desktopPane.add(textLogin);
		textLogin.setColumns(10);
		
		ClassLoader loader = getClass().getClassLoader();
				
		JButton btnResetSenha = new JButton("Reset Senha");
		btnResetSenha = new JButton(new ImageIcon(loader.getResource("img/Reset.png")));
		btnResetSenha.setText("Reset");
		btnResetSenha.addActionListener(new BotaoResetSenhaListener());
		btnResetSenha.setForeground(PadroesDesign.CORBOTOES);
		btnResetSenha.setFont(PadroesDesign.FONTEBOTOESPEQUENOS);
		btnResetSenha.setBounds(15, 380, 95, 40);
		btnResetSenha.setToolTipText("1004 - Reset de Senha");
		desktopPane.add(btnResetSenha);
				
		JButton btnRemover = new JButton("Remover");
		btnRemover = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
		btnRemover.setText("Remover");
		btnRemover.addActionListener(new BotaoExcluirListener());
		btnRemover.setToolTipText("1003 - Excluir usuario");
		btnRemover.setForeground(PadroesDesign.CORBOTOES);
		btnRemover.setFont(PadroesDesign.FONTEBOTOESPEQUENOS);
		btnRemover.setBounds(115, 380, 95, 40);
		desktopPane.add(btnRemover);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir = new JButton(new ImageIcon(loader.getResource("img/add.png")));
		btnIncluir.setText("Incluir");
		btnIncluir.addActionListener(new BotaoIncluirListener());
		btnIncluir.setForeground(PadroesDesign.CORBOTOES);
		btnIncluir.setFont(PadroesDesign.FONTEBOTOESPEQUENOS);
		btnIncluir.setBounds(215, 380, 95, 40);
		btnIncluir.setToolTipText("1001 - Incluir Usuário");
		desktopPane.add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar = new JButton(new ImageIcon(loader.getResource("img/add.png")));
		btnAlterar.setText("Alterar");
		btnAlterar.addActionListener(new BotaoAlterarListener());
		btnAlterar.setForeground(PadroesDesign.CORBOTOES);
		btnAlterar.setFont(PadroesDesign.FONTEBOTOESPEQUENOS);
		btnAlterar.setBounds(315, 380, 95, 40);
		btnAlterar.setToolTipText("Alterar Usuário");
		desktopPane.add(btnAlterar);
		
		JButton btDireitos = new JButton("Direitos");
		btDireitos = new JButton(new ImageIcon(loader.getResource("img/DireitoBotao.png")));
		btDireitos.setText("Direitos");
		btDireitos.addActionListener(new BotaoDireitosListener());
		btDireitos.setToolTipText("1005 - Atribuir Direito ao Usuário");
		btDireitos.setForeground(PadroesDesign.CORBOTOES);
		btDireitos.setFont(PadroesDesign.FONTEBOTOESPEQUENOS);
		btDireitos.setBounds(415, 380, 95, 40);
		desktopPane.add(btDireitos);
		
		JButton btnSair = new JButton("Cancelar");
		btnSair = new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		btnSair.setText("Cancelar");
		btnSair.addActionListener(new BotaoSairListener());
		btnSair.setToolTipText("Voltar para a tela Inicial");
		btnSair.setForeground(PadroesDesign.CORBOTOES);
		btnSair.setFont(PadroesDesign.FONTEBOTOESPEQUENOS);
		btnSair.setBounds(515, 380, 95, 40);
		desktopPane.add(btnSair);
					
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder(" Usuários "));
		panelTable.setBounds(15, 49, 588, 310);
		//CRIAÇÂO DO COMPONENTE TABELA
		tabela = new JTable();
		tabela.addMouseListener(new ClicandoMouse());
		tabela.setPreferredScrollableViewportSize(new Dimension(970, 465));  
		tabela.setToolTipText("Clique para selecionar um Usuário");
		tabela.setAutoResizeMode (JTable.AUTO_RESIZE_OFF); 
			
		//ADICIONA UM SCROLL A TABELA CRIADA ACIMA
		scrollPane = new JScrollPane(tabela);
		scrollPane.setHorizontalScrollBar(new JScrollBar(0));
		scrollPane.setSize(588, 310);
		panelTable.add(scrollPane);
		desktopPane.add(panelTable);
							
		refreshTabela();
		
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); 
		this.setLocationRelativeTo(null);
		this.repaint();
		this.setVisible(true);
	}
	 
	//INCLUI UM USUARIO CHAMANDO UMA CLASSE CADASTRA USUARIO
	class BotaoAlterarListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			
			if(SenhaDAO.getIDSelecionado() == 0 )
			{
				JOptionPane.showMessageDialog(null, "Favor Selecionar um Usuário antes de Clicar em Alterar! ");
			}
			
			if(SenhaDAO.getDireitosAtribuidos().contains("1000"))
			{
				CadastraUsuarioFrame.setRotinaExecutada(2);
				new CadastraUsuarioFrame();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 1000 - Permissão total em Usuário. \n Favor informar ao administrador do sistema!");
			}
		}
	}
	
		//INCLUI UM USUARIO CHAMANDO UMA CLASSE CADASTRA USUARIO
		class BotaoIncluirListener implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(SenhaDAO.getDireitosAtribuidos().contains("1000") || SenhaDAO.getDireitosAtribuidos().contains("1001")	)
				{
					CadastraUsuarioFrame.setRotinaExecutada(1);
					new CadastraUsuarioFrame();
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 1001 - Incluir Usuário. \n Favor informar ao administrador do sistema!");
				}
			}
		}
		
		//ENCERRA O SISTEMA E VOLTA A CLASSE ASSINATURAS
		class BotaoSairListener implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				new d3Frame();
				dispose();
			}
		}
		
		//INICIA UMA NOVA TELA LISTANDO OS DIREITOS DO USUARIO SELECIONADO
		class BotaoDireitosListener implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(SenhaDAO.getDireitosAtribuidos().contains("1000") || SenhaDAO.getDireitosAtribuidos().contains("1005")	)
				{	//VERIFICA SE O LOGIN FOI SELECIONADO, SE SIM O CAMPO TEXTLOGIN ESTARÁ PREENCHIDO
					if(textLogin.getText().trim().length() > 0)
					{
						new DireitosUsuarioFrame();
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Selecione um usuário para verificar seus direitos !");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 1005 - Atribuir Direito ao Usuário. \n Favor informar ao administrador do sistema!");
				}
			}
		}
		
		//EXCLUI UM USUARIO ATRAVÉS DA CLASSE SENHA - REMOVE USUARIO - PARA ESTE TEMOS QUE SELECIONAR ATRAVÈS DO CLIQUE
		class BotaoExcluirListener implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(SenhaDAO.getDireitosAtribuidos().contains("1000") || SenhaDAO.getDireitosAtribuidos().contains("1003")	)
				{	//PEGAMOS O CAMINHO ATRAVÈS DO GETSELECTES ROW = RETORNA INDICE
					int rowIndex = tabela.getSelectedRow();
					//PRECISAMOS SELECIONAR UM USUARIO, SE ESTE NÂO FOR POSICIONADO, RECEBERÁ O VALOR FORA DA TABELA (-1)
					if (rowIndex == -1)
					{
						JOptionPane.showMessageDialog(null, "Selecione o usuário a ser removido");
						return;
					}
					//PRECISAMOS PEGAR O ID DA LINHA SELECIONADA, PARA ISSO USAMOS O GET DA CLASSE USUARIOTABLEMODEL, ESTE RETORNA O ROWINDEX - INDICE DA LINHA
					Usuario usuario = new UsuarioTableModel(UsuarioList).get(rowIndex);
					textLogin.setText(usuario.getUsuario());
					//CONFIRMA SE REALMENTE FOI SELECIONADO O USUÀRIO CORRETO DO INDICE
					int confirmar = JOptionPane.showConfirmDialog(null, "Confirmar Exclusão do usuario : " + usuario.getUsuario().trim(), "Excluir Usuario", JOptionPane.YES_NO_OPTION);
					//AVALIA O VALOR DA RESPOSTA ( 0 - NÃO / 1- SIM ), SE O VALOR FOI NÂO RETORNA ( RETURN = BREAK )
					if(confirmar != 0 )
					{
						return;
					}
					//VERIFICA SE FOI SELECIONADO ALGUM VALOR E IGUALA AO RESULT
					int result = SenhaDAO.getIDSelecionado();
					if(result != 0 )
					{
						JOptionPane.showMessageDialog(null, " Usuario removido " + usuario.getUsuario().trim() + " com sucesso !");
						validar.removeUsuario();
						refreshTabela();
						textLogin.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Tente Novamente!");
						textLogin.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para as Rotina de 1003 - Excluir usuario. \n Favor informar ao administrador do sistema!");
				}
			}
		}
		
		//RESET DE SENHA UM USUARIO ATRAVÉS DA CLASSE SENHA - RESET USUARIO - PARA ESTE TEMOS QUE SELECIONAR ATRAVÈS DO CLIQUE
		class BotaoResetSenhaListener implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(SenhaDAO.getDireitosAtribuidos().contains("1000") || SenhaDAO.getDireitosAtribuidos().contains("1004")	)
				{	//PEGAMOS O CAMINHO ATRAVÈS DO GETSELECTES ROW = RETORNA INDICE
					int rowIndex = tabela.getSelectedRow();
					//SELECIONA UM ID ATRAVÈS DO CLIQUE, SE ESTE NÂO ESTIVER SELECIONADO, OU FORA DA TABELA SERÁ -1
					if (rowIndex == -1){
						JOptionPane.showMessageDialog(null, "Selecione o usuário a ser alterado !");
						return;
					}
					//PRECISAMOS PEGAR O ID DA LINHA SELECIONADA, PARA ISSO USAMOS O GET DA CLASSE USUARIOTABLEMODEL, ESTE RETORNA O ROWINDEX - INDICE DA LINHA
					Usuario usuario = new UsuarioTableModel(UsuarioList).get(rowIndex);
					textLogin.setText(usuario.getUsuario());
					//CONFIRMA EXCLUSÂO ATRAVÉS DE UM JOPTIONPANE
					int confirmar = JOptionPane.showConfirmDialog(null, "Confirmar Alteração?" , "Alteração de senha", JOptionPane.YES_NO_OPTION);
					if(confirmar != 0 )
					{
						return;
					}
					//VERIFICA SE EXISTE ALGUM ID SELECIONADO E ATRIBUI A VARIAVEL RESULT, SE ESTA FOR SELECIONADA SERÀ DIFERENTE DE 0
					int result = SenhaDAO.getIDSelecionado();
					if(result != 0 )
					{	//SE EXISTE ALGO SELECIONADO IRÀ ALTERAR ATRAVÈS DO ID SELECIONADO
						validar.ResetSenha();
						JOptionPane.showMessageDialog(null, "Senha alterada para : INCPP !");
						textLogin.setText("");
						refreshTabela();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Tente Novamente!");
					}
					refreshTabela();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina 1004 - Reset de Senha do Usuario . \n Favor informar ao administrador do sistema!");
				}
			}
		}
		
		//CLASSE QUE SELECIONA UM LOGIN ATRAVÉS DO CLIQUE, SETIDSELECIONADO
		class ClicandoMouse implements MouseListener
		{			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) 
			{
				int rowIndex = tabela.getSelectedRow();
				if (rowIndex != -1)
				{
				//AO CLICARMNOS SELECIONAMOS E INSERIRMOS O VALOR DO INDICE AO CAMPO CLICADO - ATRAVÉS DO SETIDSELECIONADO - CRIADO PARA DIFERENCIAR DO ID LOGADO
					Usuario usuario = new UsuarioTableModel(UsuarioList).get(rowIndex);
					SenhaDAO.setIDSelecionado(usuario.getId());
					SenhaDAO.setLoginSelecionado(usuario.getUsuario());
					textLogin.setText(usuario.getUsuario());
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				// TODO Auto-generated method stub
			}
		} 
		//USADO PARA ATUALIZARMOS A TABELA AO REALIZARMOS QUALQUER ALTERAÇÂO
		public void refreshTabela()
		{
		//CARREGA A FUNÇÂO FINDUSUARIO DA CLASSE CONTROLLER	
		UsuarioList = new UsuarioController().findUsuario();
			if (UsuarioList != null)
			{
				tabela.setModel(new UsuarioTableModel(UsuarioList));
				tabela.setDefaultRenderer(Object.class, new UsuarioCellRender());
			}
		}
}
