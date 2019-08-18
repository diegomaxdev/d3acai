package Email;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

public class EmailSistemaFrame extends JDialog {

	private JPanel contentPane;
	private JTextField textPara;
	private JTextField textAssunto;
	JEditorPane editorPaneTexto;
	private JLabel lblAnexoNome;
	
	SimpleDateFormat formatoASrquivo = new SimpleDateFormat("dd_MM_yyyy");
	Date dataAtual = new Date();

	public EmailSistemaFrame() {
		
		setTitle("ENVIO DE E-MAIL");
		setType(Type.POPUP);
		setModal(true);
		setBounds(100, 100, 699, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel lblDe = new JLabel("De :           " + "SIAC - siac.incpp@gmail.com" );
		lblDe.setForeground(Color.DARK_GRAY);
		lblDe.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblDe.setBounds(21, 32, 555, 20);
		desktopPane.add(lblDe);
		
		JLabel lblPara = new JLabel("Para :");
		lblPara.setForeground(Color.DARK_GRAY);
		lblPara.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblPara.setBounds(19, 71, 55, 16);
		desktopPane.add(lblPara);
		
		textPara = new JTextField();
		textPara.setBounds(88, 66, 561, 28);
		desktopPane.add(textPara);
		textPara.setColumns(10);
		
		JLabel lblAnexo = new JLabel("Anexo :");
		lblAnexo.setForeground(Color.DARK_GRAY);
		lblAnexo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblAnexo.setBounds(21, 110, 55, 16);
		desktopPane.add(lblAnexo);
		
		/*COLOCANDO UM ARQUIVO COM PADRÂO CASO QUEIRA ENVIAR DIRETO O RELATORIO*/
		//lblAnexoNome = new JLabel("\\Relatorios\\Agendamentos_INCPP_" + AgendamentoSiacDAO.getNomeFilial()  + "_"+ formatoASrquivo.format(dataAtual) +".xls");
		lblAnexoNome.setForeground(Color.DARK_GRAY);
		lblAnexoNome.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblAnexoNome.setBounds(86, 110, 420, 16);
		desktopPane.add(lblAnexoNome);
			
		JLabel lblAssunto = new JLabel("Assunto :");
		lblAssunto.setForeground(Color.DARK_GRAY);
		lblAssunto.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblAssunto.setBounds(21, 151, 68, 16);
		desktopPane.add(lblAssunto);
		
		textAssunto = new JTextField();
		textAssunto.setColumns(10);
		textAssunto.setBounds(88, 146, 561, 28);
		desktopPane.add(textAssunto);
				
		ClassLoader loader = getClass().getClassLoader();
		
		JButton btnCancelar = new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
		btnCancelar.setText("Cancelar");
		btnCancelar.addActionListener(new BotaoCancelar());
		btnCancelar.setForeground(Color.DARK_GRAY);
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCancelar.setBounds(527, 475, 125, 40);
		desktopPane.add(btnCancelar);
		
		JButton btnEnviar = new JButton(new ImageIcon(loader.getResource("img/Envio.png")));
		btnEnviar.addActionListener(new BotaoEnviar());
		btnEnviar.setText("Enviar");
		btnEnviar.setForeground(Color.DARK_GRAY);
		btnEnviar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnEnviar.setBounds(388, 475, 125, 40);
		desktopPane.add(btnEnviar);
		
		editorPaneTexto = new JEditorPane();
		editorPaneTexto.setBounds(21, 196, 628, 257);
		//editorPaneTexto.setEditable (false);

		/*
		// Adicionar um HTMLEditorKit para o painel de editor
		HTMLEditorKit kit = new HTMLEditorKit ();
		editorPaneTexto.setEditorKit(kit);
		// Adicionar alguns estilos para o html
		StyleSheet styleSheet = kit.getStyleSheet ();
		styleSheet.addRule ("body {color: # 000; font-family: times; margin: 4px;}");
		styleSheet.addRule ("h1 {color: blue;}");
		styleSheet.addRule ("h2 {color: # ff0000;}");
		styleSheet.addRule ("pre {font: 10px monaco; color: black; background-color: #fafafa;}"); */

		// Agora adicioná-lo a um painel de rolagem
		JScrollPane scrollpane = new JScrollPane ();
		editorPaneTexto.add(scrollpane);
		desktopPane.add(editorPaneTexto); 
		
		/*TRATANDO O CLIQUE NO BOTÂO FECHAR*/
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) 
			{
				dispose();
			}
		});
		this.setLocationRelativeTo(null);
		this.setResizable(false); 
		this.setVisible(true);
		
	}
	
	class BotaoCancelar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			dispose();
		}
	}
	
	class BotaoEnviar implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{	
			getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
			try{enviaEmailComAnexo();} catch (EmailException e){e.printStackTrace();}
			getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
			JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso !");
			
			dispose();
		}
	}
	
	  
    /** 
     * envia email com arquivo anexo  E HTML
     * @throws EmailException 
     */  
    public void enviaEmailComAnexo() throws EmailException
    {  
    // configura o email  
        MultiPartEmail email = new HtmlEmail();  
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
        
        //destinatário  
        String para = textPara.getText();
        para = para.replace(" ", "");
        
        if(para.contains(";"))
        {
        	 email.addTo(para.substring(0, para.indexOf(";")));
             email.addBcc(para.substring(para.indexOf(";") + 1, para.length()));
        }
        else
        {
        	email.addTo(para); 
        	email.addBcc("diego.maxdev@gmail.com", "Diego - SIAC");
        }
        
        email.setSubject("" + textAssunto.getText().trim()); // assunto do e-mail  
        if(editorPaneTexto.getText().trim().length() != 0)
        {
        	email.setMsg("" + editorPaneTexto.getText().trim()); //conteudo do e-mail  
        }
     
      	email.setAuthenticator( new DefaultAuthenticator("siac.incpp@gmail.com" ,  "p65f85d88" ) );
    	email.setFrom("siac.incpp@gmail.com");
    	email.setSslSmtpPort( "465" );
        email.setSSLOnConnect(true);

       	// cria o anexo 1.  
       	EmailAttachment anexo1 = new EmailAttachment();  
       	//anexo1.setPath("\\Relatorios\\Agendamentos_INCPP_"+ AgendamentoSiacDAO.getNomeFilial()  + "_" + formatoASrquivo.format(dataAtual) +".xls"); 
       	anexo1.setDisposition(EmailAttachment.ATTACHMENT);  
       	anexo1.setDescription("Agendamento INCPP");
	        	
       //	String nomeAnexo1 = "Agendamentos_INCPP_"+ AgendamentoSiacDAO.getNomeFilial() + "_" +  formatoASrquivo.format(dataAtual) +".xls";
       	//System.out.println(nomeAnexo1);
       	//anexo1.setName(nomeAnexo1);
       	email.attach(anexo1); 
              
       //envia o email; 
        email.send();  
    }  
}




