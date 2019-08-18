package d3Frame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import PadroesDesign.PadroesDesign;
import d3DAO.ManutencaoCliente_INCPPDAO;
import d3Validacoes.limiteDeCampoTexto;


public class InluirClienteFrame extends JFrame{

		private JPanel contentPane;
		private JTextField textNome, textConta, textArquivo, textPasta, textLocal, textValor;
		JButton btnSalvar, btnCancela;
		
		private static int rotinaExecutada; 

		public int getRotinaExecutada(){return rotinaExecutada;}
		public static void setRotinaExecutada(int rotinaExecutada) {InluirClienteFrame.rotinaExecutada = rotinaExecutada;}

		public InluirClienteFrame()  
		{
			this.inicializarComponentes();
			this.inicializarEventos();
		}	
		
		public void inicializarComponentes() 
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setBounds(100, 100, 500, 258);
			this.contentPane = new JPanel();
			this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			this.contentPane.setLayout(new BorderLayout(0, 0));
			this.setContentPane(contentPane);
			
			JDesktopPane desktopPane = new JDesktopPane();
			this.contentPane.add(desktopPane, BorderLayout.CENTER);
			
			JLabel lbNome = new JLabel("Nome : ");
			lbNome.setForeground(PadroesDesign.CORTITULOS);
			lbNome.setFont(PadroesDesign.FONTELABEL);
			lbNome.setBounds(17, 12, 100, 23);
			desktopPane.add(lbNome);
			
			this.textNome = new JTextField();
			this.textNome.setDocument(new limiteDeCampoTexto(80));
			this.textNome.setBounds(17, 28, 300, 28);
			this.textNome.setFont(PadroesDesign.FONTETEXT);
			this.textNome.setForeground(PadroesDesign.CORTEXT);
			desktopPane.add(textNome);
			
			JLabel lbConta = new JLabel("Conta : ");
			lbConta.setForeground(PadroesDesign.CORTITULOS);
			lbConta.setFont(PadroesDesign.FONTELABEL);
			lbConta.setBounds(327, 12, 100, 23);
			desktopPane.add(lbConta);
			
			this.textConta = new JTextField();
			this.textConta.setDocument(new limiteDeCampoTexto(20));
			this.textConta.setBounds(327, 28, 133, 28);
			this.textConta.setFont(PadroesDesign.FONTETEXT);
			this.textConta.setForeground(PadroesDesign.CORTEXT);
			desktopPane.add(textConta);
			
			JLabel lbArquivo = new JLabel("Arquivo : ");
			lbArquivo.setForeground(PadroesDesign.CORTITULOS);
			lbArquivo.setFont(PadroesDesign.FONTELABEL);
			lbArquivo.setBounds(17, 57, 100, 23);
			desktopPane.add(lbArquivo);
			
			this.textArquivo = new JTextField();
			this.textArquivo.setDocument(new limiteDeCampoTexto(20));
			this.textArquivo.setBounds(17, 73, 216, 28);
			this.textArquivo.setFont(PadroesDesign.FONTETEXT);
			this.textArquivo.setForeground(PadroesDesign.CORTEXT);
			desktopPane.add(textArquivo);
						
			JLabel lbPasta = new JLabel("Pasta : ");
			lbPasta.setForeground(PadroesDesign.CORTITULOS);
			lbPasta.setFont(PadroesDesign.FONTELABEL);
			lbPasta.setBounds(243, 57, 100, 23);
			desktopPane.add(lbPasta);
			
			this.textPasta = new JTextField();
			this.textPasta.setDocument(new limiteDeCampoTexto(5));
			this.textPasta.setBounds(243, 73, 216, 28);
			this.textPasta.setFont(PadroesDesign.FONTETEXT);
			this.textPasta.setForeground(PadroesDesign.CORTEXT);
			desktopPane.add(textPasta);
			
			JLabel lbValor = new JLabel("Valor : ");
			lbValor.setForeground(PadroesDesign.CORTITULOS);
			lbValor.setFont(PadroesDesign.FONTELABEL);
			lbValor.setBounds(17, 102, 100, 23);
			desktopPane.add(lbValor);
			
			this.textValor = new JTextField();
			//textValor.setDocument(new Valores(13));
			textValor.addFocusListener(new CampoValor()); 
			this.textValor.setBounds(17, 118, 130, 28);
			this.textValor.setFont(PadroesDesign.FONTETEXT);
			this.textValor.setForeground(PadroesDesign.CORTEXT);
			desktopPane.add(textValor);
			
			JLabel lbLocal = new JLabel("Local : ");
			lbLocal.setForeground(PadroesDesign.CORTITULOS);
			lbLocal.setFont(PadroesDesign.FONTELABEL);
			lbLocal.setBounds(157, 102, 100, 23);
			desktopPane.add(lbLocal);
			
			this.textLocal = new JTextField();
			this.textLocal.setDocument(new limiteDeCampoTexto(50));
			this.textLocal.setBounds(157, 118, 300, 28);
			this.textLocal.setFont(PadroesDesign.FONTETEXT);
			this.textLocal.setForeground(PadroesDesign.CORTEXT);
			desktopPane.add(textLocal);
					
			ClassLoader loader = getClass().getClassLoader();
			
