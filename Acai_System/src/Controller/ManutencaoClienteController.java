package Controller;

import java.util.List;

import Entity.Cliente;
import Facade.ManutencaoClienteFacade;

public class ManutencaoClienteController {

		//ACESSA OS METODOS DO FACADE
		private ManutencaoClienteFacade facade;
		
		public ManutencaoClienteController() {
			this.facade = new ManutencaoClienteFacade();
		}	

		//CARREGA A LISTA DE REVISTAS
		public List<Cliente> findClientes(){
			return facade.findAllClientes();
		}
		
		public List<Cliente> findPesquisaClientes(){
			return facade.findAllPesquisaClientes();
		}

}
