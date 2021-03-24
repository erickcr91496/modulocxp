package minimarketdemo.controller.generarpago;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.api.rest.proveedores.ServiceRESTproveedores;
import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.pagos.BeanCabeceraPagos;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.CabeceraPago;
import minimarketdemo.model.core.entities.Cuentabancaria;
import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.core.managers.DTOPagoProveedor;
import minimarketdemo.model.core.managers.ManagerCuentasB;
import minimarketdemo.model.generarpagos.managers.ManagerGenerarPagos;
import minimarketdemo.model.pagos.managers.Cabecera;
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

	BeanCabeceraPagos bCabecera;

	Cabecera cabecera;

	private int idProveedor;
	private int idFactura;
	private List<DetallePago> detalleList;
	private List<Apifactura> apifacturaList;
	private List<Cuentabancaria> cuentasList;
	private Apifactura factura;
	private List<Integer> proveedoresList;
	List<DTOPagoProveedor> pagos = new ArrayList<DTOPagoProveedor>();

	private BigDecimal valorApagar;

	

	// variable para maximo a pagar
	double maximoPago;

	// variables para el calculo de totales
	private BigDecimal valorApagarOpe;
	private BigDecimal valorFactura;
	private BigDecimal totalDeuda;

	// saldo que se tiene en la Cuenta Bancaria
	private BigDecimal saldo;

	// Variables para crear la cabecera
	private String descripcionpago;
	private String codigoCB;
	private Integer codigoUsuario;
	
	BeanCabeceraPagos refreshCabecera;
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

		codigoCB = "";
		descripcionpago = "";

		valorApagar = new BigDecimal(0.00);
		maximoPago = 0;

		// Inicializar variabes para gurdar valores del pago
		valorApagarOpe = new BigDecimal(0.00);
		valorFactura = new BigDecimal(0.00);
		totalDeuda = new BigDecimal(0.00);

		// probar
		cuentasList = mCuentas.findAllCuentasBancarias();
		saldo = cuentasList.get(0).getSaldocb();
		
		//listar del api sumando
		
		try {
			
			//pagos= service.devolver();
			pagos= mGenerarPagos.devolver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(" lo que llega !!! "+pagos.get(0).getIdProveedor());
			//System.out.println(" lo que llega !!! "+pagos.get(0).getTotal());
		}
		
		
		//try {
		
			
			//listaPagosProveedores= service.devolver();
			
	//	} catch (Exception e) {
			// TODO: handle exception
		//	System.out.println("esta llegando datos: "+listaPagosProveedores.get(0).getTotal());
			//e.getStackTrace();
		//}
		
		
		//listafinal= service.devolver();

	}

	public void listenerListarFacturasByIdProv() {
		apifacturaList = mGenerarPagos.findAllByIdProveedor(idProveedor);
		idFactura = apifacturaList.get(0).getIdFactura();
		detalleList = new ArrayList<DetallePago>();

		// Reiniciar variabes para gurdar valores del pago
		valorApagarOpe = new BigDecimal(0.00);
		valorFactura = new BigDecimal(0.00);
		totalDeuda = new BigDecimal(0.00);
		try {
			factura = mGenerarPagos.findByIdApiFactura(idFactura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listenerObtenerSaldo() {
		cuentasList = mCuentas.findAllByCodigoCB(codigoCB);

		saldo = cuentasList.get(0).getSaldocb();

	}

	public void listenerFactura() {
		try {
			factura = mGenerarPagos.findByIdApiFactura(idFactura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// metodo para agreagr a una lista los detalles
	// nota: no se guarda en la BDD
	public void actionListenerInsertarListaDetalle() {

		System.out.println("actionListenerInsertarListaDetalle");

		DetallePago dp = new DetallePago();
		dp.setApifactura(factura);
		dp.setCabeceraPago(null);
		dp.setValorfactura((BigDecimal) (factura.getTotal()));
		dp.setValorapagar((BigDecimal) valorApagar);

		BigDecimal subTotal = saldo.subtract(valorApagarOpe);
		if (subTotal.compareTo(valorFactura) == 1) {

			// agregar en la lista detalles para mostar en la tabla
			detalleList.add(dp);

			// sumatoria de totales en el detalle
			valorFactura = valorFactura.add(dp.getValorfactura());
			valorApagarOpe = valorApagarOpe.add(dp.getValorapagar());
			totalDeuda = valorFactura.subtract(valorApagarOpe);

			// Elimar de la lista la factura seleccionada y dar un nuevo id a la factura
			apifacturaList = mGenerarPagos.eliminarIdLista(apifacturaList, factura.getIdFactura());
			idFactura = apifacturaList.get(0).getIdFactura();
		} else {
			JSFUtil.crearMensajeWARN("Saldo insificiente");
		}

		try {
			factura = mGenerarPagos.findByIdApiFactura(idFactura);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public double mayorValoraPagar() {
		BigDecimal aux = saldo.subtract(valorApagarOpe);
		BigDecimal fact = (BigDecimal) factura.getTotal();
		if (aux.compareTo(fact) == 1) {
			return fact.doubleValue();
		} else {

			return aux.doubleValue();
		}
	}

	/// metodo para insertar en el detalle de la BDD
	public void actionListenerInsertarDetalles() {
		try {
			if (detalleList.size() != 0) {
				mCabecera.crearCabeceraPagos(codigoCB, descripcionpago, mseguridades.llevar);
				List<CabeceraPago> listcP = mCabecera.findAllCabeceraPago();
				CabeceraPago cP = listcP.get(listcP.size() - 1);
				for (DetallePago listDe : detalleList) {
					mGenerarPagos.insertarDetallePago(cP, listDe.getValorfactura(), listDe.getValorapagar(),
							listDe.getApifactura());
				}
				
				//actualizar la cuenta bancariaSS
				Cuentabancaria cb = mCuentas.findByIdCuenta(codigoCB);
				cb.setSaldocb(saldo.subtract(valorApagarOpe));				
				mCuentas.actualizarCuentaBancaria(cb);

				// crear la cabecera para mostrar en la vista detalles
				cabecera = new Cabecera();
				cabecera.setCuentabancaria(cb);
				cabecera.setDescripcion(descripcionpago);
				cabecera.setFecha(new Date());
				cabecera.setIdProveedor(factura.getIdProveedor());
				cabecera.setIdUsuario(mseguridades.llevar);
				cabecera.setNombreCajero(mseguridades.findByIdSegUsuario(mseguridades.llevar).getNombres());
				cabecera.setNroPago(cP.getCodigopago());

				JSFUtil.crearMensajeINFO("Se ha generado el pago");
			} else {
				JSFUtil.crearMensajeWARN("Ingrese los valores a pagar");
			}

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public Cabecera getCabecera() {
		return cabecera;
	}

	public void setCabecera(Cabecera cabecera) {
		this.cabecera = cabecera;
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

	public BigDecimal getValorFactura() {
		return valorFactura;
	}

	public void setValorFactura(BigDecimal valorFactura) {
		this.valorFactura = valorFactura;
	}

	public BigDecimal getTotalDeuda() {
		return totalDeuda;
	}

	public void setTotalDeuda(BigDecimal totalDeuda) {
		this.totalDeuda = totalDeuda;
	}

	public BigDecimal getValorApagarOpe() {
		return valorApagarOpe;
	}

	public void setValorApagarOpe(BigDecimal valorApagarOpe) {
		this.valorApagarOpe = valorApagarOpe;
	}


	public List<DTOPagoProveedor> getPagos() {
		return pagos;
	}

	public void setPagos(List<DTOPagoProveedor> pagos) {
		this.pagos = pagos;
	}

	

}
