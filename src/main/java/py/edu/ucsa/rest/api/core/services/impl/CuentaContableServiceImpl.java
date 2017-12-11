package py.edu.ucsa.rest.api.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.edu.ucsa.rest.api.core.dao.CuentaContableDao;
import py.edu.ucsa.rest.api.core.model.CuentaContable;
import py.edu.ucsa.rest.api.core.services.CuentaContableService;

@Service("cuentaContableService")
@Transactional
public class CuentaContableServiceImpl implements CuentaContableService {
	@Autowired
	private CuentaContableDao dao;

	public List<CuentaContable> listarTodos() {
		return dao.listarTodos();
	}

	public CuentaContable getById(Integer id) {
		return dao.getById(id);
	}

	public CuentaContable getByCodigo(String codigo) {
		return dao.getByCodigo(codigo);
	}

	public void guardarCuenta(CuentaContable cuenta) {
		if (cuenta.getId() != null)
			dao.insertar(cuenta);
		else
			dao.actualizar(cuenta);
	}

	public void eliminarCuentaByCod(String codigo) {
		dao.borrarPorCodigo(codigo);
	}

	public boolean isExisteCodigo(CuentaContable cuenta) {
		return getByCodigo(cuenta.getCodigo()) != null;
	}

	@Override
	public List<CuentaContable> getByTipo(String tipo) {
		return dao.getByTipo(tipo);
	}

	@Override
	public CuentaContable getByNroCuenta(String nroCuenta) {
		return dao.getByNroCuenta(nroCuenta);
	}

	@Override
	public List<CuentaContable> listarCuentasHijas(CuentaContable cuenta) {
		return dao.listarCuentasHijas(cuenta);
	}

	@Override
	public List<CuentaContable> listarAsentables() {
		return dao.listarAsentables();
	}

	@Override
	public void borrarPorCodigo(String codigo) {
		dao.borrarPorCodigo(codigo);
		
	}

	@Override
	public void borrarPorId(Integer id) {
		dao.borrarPorId(id);
		
	}
}
