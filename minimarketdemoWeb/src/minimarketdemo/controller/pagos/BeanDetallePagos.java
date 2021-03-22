package minimarketdemo.controller.pagos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.Cuentabancaria;
import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.pagos.managers.Cabecera;
import minimarketdemo.model.pagos.managers.Detalles;
import minimarketdemo.model.pagos.managers.ManagerDetallesPagos;

@Named
@SessionScoped
public class BeanDetallePagos implements Serializable {

	@EJB 
	ManagerDetallesPagos mDetalles;
	private List<DetallePago> detallesList;
	private Detalles detallesShow;
	private Cabecera cabecera;
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
	
	public String actionSeleccionarDetallePago(Cabecera c) {
		cabecera = c;
		detallesList = mDetalles.BuscarporIdProveedor(c.getNroPago());
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


	
}
