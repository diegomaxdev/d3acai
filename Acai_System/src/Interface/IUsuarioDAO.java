package Interface;

import java.util.List;

import Entity.DireitosTabela;
import Entity.DireitosUsuario;
import Entity.Usuario;

//CLASSE DE INTERFACE QUE OBRIGA OS MÉTODOS A SEREM IMPLEMENTADOS
public interface IUsuarioDAO 
{
	List<Usuario> findAll();
	
	List<DireitosUsuario> findDireitos();
	
	List<DireitosTabela> findAllDireitos();
}
