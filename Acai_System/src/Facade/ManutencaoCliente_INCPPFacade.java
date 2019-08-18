package Facade;

import java.util.List;

import Entity.Cliente_INCPP;
import d3DAO.ManutencaoCliente_INCPPDAO;

public class ManutencaoCliente_INCPPFacade 
{

	private ManutencaoCliente_INCPPDAO dao;

	//INSTANCIA CLASSE USUARIODAO, PARA IMPLEMENTAÇÂO DOS MÈTODOS
	public ManutencaoCliente_INCPPFacade() 
	{
		this.dao = new  ManutencaoCliente_INCPPDAO();
	}
	public List<Cliente_INCPP> findAllClientes() 
	{
		return dao.findAllClientes();
	}
		
	public List<Cliente_INCPP>  findAllPesquisaClientes() 
	{
		return dao.findAllPesquisaClientes();
	}

}
	

