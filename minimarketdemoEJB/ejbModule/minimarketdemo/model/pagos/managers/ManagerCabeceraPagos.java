package minimarketdemo.model.pagos.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.CabeceraPago;
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

}
