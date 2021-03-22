package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import java.util.List;


/**
 * The persistent class for the detalle_pagos database table.
 * 
 */
@Entity
@Table(name="detalle_pagos")
@NamedQuery(name="DetallePago.findAll", query="SELECT d FROM DetallePago d")
public class DetallePago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer iddetalle;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valorapagar;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valorfactura;

	//bi-directional many-to-one association to Apifactura
	@ManyToOne
	@JoinColumn(name="id_factura", nullable=false)
	private Apifactura apifactura;

	//bi-directional many-to-one association to CabeceraPago
	@ManyToOne
	@JoinColumn(name="codigopago", nullable=false)
	private CabeceraPago cabeceraPago;

	public DetallePago() {
	}

	public Integer getIddetalle() {
		return this.iddetalle;
	}

	public void setIddetalle(Integer iddetalle) {
		this.iddetalle = iddetalle;
	}

	public BigDecimal getValorapagar() {
		return this.valorapagar;
	}

	public void setValorapagar(BigDecimal valorapagar) {
		this.valorapagar = valorapagar;
	}

	public BigDecimal getValorfactura() {
		return this.valorfactura;
	}

	public void setValorfactura(BigDecimal valorfactura) {
		this.valorfactura = valorfactura;
	}

	public Apifactura getApifactura() {
		return this.apifactura;
	}

	public void setApifactura(Apifactura apifactura) {
		this.apifactura = apifactura;
	}

	public CabeceraPago getCabeceraPago() {
		return this.cabeceraPago;
	}

	public void setCabeceraPago(CabeceraPago cabeceraPago) {
		this.cabeceraPago = cabeceraPago;
	}

}