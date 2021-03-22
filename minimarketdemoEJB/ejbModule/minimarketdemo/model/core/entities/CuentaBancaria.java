package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the cuentabancaria database table.
 * 
 */
@Entity
@Table(name="cuentabancaria")
@NamedQuery(name="Cuentabancaria.findAll", query="SELECT c FROM Cuentabancaria c")
public class Cuentabancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=15)
	private String codigocb;

	@Column(nullable=false, length=100)
	private String descripcioncb;

	@Column(nullable=false, length=50)
	private String entidadbancariacb;

	private Boolean estadocb;

	@Column(nullable=false, length=100)
	private String propietariocb;

	@Column(precision=10, scale=2)
	private BigDecimal saldocb;

	@Column(nullable=false, length=50)
	private String tipocb;

	//bi-directional many-to-one association to CabeceraPago
	@OneToMany(mappedBy="cuentabancaria")
	private List<CabeceraPago> cabeceraPagos;

	public Cuentabancaria() {
	}

	public String getCodigocb() {
		return this.codigocb;
	}

	public void setCodigocb(String codigocb) {
		this.codigocb = codigocb;
	}

	public String getDescripcioncb() {
		return this.descripcioncb;
	}

	public void setDescripcioncb(String descripcioncb) {
		this.descripcioncb = descripcioncb;
	}

	public String getEntidadbancariacb() {
		return this.entidadbancariacb;
	}

	public void setEntidadbancariacb(String entidadbancariacb) {
		this.entidadbancariacb = entidadbancariacb;
	}

	public Boolean getEstadocb() {
		return this.estadocb;
	}

	public void setEstadocb(Boolean estadocb) {
		this.estadocb = estadocb;
	}

	public String getPropietariocb() {
		return this.propietariocb;
	}

	public void setPropietariocb(String propietariocb) {
		this.propietariocb = propietariocb;
	}

	public BigDecimal getSaldocb() {
		return this.saldocb;
	}

	public void setSaldocb(BigDecimal saldocb) {
		this.saldocb = saldocb;
	}

	public String getTipocb() {
		return this.tipocb;
	}

	public void setTipocb(String tipocb) {
		this.tipocb = tipocb;
	}

	public List<CabeceraPago> getCabeceraPagos() {
		return this.cabeceraPagos;
	}

	public void setCabeceraPagos(List<CabeceraPago> cabeceraPagos) {
		this.cabeceraPagos = cabeceraPagos;
	}

	public CabeceraPago addCabeceraPago(CabeceraPago cabeceraPago) {
		getCabeceraPagos().add(cabeceraPago);
		cabeceraPago.setCuentabancaria(this);

		return cabeceraPago;
	}

	public CabeceraPago removeCabeceraPago(CabeceraPago cabeceraPago) {
		getCabeceraPagos().remove(cabeceraPago);
		cabeceraPago.setCuentabancaria(null);

		return cabeceraPago;
	}

}