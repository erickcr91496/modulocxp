package minimarketdemo.controller.cuentaBancaria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.CuentaBancaria;
import minimarketdemo.model.core.entities.MdProvCb;
import minimarketdemo.model.core.entities.Proveedor;
import minimarketdemo.model.core.managers.ManagerCuentaProv;

@Named
@SessionScoped
public class BeanCuentaProv implements Serializable {

	private String codigoCB;
	private Integer codigoProv;
	private BigDecimal saldo;
	private MdProvCb cuentaProvEdit;

	private List<MdProvCb> listaCuentaProv;

	@EJB
	private ManagerCuentaProv mCuentasProv;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanCuentaProv() {

	}

	@PostConstruct
	public void inicializar() {
		listaCuentaProv = mCuentasProv.findAllCuentasProv();

	}

	public void actionListenerCrearCuentasProv() {
		try {
			mCuentasProv.crearCuentasProv(codigoCB, codigoProv, saldo);
			JSFUtil.crearMensajeINFO("Cuenta creada");
			// actualizamos la lista de cuentas
			listaCuentaProv = mCuentasProv.findAllCuentasProv();

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	
	public void actionListenerSeleccionarCuentaProv(MdProvCb cuentaProv) {
		cuentaProvEdit=cuentaProv;
		System.out.println("cuenta seleccionado: "+cuentaProvEdit.getCodigomd());
		
	}
	
	public String actionSeleccionarEdicionDatoPago(MdProvCb cuentaProv) {
		cuentaProvEdit=cuentaProv;
		System.out.println("cuenta seleccionado: "+cuentaProvEdit.getCodigomd());
		return "datos_pago_edicion";
	}
	
	public void actionListenerActualizarCuentaProv() {
		try {
			mCuentasProv.actualizarCuentaProv(cuentaProvEdit);
			listaCuentaProv= mCuentasProv.findAllCuentasProv();
			JSFUtil.crearMensajeINFO("Cuenta actualizada");
			
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarCuentasProvs(Integer codigo) {
		try {
			mCuentasProv.eliminarCuentasProv(codigo);
			JSFUtil.crearMensajeINFO("la cuenta se ha eliminado");
			listaCuentaProv= mCuentasProv.findAllCuentasProv();
			
		} catch (Exception e) {
			
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	
	public MdProvCb getCuentaProvEdit() {
		return cuentaProvEdit;
	}

	public void setCuentaProvEdit(MdProvCb cuentaProvEdit) {
		this.cuentaProvEdit = cuentaProvEdit;
	}

	public String getCodigoCB() {
		return codigoCB;
	}

	public void setCodigoCB(String codigoCB) {
		this.codigoCB = codigoCB;
	}

	public Integer getCodigoProv() {
		return codigoProv;
	}

	public void setCodigoProv(Integer codigoProv) {
		this.codigoProv = codigoProv;
	}

	public List<MdProvCb> getListaCuentaProv() {
		return listaCuentaProv;
	}

	public void setListaCuentaProv(List<MdProvCb> listaCuentaProv) {
		this.listaCuentaProv = listaCuentaProv;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
