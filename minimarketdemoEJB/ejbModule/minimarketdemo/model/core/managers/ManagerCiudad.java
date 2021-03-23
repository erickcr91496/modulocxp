package minimarketdemo.model.core.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import minimarketdemo.model.core.entities.Ciudade;

/**
 * Session Bean implementation class ManagerCiudad
 */
@Stateless
@LocalBean
public class ManagerCiudad {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerCiudad() {
    	
    	
    }
	public List<Ciudade> findAllCiudades() {
		return em.createNamedQuery("Ciudade.findAll", Ciudade.class).getResultList();
		
	}
	
	public void crearCiudad (String nombre, String descripcion, Integer poblacion) {
		
		Ciudade c = new Ciudade();
		c.setNombreciudad(nombre);
		c.setDescripcionciudad(descripcion);
		c.setPoblacion(poblacion);
		
		em.merge(c);
		
	}
	
}
