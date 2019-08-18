package Controller;

import java.util.List;

import Entity.Cliente_INCPP;
import Facade.ManutencaoCliente_INCPPFacade;

public class ManutencaoCliente_INCPPController {

		//ACESSA OS METODOS DO FACADE
		private ManutencaoCliente_INCPPFacade facade;
		
		public ManutencaoCliente_INCPPController() {
			this.facade = new ManutencaoCliente_INCPPFacade();
		}	

		//CARREGA A LISTA DE REVISTAS
		public List<Cliente_INCPP> findClientes(){
			return facade.findAllClientes();
		}
		
		public List<Cliente_INCPP> findPesquisaClientes(){
			return facade.findAllPesquisaClientes();
		}

}
