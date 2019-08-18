package d3Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CellRender.ManutencaoClientes_INCPPCellRender;
import Controller.ManutencaoCliente_INCPPController;
import Entity.Cliente_INCPP;
import PadroesDesign.PadroesDesign;
import PrincipalMain.d3Frame;
import d3DAO.ManutencaoCliente_INCPPDAO;
import d3DAO.SenhaDAO;
import d3TableModel.ManutencaoClientesTableModel;
import d3Validacoes.SomenteLetras;
import d3Validacoes.SomenteNumeros;
import d3Validacoes.limiteDeCampoTexto;
import net.miginfocom.swing.MigLayout;


public class PesquisaCliente extends JFrame {

	private JPanel contentPane , panelTable;
	private JScrollPane scrollPane;
	private JTable tabelaClientes;
	private List<Cliente_INCPP> ClientesList;
	private JTextField textNomeOuParte;
	private JTextField textCodigo;
	private JTextField textArquivo;
	private JTextField textconta;
	private JTextField textLocal;
	private JButton btnAlterar, btnIncluir, btnConsulta, btnCancelar, btImportar;
	JTextField textfield;
	JDesktopPane desktopPane;
	
	SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date dataAtual = new java.util.Date();

	public PesquisaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MANUTENÇÃO DE CLIENTE");
		setBounds(100, 100, 1024, 728);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ManutencaoCliente_INCPPDAO.setBuscarCliente("SELECT * FROM Clientes_INCPP");
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setForeground(PadroesDesign.CORLABEL);
		lblCodigo.setFont(PadroesDesign.FONTELABEL);
		lblCodigo.setBounds(17, 17, 140, 16);
		desktopPane.add(lblCodigo);
		
		textCodigo = new JTextField();
		textCodigo.setDocument(new SomenteNumeros(8));
		textCodigo.addFocusListener(new CampoComConteudo());
		textCodigo.setForeground(PadroesDesign.CORTEXT);
		textCodigo.setFont(PadroesDesign.FONTETEXT);
		textCodigo.setColumns(10);
		textCodigo.setBounds(17, 33, 80, 28);
		desktopPane.add(textCodigo);
		
		JLabel lblNomeOuParte = new JLabel("Nome ou Parte");
		lblNomeOuParte.setForeground(PadroesDesign.CORLABEL);
		lblNomeOuParte.setFont(PadroesDesign.FONTELABEL);
		lblNomeOuParte.setBounds(107, 17, 160, 16);
		desktopPane.add(lblNomeOuParte);
		
		textNomeOuParte = new JTextField();
		textNomeOuParte.setDocument(new SomenteLetras(50));
		textNomeOuParte.addFocusListener(new CampoComConteudo());
		textNomeOuParte.addMouseListener(new BotaoDireito());
		textNomeOuParte.setName("textNomeOuParte");
		textNomeOuParte.setForeground(PadroesDesign.CORTEXT);
		textNomeOuParte.setFont(PadroesDesign.FONTETEXT);
		textNomeOuParte.setColumns(10);
		textNomeOuParte.setBounds(107, 33, 285, 28);
		desktopPane.add(textNomeOuParte);
					
		JLabel lblConta = new JLabel("Conta");
		lblConta.setForeground(PadroesDesign.CORLABEL);
		lblConta.setFont(PadroesDesign.FONTELABEL);
		lblConta.setBounds(402, 17, 143, 16);
		desktopPane.add(lblConta);
		
		textconta = new JTextField();
		textconta.setDocument(new SomenteNumeros(11));
		textconta.addFocusListener(new CampoComConteudo());
		textconta.setForeground(PadroesDesign.CORTEXT);
		textconta.setFont(PadroesDesign.FONTETEXT);
		textconta.setColumns(10);
		textconta.setBounds(402, 33, 148, 28);
		desktopPane.add(textconta);
				
		JLabel lblArquivo = new JLabel("Arquivo");
		lblArquivo.setForeground(PadroesDesign.CORLABEL);
		lblArquivo.setFont(PadroesDesign.FONTELABEL);
		lblArquivo.setBounds(560, 17, 130, 16);
		desktopPane.add(lblArquivo);
								
		textArquivo = new JTextField();
		textArquivo.setDocument(new limiteDeCampoTexto(40));
		textArquivo.addFocusListener(new CampoComConteudo());
		textArquivo.setForeground(PadroesDesign.CORTEXT);
		textArquivo.setFont(PadroesDesign.FONTETEXT);
		textArquivo.setColumns(10);
		textArquivo.setBounds(560, 33, 115, 28);
		desktopPane.add(textArquivo);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setForeground(PadroesDesign.CORLABEL);
		lblLocal.setFont(PadroesDesign.FONTELABEL);
		lblLocal.setBounds(685, 17, 160, 16);
		desktopPane.add(lblLocal);
		
