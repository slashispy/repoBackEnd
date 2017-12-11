package py.edu.ucsa.rest.api.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.rest.api.core.model.Asiento;
import py.edu.ucsa.rest.api.core.services.AsientoService;

@RestController
@RequestMapping("/asiento")
public class AsientoController {
	
	public static final Logger logger = LoggerFactory.getLogger(CuentaContableController.class);
	
	@Autowired
	private AsientoService asientoService;
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
		public ResponseEntity<?> listarAsientos() {
		List<Asiento> cuentas = asientoService.listarTodos();
		if (cuentas.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// podríamos retornar también HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Asiento>>(cuentas, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearUsuario(@RequestBody Asiento asiento, UriComponentsBuilder ucBuilder){
		logger.info("Creando el asiento {}", asiento);
		asientoService.guardarAsiento(asiento);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/asiento/{id}").buildAndExpand(asiento.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);	
	}

}
