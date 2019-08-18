package d3Validacoes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DataQuatroDigitos extends PlainDocument
{

	private int limite = -1;
	public DataQuatroDigitos() {}

	public DataQuatroDigitos(int limite)
	{
		this.limite = limite;
	
	}
		@Override
		public void insertString(int inicio, String conteudo, AttributeSet a)
				throws BadLocationException {
		int contadorBarra = 0 ;
		
		for(char c: conteudo.toCharArray()){
				//Verificando se o conteúdo inserido possui caractere invalidos
			if(c == '/' && contadorBarra < 1)
			{
				contadorBarra ++;
			}
			else if(Character.isAlphabetic(c))
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




