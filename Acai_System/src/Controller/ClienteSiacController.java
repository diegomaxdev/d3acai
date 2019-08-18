package Controller;

import java.util.List;

import Entity.ClienteSiac;
import Facade.ClienteSiacFacade;

public class ClienteSiacController {

		//ACESSA OS METODOS DO FACADE
		private ClienteSiacFacade facade;
		
		public ClienteSiacController() {
			this.facade = new ClienteSiacFacade();
		}	

		//CARREGA A LISTA DE REVISTAS
		public List<ClienteSiac> findClienteSiac(){
			return facade.findAllClienteSiac();
		}
		
		public List<ClienteSiac> findPesquisaClienteSiac(){
			return facade.findAllPesquisaClienteSiac();
		}

}