		textLocal = new JTextField();
		textLocal.setDocument(new limiteDeCampoTexto(50));
		textLocal.addFocusListener(new CampoComConteudo());
		textLocal.setForeground(PadroesDesign.CORTEXT);
		textLocal.setFont(PadroesDesign.FONTETEXT);
		textLocal.setColumns(10);
		textLocal.setBounds(685, 33, 220, 28);
		desktopPane.add(textLocal);
					
			
		ClassLoader loader = getClass().getClassLoader();
		
		JButton btnLocalizarCampos = new JButton(new ImageIcon(loader.getResource("img/Localizar.png")));
		btnLocalizarCampos.addActionListener(new BotaoLocalizar());
		btnLocalizarCampos.setToolTipText("Localizar Cliente de acordo com o preenchimento dos Campos");
		btnLocalizarCampos.setBounds(918, 18, 71, 60);
		desktopPane.add(btnLocalizarCampos);
				
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder(" Clientes "));
		panelTable.setBounds(17, 88, 980, 535);
		
		//CRIAÇÂO DO COMPONENTE TABELA
		tabelaClientes = new JTable();
		tabelaClientes.setRowHeight(40);
		tabelaClientes.setPreferredScrollableViewportSize(new Dimension(980, 535));  
		tabelaClientes.setFillsViewportHeight(true);
		tabelaClientes.addMouseListener(new ClicandoMouse());
		tabelaClientes.setAutoResizeMode (JTable.AUTO_RESIZE_OFF); 
		
		scrollPane = new JScrollPane(tabelaClientes); 
		scrollPane.setHorizontalScrollBar(new JScrollBar(0)); 
		scrollPane.setSize(980,425);
		panelTable.add(scrollPane);
		desktopPane.add(panelTable);
			
		btImportar = new JButton(new ImageIcon(loader.getResource("img/Planilha.png")));
		btImportar.setText("<html>Importar <br> Clientes</html>");
		btImportar.addActionListener(new BotaoImportar());
		btImportar.setForeground(PadroesDesign.CORBOTOES);
		btImportar.setToolTipText("3003 - Inserir Clientes por Importação ");
		btImportar.setFont(PadroesDesign.FONTEBOTOES);
		btImportar.setBounds(346, 630,  125, 40);
		desktopPane.add(btImportar);		
		
		btnIncluir = new JButton(new ImageIcon(loader.getResource("img/add.png")));
		btnIncluir.setText("Incluir");
		btnIncluir.addActionListener(new BotaoIncluir());
		btnIncluir.setForeground(PadroesDesign.CORBOTOES);
		btnIncluir.setToolTipText("3002 - Incluir Cliente");
		btnIncluir.setFont(PadroesDesign.FONTEBOTOES);
		btnIncluir.setBounds(476, 630,  125, 40);
		desktopPane.add(btnIncluir);
			
		btnAlterar = new JButton(new ImageIcon(loader.getResource("img/Alterar.png")));
		btnAlterar.setText("Alterar");
		btnAlterar.addActionListener(new BotaoAlterar());
		btnAlterar.setForeground(PadroesDesign.CORBOTOES);
		btnAlterar.setToolTipText("3002 - Alterar Cliente");
		btnAlterar.setFont(PadroesDesign.FONTEBOTOES);
		btnAlterar.setBounds(606, 630,  125, 40);
		desktopPane.add(btnAlterar);
		
		btnConsulta = new JButton(new ImageIcon(loader.getResource("img/find.png")));
		btnConsulta.setText("Consultar");
		btnConsulta.addActionListener(new BotaoConsultar());
		btnConsulta.setForeground(PadroesDesign.CORBOTOES);
		btnConsulta.setToolTipText("3004 - Consultar Cliente");
		btnConsulta.setFont(PadroesDesign.FONTEBOTOES);
		btnConsulta.setBounds(736, 630,  125, 40);
		desktopPane.add(btnConsulta);
		
		btnCancelar = new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		btnCancelar.setToolTipText("Cancelar");
		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new BotaoCancelar());
		btnCancelar.setForeground(PadroesDesign.CORBOTOES);
		btnCancelar.setFont(PadroesDesign.FONTEBOTOES);
		btnCancelar.setBounds(866, 630,  125, 40);
		desktopPane.add(btnCancelar);
						
		refreshTabela();
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.repaint();
	}
	
	class BotaoImportar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{			
			if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3003"))
			{
				getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
				ManutencaoCliente_INCPPDAO processar = new ManutencaoCliente_INCPPDAO();
				processar.ImportarClientes();
				refreshTabela();
				getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(null, "Clientes Importados com Sucesso!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina de 3003 - Importação de Clientes. \n Favor informar ao administrador do sistema!");
			}
		}
	}
	
	class BotaoDireito extends MouseAdapter
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
	
	class BotaoIncluir implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3001"))
			{
				InluirClienteFrame.setRotinaExecutada(1);
				new InluirClienteFrame();
				dispose();
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
			if(ManutencaoCliente_INCPPDAO.getIdCliente() != 0)
			{
				if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3002"))
				{
					InluirClienteFrame.setRotinaExecutada(2);
					new InluirClienteFrame();
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina de 3002 - Alterar Cliente. \n Favor informar ao administrador do sistema!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Favor efetuar a consulta e selecionar o cliente, ates de clicar em Alterar!");
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
		
	class BotaoConsultar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			
			if(ManutencaoCliente_INCPPDAO.getIdCliente() != 0)
			{
				if(SenhaDAO.getDireitosAtribuidos().contains("3000") || SenhaDAO.getDireitosAtribuidos().contains("3004"))
				{
					InluirClienteFrame.setRotinaExecutada(3);
					new InluirClienteFrame();
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuário sem permissão para a Rotina de 3004 - Consultar Cliente. \n Favor informar ao administrador do sistema!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Favor efetuar a consulta e selecionar o cliente, ates de clicar em consultar!");
			}
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
			int rowIndex = tabelaClientes.getSelectedRow();
			if (rowIndex != -1)
			{
				//AO CLICAR NO VALOR DA TABELA CARREGA AS VÀRIÀVEIS DA CLASSE DAO< ESTAS SERÂO USADAS PELA TELA DE MANUTENÇÃO	
				Cliente_INCPP cliente = new ManutencaoClientesTableModel(ClientesList).get(rowIndex);
				ManutencaoCliente_INCPPDAO.setIdCliente(cliente.getIdCliente());
				ManutencaoCliente_INCPPDAO.setNome(cliente.getNome());
				ManutencaoCliente_INCPPDAO.setConta(cliente.getConta());
				System.out.println(cliente.getValor());
				ManutencaoCliente_INCPPDAO.setValor(cliente.getValor());
				ManutencaoCliente_INCPPDAO.setArquivo(cliente.getArquivo());
				ManutencaoCliente_INCPPDAO.setPasta(cliente.getPasta());
				ManutencaoCliente_INCPPDAO.setLocal(cliente.getLocal());
				ManutencaoCliente_INCPPDAO.setData(cliente.getData());
			
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
			String localizar = ""; 
			
			if(textNomeOuParte.getText().trim().length() > 0 )
			{
				localizar += "SELECT * FROM CLIENTES_INCPP WHERE NOME LIKE \'%" + textNomeOuParte.getText().trim().toUpperCase() + "%\'";
			
			}
			else if(textNomeOuParte.getText().trim().length() == 0 || textNomeOuParte.getText().trim().equals(""))
			{
				 localizar += "SELECT * FROM CLIENTES_INCPP  WHERE (1=1)"; 
			}
								
			if(textCodigo.getText().trim().length() > 0){
				localizar +=  " AND IDCLIENTE = "+ Integer.parseInt(textCodigo.getText().trim());
			}
						
			if(textArquivo.getText().trim().length() > 0){
				localizar +=  " AND ARQUIVO LIKE \'"+ textArquivo.getText().trim() +"%\'";
			}
			
			if(textconta.getText().trim().length() > 0){
				localizar +=  " AND CONTA LIKE \'"+ textconta.getText().trim() +"%\'";
			}
							
			/*INSERIR A EMPRESA*/
			if(textLocal.getText().trim().length() > 0){
				localizar +=  " AND LOCAL LIKE \'%"+ textLocal.getText().trim() +"%\'";
			}
						
			localizar += " ORDER BY NOME ";
			
			System.out.println(localizar);
					
			//DEFINE O VALOR NA VARIÀVEL USADA PARA CARREGAR A LISTA NA CLASSE DAO
			ManutencaoCliente_INCPPDAO.setBuscarCliente(localizar);
			refreshTabela();
			
			if(!ManutencaoCliente_INCPPDAO.isClienteValido())
			{
				JOptionPane.showMessageDialog(null, "Não foi localizado nenhum cliente seguindo os critérios acima, favor verificar !");
			}
		}
	}	
		
	public void refreshTabela()
	{
		
		getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));		


		//CARREGA A FUNÇÂO FINDCLIENTES DA CLASSE CONTROLLER	
		ClientesList = new ManutencaoCliente_INCPPController().findClientes();
		if (ClientesList != null){
			tabelaClientes.setModel(new ManutencaoClientesTableModel(ClientesList));
			tabelaClientes.setDefaultRenderer(Object.class, new ManutencaoClientes_INCPPCellRender());
		}
		
		System.out.println(ClientesList.size());
		if(ClientesList.size() == 0)
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
					
			if(textLocal.getText().trim().length() > 0)
			{
				textLocal.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				textLocal.setBackground(Color.WHITE);
			}
			
			
			if(textCodigo.getText().trim().length() > 0)
			{
				textCodigo.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				textCodigo.setBackground(Color.WHITE);
			}
			
			
			if(textconta.getText().trim().length() > 0)
			{
				textconta.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				textconta.setBackground(Color.WHITE);
			}
		
			if(textArquivo.getText().trim().length() > 0)
			{
				textArquivo.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				textArquivo.setBackground(Color.WHITE);
			}
		}
	}
	
}
