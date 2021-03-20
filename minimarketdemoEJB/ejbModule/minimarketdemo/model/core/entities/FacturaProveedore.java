package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the factura_proveedores database table.
 * 
 */
@Entity
@Table(name="factura_proveedores")
@NamedQuery(name="FacturaProveedore.findAll", query="SELECT f FROM FacturaProveedore f")
public class FacturaProveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer idfactura;

	@Column(nullable=false)
	private Integer codigoprov;

	@Column(precision=10, scale=2)
	private BigDecimal saldoenviado;

	public FacturaProveedore() {
	}

	public Integer getIdfactura() {
		return this.idfactura;
	}

	public void setIdfactura(Integer idfactura) {
		this.idfactura = idfactura;
	}

	public Integer getCodigoprov() {
		return this.codigoprov;
	}

	public void setCodigoprov(Integer codigoprov) {
		this.codigoprov = codigoprov;
	}

	public BigDecimal getSaldoenviado() {
		return this.saldoenviado;
	}

	public void setSaldoenviado(BigDecimal saldoenviado) {
		this.saldoenviado = saldoenviado;
	}

}