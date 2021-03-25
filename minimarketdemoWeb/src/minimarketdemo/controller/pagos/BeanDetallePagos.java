package minimarketdemo.controller.pagos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.Cuentabancaria;
import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.pago.dto.DTOReporteEstadoCuentaProv;
import minimarketdemo.model.pagos.managers.Cabecera;
import minimarketdemo.model.pagos.managers.Detalles;
import minimarketdemo.model.pagos.managers.ManagerDetallesPagos;

@Named
@SessionScoped
public class BeanDetallePagos implements Serializable {

	@EJB
	ManagerDetallesPagos mDetalles;
	private List<DetallePago> detallesList;
	private List<DetallePago> detallesListFacturas;

	private Detalles detallesShow;
	private Cabecera cabecera;

	// variables para realizar operaciones
	private BigDecimal valorApagar;
	private BigDecimal valorFactura;
	private BigDecimal total;

	public BeanDetallePagos() {

	}

//	public void inicializar() {
//		listaDetalles=mDetalles.findDetallesByPago(BeanCabeceraPagos.);
//			try{
//				System.out.println("BeanDetalles!!!!");
//				detallesList = mDetalles.findDetallesByPago(1);
//			}catch (Exception e) {
//				JSFUtil.crearMensajeERROR(e.getMessage());
//		e.printStackTrace();
//		}
//	}

	public void inicializar() {
		// detallesListFacturas= mDetalles.listarDetalle();
	}

	public String actionSeleccionarDetallePago(Cabecera c) {
		cabecera = c;
		detallesList = mDetalles.BuscarporIdProveedor(c.getNroPago());

		valorApagar = new BigDecimal(0.00);
		valorFactura = new BigDecimal(0.00);
		total = new BigDecimal(0.00);

		for (DetallePago list : detallesList) {
			valorApagar = valorApagar.add(list.getValorapagar());
			valorFactura = valorFactura.add(list.getValorfactura());
		}
		total = valorFactura.subtract(valorApagar);

		System.out.println("cuenta seleccionado: " + c.getNroPago());
		return "detalles_factura";
	}

	public Cabecera getCabecera() {
		return cabecera;
	}

	public void setCabecera(Cabecera cabecera) {
		this.cabecera = cabecera;
	}

	public List<DetallePago> getDetallesList() {
		return detallesList;
	}

	public void setDetallesList(List<DetallePago> detallesList) {
		this.detallesList = detallesList;
	}

	public List<DetallePago> getDetallesListFacturas() {
		return detallesListFacturas;
	}

	public void setDetallesListFacturas(List<DetallePago> detallesListFacturas) {
		this.detallesListFacturas = detallesListFacturas;
	}

	public BigDecimal getValorApagar() {
		return valorApagar;
	}

	public void setValorApagar(BigDecimal valorApagar) {
		this.valorApagar = valorApagar;
	}

	public BigDecimal getValorFactura() {
		return valorFactura;
	}

	public void setValorFactura(BigDecimal valorFactura) {
		this.valorFactura = valorFactura;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	

}
