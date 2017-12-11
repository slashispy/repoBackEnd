package py.edu.ucsa.rest.api.core.dao;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Asiento;

public interface AsientoDao {
	List<Asiento> listarTodos();
	void insertar(Asiento asiento);
	void actualizar(Asiento asiento);
	Asiento getById(Integer id);
	
}
