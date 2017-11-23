package py.edu.ucsa.rest.api.core.dao;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Perfil;

public interface PerfilDao {
	Perfil getById(int id);
	Perfil getByCodigo(String codigo);
	void insertar(Perfil perfil);
	void actualizar(Perfil perfil);
	void borrarPorId(int  id);
	List<Perfil> listarTodos();
}
