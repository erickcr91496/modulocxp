package minimarketdemo.model.core.managers;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import minimarketdemo.model.core.entities.CuentaBancaria;
import minimarketdemo.model.core.entities.Proveedor;

/**
 * Session Bean implementation class ManagerCuentasB
 */
@Stateless
@LocalBean
public class ManagerCuentasB {
@PersistenceContext
private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerCuentasB() {
    }

    
    
    public List<CuentaBancaria> findAllCuentasBancarias(){
    	return em.createNamedQuery("CuentaBancaria.findAll", CuentaBancaria.class).getResultList();
    }
       
    
    public void crearCuentaBancaria( int condigoProv ,String nombre ,String tipo_cuenta,String entidad_bancaria, String descripcion,
    		BigDecimal saldoCB, boolean estadoCB ) {
    	
    	CuentaBancaria c = new CuentaBancaria();
    	
    	c.setCodigoprov(condigoProv);
    	c.setNombre(nombre);
    	c.setTipoCuenta(tipo_cuenta);
    	c.setEntidadBancaria(descripcion);
    	c.setDescripcion(descripcion);
    	c.setSaldocb(saldoCB);
    	c.setEstadocb(estadoCB);
    	
    	em.persist(c);
    }
    
    public void eliminarCuentaBancaria(String codigo) throws Exception{
    	CuentaBancaria c=em.find(CuentaBancaria.class, codigo);
    	if(c==null)
    		throw new Exception("No existe la cuenta indicada: "+codigo);
    	em.remove(c);
    			
    }
    
    public void actualizarCuentaBancaria(CuentaBancaria cuenta) throws Exception {
    	CuentaBancaria c=em.find(CuentaBancaria.class, cuenta.getCodigocb());
    	
    	if(c==null) 
    		throw new Exception("No existe la cuenta indicada ("+cuenta.getCodigocb()+")");
    	
    	c.setNombre(cuenta.getNombre());
    	c.setCodigoprov(cuenta.getCodigoprov());
    	c.setTipoCuenta(cuenta.getTipoCuenta());
    	c.setEntidadBancaria(cuenta.getEntidadBancaria());
    	c.setDescripcion(cuenta.getDescripcion());
    	c.setSaldocb(cuenta.getSaldocb());
    	
    	em.merge(c);
    	}
    
    
    
}
