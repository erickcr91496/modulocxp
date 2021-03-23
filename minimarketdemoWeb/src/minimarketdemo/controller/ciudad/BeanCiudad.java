package minimarketdemo.controller.ciudad;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.Ciudade;
import minimarketdemo.model.core.managers.ManagerCiudad;

@Named
@SessionScoped
public class BeanCiudad implements Serializable {

	@EJB 
	private ManagerCiudad mCiudad;
	
	private String nombre;
	private String descripcion;
	private Integer poblacion;
	
	private List<Ciudade> listaCiudades;
	
	public BeanCiudad() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void inicializar() {
		listaCiudades = mCiudad.findAllCiudades();
		
	}
	
	public void actionListenerCrearCuenta() {
		try {
			mCiudad.crearCiudad(nombre, descripcion, poblacion);
			JSFUtil.crearMensajeINFO("Ciudad creada");
			listaCiudades = mCiudad.findAllCiudades();
			nombre="";
			descripcion="";
			poblacion=0;

		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();		}
	}
	
	
	
	public ManagerCiudad getmCiudad() {
		return mCiudad;
	}
	public void setmCiudad(ManagerCiudad mCiudad) {
		this.mCiudad = mCiudad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}
	public List<Ciudade> getListaCiudades() {
		return listaCiudades;
	}
	public void setListaCiudades(List<Ciudade> listaCiudades) {
		this.listaCiudades = listaCiudades;
	}
	

}
