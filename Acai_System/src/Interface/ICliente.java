package Interface;
import java.util.List;

import Entity.Agendamento;


public interface ICliente
{
	List<Agendamento> findAllAgendamento();

	List<Agendamento> findAllPesquisaAgendamento();
}
