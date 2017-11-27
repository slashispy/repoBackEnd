package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.PerfilDao;
import py.edu.ucsa.rest.api.core.model.Perfil;
import py.edu.ucsa.rest.api.util.UtilidadesVarias;
@Repository("perfilDao")
public class PerfilDaoImpl extends AbstractDao<Integer, Perfil> implements PerfilDao {

	@Override
	public Perfil getById(int id) {
		Perfil p = super.getById(id);
		if ( p!= null) {
			UtilidadesVarias uv = new UtilidadesVarias();
			uv.initializeCollection(p.getPermisos());
			return p;
		}else {
			return p;
		}
	}

	@Override
	public void insertar(Perfil perfil) {
		super.persistir(perfil);

	}

	@Override
	public void actualizar(Perfil perfil) {
		super.actualizar(perfil);

	}

	@Override
	public void borrarPorId(int id) {
		Perfil u = (Perfil) getEntityManager()
				.createQuery("SELECT p FROM Perfil p WHERE p.id = :id")
				.setParameter("id", id)
				.getSingleResult();
		eliminar(u);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Perfil> listarTodos() {
		List<Perfil> perfiles = getEntityManager()
				.createQuery("SELECT p FROM Perfil p ORDER BY p.id ASC")
				.getResultList();
		return perfiles;
	}

	@Override
	public Perfil getByCodigo(String codigo) {
		logger.debug("Codigo: "+codigo);
		try {
			Perfil u = (Perfil) getEntityManager()
					.createQuery("SELECT p FROM Perfil p WHERE p.codigo = :codigo")
					.setParameter("codigo", codigo)
					.getSingleResult();
			if(u!= null) {
				UtilidadesVarias uv = new UtilidadesVarias();
				uv.initializeCollection(u.getPermisos());
			}
			return u;
		}catch(NoResultException ex) {
			return null;
		}
	}

}
