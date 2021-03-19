package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the apifacturas database table.
 * 
 */
@Entity
@Table(name="apifacturas")
@NamedQuery(name="Apifactura.findAll", query="SELECT a FROM Apifactura a")
public class Apifactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_factura", unique=true, nullable=false)
	private Integer idFactura;

	@Column(name="id_proveedor", nullable=false)
	private Integer idProveedor;

	@Column(precision=10, scale=2)
	private BigDecimal total;

	public Apifactura() {
	}

	public Integer getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}