package py.edu.ucsa.rest.api.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.rest.api.core.dao.AbstractDao;
import py.edu.ucsa.rest.api.core.dao.AsientoDao;
import py.edu.ucsa.rest.api.core.model.Asiento;

@Repository("asientoDao")
public class AsientoDaoImpl extends AbstractDao<Integer, Asiento> implements AsientoDao {

	@Override
	public void registrarAsiento(Asiento asiento) {
		

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Asiento> listarTodos() {
		List<Asiento> asientos = getEntityManager()
				.createQuery("SELECT a FROM Asiento a ORDER BY a.numeroAsiento ASC")
				.getResultList();
		return asientos;
	}

	@Override
	public void insertar(Asiento cuenta) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actualizar(Asiento asiento) {
		
	}

}
