package minimarketdemo.model.pagos.managers;

import java.io.Serializable;
import java.math.BigDecimal;

public class Detalles implements Serializable {
	

	
	public int iddetalle;
	
	public String codigoPago;
	
	public BigDecimal valorfactura;
	
	public BigDecimal valorapagar;
	
	public int id_factura;

	public int getIddetalle() {
		return iddetalle;
	}

	public void setIddetalle(int iddetalle) {
		this.iddetalle = iddetalle;
	}

	public String getCodigoPago() {
		return codigoPago;
	}

	public void setCodigoPago(String codigoPago) {
		this.codigoPago = codigoPago;
	}

	public BigDecimal getValorfactura() {
		return valorfactura;
	}

	public void setValorfactura(BigDecimal valorfactura) {
		this.valorfactura = valorfactura;
	}

	public BigDecimal getValorapagar() {
		return valorapagar;
	}

	public void setValorapagar(BigDecimal valorapagar) {
		this.valorapagar = valorapagar;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}


}
