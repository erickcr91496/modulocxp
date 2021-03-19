package minimarketdemo.model.pago.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DTOPagoProveedores implements Serializable {

	private Integer id_proveedor;

	private BigDecimal saldo_pago;

	public DTOPagoProveedores(Integer id_proveedor, BigDecimal saldo_pago) {
		super();
		this.id_proveedor = id_proveedor;
		this.saldo_pago = saldo_pago;
	}

	public Integer getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public BigDecimal getSaldo_pago() {
		return saldo_pago;
	}

	public void setSaldo_pago(BigDecimal saldo_pago) {
		this.saldo_pago = saldo_pago;
	}
	
	
}