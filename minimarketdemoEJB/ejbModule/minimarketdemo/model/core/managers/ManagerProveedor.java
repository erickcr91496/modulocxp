package minimarketdemo.model.core.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import minimarketdemo.model.core.entities.Proveedor;
/**
 * Session Bean implementation class ManagerProveedor
 */
@Stateless
@LocalBean
public class ManagerProveedor {

    /**
     * Default constructor. 
     */
    public ManagerProveedor() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Proveedor> ListarProveedores(){
		System.out.println("Funcioooooooooooooona");
		Client client = ClientBuilder.newClient().register(new Proveedor());
		List<Proveedor> pro = client
		.target("http://localhost:8080/modComprasWeb/apirest/proveedores/listado")
		.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Proveedor>>() {
		});
		
		for(Proveedor p: pro) {
			 
			System.out.println(p.getNombre());
			System.out.println(p.getCodigoprov());
			 
			}
		
		return pro;
	}
    
    public Proveedor proveedorById(int id){
    	System.out.println("http://localhost:8080/modComprasWeb/apirest/proveedores/proveedroid/"+id);
    	String Url = "http://localhost:8080/modComprasWeb/apirest/proveedores/proveedroid/"+id;
    	Client cliente = ClientBuilder.newClient();
    	Proveedor pro = cliente.target(Url)
    	.request(MediaType.APPLICATION_JSON_TYPE).get(Proveedor.class);
		
		return pro;
	}

}
