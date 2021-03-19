package minimarketdemo.controller.pagos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.pagos.managers.Cabecera;
import minimarketdemo.model.pagos.managers.ManagerCabeceraPagos;

@Named
@SessionScoped
public class BeanCabeceraPagos implements Serializable {

	
	
	@EJB 
	ManagerCabeceraPagos mCabecera;
	
	private List<Cabecera> cabeceraList;
	
	public BeanCabeceraPagos() {

	}
	
	@PostConstruct
	public void inicializar() {
		try {
			System.out.println("Bean Cabcera!!!");
			
			cabeceraList = mCabecera.finAllCabecera();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Cabecera> getCabeceraList() {
		return cabeceraList;
	}

	public void setCabeceraList(List<Cabecera> cabeceraList) {
		this.cabeceraList = cabeceraList;
	}
	
	
	
	

}
