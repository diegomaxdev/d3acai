package d3Validacoes;

import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CEPDocument extends PlainDocument {
	
		private int limite = -1;
		public CEPDocument() {}
		
		public CEPDocument(int limite){
			this.limite = limite;
			
		}
		
			@Override
			public void insertString(int inicio, String conteudo, AttributeSet a)
				throws BadLocationException {
								
				for(char c: conteudo.toCharArray())
				{
					/*if(c == KeyEvent.VK_TAB)
					{
						return;
					}	*/
				//Verificando se o conteúdo inserido possui caractere invalidos
					if(c != '-' )
					{	
						if(Character.isAlphabetic(c))
						{
							return;
						}
					}
				}	
		if(this.limite >= 0)
		{
			if(this.getText(0, this.getLength()).length() + conteudo.length() > this.limite) return;
		}
			
			super.insertString(inicio, conteudo.toUpperCase(), a);
		}
	}