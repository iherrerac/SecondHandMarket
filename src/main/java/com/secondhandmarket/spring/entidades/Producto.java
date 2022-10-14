package com.secondhandmarket.spring.entidades;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Producto {
	
	@Id@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	@NotBlank(message = "Nombre requerido")
	@Size(min = 2, max = 25)
	private String nombre;
	
	@Column(nullable = false)
	@NotNull( message = "Precio requerido") 
	@Min (1)
	@Digits(integer= 12, fraction=2)
	private float precio;
	
	@Column(nullable = true)
	//@NotBlank(message = "Introduce URL imagen")//Para cadenas, ingnora espacios en blanco al final
	private String imagen;
	
	@ManyToOne //Todo producto tendra un usuario
	@JoinColumn(nullable = false)
	@NotNull
	@Valid
	private Usuario propietario;
	
	@ManyToOne // Un producto podra estar comprado mediante una compra
	@JoinColumn(nullable = true)
	@Valid
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
