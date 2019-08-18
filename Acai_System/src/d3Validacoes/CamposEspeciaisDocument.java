package d3Validacoes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CamposEspeciaisDocument extends PlainDocument{

		@Override
		public void insertString(int inicio, String conteudo, AttributeSet a)
				throws BadLocationException {
			
			for(char c: conteudo.toCharArray()){
				//Verificando se o conteúdo inserido possui caractere invalidos
				if(!Character.isAlphabetic(c) && c != ' ')
				{
					return;
				}
			}
		
			super.insertString(inicio, conteudo, a);
		}

}

