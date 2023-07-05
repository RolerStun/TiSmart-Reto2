package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sede")
public class Sede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSede;

	@Column(name = "descSede", nullable = false)
	private String descSede;

	@Column(name = "fechaRegistro", nullable = false)
	private Date fechaRegistro;

	public Long getIdSede() {
		return idSede;
	}

	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	public String getDescSede() {
		return descSede;
	}

	public void setDescSede(String descSede) {
		this.descSede = descSede;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Sede(String descSede, Date fechaRegistro) {
		super();
		this.descSede = descSede;
		this.fechaRegistro = fechaRegistro;
	}

	public Sede() {
		super();
		// TODO Auto-generated constructor stub
	}

}
