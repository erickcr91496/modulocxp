package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pagos database table.
 * 
 */
@Entity
@Table(name="pagos")
@NamedQuery(name="Pago.findAll", query="SELECT p FROM Pago p")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nro_pago")
	private String nroPago;

	private String descripcion;

	private Boolean estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="id_usuario")
	private Integer idUsuario;

	//bi-directional many-to-one association to MdProvCb
	@ManyToOne
	@JoinColumn(name="codigomd")
	private MdProvCb mdProvCb;

	public Pago() {
	}

	public String getNroPago() {
		return this.nroPago;
	}

	public void setNroPago(String nroPago) {
		this.nroPago = nroPago;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public MdProvCb getMdProvCb() {
		return this.mdProvCb;
	}

	public void setMdProvCb(MdProvCb mdProvCb) {
		this.mdProvCb = mdProvCb;
	}

}