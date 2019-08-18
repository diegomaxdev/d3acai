package Interface;
import java.util.List;

import Entity.Cliente;
import Entity.Cliente_INCPP;


public interface IManutencaoCliente 
{
	List<Cliente> findAllClientes();

	List<Cliente> findAllPesquisaClientes();
}
