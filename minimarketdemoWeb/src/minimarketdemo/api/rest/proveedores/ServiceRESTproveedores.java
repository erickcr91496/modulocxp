package minimarketdemo.api.rest.proveedores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.managers.DTOPagoProveedor;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.managers.ManagerProveedorFacturas;

@RequestScoped
@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
public class ServiceRESTproveedores {

	@EJB
	ManagerDAO mDAO;
	@EJB
	ManagerProveedorFacturas mFacturas;
	
	@GET
	@Path(value = "pagos")
	public List<DTOPagoProveedor> devolver() throws Exception {
		mFacturas.actualizarApiFacturas();
		
		List<Apifactura> list = mDAO.findAll(Apifactura.class);
		List<DTOPagoProveedor> pagos = new ArrayList<DTOPagoProveedor>();

		List provs = new ArrayList();

		List totales = new ArrayList();

		for (Apifactura p : list) {

			provs.add(p.getIdProveedor());
		}

		provs = (List) provs.stream().distinct().collect(Collectors.toList());

		BigDecimal total = new BigDecimal(0);
		BigDecimal cero = new BigDecimal(0);
		int cont = 0;
		double saldo = 0;

		for (int i = 0; i < provs.size(); i++) {
			for (int j = 0; j < list.size(); j++) {

				if (provs.get(i) == list.get(j).getIdProveedor()) {
					total = total.add(list.get(j).getTotal());

				}

			}
			totales.add(total);
			total = cero;
		}

		for (int i = 0; i < provs.size(); i++) {

			DTOPagoProveedor pag = new DTOPagoProveedor((int) provs.get(i), (BigDecimal) totales.get(i));
			pagos.add(pag);

		}

		return pagos;
	}
}
