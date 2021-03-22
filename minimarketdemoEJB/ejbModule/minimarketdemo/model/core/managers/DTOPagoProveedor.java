package minimarketdemo.model.core.managers;

import java.math.BigDecimal;


public class DTOPagoProveedor {

	
	private Integer idProveedor;

	private BigDecimal total;

	public DTOPagoProveedor(Integer idProveedor, BigDecimal total) {
		super();
		this.idProveedor = idProveedor;
		this.total = total;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	


}
