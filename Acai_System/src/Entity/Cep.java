package Entity;

public class Cep 
{
	
		private String codigo;
		private String tipo;
		private String logradouro;
		private String complemento;
		private String bairro;
		private String cidade;
		private String estado;
	
	
		public String getCodigo() {
			return codigo;
		}


		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}


		public String getTipo() {
			return tipo;
		}


		public void setTipo(String tipo) {
			this.tipo = tipo;
		}


		public String getLogradouro() {
			return logradouro;
		}


		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}


		public String getComplemento() {
			return complemento;
		}


		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}


		public String getBairro() {
			return bairro;
		}


		public void setBairro(String bairro) {
			this.bairro = bairro;
		}


		public String getCidade() {
			return cidade;
		}


		public void setCidade(String cidade) {
			this.cidade = cidade;
		}


		public String getEstado() {
			return estado;
		}


		public void setEstado(String estado) {
			this.estado = estado;
		}


		@Override
		public String toString() {
			return super.toString();
		}
}
