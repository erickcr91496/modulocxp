package minimarketdemo.api.rest.ciudad;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.core.entities.AudBitacora;
import minimarketdemo.model.core.entities.Ciudade;
import minimarketdemo.model.core.managers.ManagerCiudad;
import minimarketdemo.model.core.utils.ModelUtil;

@RequestScoped
@Path("ciudad")
@Produces("application/json")
@Consumes("application/json")
public class ServicioRestCiudad {

	  @EJB
	  private ManagerCiudad mCiudad;

		@GET
		@Path(value = "ciudades")
		public List<Ciudade> findAllCiudad() {
			return mCiudad.findAllCiudades();
		}}
