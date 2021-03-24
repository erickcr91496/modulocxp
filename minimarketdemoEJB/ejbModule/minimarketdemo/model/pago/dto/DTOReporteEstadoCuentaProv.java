package minimarketdemo.model.pago.dto;

import java.math.BigDecimal;

public class DTOReporteEstadoCuentaProv {

	
	private Integer id_proveedor;
	private Integer id_factura;
	private String codigo_pago;
	private BigDecimal valor_factura;
	private BigDecimal valor_pagago;
	
	
	
	
	public DTOReporteEstadoCuentaProv(Integer id_proveedor, Integer id_factura, String codigo_pago,
			BigDecimal valor_factura, BigDecimal valor_pagago) {
		super();
		this.id_proveedor = id_proveedor;
		this.id_factura = id_factura;
		this.codigo_pago = codigo_pago;
		this.valor_factura = valor_factura;
		this.valor_pagago = valor_pagago;
	}
	public Integer getId_proveedor() {
		return id_proveedor;
	}
	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	public Integer getId_factura() {
		return id_factura;
	}
	public void setId_factura(Integer id_factura) {
		this.id_factura = id_factura;
	}
	public String getCodigo_pago() {
		return codigo_pago;
	}
	public void setCodigo_pago(String codigo_pago) {
		this.codigo_pago = codigo_pago;
	}
	public BigDecimal getValor_factura() {
		return valor_factura;
	}
	public void setValor_factura(BigDecimal valor_factura) {
		this.valor_factura = valor_factura;
	}
	public BigDecimal getValor_pagago() {
		return valor_pagago;
	}
	public void setValor_pagago(BigDecimal valor_pagago) {
		this.valor_pagago = valor_pagago;
	}
	
	
}
