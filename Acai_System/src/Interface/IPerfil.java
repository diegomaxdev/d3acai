package Interface;

import java.util.List;

import Entity.Perfil;

public interface IPerfil {

		List<Perfil> findAllPerfil();
		List<Perfil> findAllPerfilAtivo();
}
	
