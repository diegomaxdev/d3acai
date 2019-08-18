package Interface;
import java.util.List;

import Entity.Produto;


public interface IProduto 
{
	List<Produto> findAllProduto();

	List<Produto> findAllPesquisaProduto();
}
