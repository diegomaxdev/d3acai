package ConfiguracoesFrame;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import CellRender.TabelaDireitosCellRender;
import Controller.UsuarioController;
import Entity.DireitosTabela;
import Entity.DireitosUsuario;
import PadroesDesign.PadroesDesign;
import d3DAO.SenhaDAO;
import d3TableModel.DireitoTableModel;
import d3TableModel.ListaDIreitosTableModel;
import net.miginfocom.swing.MigLayout;


public class DireitosUsuarioFrame extends JFrame {

	private JPanel contentPane , panelTable, panelTableLista;
	private JTable tabelaPermitidas, tabelaNaoPermitidas;
	JDesktopPane desktopPane;
	JLabel lbLoginSelecionado, lblUsuario;
	JButton btnIncluir , btnRemover , btnRemoverTodas ,  btnIncluirTodas , btnSair;
	private JScrollPane scrollPane , scrollPaneLista;
	private List<DireitosUsuario> DireitosList;
	private List<DireitosTabela> TabelaDireitosList;
	private JTextField textRotina;
	SenhaDAO validar;
	
	private static String rotinaSelecionada;
	
	//TEXT ROTINA PREENCHIDA - MONTA UM CAMPO E IMPREME O QUE FOI SELECIONADO
	public static String getRotinaSelecionada() 	{	return rotinaSelecionada;	}
	public static void setRotinaSelecionada(String rotinaSelecionada) {	rotinaSelecionada = rotinaSelecionada;	}
	
	public DireitosUsuarioFrame() 
	{
		inicializarComponentes();
	}
	
	private void inicializarComponentes() 
	{
		this.setTitle(" PERMISSÕES DO SISTEMA : " + SenhaDAO.getLoginSelecionado());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 420);
		
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.desktopPane = new JDesktopPane();
		this.contentPane.add(desktopPane, BorderLayout.CENTER);
		
		this.lblUsuario = new JLabel("Usuário : ");
		this.lblUsuario.setForeground(PadroesDesign.CORTITULOS);
		this.lblUsuario.setFont(PadroesDesign.FONTETITULOS);
		this.lblUsuario.setBounds(32, 23, 75, 16);
		this.desktopPane.add(lblUsuario);
		
		//CARREGA O NOME DO LABEL ATRAVÈS DO USUARIO SELECIONADO EM SENHA - getLoginSelecionado
		this.lbLoginSelecionado = new JLabel(SenhaDAO.getLoginSelecionado().trim());
		this.lbLoginSelecionado.setForeground(PadroesDesign.CORTITULOS);
		this.lbLoginSelecionado.setFont(PadroesDesign.FONTETITULOS);
		this.lbLoginSelecionado.setBounds(119, 24, 75, 16);
		this.desktopPane.add(lbLoginSelecionado);
		
		this.textRotina = new JTextField();
		this.textRotina.setText(getRotinaSelecionada());
		this.textRotina.setBounds(483, 24, 365, 28);
		this.textRotina.setFont(PadroesDesign.FONTETEXT);
		this.textRotina.setForeground(PadroesDesign.CORTEXT);
		this.textRotina.setEditable(false);
		this.desktopPane.add( textRotina);
		this.textRotina.setColumns(10);
		
//******************************************************** PRIMEIRA TABELA PERMISSÔES ********************************************************************************************************************************
		this.panelTable = new JPanel(new MigLayout());
		this.panelTable.setBorder(BorderFactory.createTitledBorder(" Direitos Atribuídos : "));
		this.panelTable.setBounds(18, 60, 365, 267);
		
		//CRIAÇÂO DO COMPONENTE TABELA
		this.tabelaPermitidas = new JTable();
		this.tabelaPermitidas.addMouseListener(new ClicandoMousePermitidos());
		this.tabelaPermitidas.setToolTipText("Clique para selecionar um Direito");
				
		//ADICIONA UM SCROLL A TABELA CRIADA ACIMA
		this.scrollPane = new JScrollPane(tabelaPermitidas);
		this.panelTable.add(scrollPane);
		this.desktopPane.add(panelTable);
		
//******************************************************** SEGUNDA TABELA  SEM PREMISSÔES *************************************************************************************************************		
		
		//PAINEL QUE EXIBIRÁ OS DIREITOS EXISTENTES
		this.panelTableLista = new JPanel(new MigLayout());
		this.panelTableLista.setBorder(BorderFactory.createTitledBorder(" Direitos Existentes : "));
		this.panelTableLista.setBounds(483, 60, 365, 267);
		
		//CRIAÇÂO DO COMPONENTE TABELA
		this.tabelaNaoPermitidas = new JTable();
		this.tabelaNaoPermitidas.addMouseListener(new ClicandoMouseSemPermissao());
		this.tabelaNaoPermitidas.setToolTipText("Clique para selecionar um Direito");
				
		//ADICIONA UM SCROLL A TABELA CRIADA ACIMA
		this.scrollPaneLista = new JScrollPane(tabelaNaoPermitidas);
		this.panelTableLista.add(scrollPaneLista);
		this.desktopPane.add(panelTableLista); 
						
//******************************************************** PRIMEIRA TABELA ************************************************************************************************************************	
		refreshTabela();
		
		ClassLoader loader = getClass().getClassLoader();
		
