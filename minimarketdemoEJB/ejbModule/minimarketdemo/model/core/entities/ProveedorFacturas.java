package minimarketdemo.model.core.entities;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class ProveedorFacturas implements Serializable {
	
	
	private Date fecha;
	private Date fecha_vencimiento;
	private int id_factura;
	private int id_proveedor;
	private int id_usuario;
	private BigDecimal total;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public int getId_factura() {
		return id_factura;
	}
	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}
	public int getId_proveedor() {
		return id_proveedor;
	}
	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	


	
	
	
	
	
	
	
}