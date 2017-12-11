package py.edu.ucsa.rest.api.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.ucsa.rest.api.core.dao.AsientoDao;
import py.edu.ucsa.rest.api.core.model.Asiento;
import py.edu.ucsa.rest.api.core.services.AsientoService;

@Service("asientoService")
@Transactional
public class AsientoServiceImpl implements AsientoService {

	@Autowired
	private AsientoDao dao;
	
	@Override
	public void guardarAsiento(Asiento asiento) {
		if(asiento.getId() != null) {
			dao.insertar(asiento);
		}else {
			dao.actualizar(asiento);
		}

	}

	@Override
	public List<Asiento> listarTodos() {
		return dao.listarTodos();
	}

	@Override
	public Asiento getById(Integer id) {
		return dao.getById(id);
	}



	

}
