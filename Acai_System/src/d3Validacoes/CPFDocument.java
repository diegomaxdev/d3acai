package d3Validacoes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CPFDocument extends PlainDocument {



	private int limite = -1;
	public CPFDocument() {}
	
	public CPFDocument(int limite){
		this.limite = limite;
		
	}
			@Override
			public void insertString(int inicio, String conteudo, AttributeSet a)
					throws BadLocationException {
				
			for(char c: conteudo.toCharArray()){
					//Verificando se o conte�do inserido possui caractere invalidos
				
				if(c != '.' || c != '-')
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


