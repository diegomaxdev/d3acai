package Controller;

import java.util.List;

import Entity.DireitosTabela;
import Entity.DireitosUsuario;
import Entity.Usuario;
import Facade.UsuarioFacade;

public class UsuarioController {
	// ACESSA OS METODOS DO FACADE
	private UsuarioFacade facade;

	public UsuarioController() {
		this.facade = new UsuarioFacade();
	}

	// CARREGA A LISTA DE USUARIOS
	public List<Usuario> findUsuario() {
		return facade.findAll();
	}

	// CARREGA A LISTA DE DIREITOS DO USUARIO
	public List<DireitosUsuario> findDireitosPermitidos() {
		return facade.findDireitos();
	}

	public List<DireitosTabela> findTabelaDireitos() {
		return facade.findAllDireitos();
	}
}
