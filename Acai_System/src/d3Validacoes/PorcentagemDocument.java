package d3Validacoes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class PorcentagemDocument extends PlainDocument
{
	int contador = 0 ;
	private int limite = -1;
	public  PorcentagemDocument () {}

	public  PorcentagemDocument (int limite)
	{
		this.limite = limite;
	
	}
		@Override
		public void insertString(int inicio, String conteudo, AttributeSet a)
				throws BadLocationException {
		
		
		for(char c: conteudo.toCharArray()){
				//Verificando se o conteúdo inserido possui caractere invalidos
			if(Character.isAlphabetic(c))
			{
				return;
			}
			
		}
		if(this.limite >= 0)
		{
			if(this.getText(0, this.getLength()).length() + conteudo.length() > this.limite) return;
		}
	
	super.insertString(inicio, conteudo, a);
}
}




