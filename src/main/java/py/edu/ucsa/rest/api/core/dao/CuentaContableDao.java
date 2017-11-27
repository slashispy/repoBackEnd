package py.edu.ucsa.rest.api.core.dao;



import java.util.List;

import py.edu.ucsa.rest.api.core.model.CuentaContable;

public interface CuentaContableDao {
	List<CuentaContable> listarTodos();
	CuentaContable getById(Integer id);
	CuentaContable getByCodigo(String codigo);
	List<CuentaContable> getByTipo(String tipo);
	CuentaContable getByNroCuenta(String nroCuenta);
	List<CuentaContable> listarCuentasHijas(CuentaContable cuenta);
	List<CuentaContable> listarAsentables();
	void insertar(CuentaContable cuenta);
	void actualizar(CuentaContable cuenta);
	void borrarPorCodigo(String codigo);
	void borrarPorId(Integer id);
//	void borrarTodos();
}

