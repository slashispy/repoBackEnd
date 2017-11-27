package py.edu.ucsa.rest.api.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="asientos_cab")
@NamedQuery(name="Asiento.findAll", query="SELECT a FROM Asiento a")
public class Asiento implements Serializable {
	private static final long serialVersionUID = 3103838628166979596L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private long numeroAsiento;
	
	@Column(name="fecha_asiento")
	private Date fechaAsiento;
	
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	
	private String descripcion;
	
	private String estado; //P:   A:
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="asiento")
	private List<DetalleAsiento> detalleAsientos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(long numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public Date getFechaAsiento() {
		return fechaAsiento;
	}

	public void setFechaAsiento(Date fechaAsiento) {
		this.fechaAsiento = fechaAsiento;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetalleAsiento> getDetalleAsientos() {
		return detalleAsientos;
	}

	public void setDetalleAsientos(List<DetalleAsiento> detalleAsientos) {
		this.detalleAsientos = detalleAsientos;
	}
	
	

}