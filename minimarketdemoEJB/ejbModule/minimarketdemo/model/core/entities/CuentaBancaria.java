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
@Table(name="cuenta_bancaria")
@NamedQuery(name="CuentaBancaria.findAll", query="SELECT c FROM CuentaBancaria c")
public class CuentaBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String codigocb;

	private String descripcion;

	@Column(name="entidad_bancaria")
	private String entidadBancaria;

	private Boolean estadocb;

	private String nombre;

	private BigDecimal saldocb;

	@Column(name="tipo_cuenta")
	private String tipoCuenta;

	//bi-directional many-to-one association to MdProvCb
	@OneToMany(mappedBy="cuentaBancaria")
	private List<MdProvCb> mdProvCbs;

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

	public List<MdProvCb> getMdProvCbs() {
		return this.mdProvCbs;
	}

	public void setMdProvCbs(List<MdProvCb> mdProvCbs) {
		this.mdProvCbs = mdProvCbs;
	}

	public MdProvCb addMdProvCb(MdProvCb mdProvCb) {
		getMdProvCbs().add(mdProvCb);
		mdProvCb.setCuentaBancaria(this);

		return mdProvCb;
	}

	public MdProvCb removeMdProvCb(MdProvCb mdProvCb) {
		getMdProvCbs().remove(mdProvCb);
		mdProvCb.setCuentaBancaria(null);

		return mdProvCb;
	}

}