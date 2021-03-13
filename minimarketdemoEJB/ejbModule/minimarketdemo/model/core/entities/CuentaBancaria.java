package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the cuenta_bancaria database table.
 * 
 */
@Entity
@Table(name="cuenta_bancaria")
@NamedQuery(name="CuentaBancaria.findAll", query="SELECT c FROM CuentaBancaria c")
public class CuentaBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, length=2147483647)
	private String codigocb;

	@Column(length=50)
	private String descripcion;

	@Column(name="entidad_bancaria", length=50)
	private String entidadBancaria;

	private Boolean estadocb;

	@Column(length=50)
	private String nombre;

	@Column(precision=10, scale=2)
	private BigDecimal saldocb;

	@Column(name="tipo_cuenta", length=50)
	private String tipoCuenta;

	public CuentaBancaria() {
	}

	public String getCodigocb() {
		return this.codigocb;
	}

	public void setCodigocb(String codigocb) {
		this.codigocb = codigocb;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEntidadBancaria() {
		return this.entidadBancaria;
	}

	public void setEntidadBancaria(String entidadBancaria) {
		this.entidadBancaria = entidadBancaria;
	}

	public Boolean getEstadocb() {
		return this.estadocb;
	}

	public void setEstadocb(Boolean estadocb) {
		this.estadocb = estadocb;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getSaldocb() {
		return this.saldocb;
	}

	public void setSaldocb(BigDecimal saldocb) {
		this.saldocb = saldocb;
	}

	public String getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}