package minimarketdemo.model.core.managers;

import java.math.BigDecimal;
import java.util.List;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import javax.ejb.EJB;
=======
>>>>>>> d183b02 (conflictos 1)
>>>>>>> 56f47a5 (conflictos 1)
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

<<<<<<< HEAD
import minimarketdemo.model.core.entities.CuentaBancaria;
import minimarketdemo.model.core.entities.Proveedor;
=======
<<<<<<< HEAD
import minimarketdemo.model.core.entities.Cuentabancaria;

=======
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


	@EJB
	ManagerDAO mDAO;

    /**
     * Default constructor. 
     */
    public ManagerCuentasB() {
    }

    
    
    public List<CuentaBancaria> findAllCuentasBancarias(){
    	return em.createNamedQuery("CuentaBancaria.findAll", CuentaBancaria.class).getResultList();
    public List<Cuentabancaria> findAllCuentasBancarias(){
    	return em.createNamedQuery("Cuentabancaria.findAll", Cuentabancaria.class).getResultList();
    }
       


    public List<Proveedor> findAllProveedores(){
    	return em.createNamedQuery("Proveedor.findAll", Proveedor.class).getResultList();
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

    	Cuentabancaria c=em.find(Cuentabancaria.class, codigo);
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
