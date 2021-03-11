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
    
    public List<Proveedor> findAllProveedores(){
    	return em.createNamedQuery("Proveedor.findAll", Proveedor.class).getResultList();
    }
    
    
    public void crearCuentaBancaria(String codigo, String nombre, String tipo_cuenta,String entidad_bancaria, String descripcion,
    		BigDecimal saldoCB, boolean estadoCB ) {
    	
    	CuentaBancaria c = new CuentaBancaria();
    	c.setCodigocb(codigo);
    	c.setNombre(nombre);
    	c.setTipoCuenta(tipo_cuenta);
    	c.setEntidadBancaria(descripcion);
    	c.setDescripcion(descripcion);
    	c.setSaldocb(saldoCB);
    	c.setEstadocb(estadoCB);
    	
    	em.persist(c);
    }
    
}
