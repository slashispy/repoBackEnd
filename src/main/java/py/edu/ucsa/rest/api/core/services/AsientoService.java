package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Asiento;

public interface AsientoService {
	void guardarAsiento(Asiento asiento	);
	List<Asiento> listarTodos();
	Asiento getById(Integer id);
}
