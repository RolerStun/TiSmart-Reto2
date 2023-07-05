package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gerente")
public class Gerente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGerente;

	@Column(name = "descGerente", nullable = false)
	private String descGerente;

	@Column(name = "fechaRegistro", nullable = false)
	private Date fechaRegistro;

	public Long getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}

	public String getDescGerente() {
		return descGerente;
	}

	public void setDescGerente(String descGerente) {
		this.descGerente = descGerente;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Gerente(String descGerente, Date fechaRegistro) {
		super();
		this.descGerente = descGerente;
		this.fechaRegistro = fechaRegistro;
	}

	public Gerente() {
		super();
		// TODO Auto-generated constructor stub
	}

}
