package com.tismart.model;

import java.util.Date;

public class HospitalDTO {

	private Long id;
	private Long idDitrito;
	private String distrito;
	private String nombre;
	private Long antiguedad;
	private Double area;
	private Long idSede;
	private String sede;
	private Long idGerente;
	private String gerente;
	private Long idCondicion;
	private String condicion;
	private Date fecha;

	public Long getIdDitrito() {
		return idDitrito;
	}

	public void setIdDitrito(Long idDitrito) {
		this.idDitrito = idDitrito;
	}

	public Long getIdSede() {
		return idSede;
	}

	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	public Long getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(Long idGerente) {
		this.idGerente = idGerente;
	}

	public Long getIdCondicion() {
		return idCondicion;
	}

	public void setIdCondicion(Long idCondicion) {
		this.idCondicion = idCondicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getGerente() {
		return gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
