package minimarketdemo.model.core.managers;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import minimarketdemo.model.core.entities.CuentaBancaria;
import minimarketdemo.model.core.entities.MdProvCb;
import minimarketdemo.model.core.entities.Proveedor;

/**
 * Session Bean implementation class ManagerCuentaProv
 */
@Stateless
@LocalBean
public class ManagerCuentaProv {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerCuentaProv() {
        // TODO Auto-generated constructor stub
    }

    public List<MdProvCb> findAllCuentasProv(){
    	return em.createNamedQuery("MdProvCb.findAll", MdProvCb.class).getResultList();
    }
    
    
    public void crearCuentasProv(String codigoCB, Integer codigoProv, BigDecimal saldo ) {
    	
    	MdProvCb m=new MdProvCb();
    	CuentaBancaria c= em.find(CuentaBancaria.class, codigoCB);
    	Proveedor p= em.find(Proveedor.class, codigoProv);
    	
    	m.setCuentaBancaria(c);
    	m.setProveedor(p);
    	m.setSaldoPagar(saldo);
    
    	em.persist(m);
    }
    
}
