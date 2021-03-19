package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cabecera_pagos database table.
 * 
 */
@Entity
@Table(name="cabecera_pagos")
@NamedQuery(name="CabeceraPago.findAll", query="SELECT c FROM CabeceraPago c")
public class CabeceraPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=15)
	private String codigopago;

	@Column(nullable=false)
	private Integer codigousuario;

	@Column(nullable=false, length=100)
	private String descripcionpago;

	@Temporal(TemporalType.DATE)
	private Date fechapago;

	//bi-directional many-to-one association to Cuentabancaria
	@ManyToOne
	@JoinColumn(name="codigocb", nullable=false)
	private Cuentabancaria cuentabancaria;

	//bi-directional many-to-one association to DetallePago
	@OneToMany(mappedBy="cabeceraPago")
	private List<DetallePago> detallePagos;

	public CabeceraPago() {
	}

	public String getCodigopago() {
		return this.codigopago;
	}

	public void setCodigopago(String codigopago) {
		this.codigopago = codigopago;
	}

	public Integer getCodigousuario() {
		return this.codigousuario;
	}

	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}

	public String getDescripcionpago() {
		return this.descripcionpago;
	}

	public void setDescripcionpago(String descripcionpago) {
		this.descripcionpago = descripcionpago;
	}

	public Date getFechapago() {
		return this.fechapago;
	}

	public void setFechapago(Date fechapago) {
		this.fechapago = fechapago;
	}

	public Cuentabancaria getCuentabancaria() {
		return this.cuentabancaria;
	}

	public void setCuentabancaria(Cuentabancaria cuentabancaria) {
		this.cuentabancaria = cuentabancaria;
	}

	public List<DetallePago> getDetallePagos() {
		return this.detallePagos;
	}

	public void setDetallePagos(List<DetallePago> detallePagos) {
		this.detallePagos = detallePagos;
	}

	public DetallePago addDetallePago(DetallePago detallePago) {
		getDetallePagos().add(detallePago);
		detallePago.setCabeceraPago(this);

		return detallePago;
	}

	public DetallePago removeDetallePago(DetallePago detallePago) {
		getDetallePagos().remove(detallePago);
		detallePago.setCabeceraPago(null);

		return detallePago;
	}

}