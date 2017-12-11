package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.CuentaContableDao;
import py.edu.ucsa.rest.api.core.model.CuentaContable;

@Repository("cuentaContableDao")
public class CuentaContableDaoImpl extends AbstractDao<Integer, CuentaContable> implements CuentaContableDao {
	@Override
	public CuentaContable getById(Integer id) {
		CuentaContable p = super.getById(id);
		return p;
	}

	@Override
	public void borrarPorCodigo(String codigo) {
		CuentaContable p = (CuentaContable) getEntityManager()
				.createQuery("SELECT p FROM CuentaContable p WHERE p.codigo = :cod")
				.setParameter("cod", codigo).getSingleResult();
		eliminar(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CuentaContable> getByTipo(String tipo) {
		logger.debug("Tipo Cuenta: " + tipo);
		try {
//			List<CuentaContable> p = (List<CuentaContable>) getEntityManager()
//					.createQuery("SELECT p FROM CuentaContable p WHERE p.tipo = :tipo")
//					.setParameter("tipo", tipo).getResultList();
			
			Query q = getEntityManager().createQuery("SELECT p FROM CuentaContable p WHERE p.tipo = :tipo");
			q.setParameter("tipo", tipo);
			List<CuentaContable> p = q.getResultList();
			return p;
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@Override
	public CuentaContable getByCodigo(String codigo) {
		logger.debug("Codigo: " + codigo);
		try {
			CuentaContable p = (CuentaContable) getEntityManager()
					.createQuery("SELECT p FROM CuentaContable p WHERE p.codigo = :codigo")
					.setParameter("codigo", codigo).getSingleResult();
			return p;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CuentaContable> listarTodos() {
		List<CuentaContable> perfil = getEntityManager()
				.createQuery("SELECT p FROM CuentaContable p ORDER BY p.id ASC")
				.getResultList();
		return perfil;
	}

	@Override
	public void insertar(CuentaContable cuenta) {
		super.persistir(cuenta);
	}

	@Override
	public void actualizar(CuentaContable cuenta) {
		super.actualizar(cuenta);
	}

	// Una alternativa a Hibernate.initialize()
	protected void initializeCollection(Collection<?> collection) {
		if (collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}

	@Override
	public CuentaContable getByNroCuenta(String nroCuenta) {
		logger.debug("nroCuenta: " + nroCuenta);
		try {
			CuentaContable p = (CuentaContable) getEntityManager().createQuery("SELECT p FROM CuentaContable p WHERE p.numeroCuenta = :nroCuenta")
					.setParameter("nroCuenta", nroCuenta).getSingleResult();
			return p;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CuentaContable> listarCuentasHijas(CuentaContable cuenta) {
		logger.debug("Cuenta ID: " + cuenta.getId());
		try {
			List<CuentaContable> p = (List<CuentaContable>) getEntityManager()
					.createQuery("SELECT p FROM CuentaContable p WHERE p.cuentaPadre.id = :idCuenta")
					.setParameter("idCuenta", cuenta.getId()).getResultList();
			return p;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CuentaContable> listarAsentables() {
		logger.debug("Listando asentables");
		try {
			List<CuentaContable> p = getEntityManager()
					.createQuery("SELECT p FROM CuentaContable p WHERE p.asentable = true")
					.getResultList();
			return p;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void borrarPorId(Integer id) {
		CuentaContable p = (CuentaContable) getEntityManager().createQuery("SELECT p FROM CuentaContable p WHERE p.id = :id")
				.setParameter("id", id).getSingleResult();
		eliminar(p);
	}
}