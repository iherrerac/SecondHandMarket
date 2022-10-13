package com.secondhandmarket.spring.entidades;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Producto {
	
	@Id@GeneratedValue
	private long id;
	
	private String nombre;
	private float precio;
	private String imagen;
	
	@ManyToOne //Todo producto tendra un usuario
	private Usuario propietario;
	
	@ManyToOne // Un producto podra estar comprado mediante una compra
	private Compra compra;
	
	public Producto () {}

	public Producto(String nombre, float precio, String imagen, Usuario propietario) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
		this.propietario = propietario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compra, id, imagen, nombre, precio, propietario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(compra, other.compra) && id == other.id && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre)
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio)
				&& Objects.equals(propietario, other.propietario);
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", imagen=" + imagen
				+ ", propietario=" + propietario + ", compra=" + compra + "]";
	}
}
