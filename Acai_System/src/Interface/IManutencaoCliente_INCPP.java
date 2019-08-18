package Interface;

import java.util.List;

import Entity.Cliente_INCPP;

public interface IManutencaoCliente_INCPP 
{
	List<Cliente_INCPP> findAllClientes();

	List<Cliente_INCPP> findAllPesquisaClientes();
}
