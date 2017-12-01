package py.edu.ucsa.rest.api.core.dao;

import java.util.List;

import py.edu.ucsa.rest.api.core.model.Usuario;
import py.edu.ucsa.rest.web.dto.UsuarioDTO;

public interface UsuarioDao {
	Usuario getById(int id);
	Usuario getByUsuario(String usuario);
	void insertar(Usuario usuario);
	void actualizar(Usuario usuario);
	void borrarPorUsuario(String usuario);
	List<Usuario> listarTodos();
	List<UsuarioDTO> getReporte();
	List<UsuarioDTO> getReporteActivos();
	List<UsuarioDTO> getReporteInactivos();
}
