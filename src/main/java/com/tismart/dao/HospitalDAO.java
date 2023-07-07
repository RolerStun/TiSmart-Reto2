package com.tismart.dao;

import java.util.List;

import com.tismart.model.Condicion;
import com.tismart.model.Distrito;
import com.tismart.model.Gerente;
import com.tismart.model.Hospital;
import com.tismart.model.Sede;

public interface HospitalDAO {
	
	//Lista de hospitales y filtrado por nombre y sede
	public List<Hospital> listaHospitales(String hospital, Long sede);
	
	//Agregar hospital
	public void nuevoHospital(Hospital hospital);
	
	//Modificar hospital
	public void modificarHospital(Hospital hospital);
	
	//Eliminar hospital
	public void eliminarHospital(Hospital hospital);
	
	//Metodo para llenar los selectormenus
	public List<Distrito> listarDistritos();
	public List<Sede> listarSedes();
	public List<Gerente> listarGerentes();
	public List<Condicion> listarCondiciones();
	
	//Obtener datos por Id
	public Distrito findDistritoById(Long id);
	public Sede findSedeById(Long id);
	public Gerente findGerenteById(Long id);
	public Condicion findCondicionById(Long id);
	
}
