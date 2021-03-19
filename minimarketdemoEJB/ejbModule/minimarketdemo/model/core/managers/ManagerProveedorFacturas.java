package minimarketdemo.model.core.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.sun.tools.javac.util.DefinedBy.Api;

import minimarketdemo.model.core.entities.Apifactura;
import minimarketdemo.model.core.entities.ProveedorFacturas;
import minimarketdemo.model.pagos.managers.ManagerCabeceraPagos;
/**
 * Session Bean implementation class ManagerProveedor
 */
@Stateless
@LocalBean
public class ManagerProveedorFacturas {

    /**
     * Default constructor. 
     */
    public ManagerProveedorFacturas() {
        // TODO Auto-generated constructor stub
    }
    
    @EJB
    ManagerDAO mDAO;
    
    String Url = "http://grupo2.j.layershift.co.uk/comprasWeb/api/";
    
    public List<ProveedorFacturas> ListarProveedoresFacturas(){
		System.out.println("Funcioooooooooooooona");
		Client client = ClientBuilder.newClient().register(new ProveedorFacturas());
		List<ProveedorFacturas> pro = client
		.target(Url+"facturas")
		.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ProveedorFacturas>>() {
		});
		
		for(ProveedorFacturas p: pro) {
			 
			System.out.println(p.getId_proveedor());
			System.out.println(p.getId_factura());
			System.out.println(p.getFecha());
			}
		
		return pro;
	}
    
    public void actualizarApiFacturas() throws Exception {
    	List<ProveedorFacturas> list = ListarProveedoresFacturas();
    	List<Apifactura> listFacturas = mDAO.findAll(Apifactura.class);
    	
    	for (ProveedorFacturas p : list) {
    		int cont = 0;
    		Apifactura api = new Apifactura();
    		api.setIdFactura(p.getId_factura());
    		api.setIdProveedor(p.getId_proveedor());
    		api.setTotal(p.getTotal());
    		
    		
    		for (Apifactura fact : listFacturas) {
    			System.out.println("Pregunta  " +fact.getIdFactura() + " == " +api.getIdFactura() + " " + cont);
				if(fact.getIdFactura().equals(api.getIdFactura())) {
					cont++;
					break;
				}
			}   		
    		if(cont == 0) {
    			mDAO.insertar(api);
    		}  				
		}
    
    }

}
