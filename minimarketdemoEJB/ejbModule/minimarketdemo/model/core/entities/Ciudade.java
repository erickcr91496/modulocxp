package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ciudades database table.
 * 
 */
@Entity
@Table(name="ciudades")
@NamedQuery(name="Ciudade.findAll", query="SELECT c FROM Ciudade c")
public class Ciudade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ciudad", unique=true, nullable=false)
	private Integer idCiudad;

	@Column(nullable=false, length=100)
	private String descripcionciudad;

	@Column(nullable=false, length=50)
	private String nombreciudad;

	@Column(nullable=false)
	private Integer poblacion;

	public Ciudade() {
	}

	public Integer getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getDescripcionciudad() {
		return this.descripcionciudad;
	}

	public void setDescripcionciudad(String descripcionciudad) {
		this.descripcionciudad = descripcionciudad;
	}

	public String getNombreciudad() {
		return this.nombreciudad;
	}

	public void setNombreciudad(String nombreciudad) {
		this.nombreciudad = nombreciudad;
	}

	public Integer getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}

}