package com.secondhandmarket.spring.entidades;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;




@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
	@Id@GeneratedValue
	private long id;
	
	private String nombre;
	private String apellidos;
	private String avatar;
	private String email;
	private String password;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP) //Anotacion precisa em BD, fecha y hora
	private Date fechaAlta;

	public Usuario() {}

	public Usuario(String nombre, String apellidos, String avatar, String email, String password, Date fechaAlta) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.avatar = avatar;
		this.email = email;
		this.password = password;
		this.fechaAlta = fechaAlta;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, avatar, email, fechaAlta, id, nombre, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(avatar, other.avatar)
				&& Objects.equals(email, other.email) && Objects.equals(fechaAlta, other.fechaAlta) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", avatar=" + avatar
				+ ", email=" + email + ", password=" + password + ", fechaAlta=" + fechaAlta + "]";
	}
	
}
