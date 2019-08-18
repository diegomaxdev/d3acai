package Facade;
import java.util.List;

import Entity.DireitosTabela;
import Entity.DireitosUsuario;
import Entity.Usuario;
import d3DAO.SenhaDAO;


public class UsuarioFacade {

	//CRIA UM OBJETO DA CLASSE ISUARIODAO 	
	private SenhaDAO dao;
	
	//INSTANCIA CLASSE USUARIODAO, PARA IMPLEMENTAÇÂO DOS MÈTODOS
	public UsuarioFacade() 
	{
		this.dao = new SenhaDAO();
	}
	public List<Usuario> findAll() 
	{
		return dao.findAll();
	}
	public List<DireitosUsuario> findDireitos() 
	{
		return dao.findDireitos();
	}
	public List<DireitosTabela> findAllDireitos() 
	{
		return dao.findAllDireitos();
	}
}
	
