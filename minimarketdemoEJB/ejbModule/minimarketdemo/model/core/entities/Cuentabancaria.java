package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the cuenta_bancaria database table.
 * 
 */
@Entity
<<<<<<< HEAD
@Table(name="cuenta_bancaria")
@NamedQuery(name="CuentaBancaria.findAll", query="SELECT c FROM CuentaBancaria c")
public class CuentaBancaria implements Serializable {
=======
@Table(name="cuentabancaria")

@NamedQuery(name="Cuentabancaria.findAll", query="SELECT c FROM Cuentabancaria c")
public class Cuentabancaria implements Serializable {
>>>>>>> 0654384 (migracion bdd corregida y actualizaciones vistas)
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
<<<<<<< HEAD
	@Column(unique=true, nullable=false, length=2147483647)
=======

	@Column(unique=true, nullable=false, length=15)
>>>>>>> 0654384 (migracion bdd corregida y actualizaciones vistas)
	private String codigocb;

	@Column(nullable=false)
	private Integer codigoprov;

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

	//bi-directional many-to-one association to Pago
	@OneToMany(mappedBy="cuentaBancaria")
	private List<Pago> pagos;

<<<<<<< HEAD
=======
	//bi-directional many-to-one association to CabeceraPago

	@OneToMany(mappedBy="cuentabancaria")
	private List<CabeceraPago> cabeceraPagos;
>>>>>>> 0654384 (migracion bdd corregida y actualizaciones vistas)

	public CuentaBancaria() {
	}

	public String getCodigocb() {
		return this.codigocb;
	}

	public void setCodigocb(String codigocb) {
		this.codigocb = codigocb;
	}

	public Integer getCodigoprov() {
		return this.codigoprov;
	}

	public void setCodigoprov(Integer codigoprov) {
		this.codigoprov = codigoprov;
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

	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Pago addPago(Pago pago) {
		getPagos().add(pago);
		pago.setCuentaBancaria(this);

		return pago;
	}

	public Pago removePago(Pago pago) {
		getPagos().remove(pago);
		pago.setCuentaBancaria(null);

		return pago;
	}


}