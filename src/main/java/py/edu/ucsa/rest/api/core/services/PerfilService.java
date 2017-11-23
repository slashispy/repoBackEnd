package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Perfil;

public interface PerfilService {
	Perfil getById(long id);
	Perfil getByCodigo(String codigo);
	void guardarPerfil(Perfil perfil);
	void eliminarPerfilById(long id);
	List<Perfil> listarTodos();
	void eliminarTodos();
	boolean isExistePerfil(Perfil perfil);
}
