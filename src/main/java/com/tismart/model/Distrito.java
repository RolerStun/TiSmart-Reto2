package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "distrito")
public class Distrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDistrito;

	@ManyToOne
	@JoinColumn(name = "idProvincia")
	private Provincia provincia;

	@Column(name = "descDistrito", nullable = false)
	private String descDistrito;

	@Column(name = "fechaRegistro", nullable = false)
	private Date fechaRegistro;

	public Long getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Long idDistrito) {
		this.idDistrito = idDistrito;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getDescDistrito() {
		return descDistrito;
	}

	public void setDescDistrito(String descDistrito) {
		this.descDistrito = descDistrito;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Distrito(Provincia provincia, String descDistrito, Date fechaRegistro) {
		super();
		this.provincia = provincia;
		this.descDistrito = descDistrito;
		this.fechaRegistro = fechaRegistro;
	}

	public Distrito() {
		super();
		// TODO Auto-generated constructor stub
	}

}
