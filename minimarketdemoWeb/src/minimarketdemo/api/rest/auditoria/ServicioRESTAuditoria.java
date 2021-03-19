package minimarketdemo.api.rest.auditoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.core.utils.ModelUtil;
import minimarketdemo.model.pago.dto.DTOPagoProveedores;

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
	public List<AudBitacora> findBitacoraAyer(){
		return mAuditoria.findBitacoraByFecha(ModelUtil.addDays(new Date(), -1), new Date());
	}
	
	@POST
	@Path(value = "bitacora")
	public String crearPistaAuditoria() {
		System.out.println("Se ha creado una nueva pista en auditoria");
		return "{\"mensaje\":\"Se ha creado ok.\"}";
	}
}
