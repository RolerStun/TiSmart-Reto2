package com.tismart.dao;

import java.util.List;

import com.tismart.model.Condicion;
import com.tismart.model.Distrito;
import com.tismart.model.Gerente;
import com.tismart.model.Hospital;
import com.tismart.model.Sede;

public interface HospitalDAO {

	public List<Hospital> listaHospitales();
	public void nuevoHospital(Hospital hospital);
	public void modificarHospital(Hospital hospital);
	public void eliminarHospital(Hospital hospital);
	
	//metodo para llenar los selectormenus
	public List<Distrito> listarDistritos();
	public List<Sede> listarSedes();
	public List<Gerente> listarGerentes();
	public List<Condicion> listarCondiciones();
	
	//filtrado
	/*public List<Hospital> filtrarPorHospital(String searchKeyword);
	public List<Hospital> filtrarPorSede(String selectedSede);*/
	public List<Hospital> filtrarPorNombre(String searchKeyword);
	public List<Hospital> filtrarPorSede(Long idSede);
	
}
