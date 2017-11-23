package py.edu.ucsa.rest.api.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.ucsa.rest.api.core.dao.PerfilDao;
import py.edu.ucsa.rest.api.core.model.Perfil;

@Service("perfilService")
@Transactional
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilDao dao;
	
	@Override
	public Perfil getById(long id) {
		return dao.getById((int) id);
	}

	@Override
	public void guardarPerfil(Perfil perfil) {
		if(perfil.getId()!= null) {
			dao.insertar(perfil);
		}else {
			dao.actualizar(perfil);
		}

	}

	@Override
	public void eliminarPerfilById(long id) {
		 dao.borrarPorId((int) id);
	}

	@Override
	public List<Perfil> listarTodos() {
		return dao.listarTodos();
	}

	@Override
	public void eliminarTodos() {
		List<Perfil> profiles = dao.listarTodos();
		for(Perfil w: profiles) {
			dao.borrarPorId(w.getId());
		}
	}

	@Override
	public boolean isExistePerfil(Perfil perfil) {
		return getByCodigo(perfil.getCodigo()) != null;
	}

	@Override
	public Perfil getByCodigo(String codigo) {
		return dao.getByCodigo(codigo);
	}

}
