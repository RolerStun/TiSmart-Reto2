package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provincia")
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProvincia;

	@Column(name = "descProvincia", nullable = false)
	private String descProvincia;

	@Column(name = "fechaRegistro", nullable = false)
	private Date fechaRegistro;

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getDescProvincia() {
		return descProvincia;
	}

	public void setDescProvincia(String descProvincia) {
		this.descProvincia = descProvincia;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Provincia(String descProvincia, Date fechaRegistro) {
		super();
		this.descProvincia = descProvincia;
		this.fechaRegistro = fechaRegistro;
	}

	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
	}

}
