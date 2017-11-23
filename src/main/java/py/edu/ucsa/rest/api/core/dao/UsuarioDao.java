package py.edu.ucsa.rest.api.core.dao;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Usuario;

public interface UsuarioDao {
	Usuario getById(int id);
	Usuario getByUsuario(String usuario);
	void insertar(Usuario usuario);
	void actualizar(Usuario usuario);
	void borrarPorUsuario(String usuario);
	List<Usuario> listarTodos();
}
