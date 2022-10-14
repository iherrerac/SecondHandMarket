package com.secondhandmarket.spring.entidades;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Compra {
	
	@Id @GeneratedValue
	private long id;
	
	@CreatedDate // Activar posibilidad de auditoria
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCompra;
	
	@ManyToOne //Asociacion con el usuario propietario de esta compra
	@Column(nullable = false)
	private Usuario propietario;

	public Compra(Usuario propietario) {
		super();
		this.propietario = propietario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaCompra, id, propietario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(fechaCompra, other.fechaCompra) && id == other.id
				&& Objects.equals(propietario, other.propietario);
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", fechaCompra=" + fechaCompra + ", propietario=" + propietario + "]";
	}
	
	

}
