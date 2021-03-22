package minimarketdemo.model.generarpagos.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.CabeceraPago;
import minimarketdemo.model.core.entities.DetallePago;
import minimarketdemo.model.core.entities.ProveedorFacturas;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerGenerarPagos
 */
@Stateless
@LocalBean
public class ManagerGenerarPagos {

	@EJB
	ManagerDAO mDAO;

	public ManagerGenerarPagos() {
		// TODO Auto-generated constructor stub
	}

	public List<Apifactura> findAllApiFacturas() {
		return mDAO.findAll(Apifactura.class);
	}

	public List<Integer> findAllIdProveedores() {
		List<Apifactura> list = findAllApiFacturas();
		List<Integer> aux = new ArrayList<Integer>();

		for (Apifactura a : list) {
			if (!aux.contains(a.getIdProveedor()) && a.getTotal().compareTo(BigDecimal.ZERO) != 0) {
				aux.add(a.getIdProveedor());
			}
		}

		return aux;
	}

	public List<Apifactura> findAllByIdProveedor(Integer id) {
		return mDAO.findWhere(Apifactura.class, "o.idProveedor='" + id + "' and o.total!='0'", null);
	}

	public Apifactura findByIdApiFactura(Integer id) throws Exception {
		return (Apifactura) mDAO.findById(Apifactura.class, id);
	}

	public List<Apifactura> eliminarIdLista(List<Apifactura> lista, Integer id) {
		List<Apifactura> aux = lista;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getIdFactura().equals(id)) {
				aux.remove(i);
			}
		}

		return aux;
	}

	public void insertarDetallePago(CabeceraPago cabecera, BigDecimal valorFactura, BigDecimal valorApagar,
			Apifactura factura) throws Exception {

		Apifactura fac = new Apifactura();
		fac.setIdProveedor(factura.getIdProveedor());
		fac.setTotal(valorFactura.subtract(valorApagar));
		fac.setIdFactura(factura.getIdFactura());
		mDAO.actualizar(fac);
		DetallePago detalle = new DetallePago();
		detalle.setApifactura(factura);
		detalle.setCabeceraPago(cabecera);
		detalle.setValorapagar(valorApagar);
		detalle.setValorfactura(valorFactura);
		mDAO.insertar(detalle);
	}
}
