package minimarketdemo.model.pagos.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.pago.dto.DTOReporteEstadoCuentaProv;

/**
 * Session Bean implementation class ManagerDetallesPagos
 */
@Stateless
@LocalBean
public class ManagerDetallesPagos {

	@EJB
	ManagerDAO mDAO;
	@EJB
	ManagerDetallesPagos mDetalles;
	DetallePago dp;
	@PersistenceContext

	EntityManager em;
	
	public ManagerDetallesPagos() {
		
	}
	
	//public List<Detalles> findDetallesByPago(Integer codigopago) throws Exception{
		
//		Query q = dp.createQuery("Select d from DetallePago d where d.detalle.codigopago=",Detalles.class);
//		q.setParameter("codigopago",codigopago);
//		return q.getResultList();
//	}
	
	
		//System.out.println("ManagerDetalles!!!!!!!!!!");
		//mDAO.findWhere(DetallePago.class, "d.codigoPago=", null);
		
		//List<DetallePago> dPago = mDAO.findWhere(DetallePago.class, "o.codigopago='PAG-PROV-0001", null);
		//List<Detalles> dt = new ArrayList<Detalles>();
		
		//for (DetallePago dp : dPago) {
		//	System.out.println(dp.getIddetalle());
		//	Detalles d = new Detalles();
		//	d.setCodigoPago(dp.getCabeceraPago().getCodigopago());
		//	d.setIddetalle(dp.getIddetalle());
		//	d.setValorapagar(dp.getValorapagar());
		//	d.setValorfactura(dp.getValorfactura());
		//}
		
		public List<DetallePago> BuscarporIdProveedor(String codigopago) {
			return mDAO.findWhere(DetallePago.class, "o.cabeceraPago.codigopago='"+codigopago+"'", null);
			
		}
	



}
