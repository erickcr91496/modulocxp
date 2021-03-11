package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the md_prov_cb database table.
 * 
 */
@Entity
@Table(name="md_prov_cb")
@NamedQuery(name="MdProvCb.findAll", query="SELECT m FROM MdProvCb m")
public class MdProvCb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigomd;

	@Column(name="saldo_pagar")
	private BigDecimal saldoPagar;

	//bi-directional many-to-one association to CuentaBancaria
	@ManyToOne
	@JoinColumn(name="codigocb")
	private CuentaBancaria cuentaBancaria;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="codigoprov")
	private Proveedor proveedor;

	//bi-directional many-to-one association to Pago
	@OneToMany(mappedBy="mdProvCb")
	private List<Pago> pagos;

	public MdProvCb() {
	}

	public Integer getCodigomd() {
		return this.codigomd;
	}

	public void setCodigomd(Integer codigomd) {
		this.codigomd = codigomd;
	}

	public BigDecimal getSaldoPagar() {
		return this.saldoPagar;
	}

	public void setSaldoPagar(BigDecimal saldoPagar) {
		this.saldoPagar = saldoPagar;
	}

	public CuentaBancaria getCuentaBancaria() {
		return this.cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Pago addPago(Pago pago) {
		getPagos().add(pago);
		pago.setMdProvCb(this);

		return pago;
	}

	public Pago removePago(Pago pago) {
		getPagos().remove(pago);
		pago.setMdProvCb(null);

		return pago;
	}

}