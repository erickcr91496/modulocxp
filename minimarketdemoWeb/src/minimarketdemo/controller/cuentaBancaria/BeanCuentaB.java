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
import minimarketdemo.model.core.entities.Proveedor;
import minimarketdemo.model.core.managers.ManagerCuentasB;

@Named
@SessionScoped
public class BeanCuentaB implements Serializable {

	private String codigoCB;
	private String nombre;
	private String tipoCuenta;
	private String entidadBancaria;
	private String descripcion;
	private BigDecimal saldo;
	private Boolean estado;
	
	
	private List<Proveedor> listaProveedores;
	private List<CuentaBancaria> listaCuentasBancarias;
	
	
	@EJB
	private ManagerCuentasB mCuentas;
	

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanCuentaB() {
	}

	@PostConstruct
	public void inicializar() {
		listaCuentasBancarias=mCuentas.findAllCuentasBancarias();
		listaProveedores= mCuentas.findAllProveedores();
	}
	
	public void actionListenerCrearCuenta() {
		try {
			mCuentas.crearCuentaBancaria(codigoCB, nombre, tipoCuenta, entidadBancaria, descripcion, saldo, true);
			JSFUtil.crearMensajeINFO("Cuenta creada");
			//actualizamos la lista de cuentas
			listaCuentasBancarias= mCuentas.findAllCuentasBancarias();
			codigoCB="";
			nombre="";
			tipoCuenta="";
			entidadBancaria="";
			descripcion="";
			
			
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	

	
	public List<Proveedor> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public List<CuentaBancaria> getListaCuentasBancarias() {
		return listaCuentasBancarias;
	}

	public void setListaCuentasBancarias(List<CuentaBancaria> listaCuentasBancarias) {
		this.listaCuentasBancarias = listaCuentasBancarias;
	}

	public String getCodigoCB() {
		return codigoCB;
	}

	public void setCodigoCB(String codigoCB) {
		this.codigoCB = codigoCB;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
