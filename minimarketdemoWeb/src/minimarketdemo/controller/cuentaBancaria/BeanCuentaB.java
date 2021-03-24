package minimarketdemo.controller.cuentaBancaria;
 
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.api.rest.proveedores.ServiceRESTproveedores;
import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.Cuentabancaria;
import minimarketdemo.model.core.managers.DTOPagoProveedor;
import minimarketdemo.model.core.managers.ManagerCuentasB;




import minimarketdemo.model.core.managers.ManagerProveedorFacturas;

@Named
@SessionScoped
public class BeanCuentaB implements Serializable {

	private String nombre;
	private String tipoCuenta;
	private String entidadBancaria;
	private String descripcion;
	private BigDecimal saldo;
	private Boolean estado;
	private Cuentabancaria cuentaEdit;


	

	private List<Cuentabancaria> listaCuentasBancarias;

	@EJB
	private ManagerCuentasB mCuentas;
	@EJB
	private ManagerProveedorFacturas mProveedor;



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanCuentaB() {
	}

	@PostConstruct
	public void inicializar() {
		listaCuentasBancarias = mCuentas.findAllCuentasBancarias();		
	}

	public void actionListenerCrearCuenta() {
		try {
			System.out.println("actionListenerCrearCuenta!!!!!!!!!");
			mCuentas.crearCuentaBancaria(nombre, tipoCuenta, entidadBancaria, descripcion, saldo, true);
			JSFUtil.crearMensajeINFO("Cuenta creada");
			// actualizamos la lista de cuentas
			listaCuentasBancarias = mCuentas.findAllCuentasBancarias();
			nombre = "";
			tipoCuenta = "";
			entidadBancaria = "";
			descripcion = "";

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerSeleccionarCuenta(Cuentabancaria cuenta) {
		cuentaEdit = cuenta;
		System.out.println("cuenta seleccionado: " + cuentaEdit.getCodigocb());

	}

	public void actionListenerActualizarCuenta() {
		try {
			mCuentas.actualizarCuentaBancaria(cuentaEdit);
			listaCuentasBancarias = mCuentas.findAllCuentasBancarias();
			JSFUtil.crearMensajeINFO("Cuenta actualizada");

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarCuenta(String codigo) {
		try {
			mCuentas.eliminarCuentaBancaria(codigo);
			JSFUtil.crearMensajeINFO("la cuenta se ha eliminado");
			listaCuentasBancarias = mCuentas.findAllCuentasBancarias();

		} catch (Exception e) {

			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionSeleccionarEdicionCuenta(Cuentabancaria cuentab) {
		cuentaEdit = cuentab;
		System.out.println("cuenta seleccionado: " + cuentaEdit.getCodigocb());
		return "cuentas_edicion";
	}
	

	

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Cuentabancaria getCuentaEdit() {
		return cuentaEdit;
	}

	public void setCuentaEdit(Cuentabancaria cuentaEdit) {
		this.cuentaEdit = cuentaEdit;
	}

	

	public List<Cuentabancaria> getListaCuentasBancarias() {
		return listaCuentasBancarias;
	}

	public void setListaCuentasBancarias(List<Cuentabancaria> listaCuentasBancarias) {
		this.listaCuentasBancarias = listaCuentasBancarias;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getEntidadBancaria() {
		return entidadBancaria;
	}

	public void setEntidadBancaria(String entidadBancaria) {
		this.entidadBancaria = entidadBancaria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

