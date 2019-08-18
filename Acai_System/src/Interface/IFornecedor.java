package Interface;

import java.util.List;

import Entity.Fornecedor;

public interface IFornecedor {

		List<Fornecedor> findAllFornecedor();
		List<Fornecedor> findAllFornecedorAtivo();
}
	
