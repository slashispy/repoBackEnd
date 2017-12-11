package py.edu.ucsa.rest.api.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.rest.api.core.model.CuentaContable;
import py.edu.ucsa.rest.api.core.services.CuentaContableService;
import py.edu.ucsa.rest.api.util.ErrorDTO;

@RestController
@RequestMapping("/cuenta-contable")
public class CuentaContableController {
public static final Logger logger = LoggerFactory.getLogger(CuentaContableController.class);

@Autowired
private CuentaContableService cuentaContableService;

//================ RECUPERAMOS TODOS LOS PERFILES ================
@SuppressWarnings("rawtypes")
@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarCuentas() {
	List<CuentaContable> cuentas = cuentaContableService.listarTodos();
	if (cuentas.isEmpty()) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
		// podríamos retornar también HttpStatus.NOT_FOUND
	}
	return new ResponseEntity<List<CuentaContable>>(cuentas, HttpStatus.OK);
}

//================ RECUPERAMOS UN PERFILES A PARTIR DE SU ID ================ 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCuenta(@PathVariable("id") Integer id) {
		logger.info("Vamos a obtener la Cuenta Contable con id {}.", id);
		CuentaContable cuenta =  cuentaContableService.getById(id);
		if (cuenta == null) {
			logger.error("No se encontró ninguna cuenta con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO(
					"No se encontró ninguna Cuenta con id " + id), HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<CuentaContable>(cuenta, HttpStatus.OK);
	}
	
//	 ================ CREAMOS UNA CUENTA ================
		@RequestMapping(value = "/", method = RequestMethod.POST)
		 public ResponseEntity<?> crearUsuario(@RequestBody CuentaContable cuenta, UriComponentsBuilder ucBuilder) {
			 logger.info("Creando el Cuenta Contable : {}", cuenta);
			 if (cuentaContableService.isExisteCodigo(cuenta)) {
				 logger.error("Inserción fallida. Ya existe un registro con la cuenta {}", cuenta.getNumeroCuenta());
				 return new ResponseEntity<ErrorDTO>(new ErrorDTO(
						 "Inserción Fallida. Ya existe un registro con la cuenta " +
								 cuenta.getNumeroCuenta()), HttpStatus.CONFLICT);

			 }
			 
			 cuentaContableService.guardarCuenta(cuenta);
			 HttpHeaders headers = new HttpHeaders();
			 headers.setLocation(ucBuilder.path("/cuenta-contable/{id}").buildAndExpand(cuenta.getId()).toUri());
			 return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
//		
//		// ================ ACTUALIZAMOS LOS DATOS DE UN PERFIL ================ 
//		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//		public ResponseEntity<?> actualizarPerfil(@PathVariable("id") long id, @RequestBody Perfil perfil) {
//			logger.info("Actualizando el Perfil con id {}", id);
//			Perfil perfilBD = cuentaContableService.getById(id);
//			if (perfilBD == null) {
//				 logger.error("Actualización fallida. No existe el perfil con el id {}.", id);
//				 return new ResponseEntity<ErrorDTO>(
//						 new ErrorDTO("Actualización fallida. No existe el perfil con el id " + id), HttpStatus.NOT_FOUND);
//
//			}
//			 perfilBD.setCodigo(perfil.getCodigo());
//			 perfilBD.setDescripcion(perfil.getDescripcion());
//			 cuentaContableService.guardarPerfil(perfilBD);
//			 return new ResponseEntity<Perfil>(perfilBD, HttpStatus.OK);
//
//		}
//		
//		// ================ ELIMINAMOS UN PERFIL ================ 
//		@RequestMapping(value = "/{cod}", method = RequestMethod.DELETE)
//		public ResponseEntity<?> eliminarPerfil(@PathVariable("cod") String codigo) {
//			logger.info("Obteniendo y eliminando el Perfil con cod {}", codigo);
//			Perfil perfil = cuentaContableService.getByCodigo(codigo);
//			if (perfil == null) {
//				logger.error("Eliminación fallida. No existe el perfil con el cod {}", codigo);
//				return new ResponseEntity<ErrorDTO>(new ErrorDTO("No existe el perfil con el cod " + codigo),
//						HttpStatus.NOT_FOUND);
//
//			}
//			cuentaContableService.eliminarPerfilByCod(codigo);
//			return new ResponseEntity<Perfil>(HttpStatus.NO_CONTENT);
//		}
//		
//		// ================ ELIMINAMOS TODOS LOS USUARIOS ================
//		@RequestMapping(value = "/", method = RequestMethod.DELETE)
//		public ResponseEntity<Perfil> eliminarPerfiles () {
//			logger.info("Borrando todos los perfiles");
//			cuentaContableService.eliminarTodos();
//			return new ResponseEntity<Perfil>(HttpStatus.NO_CONTENT);
//		}

}