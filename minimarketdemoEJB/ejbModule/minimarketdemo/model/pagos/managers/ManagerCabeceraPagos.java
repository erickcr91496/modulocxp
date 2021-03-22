package minimarketdemo.model.pagos.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.CabeceraPago;
import minimarketdemo.model.core.entities.Cuentabancaria;
import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.managers.ManagerProveedorFacturas;

/**
 * Session Bean implementation class ManagerCabeceraPagos
 */
@Stateless
@LocalBean
public class ManagerCabeceraPagos {
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	ManagerDAO mDAO;
	@EJB
	ManagerProveedorFacturas mFacturas;
	
	
	public ManagerCabeceraPagos() {

	}

	public List<Cabecera> finAllCabecera() throws Exception {

		
		System.out.println("ManagerCabecera!!!!!!");
		mFacturas.actualizarApiFacturas();
		
		List<CabeceraPago> cbPago = mDAO.findAll(CabeceraPago.class);
		List<Cabecera> cb = new ArrayList<Cabecera>();

		for (CabeceraPago p : cbPago) {
			
			System.out.println(p.getDescripcionpago());
			
			SegUsuario cajero = new SegUsuario();			
			Cabecera c =  new Cabecera();
					
			c.setCuentabancaria(p.getCuentabancaria());
			c.setDescripcion(p.getDescripcionpago());
			c.setFecha(p.getFechapago());
			cajero = (SegUsuario) mDAO.findById(SegUsuario.class, p.getCodigousuario());
			
			c.setIdProveedor(p.getDetallePagos().get(0).getApifactura().getIdProveedor());
			
			c.setIdUsuario(cajero.getIdSegUsuario());
			c.setNombreCajero(cajero.getNombres());
			c.setNroPago(p.getCodigopago());
			cb.add(c);
		}

		return cb;

	}
	
	

    public void crearCabeceraPagos(String codigocb,String descripcionPago, Integer codigoUsuario ) throws Exception {

    	
    	CabeceraPago ca= new CabeceraPago();
   
    	ca.setDescripcionpago(descripcionPago);
    	ca.setFechapago(new Date());
    	
    	Cuentabancaria u= em.find(Cuentabancaria.class, codigocb);
    	if(u==null)
    		throw new Exception("No existe la cuenta indicada");
    	ca.setCuentabancaria(u);
    	
    	
    	ca.setCodigousuario(codigoUsuario);
    	
    	
    	
    	em.persist(ca);
    }
    
    public List<CabeceraPago> findAllCabeceraPago(){
    	return mDAO.findAll(CabeceraPago.class);
    }
    
    
    
    

}
