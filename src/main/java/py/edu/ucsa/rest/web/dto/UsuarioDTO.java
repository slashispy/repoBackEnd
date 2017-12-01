package py.edu.ucsa.rest.web.dto;

public class UsuarioDTO {
	private long id;
	private String nombre;
	private String apellido;
	private String nombreCompleto;
	private String email;
	private String usuario;
	private Integer estado;
	
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public UsuarioDTO() {
		super();
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombreCompleto() {
		this.nombreCompleto = this.nombre + " " + this.apellido;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
