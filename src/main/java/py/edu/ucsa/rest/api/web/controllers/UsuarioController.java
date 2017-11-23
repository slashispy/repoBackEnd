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

import py.edu.ucsa.rest.api.core.model.Usuario;
import py.edu.ucsa.rest.api.core.services.UsuarioService;
import py.edu.ucsa.rest.api.util.ErrorDTO;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	public static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.listarTodos();
		if(usuarios.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id ){
		logger.info("Vamos obtener el usuario con id {}.", id);
		Usuario usuario = usuarioService.getById(id);
		if(usuario == null) {
			logger.error("No se encontro ningun Usuario con id {}.",id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("No se encontro ningun Usuario con id " + id), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario user, UriComponentsBuilder ucBuilder){
		logger.info("Creando el Usario {}", user);
		if(usuarioService.isExisteUsuario(user)) {
			logger.error("Insercion Fallida. Ya existe un registro con el usuario {}.", user.getUsuario());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Insercion fallida. Ya existe un registro con el usuario "+user.getUsuario()), HttpStatus.CONFLICT);
		}
		usuarioService.guardarUsuario(user);
		user = usuarioService.getByUsuario(user.getUsuario());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario user, @PathVariable("id") long id){
		logger.info("Actualizando el usuario con id {}", id);
		Usuario usuarioBD = usuarioService.getById(id);
		if(usuarioBD == null) {
			logger.error("Actualizacion Fallida. No existe el usuario con id {}.", id);
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Actualizacion Fallida. No existe el usuario con id " +id), HttpStatus.NOT_FOUND);
		}
		usuarioBD.setApellido(user.getApellido());
		usuarioBD.setEmail(user.getEmail());
		usuarioBD.setEstado(user.getEstado());
		usuarioBD.setNombre(user.getNombre());
		usuarioBD.setPerfiles(user.getPerfiles());
		usuarioService.guardarUsuario(usuarioBD);
		return new ResponseEntity<Usuario>(usuarioBD, HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id") long id){
		logger.info("Obteniendo y eliminando Usuario con id {}", id);
		Usuario user = usuarioService.getById(id);
		if(user == null) {
			logger.error("Eliminacion Fallida, No existe Usuario con el id {}",id );
			return new ResponseEntity<ErrorDTO>(new ErrorDTO("Eliminacion Fallida, No existe Usuario con el id "+ id), HttpStatus.NOT_FOUND);
		}
		usuarioService.eliminarUsuarioById(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarUsuarios(){
		logger.info("Borrando todos los usuarios");
		usuarioService.eliminarTodos();
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
}
