package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.AsientoDao;
import py.edu.ucsa.rest.api.core.model.Asiento;

@Repository("asientoDao")
public class AsientoDaoImpl extends AbstractDao<Integer, Asiento> implements AsientoDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Asiento> listarTodos() {
		List<Asiento> asientos = getEntityManager()
				.createNamedQuery("Asiento.findAll")
				.getResultList();
		return asientos;
	}

	@Override
	public void insertar(Asiento cuenta) {
		super.persistir(cuenta);
		
	}
	
	@Override
	public void actualizar(Asiento asiento) {
		super.actualizar(asiento);
	}
	
	@Override
	public Asiento getById(Integer id) {
		return super.getById(id);
	}

}
