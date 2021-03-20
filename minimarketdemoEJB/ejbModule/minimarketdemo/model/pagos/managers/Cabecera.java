package minimarketdemo.model.pagos.managers;

import java.io.Serializable;
import java.util.Date;


import minimarketdemo.model.core.entities.Cuentabancaria;

public class Cabecera implements Serializable {

	private String nroPago;

	private String descripcion;

	private Date fecha;

	private Integer idUsuario;
	
	private String nombreCajero;

	private Cuentabancaria cuentabancaria;
	
	private Integer idProveedor;

	public String getNroPago() {
		return nroPago;
	}

	public void setNroPago(String nroPago) {
		this.nroPago = nroPago;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreCajero() {
		return nombreCajero;
	}

	public void setNombreCajero(String nombreCajero) {
		this.nombreCajero = nombreCajero;
	}



	public Cuentabancaria getCuentabancaria() {
		return cuentabancaria;
	}

	public void setCuentabancaria(Cuentabancaria cuentabancaria) {
		this.cuentabancaria = cuentabancaria;
	}

	public Integer getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}


	
	

}