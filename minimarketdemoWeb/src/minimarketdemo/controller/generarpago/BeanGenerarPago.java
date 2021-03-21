package minimarketdemo.controller.generarpago;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.CabeceraPago;
import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.generarpagos.managers.ManagerGenerarPagos;
import minimarketdemo.model.pagos.managers.ManagerCabeceraPagos;

@Named
@SessionScoped
public class BeanGenerarPago implements Serializable {

	@EJB
	ManagerGenerarPagos mGenerarPagos;
	@EJB 
	ManagerCabeceraPagos mCabecera;

	private int idProveedor;
	private int idFactura;
	private List<DetallePago> detalleList;
	private List<Apifactura> apifacturaList;
	private Apifactura factura;
	private List<Integer> proveedoresList;
	private BigDecimal valorApagar;
	
	//Variables para crear la cabecera
	private String descripcionpago;
	private String codigoCB;
	private Integer codigoUsuario;

	public BeanGenerarPago() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() throws Exception {
		proveedoresList = mGenerarPagos.findAllIdProveedores();
		idProveedor = proveedoresList.get(0);
		apifacturaList = mGenerarPagos.findAllByIdProveedor(idProveedor);
		idFactura = apifacturaList.get(0).getIdFactura();
		factura = mGenerarPagos.findByIdApiFactura(idFactura);
		detalleList = new ArrayList<DetallePago>();
	}

	public void listenerListarFacturasByIdProv() {
		apifacturaList = mGenerarPagos.findAllByIdProveedor(idProveedor);
		idFactura = apifacturaList.get(0).getIdFactura();
		try {
			factura = mGenerarPagos.findByIdApiFactura(idFactura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listenerFactura() {
		try {
			factura = mGenerarPagos.findByIdApiFactura(idFactura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actionListenerInsertarListaDetalle() {

		System.out.println("actionListenerInsertarListaDetalle");

		DetallePago dp = new DetallePago();
		dp.setApifactura(factura);
		dp.setCabeceraPago(null);
		dp.setValorfactura((BigDecimal) (factura.getTotal()));		
		dp.setValorapagar((BigDecimal) valorApagar);
		detalleList.add(dp);
		
		apifacturaList = mGenerarPagos.eliminarIdLista(apifacturaList, factura.getIdFactura());
		

		
		idFactura = apifacturaList.get(0).getIdFactura();
		try {
			factura = mGenerarPagos.findByIdApiFactura(idFactura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void actionListenerInsertarDetalles() {
		try {
			mCabecera.crearCabeceraPagos(codigoCB, descripcionpago, codigoUsuario);
			List<CabeceraPago> listcP = mCabecera.findAllCabeceraPago();
			CabeceraPago cP = listcP.get(listcP.size()-1);
			for (DetallePago listDe : detalleList) {
				mGenerarPagos.insertarDetallePago(cP, listDe.getValorfactura(), listDe.getValorapagar(), listDe.getApifactura());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public BigDecimal getValorApagar() {
		return valorApagar;
	}

	public void setValorApagar(BigDecimal valorApagar) {
		this.valorApagar = valorApagar;
	}

	public Apifactura getFactura() {
		return factura;
	}

	public void setFactura(Apifactura factura) {
		this.factura = factura;
	}

	public List<Integer> getProveedoresList() {
		return proveedoresList;
	}

	public void setProveedoresList(List<Integer> proveedoresList) {
		this.proveedoresList = proveedoresList;
	}

	public List<Apifactura> getApifacturaList() {
		return apifacturaList;
	}

	public void setApifacturaList(List<Apifactura> apifacturaList) {
		this.apifacturaList = apifacturaList;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public List<DetallePago> getDetalleList() {
		return detalleList;
	}

	public void setDetalleList(List<DetallePago> detalleList) {
		this.detalleList = detalleList;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getDescripcionpago() {
		return descripcionpago;
	}

	public void setDescripcionpago(String descripcionpago) {
		this.descripcionpago = descripcionpago;
	}

	public String getCodigoCB() {
		return codigoCB;
	}

	public void setCodigoCB(String codigoCB) {
		this.codigoCB = codigoCB;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	

}
