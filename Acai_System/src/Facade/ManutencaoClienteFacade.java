package Facade;

import java.util.List;

import Entity.Cliente;
import d3DAO.ManutencaoClienteDAO;

public class ManutencaoClienteFacade {

	private ManutencaoClienteDAO dao;

	//INSTANCIA CLASSE USUARIODAO, PARA IMPLEMENTAÇÂO DOS MÈTODOS
	public ManutencaoClienteFacade() 
	{
		this.dao = new  ManutencaoClienteDAO();
	}
	public List<Cliente> findAllClientes() 
	{
		return dao.findAllClientes();
	}
		
	public List<Cliente>  findAllPesquisaClientes() 
	{
		return dao.findAllPesquisaClientes();
	}

}
	

