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
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.managers.ManagerCuentasB;
import minimarketdemo.model.core.managers.ManagerProveedor;

@Named
@SessionScoped
public class BeanCuentaB implements Serializable {

	private int codiProv;
	private String codigoCB;
	private String tipoCuenta;
	private String entidadBancaria;
	private String descripcion;
	private BigDecimal saldo;
	private Boolean estado;
	private CuentaBancaria cuentaEdit;

	private List<Proveedor> listaProveedores;
	private List<CuentaBancaria> listaCuentasBancarias;

	@EJB
	private ManagerCuentasB mCuentas;
	@EJB
	private ManagerProveedor mProveedor;

	private Proveedor provee;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BeanCuentaB() {
	}

	@PostConstruct
	public void inicializar() {
		listaCuentasBancarias = mCuentas.findAllCuentasBancarias();
		listaProveedores = mProveedor.ListarProveedores();
		provee = new Proveedor();

	}

	public void actionListenerCrearCuenta() {
		try {

			mCuentas.crearCuentaBancaria(nombre, tipoCuenta, entidadBancaria, descripcion, saldo, true);
			JSFUtil.crearMensajeINFO("Cuenta creada");
			//actualizamos la lista de cuentas
			listaCuentasBancarias= mCuentas.findAllCuentasBancarias();
			nombre="";
			tipoCuenta="";
			entidadBancaria="";
			descripcion="";
			
			

			System.out.println("actionListenerCrearCuenta!!!!!!!!!");
			provee = mProveedor.proveedorById(codiProv);
			System.out.println(provee.getNombre());
			mCuentas.crearCuentaBancaria(provee.getCodigoprov(), provee.getNombre(), tipoCuenta, entidadBancaria,
					descripcion, saldo, true);
			JSFUtil.crearMensajeINFO("Cuenta creada");
			// actualizamos la lista de cuentas
			listaCuentasBancarias = mCuentas.findAllCuentasBancarias();
			codigoCB = "";
			tipoCuenta = "";
			entidadBancaria = "";
			descripcion = "";

			provee = new Proveedor();

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerSeleccionarCuenta(CuentaBancaria cuenta) {
		cuentaEdit = cuenta;
		System.out.println("cuenta seleccionado: " + cuentaEdit.getCodigocb());

	}

	public void actionListenerActualizarCuenta() {
		try {
			provee = mProveedor.proveedorById(codiProv);
			cuentaEdit.setCodigoprov(provee.getCodigoprov());
			cuentaEdit.setNombre(provee.getNombre());
			mCuentas.actualizarCuentaBancaria(cuentaEdit);
			listaCuentasBancarias = mCuentas.findAllCuentasBancarias();
			JSFUtil.crearMensajeINFO("Cuenta actualizada");

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionCuenta(CuentaBancaria cuentab) {
		cuentaEdit=cuentab;
		System.out.println("cuenta seleccionado: "+cuentaEdit.getCodigocb());
		return "cuentas_edicion";
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

	public String actionSeleccionarEdicionCuenta(CuentaBancaria cuentab) {
		cuentaEdit = cuentab;
		System.out.println("cuenta seleccionado: " + cuentaEdit.getCodigocb());
		return "cuentas_edicion";
	}
	

	public String getCodigoCB() {
		return codigoCB;
	}

	public void setCodigoCB(String codigoCB) {
		this.codigoCB = codigoCB;
	}

	public int getCodiProv() {
		return codiProv;
	}

	public void setCodiProv(int codiProv) {
		this.codiProv = codiProv;
	}

	public Proveedor getProvee() {
		return provee;
	}

	public void setProvee(Proveedor provee) {
		this.provee = provee;
	}

	public CuentaBancaria getCuentaEdit() {
		return cuentaEdit;
	}

	public void setCuentaEdit(CuentaBancaria cuentaEdit) {
		this.cuentaEdit = cuentaEdit;
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
