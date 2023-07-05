package com.tismart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.tismart.model.Condicion;
import com.tismart.model.Distrito;
import com.tismart.model.Gerente;
import com.tismart.model.Hospital;
import com.tismart.model.JPAUtil;
import com.tismart.model.Sede;

public class HospitalDAOImpl implements HospitalDAO {

	EntityManager entity = JPAUtil.getEntityManagedFactory().createEntityManager();

	@Override
	public List<Hospital> listaHospitales() {
		List<Hospital> listaHospitales = null;
		entity.getTransaction().begin();

		String q = "SELECT h FROM Hospital h";

		try {
			listaHospitales = entity.createQuery(q).getResultList();
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}

		return listaHospitales;
	}

	@Override
	public void nuevoHospital(Hospital hospital) {
		try {
			entity.getTransaction().begin();
			entity.persist(hospital);
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}
	}

	@Override
	public void modificarHospital(Hospital hospital) {
		try {
			entity.getTransaction().begin();
			entity.merge(hospital);
			/*
			 * if (hospital.getIdHospital() != null) { Hospital existingHospital =
			 * entity.find(Hospital.class, hospital.getIdHospital());
			 * 
			 * if (existingHospital != null) {
			 * existingHospital.setNombre(hospital.getNombre());
			 * existingHospital.setAntiguedad(hospital.getAntiguedad());
			 * existingHospital.setArea(hospital.getArea());
			 * existingHospital.setDistrito(hospital.getDistrito());
			 * existingHospital.setSede(hospital.getSede());
			 * existingHospital.setGerente(hospital.getGerente());
			 * existingHospital.setCondicion(hospital.getCondicion());
			 * existingHospital.setFechaRegistro(hospital.getFechaRegistro()); } }
			 */
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}
	}

	@Override
	public void eliminarHospital(Hospital hospital) {
		try {
			entity.getTransaction().begin();
			hospital = entity.find(Hospital.class, hospital.getIdHospital());
			entity.remove(hospital);
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}
	}

	@Override
	public List<Distrito> listarDistritos() {
		List<Distrito> listaDistritos = null;
		entity.getTransaction().begin();

		String q = "SELECT d FROM Distrito d";

		try {
			listaDistritos = entity.createQuery(q).getResultList();
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}

		return listaDistritos;
	}

	@Override
	public List<Sede> listarSedes() {
		List<Sede> listarSedes = null;
		entity.getTransaction().begin();

		String q = "SELECT s FROM Sede s";

		try {
			listarSedes = entity.createQuery(q).getResultList();
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}

		return listarSedes;
	}

	@Override
	public List<Gerente> listarGerentes() {
		List<Gerente> listarGerentes = null;
		entity.getTransaction().begin();

		String q = "SELECT g FROM Gerente g";

		try {
			listarGerentes = entity.createQuery(q).getResultList();
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}

		return listarGerentes;
	}

	@Override
	public List<Condicion> listarCondiciones() {
		List<Condicion> listarCondiciones = null;
		entity.getTransaction().begin();

		String q = "SELECT c FROM Condicion c";

		try {
			listarCondiciones = entity.createQuery(q).getResultList();
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}

		return listarCondiciones;
	}

	@Override
	public List<Hospital> filtrarPorNombre(String searchKeyword) {
		String jpql = "SELECT h FROM Hospital h WHERE h.nombre LIKE :keyword";
		TypedQuery<Hospital> query = entity.createQuery(jpql, Hospital.class);
		query.setParameter("keyword", "%" + searchKeyword + "%");
		List<Hospital> hospitals = query.getResultList();

		return hospitals;
	}

	@Override
	public List<Hospital> filtrarPorSede(Long idSede) {
		String query = "SELECT h FROM Hospital h WHERE h.sede.idSede = :idSede";
		TypedQuery<Hospital> typedQuery = entity.createQuery(query, Hospital.class);
		typedQuery.setParameter("idSede", idSede);
		return typedQuery.getResultList();
	}

	/*
	 * @Override public List<Hospital> filtrarPorHospital(String searchKeyword) {
	 * List<Hospital> listaHospitales = null; entity.getTransaction().begin();
	 * 
	 * String q = "SELECT h FROM Hospital h"; listaHospitales =
	 * entity.createQuery(q).getResultList(); List<Hospital> itemsFiltrados = new
	 * ArrayList<>();
	 * 
	 * // Ejemplo de filtrado por hospital (suponiendo que tienes una lista de items
	 * // llamada "items") for (Hospital item : listaHospitales) { if
	 * (item.getNombre().contains(searchKeyword)) { itemsFiltrados.add(item); } }
	 * 
	 * return itemsFiltrados; }
	 * 
	 * @Override public List<Hospital> filtrarPorSede(String selectedSede) {
	 * List<Hospital> listaHospitales = null; entity.getTransaction().begin();
	 * 
	 * String q = "SELECT h FROM Hospital h"; listaHospitales =
	 * entity.createQuery(q).getResultList(); List<Hospital> itemsFiltrados = new
	 * ArrayList<>();
	 * 
	 * System.out.println(listaHospitales); System.out.println(selectedSede);
	 * 
	 * for (Hospital item : listaHospitales) { if
	 * (item.getSede().getDescSede().equals(selectedSede)) {
	 * itemsFiltrados.add(item); } }
	 * 
	 * return itemsFiltrados; }
	 */

}
