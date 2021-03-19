package minimarketdemo.api.rest.auditoria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.managers.DTOPagoProveedor;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.utils.ModelUtil;

@RequestScoped
@Path("auditoria")
@Produces("application/json")
@Consumes("application/json")
public class ServicioRESTAuditoria {
	@EJB
	private ManagerAuditoria mAuditoria;
	@EJB
	ManagerDAO mDAO;

	@GET
	@Path(value = "bitacora")
	public List<AudBitacora> findBitacoraAyer() {
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(new Date(), -1), new Date());
	}

	@POST
	@Path(value = "bitacora")
	public String crearPistaAuditoria() {
		System.out.println("Se ha creado una nueva pista en auditoria");
		return "{\"mensaje\":\"Se ha creado ok.\"}";
	}

	@GET
	@Path(value = "pagos/proveedor")
	public List<DTOPagoProveedor> devolver() {

		List<Apifactura> list = mDAO.findAll(Apifactura.class);
		List<DTOPagoProveedor> pagos = new ArrayList<DTOPagoProveedor>();
		
		List provs = new ArrayList();
		
		List totales = new ArrayList();
		
		for(Apifactura p:list) {
		
			provs.add(p.getIdProveedor());
		}
		
		provs=(List) provs.stream().distinct().collect(Collectors.toList());
		
		BigDecimal total= new BigDecimal(0);
		BigDecimal cero= new BigDecimal(0);
		int cont=0;
		double saldo=0;
		
		for(int i=0; i<provs.size(); i++) {
			for(int j=0; j<list.size();j++) {
				
				if(provs.get(i)== list.get(j).getIdProveedor()){
					total=total.add(list.get(j).getTotal());
					
				}
						
			}
			totales.add(total);
			total=cero;		
		}
		
		
		for (int i=0; i<provs.size(); i++) {
		

			DTOPagoProveedor pag = new DTOPagoProveedor((int)provs.get(i), (BigDecimal)totales.get(i));
			pagos.add(pag);
			
		}
		
		
		return pagos;
	}

}
