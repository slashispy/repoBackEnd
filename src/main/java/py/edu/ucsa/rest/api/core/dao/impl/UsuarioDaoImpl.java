package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.UsuarioDao;
import py.edu.ucsa.rest.api.core.model.Usuario;
import py.edu.ucsa.rest.api.util.UtilidadesVarias;
@Repository("usuarioDao")
public class UsuarioDaoImpl extends AbstractDao<Integer, Usuario> implements UsuarioDao {

	@Override
	public Usuario getById(int id) {
		Usuario u = super.getById(id);
		if (u!= null) {
			UtilidadesVarias uv = new UtilidadesVarias();
			uv.initializeCollection(u.getPerfiles());
			return u;
		}else {
			return u;
		}
		
	}

	
	@Override
	public Usuario getByUsuario(String usuario) {
		logger.debug("Usuario: "+usuario);
		try {
			Usuario u = (Usuario) getEntityManager()
					.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
					.setParameter("usuario", usuario)
					.getSingleResult();
			if(u!= null) {
				UtilidadesVarias uv = new UtilidadesVarias();
				uv.initializeCollection(u.getPerfiles());
			}
			return u;
		}catch(NoResultException ex) {
			return null;
		}
		
	}

	@Override
	public void insertar(Usuario usuario) {
		super.persistir(usuario);

	}

	@Override
	public void actualizar(Usuario usuario) {
		super.actualizar(usuario);

	}

	@Override
	public void borrarPorUsuario(String usuario) {
		Usuario u = (Usuario) getEntityManager()
				.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
				.setParameter("usuario", usuario)
				.getSingleResult();
		super.eliminar(u);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = getEntityManager()
				.createQuery("SELECT u FROM Usuario u ORDER BY u.nombre ASC")
				.getResultList();
		return usuarios;
	}

}
