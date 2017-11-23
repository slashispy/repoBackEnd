package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Usuario;

public interface UsuarioService {
	Usuario getById(long id);
	Usuario getByUsuario(String name);
	void guardarUsuario(Usuario user);
//	void actualizarUsuario(UsuarioDTO user);
	void eliminarUsuarioById(long id);
	List<Usuario> listarTodos();
	void eliminarTodos();
	boolean isExisteUsuario(Usuario user);
}
