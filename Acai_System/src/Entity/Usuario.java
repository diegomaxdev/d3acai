package Entity;

public class Usuario {

		private int id;
		private String usuario;
		private String senha;
		private int selecaoID;
					
		public int getId() {return id;}
		public void setId(int id) {this.id = id;}
		public String getUsuario() {return  usuario;}
		public void setUsuario(String usuario) {this. usuario = usuario;}
		public String getSenha() {return senha;}
		public void setSenha(String senha) {this.senha = senha;}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}
		
		public int getSelecaoID() {return selecaoID;}
		public void setSelecaoID(int selecaoID) {this.selecaoID = selecaoID;}
		
	}
