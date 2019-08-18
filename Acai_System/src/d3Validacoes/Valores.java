package d3Validacoes;

	import javax.swing.text.AttributeSet;
	import javax.swing.text.BadLocationException;
	import javax.swing.text.PlainDocument;

public class Valores extends PlainDocument{
	
	private int limite = -1;
	public Valores() {}
	
	public Valores(int limite){
		this.limite = limite;
		
	}
	

		@Override
		public void insertString(int inicio, String conteudo, AttributeSet a)
		throws BadLocationException 
		{
			int contador = 0;
			int contVirgula = 0;
			for(char c: conteudo.toCharArray())
			{
				contador++;
				if(Character.isDigit(c))
				{
					
				}
				else if(c == 'R' || c == '$' || c == ',' || c == ' ' || c == '.')
				{
					
					if(c == 'R' && contador > 1)
					{
						return;
					}
					if(c == '$' && contador != 2)
					{
						return;
					}
					
					if(c == ',')
					{ 
						contVirgula++;
						if (contVirgula > 2)
						{
							return;
						}
					}	
				}
				else if(Character.isAlphabetic(c) || c == '/' || c == '*' || c == '=' || c == '-' || c == '+' || c == '(' || 
						c == ')' || c == '&' || c == '%' || c == '#' || c == '@' || c == '!' || c == '"' || c == '|' || c == '\\' || 
						c == '<' || c == '>' || c == '?' || c == ':' ||  c == ':' || c == '}' || c == '{' || c == ']' || c == '[' || c == '¨' || 
						c == 'ª' || c == 'º' || c == '§' || c == '_' || c == '`' || c == '´' )
				{
					return;
				}
				
				
				if(this.limite >= 0)
				{
					if(this.getText(0, this.getLength()).length() + conteudo.length() > this.limite) return;
				}
			}
		super.insertString(inicio, conteudo, a);
	}
}

