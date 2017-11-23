package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.ucsa.rest.api.core.dao.UsuarioDao;
import py.edu.ucsa.rest.api.core.model.Usuario;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

//	private static final AtomicLong counter = new AtomicLong();
//	private static List<UsuarioDTO> usuarios;
//	
//	static {
//		usuarios = crearUsuariosEnMemoria();
//	}
	
	@Autowired
	private UsuarioDao dao;
	
	@Override
	public Usuario getById(long id) {
		return dao.getById((int) id);
	}

//	private static List<UsuarioDTO> crearUsuariosEnMemoria() {
//		List<UsuarioDTO> usuarios = new ArrayList<>();
//		usuarios.add(new UsuarioDTO(counter.incrementAndGet(), "Andrés", "Planás", "Zurbaran 949",26, "aplanas", "Andres91*" ));
//		usuarios.add(new UsuarioDTO(counter.incrementAndGet(), "Agustina", "Trinidad", "Dr Mortero 983",25, "atrinidad", "Agustina92*" ));
//		usuarios.add(new UsuarioDTO(counter.incrementAndGet(), "Valentina", "Trinidad", "Dr Mortero ",3, "vtrinidad", "valentina14*" ));
//		return usuarios;
//	}

	@Override
	public Usuario getByUsuario(String name) {
		return dao.getByUsuario(name);
	}

	@Override
	public void guardarUsuario(Usuario user) {
		if(user.getId()!= null) {
			dao.insertar(user);
		}else {
			dao.actualizar(user);
		}
	}

//	@Override
//	public void actualizarUsuario(UsuarioDTO user) {
//		int index = usuarios.indexOf(user);
//		usuarios.set(index, user);
//
//	}

	@Override
	public void eliminarUsuarioById(long id) {
		Usuario user = dao.getById((int) id);
		dao.borrarPorUsuario(user.getUsuario());
	}

	@Override
	public List<Usuario> listarTodos() {
		return dao.listarTodos();
	}

	@Override
	public void eliminarTodos() {
		List<Usuario> users = dao.listarTodos();
		for(Usuario w: users) {
			dao.borrarPorUsuario(w.getUsuario());
		}
	}

	@Override
	public boolean isExisteUsuario(Usuario user) {
		return getByUsuario(user.getUsuario()) != null;
	}

}
