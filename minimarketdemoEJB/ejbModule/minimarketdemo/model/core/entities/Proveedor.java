package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoprov;

	private String correo;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to MdProvCb
	@OneToMany(mappedBy="proveedor")
	private List<MdProvCb> mdProvCbs;

	public Proveedor() {
	}

	public Integer getCodigoprov() {
		return this.codigoprov;
	}

	public void setCodigoprov(Integer codigoprov) {
		this.codigoprov = codigoprov;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<MdProvCb> getMdProvCbs() {
		return this.mdProvCbs;
	}

	public void setMdProvCbs(List<MdProvCb> mdProvCbs) {
		this.mdProvCbs = mdProvCbs;
	}

	public MdProvCb addMdProvCb(MdProvCb mdProvCb) {
		getMdProvCbs().add(mdProvCb);
		mdProvCb.setProveedor(this);

		return mdProvCb;
	}

	public MdProvCb removeMdProvCb(MdProvCb mdProvCb) {
		getMdProvCbs().remove(mdProvCb);
		mdProvCb.setProveedor(null);

		return mdProvCb;
	}

}