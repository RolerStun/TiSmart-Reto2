package com.tismart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hospital")
public class Hospital implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8528279833473608209L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHospital;

	@ManyToOne
	@JoinColumn(name = "idDistrito")
	private Distrito distrito;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "antiguedad", nullable = false)
	private Long antiguedad;

	@Column(name = "area", nullable = false)
	private Double area;

	@ManyToOne
	@JoinColumn(name = "idSede")
	private Sede sede;

	@ManyToOne
	@JoinColumn(name = "idGerente")
	private Gerente gerente;

	@ManyToOne
	@JoinColumn(name = "idCondicion")
	private Condicion condicion;

	@Column(name = "fechaRegistro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	public Hospital() {
		distrito = new Distrito();
		sede = new Sede();
		gerente = new Gerente();
		condicion = new Condicion();
	}

	public Long getIdHospital() {
		return idHospital;
	}

	public void setIdHospital(Long idHospital) {
		this.idHospital = idHospital;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(Long antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public Condicion getCondicion() {
		return condicion;
	}

	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Hospital(Distrito distrito, String nombre, Long antiguedad, Double area, Sede sede, Gerente gerente,
			Condicion condicion, Date fechaRegistro) {
		this.distrito = distrito;
		this.nombre = nombre;
		this.antiguedad = antiguedad;
		this.area = area;
		this.sede = sede;
		this.gerente = gerente;
		this.condicion = condicion;
		this.fechaRegistro = fechaRegistro;
	}

}