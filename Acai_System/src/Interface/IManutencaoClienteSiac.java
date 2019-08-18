package Interface;
import java.util.List;

import Entity.ClienteSiac;


public interface IManutencaoClienteSiac 
{
	List<ClienteSiac> findAllClienteSiac();

	List<ClienteSiac> findAllPesquisaClienteSiac();
}
