package minimarketdemo.controller.generarpago;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.CabeceraPago;
import minimarketdemo.model.core.entities.Cuentabancaria;
import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.core.managers.ManagerCuentasB;
import minimarketdemo.model.generarpagos.managers.ManagerGenerarPagos;
import minimarketdemo.model.pagos.managers.ManagerCabeceraPagos;
import minimarketdemo.model.seguridades.dtos.LoginDTO;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;

@Named
@SessionScoped
public class BeanGenerarPago implements Serializable {

	@EJB
	ManagerGenerarPagos mGenerarPagos;
	@EJB 
	ManagerCabeceraPagos mCabecera;
	@EJB
	ManagerSeguridades mseguridades;
	@EJB
	ManagerCuentasB mCuentas;

	private int idProveedor;
	private int idFactura;
	private List<DetallePago> detalleList;
	private List<Apifactura> apifacturaList;
	private List<Cuentabancaria>  cuentasList;
	private Apifactura factura;
	private List<Integer> proveedoresList;
	private BigDecimal valorApagar;
	private BigDecimal saldo;
	
	//Variables para crear la cabecera
	private String descripcionpago;
	private String codigoCB;
	private Integer codigoUsuario;
	
	LoginDTO dto;

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
		
		//probar
		cuentasList = mCuentas.findAllByCodigoCB("CTA-BAN-0001");
		saldo = cuentasList.get(0).getSaldocb();
		
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
	
	public void listenerObtenerSaldo() {
		cuentasList = mCuentas.findAllByCodigoCB(codigoCB);
		
		saldo= cuentasList.get(0).getSaldocb();
		
		
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
			mCabecera.crearCabeceraPagos(codigoCB, descripcionpago, mseguridades.llevar);
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

	public LoginDTO getDto() {
		return dto;
	}

	public void setDto(LoginDTO dto) {
		this.dto = dto;
	}

	public List<Cuentabancaria> getCuentasList() {
		return cuentasList;
	}

	public void setCuentasList(List<Cuentabancaria> cuentasList) {
		this.cuentasList = cuentasList;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	

}
