package minimarketdemo.model.core.managers;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import minimarketdemo.model.core.entities.Cuentabancaria;


/**
 * Session Bean implementation class ManagerCuentasB
 */
@Stateless
@LocalBean
public class ManagerCuentasB {
@PersistenceContext
private EntityManager em;

	@EJB
	ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerCuentasB() {
    }

    
    
    public List<Cuentabancaria> findAllCuentasBancarias(){
    	return em.createNamedQuery("Cuentabancaria.findAll", Cuentabancaria.class).getResultList();
    }
       
    
    public void crearCuentaBancaria(String nombre ,String tipo_cuenta,String entidad_bancaria, String descripcion,
    		BigDecimal saldoCB, boolean estadoCB ) throws Exception {
    	
    	Cuentabancaria c = new Cuentabancaria();
    	

    	c.setPropietariocb(nombre);
    	c.setTipocb(tipo_cuenta);
    	c.setEntidadbancariacb(descripcion);
    	c.setDescripcioncb(descripcion);
    	c.setSaldocb(saldoCB);
    	c.setEstadocb(estadoCB);
    	
    	mDAO.insertar(c);
    }
    
    public void eliminarCuentaBancaria(String codigo) throws Exception{
    	Cuentabancaria c=em.find(Cuentabancaria.class, codigo);
    	if(c==null)
    		throw new Exception("No existe la cuenta indicada: "+codigo);
    	em.remove(c);
    			
    }
    
    public void actualizarCuentaBancaria(Cuentabancaria cuenta) throws Exception {
    	Cuentabancaria c=em.find(Cuentabancaria.class, cuenta.getCodigocb());
    	
    	if(c==null) 
    		throw new Exception("No existe la cuenta indicada ("+cuenta.getCodigocb()+")");
    	
    	c.setPropietariocb(cuenta.getPropietariocb());
    	c.setTipocb(cuenta.getTipocb());
    	c.setEntidadbancariacb(cuenta.getEntidadbancariacb());
    	c.setDescripcioncb(cuenta.getDescripcioncb());
    	c.setSaldocb(cuenta.getSaldocb());
    	
    	em.merge(c);
    	}
    
    
    
}