		this.btnIncluirTodas = new JButton(new ImageIcon(loader.getResource("img/adicionarTodas.png")));
		this.btnIncluirTodas.addActionListener(new BotaoIncluirTodosDireitos());
		this.btnIncluirTodas.setBounds(398, 73, 70, 40);
		this.btnIncluirTodas.setToolTipText("Atribuir todas as Permissões ao Usuário");
		this.desktopPane.add(btnIncluirTodas);
		
		this.btnIncluir = new JButton(new ImageIcon(loader.getResource("img/Incluir.png")));
		this.btnIncluir.addActionListener(new BotaoIncluir());
		this.btnIncluir.setBounds(398, 123, 70, 40);
		this.btnIncluir.setToolTipText("Atribuir Permissão específica ao Usuário");
		this.desktopPane.add(btnIncluir);
		
		this.btnRemover = new JButton(new ImageIcon(loader.getResource("img/remover.png")));
		this.btnRemover.addActionListener(new BotaoRemover());
		this.btnRemover.setBounds(398, 173, 70, 40);
		this.btnRemover.setToolTipText("Remover Permissão específica ao Usuário");
		this.desktopPane.add(btnRemover);
		
		this.btnRemoverTodas = new JButton(new ImageIcon(loader.getResource("img/removerTodas.png")));
		this.btnRemoverTodas.addActionListener(new BotaoRemoverTodosDireitos());
		this.btnRemoverTodas.setBounds(398, 223, 70, 40);
		this.btnRemoverTodas.setToolTipText("Remover todas as Permissões já atribuída ao Usuário");
		this.desktopPane.add(btnRemoverTodas);
		
		this.btnSair = new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		this.btnSair.addActionListener(new BotaoSair());
		this.btnSair.setBounds(398, 273, 70, 40);
		this.btnSair.setToolTipText("Voltar para a tela Inicial");
		this.desktopPane.add(this.btnSair);
		
		this.repaint();
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); 
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	class ClicandoMouseSemPermissao implements MouseListener
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
		public void mousePressed(MouseEvent e) {
			int rowIndex = tabelaNaoPermitidas.getSelectedRow();
			if (rowIndex != -1)
			{
				//AO CLICARMNOS SELECIONAMOS E INSERIRMOS O VALOR DO INDICE AO CAMPO CLICADO - ATRAVÉS DO SETIDSELECIONADO - CRIADO PARA DIFERENCIAR DO ID LOGADO
				DireitosTabela direito = new ListaDIreitosTableModel(TabelaDireitosList).get(rowIndex);
				textRotina.setText(direito.getIdRotina() + " - " + direito.getDescricao());
				SenhaDAO.setRotinaSelecionada(direito.getIdRotina());
				btnIncluir.setEnabled(true);
				btnRemover.setEnabled(false);
			}
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	} 
	
	class ClicandoMousePermitidos implements MouseListener
	{			
		@Override
		public void mouseClicked(MouseEvent e) {
			int rowIndex = tabelaPermitidas.getSelectedRow();
			if (rowIndex != -1)
			{
				//AO CLICARMNOS SELECIONAMOS E INSERIRMOS O VALOR DO INDICE AO CAMPO CLICADO - ATRAVÉS DO SETIDSELECIONADO - CRIADO PARA DIFERENCIAR DO ID LOGADO
				DireitosUsuario direito = new DireitoTableModel(DireitosList).get(rowIndex);
				textRotina.setText(direito.getIdRotina() + " - " + direito.getDescricao());
				SenhaDAO.setRotinaSelecionada(direito.getIdRotina());
				btnIncluir.setEnabled(false);
				btnRemover.setEnabled(true);
			}
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
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	} 
	
	public void refreshTabela()
	{
		//CARREGA A FUNÇÂO FINDUSUARIO DA CLASSE CONTROLLER	
		DireitosList = new UsuarioController().findDireitosPermitidos();
		if (DireitosList != null)
		{
			tabelaPermitidas.setModel(new DireitoTableModel(DireitosList));
			tabelaPermitidas.setDefaultRenderer(Object.class, new TabelaDireitosCellRender());
		}
		
		//CARREGA A FUNÇÂO FINDUSUARIO DA CLASSE CONTROLLER	
		TabelaDireitosList = new UsuarioController().findTabelaDireitos();
		if (TabelaDireitosList != null)
			{
				tabelaNaoPermitidas.setModel(new ListaDIreitosTableModel(TabelaDireitosList));
				tabelaNaoPermitidas.setDefaultRenderer(Object.class, new TabelaDireitosCellRender());
			} 
	}
		
	class BotaoIncluir implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			SenhaDAO validar = new SenhaDAO();
			validar.adicionaDireito();
			refreshTabela();
			
		}
	}
	
	class BotaoIncluirTodosDireitos implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			SenhaDAO validar = new SenhaDAO();
			validar.adicionarTodosDireitos();
			refreshTabela();
			
		}
	}
	
	class BotaoRemover implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			SenhaDAO validar = new SenhaDAO();
			validar.removeDireitos();
			refreshTabela();
		}
	}
	
	class BotaoRemoverTodosDireitos implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			SenhaDAO validar = new SenhaDAO();
			validar.removerTodosDireitos();
			refreshTabela();
		}
	}
		
	class BotaoSair implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			new SenhaFrame();
			dispose();
		}
	}
	
}
