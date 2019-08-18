package Entity;

public class Filial {
		
		private int idFilial;
		private String descricao;
		private int statusFilial;
		private String statusVisual;
		
		
		public String getStatusVisual() {
			return statusVisual;
		}
		public void setStatusVisual(String statusVisual) {
			this.statusVisual = statusVisual;
		}
		
		public int getIdFilial() {
			return idFilial;
		}
		public void setIdFilial(int idCanal) {
			this.idFilial = idCanal;
		}
		
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		public int getStatusFilial() {
			return statusFilial;
		}
		public void setStatusFilial(int status) {
			this.statusFilial = status;
		}
		
		
		@Override
		public String toString() {
			return super.toString();
		}

}

