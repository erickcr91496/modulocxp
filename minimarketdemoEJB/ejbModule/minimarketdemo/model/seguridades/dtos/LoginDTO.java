package minimarketdemo.model.seguridades.dtos;

import java.util.ArrayList;
import java.util.List;

import minimarketdemo.model.core.entities.SegModulo;

public class LoginDTO {
	private int idSegUsuario;
	private String correo;
	private String codigo;
	private String nombre;
	private List<SegModulo> listaModulos;
	
	public LoginDTO() {
		listaModulos=new ArrayList<SegModulo>();
	}
	public int getIdSegUsuario() {
		return idSegUsuario;
	}
	public void setIdSegUsuario(int idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public List<SegModulo> getListaModulos() {
		return listaModulos;
	}
	public void setListaModulos(List<SegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
