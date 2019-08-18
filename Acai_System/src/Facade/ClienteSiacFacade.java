package Facade;

import java.util.List;

import Entity.ClienteSiac;
import d3DAO.ClienteSiacDAO;

public class ClienteSiacFacade 
{

	private ClienteSiacDAO dao;

	//INSTANCIA CLASSE USUARIODAO, PARA IMPLEMENTA��O DOS M�TODOS
	public ClienteSiacFacade() 
	{
		this.dao = new  ClienteSiacDAO();
	}
	public List<ClienteSiac> findAllClienteSiac() 
	{
		return dao.findAllClienteSiac();
	}
		
	public List<ClienteSiac>  findAllPesquisaClienteSiac() 
	{
		return dao.findAllPesquisaClienteSiac();
	}

}
	