			this.btnSalvar =  new JButton(new ImageIcon(loader.getResource("img/save.png")));
			this.btnSalvar.setText("Salvar");
			this.btnSalvar.setForeground(PadroesDesign.CORBOTOES);
			this.btnSalvar.setFont(PadroesDesign.FONTEBOTOES);
			this.btnSalvar.setBounds(199, 160, 125, 40);
			desktopPane.add(btnSalvar);
			
			
			this.btnCancela =new JButton(new ImageIcon(loader.getResource("img/btSair.png")));
			this.btnCancela.setText("Cancelar");
			this.btnCancela.setForeground(PadroesDesign.CORBOTOES);
			this.btnCancela.setToolTipText("Voltar para a tela Inicial");
			this.btnCancela.setFont(PadroesDesign.FONTEBOTOES);
			this.btnCancela.setBounds(334, 160, 125, 40);
			desktopPane.add(btnCancela);
			
			switch (rotinaExecutada) {
			case 1:
				this.setTitle("CADASTRO DE CLIENTES");
				break;
			case 2:
				this.setTitle("CONSULTA DE CLIENTES");
				textNome.setText(ManutencaoCliente_INCPPDAO.getNome()); 
				textConta.setText(ManutencaoCliente_INCPPDAO.getConta()); 
				textArquivo.setText(ManutencaoCliente_INCPPDAO.getArquivo()); 
				textPasta.setText(ManutencaoCliente_INCPPDAO.getPasta()); 
				textLocal.setText(ManutencaoCliente_INCPPDAO.getLocal());
				textValor.setText(ManutencaoCliente_INCPPDAO.getValor());
				
				break;
			case 3:
				this.setTitle("CONSULTA DE CLIENTES");
				textNome.setText(ManutencaoCliente_INCPPDAO.getNome()); 
				textConta.setText(ManutencaoCliente_INCPPDAO.getConta()); 
				textArquivo.setText(ManutencaoCliente_INCPPDAO.getArquivo()); 
				textPasta.setText(ManutencaoCliente_INCPPDAO.getPasta()); 
				textLocal.setText(ManutencaoCliente_INCPPDAO.getLocal());
				textValor.setText(ManutencaoCliente_INCPPDAO.getValor());
				
				textNome.setEditable(false); 
				textValor.setEditable(false);
				textConta.setEditable(false); 
				textArquivo.setEditable(false); 
				textPasta.setEditable(false); 
				textLocal.setEditable(false); 
				btnSalvar.setVisible(false);
		
				break;
			default:
				break;
			}
			
			this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE); 
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
		
		class CampoValor implements FocusListener
		{
			@Override
			public void focusGained(FocusEvent arg0) {
					
			}

			@Override
			public void focusLost(FocusEvent arg0) 
			{
				//TRATAMENTO CASO RECEBA , NO CAMPO SEM ESTE DARIA ERRO NA CONVERSÂO
				String valor = textValor.getText();
				if(valor.contains(",") || valor.contains("R") || valor.contains("$") || valor.contains(" "))
				{
					valor = valor.replace(".", "");
					valor = valor.replace(",", ".");
					valor = valor.replace("R", "");
					valor = valor.replace("$", "");
					valor = valor.replace(" ", "");
					valor = valor.replace("\"", "");
				}
				
				System.out.println(valor);
				
				if(valor.trim().length() == 0)
				{
					textValor.setText(NumberFormat.getCurrencyInstance().format(0));
				}
				else
				{

					textValor.setText(NumberFormat.getCurrencyInstance().format(Double.parseDouble(valor)));
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
					String nome = textNome.getText().trim();
												
					if(nome.trim().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Favor preencher o campo Nome.");
						textNome.grabFocus();
						return;
					}
				
					ManutencaoCliente_INCPPDAO.setNome(nome); 
					ManutencaoCliente_INCPPDAO.setConta(textConta.getText().trim() + " "); 
					ManutencaoCliente_INCPPDAO.setArquivo(textArquivo.getText().trim() + " "); 
					ManutencaoCliente_INCPPDAO.setPasta(textPasta.getText().trim() + " "); 
					ManutencaoCliente_INCPPDAO.setLocal(textLocal.getText().trim() + " ");
					ManutencaoCliente_INCPPDAO.setValor(textValor.getText().trim() + " ");
						
					ManutencaoCliente_INCPPDAO validar = new ManutencaoCliente_INCPPDAO();
					
					if(rotinaExecutada == 1)
					{				
						//SE FOR FALSO, ELA NÂO EXISTE - CADASTRAMOS O USUARIO ATRAVES DA DESCRICAO
						int n = JOptionPane.showConfirmDialog(null, "Confirma a inclusão do Cliente: " + nome + " ?" ,"Confirmação de inclusão " ,JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) 
						{	getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));		
							validar.IserirCliente();
							getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
						}
					}
					//SEGUNDA OPÇÂO DA ROTINA EXECUTADA
					else
					{						
						//SE FOR FALSO, ELA NÂO EXISTE - CADASTRAMOS O USUARIO ATRAVES DA DESCRICAO
						int n = JOptionPane.showConfirmDialog(null,  "Confirma a alteração do Cliente: " + nome + " ?" ,"Confirmação de alteração " ,JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) 
						{
							getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));		
							validar.AtualizarCliente();
							getContentPane().setCursor (Cursor.getPredefinedCursor (Cursor.DEFAULT_CURSOR));
						}
					}
					
					dispose();
					new PesquisaCliente();
				}
			}); //Fecha o Action Listener	
		
			this.btnCancela.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{	
					dispose();
					new PesquisaCliente();
				}
			});
		}
		
		
	}