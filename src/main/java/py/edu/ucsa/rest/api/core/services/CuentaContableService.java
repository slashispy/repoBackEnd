package py.edu.ucsa.rest.api.core.services;



import java.util.List;

import py.edu.ucsa.rest.api.core.model.CuentaContable;

public interface CuentaContableService {
	List<CuentaContable> listarTodos();
	CuentaContable getById(Integer id);
	CuentaContable getByCodigo(String codigo);
	List<CuentaContable> getByTipo(String tipo);
	CuentaContable getByNroCuenta(String nroCuenta);
	List<CuentaContable> listarCuentasHijas(CuentaContable cuenta);
	List<CuentaContable> listarAsentables();
	void guardarCuenta(CuentaContable cuenta);
	void borrarPorCodigo(String codigo);
	void borrarPorId(Integer id);
	void eliminarCuentaByCod(String codigo);
	boolean isExisteCodigo(CuentaContable codigo);
}

