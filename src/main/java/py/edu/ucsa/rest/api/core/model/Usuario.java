package py.edu.ucsa.rest.api.core.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import py.edu.ucsa.rest.api.util.MD5;
@Entity
@Table(name="usuarios")
@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.findByIdActivos", query="SELECT u FROM Usuario u WHERE u.estado = 1 and u.id = :id")
	})

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String apellido;
	private String clave;
	private String email;
	private String nombre;
	private String usuario;
	private Integer estado;
	
	@ManyToMany
	//@NotEmpty
	@JoinTable(
			name = "usuarios_perfiles"
			, joinColumns= {
					@JoinColumn(name="id_usuario")
			}
			, inverseJoinColumns= {
					@JoinColumn(name="id_perfil")
			})
	private List<Perfil> perfiles;
	
	public Usuario() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		MD5 md5 = new MD5();
		this.clave = md5.getMD5(clave);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	
}
