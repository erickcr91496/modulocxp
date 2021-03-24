package minimarketdemo.controller.pagos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;

import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.pago.dto.DTOReporteEstadoCuentaProv;
import minimarketdemo.model.pagos.managers.Cabecera;
import minimarketdemo.model.pagos.managers.ManagerCabeceraPagos;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;

@Named
@SessionScoped
public class BeanCabeceraPagos implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcionpago;
	private String codigoCB;
	private Integer codigoUsuario;
	
	
	@EJB 
	ManagerCabeceraPagos mCabecera;

	@EJB 
	ManagerSeguridades mUsuario;
	
	private List<Cabecera> cabeceraList;
	
	private List<SegUsuario> listaUsuarios;
	
	private List<DTOReporteEstadoCuentaProv> listaEstadoCuentaProv;
	public BeanCabeceraPagos() {

	}
	
	@PostConstruct
	public void inicializar() {
		try {
			System.out.println("Bean Cabcera!!!");
			
			cabeceraList = mCabecera.finAllCabecera();

			listaUsuarios= mUsuario.findAllUsuarios();
			listaEstadoCuentaProv = mCabecera.findDataEstadoCuentaProveedor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}


	
	public void actionListenerCrearCabeceraPago() {
		try {
			mCabecera.crearCabeceraPagos(codigoCB, descripcionpago, codigoUsuario);
			JSFUtil.crearMensajeINFO("Cabecera creada");
			// actualizamos la lista de cuentas
			//cabeceraList= mCabecera.finAllCabecera();
		
			descripcionpago="";

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	
	
	
	
	public List<SegUsuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<SegUsuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getCodigoCB() {
		return codigoCB;
	}

	public void setCodigoCB(String codigoCB) {
		this.codigoCB = codigoCB;
	}

	public List<Cabecera> getCabeceraList() {
		return cabeceraList;
	}

	public void setCabeceraList(List<Cabecera> cabeceraList) {
		this.cabeceraList = cabeceraList;
	}


	public String getDescripcionpago() {
		return descripcionpago;
	}

	public void setDescripcionpago(String descripcionpago) {
		this.descripcionpago = descripcionpago;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	public List<DTOReporteEstadoCuentaProv> getListaEstadoCuentaProv() {
		return listaEstadoCuentaProv;
	}

	public void setListaEstadoCuentaProv(List<DTOReporteEstadoCuentaProv> listaEstadoCuentaProv) {
		this.listaEstadoCuentaProv = listaEstadoCuentaProv;
	}


	
	


}
