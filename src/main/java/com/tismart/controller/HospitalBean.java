package com.tismart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.tismart.dao.HospitalDAO;
import com.tismart.dao.HospitalDAOImpl;
import com.tismart.model.Condicion;
import com.tismart.model.Distrito;
import com.tismart.model.Gerente;
import com.tismart.model.Hospital;
import com.tismart.model.Sede;

@ManagedBean(name = "hospitalBean")
@RequestScoped
public class HospitalBean {

	private List<Hospital> listar;
	private String searchKeyword;
	private int count;
	private Hospital hospital;
	private Sede selectedSede;

	private List<SelectItem> listDistritos;
	private List<SelectItem> listSedes;
	private List<SelectItem> listGerentes;
	private List<SelectItem> listCondiciones;

	public HospitalBean() {
		hospital = new Hospital();
		selectedSede = new Sede();
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public Sede getSelectedSede() {
		return selectedSede;
	}

	public void setSelectedSede(Sede selectedSede) {
		this.selectedSede = selectedSede;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Hospital> getListar() {
		HospitalDAO hDao = new HospitalDAOImpl();
		Long idSede = selectedSede.getIdSede();
		listar = hDao.listaHospitales(searchKeyword, idSede);
		count = listar.size();
		return listar;
	}

	public void prepararNuevoHospital(ActionEvent actionEvent) {
		hospital = new Hospital();
	}

	public void nuevoHospital() {
		HospitalDAO hDao = new HospitalDAOImpl();
		hDao.nuevoHospital(hospital);
		hospital = new Hospital();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El hospital se guardo correctamente"));
	}

	public void modificarHospital() {
		HospitalDAO hDao = new HospitalDAOImpl();
		hDao.modificarHospital(hospital);
		hospital = new Hospital();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El hospital se actualizo correctamente"));
	}

	public void eliminarHospital() {
		HospitalDAO hDao = new HospitalDAOImpl();
		hDao.eliminarHospital(hospital);
		hospital = new Hospital();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El hospital se elimino correctamente"));
	}

	public List<SelectItem> getListDistritos() {
		this.listDistritos = new ArrayList<SelectItem>();
		HospitalDAO hDao = new HospitalDAOImpl();
		List<Distrito> d = hDao.listarDistritos();
		listDistritos.clear();

		for (Distrito distrito : d) {
			SelectItem distritoItem = new SelectItem(distrito.getIdDistrito(), distrito.getDescDistrito());
			this.listDistritos.add(distritoItem);
		}

		return listDistritos;
	}

	public List<SelectItem> getListSedes() {
		this.listSedes = new ArrayList<SelectItem>();
		HospitalDAO hDao = new HospitalDAOImpl();
		List<Sede> s = hDao.listarSedes();
		listSedes.clear();

		for (Sede sede : s) {
			SelectItem sedeItem = new SelectItem(sede.getIdSede(), sede.getDescSede());
			this.listSedes.add(sedeItem);
		}

		return listSedes;
	}

	public List<SelectItem> getListGerentes() {
		this.listGerentes = new ArrayList<SelectItem>();
		HospitalDAO hDao = new HospitalDAOImpl();
		List<Gerente> g = hDao.listarGerentes();
		listGerentes.clear();

		for (Gerente gerente : g) {
			SelectItem gerenteItem = new SelectItem(gerente.getIdGerente(), gerente.getDescGerente());
			this.listGerentes.add(gerenteItem);
		}

		return listGerentes;
	}

	public List<SelectItem> getListCondiciones() {
		this.listCondiciones = new ArrayList<SelectItem>();
		HospitalDAO hDao = new HospitalDAOImpl();
		List<Condicion> c = hDao.listarCondiciones();
		listCondiciones.clear();

		for (Condicion condicion : c) {
			SelectItem condicionItem = new SelectItem(condicion.getIdCondicion(), condicion.getDescCondicion());
			this.listCondiciones.add(condicionItem);
		}

		return listCondiciones;
	}

	public void cancelar() {
		hospital = new Hospital();
	}
}
