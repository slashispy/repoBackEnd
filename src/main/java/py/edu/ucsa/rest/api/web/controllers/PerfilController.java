package py.edu.ucsa.rest.api.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.rest.api.core.model.Perfil;
import py.edu.ucsa.rest.api.core.services.PerfilService;
import py.edu.ucsa.rest.api.util.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("/perfil")
public class PerfilController {
public static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private PerfilService perfilService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarPerfil(){
		List<Perfil> perfiles = perfilService.listarTodos();
		if(perfiles.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Perfil>>(perfiles,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPerfilById(@PathVariable("id") Long id ){
		logger.info("Vamos obtener el perfil con id {}.", id);
		Perfil perfil = perfilService.getById(id);
		if(perfil == null) {
			logger.error("No se encontro ningun Usuario con id {}.",id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No se encontro ningun Usuario con id " + id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearPerfil(@RequestBody Perfil perfil, UriComponentsBuilder ucBuilder){
		logger.info("Creando el Perfil {}", perfil);
		if(perfilService.isExistePerfil(perfil)) {
			logger.error("Insercion Fallida. Ya existe un registro con el perfil {}.", perfil.getCodigo());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion fallida. Ya existe un registro con el perfil "+perfil.getCodigo()), HttpStatus.CONFLICT);
		}
		perfilService.guardarPerfil(perfil);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarUsuario(@RequestBody Perfil profile, @PathVariable("id") long id){
		logger.info("Actualizando del perfil con id {}", id);
		Perfil perfilBD = perfilService.getById(id);
		if(perfilBD == null) {
			logger.error("Actualizacion Fallida. No existe el perfil con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe el perfil con id " +id), HttpStatus.NOT_FOUND);
		}
		perfilBD.setDescripcion(profile.getDescripcion());
		perfilBD.setPermisos(profile.getPermisos());
		perfilService.guardarPerfil(perfilBD);
		return new ResponseEntity<Perfil>(perfilBD, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id") long id){
		logger.info("Obteniendo y eliminando Perfil con id {}", id);
		Perfil profile = perfilService.getById(id);
		if(profile == null) {
			logger.error("Eliminacion Fallida, No existe Usuario con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Perfil con el id "+ id), HttpStatus.NOT_FOUND);
		}
		perfilService.eliminarPerfilById(id);
		return new ResponseEntity<Perfil>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarUsuarios(){
		logger.info("Borrando todos los perfiles");
		perfilService.eliminarTodos();
		return new ResponseEntity<Perfil>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
