package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "condicion")
public class Condicion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCondicion;

	@Column(name = "descCondicion", nullable = false)
	private String descCondicion;

	@Column(name = "fechaRegistro", nullable = false)
	private Date fechaRegistro;

	public Long getIdCondicion() {
		return idCondicion;
	}

	public void setIdCondicion(Long idCondicion) {
		this.idCondicion = idCondicion;
	}

	public String getDescCondicion() {
		return descCondicion;
	}

	public void setDescCondicion(String descCondicion) {
		this.descCondicion = descCondicion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Condicion(String descCondicion, Date fechaRegistro) {
		super();
		this.descCondicion = descCondicion;
		this.fechaRegistro = fechaRegistro;
	}

	public Condicion() {
		super();
		// TODO Auto-generated constructor stub
	}

}