package py.edu.ucsa.rest.api.core.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="asientos_det")
@NamedQuery(name="DetalleAsiento.findAll", query="SELECT d FROM DetalleAsiento d")
public class DetalleAsiento implements Serializable {

	private static final long serialVersionUID = 3103838628166979596L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name= "asiento", referencedColumnName = "id")
	private Asiento asiento;
	
	@Column(name="monto_debe", nullable=true)
	private Long montoDebe;
	
	@Column(name="monto_haber", nullable=true)
	private Long montoHaber;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private CuentaContable cuentaContable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public Long getMontoDebe() {
		return montoDebe;
	}

	public void setMontoDebe(Long montoDebe) {
		this.montoDebe = montoDebe;
	}

	public Long getMontoHaber() {
		return montoHaber;
	}

	public void setMontoHaber(Long montoHaber) {
		this.montoHaber = montoHaber;
	}

	public CuentaContable getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(CuentaContable cuentaContable) {
		this.cuentaContable = cuentaContable;
	}
	
	
	
	
}
