package com.tismart.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.tismart.model.Condicion;
import com.tismart.model.Distrito;
import com.tismart.model.Gerente;
import com.tismart.model.Hospital;
import com.tismart.model.JPAUtil;
import com.tismart.model.Sede;

public class HospitalDAOImpl implements HospitalDAO {

	EntityManager entity = JPAUtil.getEntityManagedFactory().createEntityManager();

	@Override
	public List<Hospital> listaHospitales(String hospital, Long sede) {
		List<Hospital> listaHospitales = new ArrayList<>();
		entity.getTransaction().begin();

		try {
			StoredProcedureQuery query = entity.createStoredProcedureQuery("lista_hospitales");
			if (hospital != null) {
				query.registerStoredProcedureParameter("p_hospital", String.class, ParameterMode.IN);
				query.setParameter("p_hospital", hospital);
			}
			if (sede != null) {
				query.registerStoredProcedureParameter("p_sede", Long.class, ParameterMode.IN);
				query.setParameter("p_sede", sede);
			}
			query.registerStoredProcedureParameter("p_result", Class.class, ParameterMode.REF_CURSOR);
			query.execute();

			List<Object[]> results = query.getResultList();

			for (Object[] result : results) {
				Hospital h = new Hospital();
				h.setIdHospital(((BigDecimal) result[0]).longValue());
				h.setDistrito(findDistritoById(((BigDecimal) result[1]).longValue()));
				h.setNombre((String) result[2]);
				h.setAntiguedad(((BigDecimal) result[3]).longValue());
				h.setArea(((BigDecimal) result[4]).doubleValue());
				h.setSede((findSedeById(((BigDecimal) result[5]).longValue())));
				h.setGerente(findGerenteById(((BigDecimal) result[6]).longValue()));
				h.setCondicion(findCondicionById(((BigDecimal) result[7]).longValue()));
				h.setFechaRegistro((Date) result[8]);

				listaHospitales.add(h);
			}

			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}

		// JPAUtil.shutdown();

		return listaHospitales;
	}

	@Override
	public void nuevoHospital(Hospital hospital) {
		entity.getTransaction().begin();

		try {
			StoredProcedureQuery query = entity.createStoredProcedureQuery("insertar_hospital");
			query.registerStoredProcedureParameter("p_iddistrito", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_antiguedad", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_area", Double.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_idsede", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_idgerente", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_idcondicion", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_fecharegistro", Date.class, ParameterMode.IN);

			query.setParameter("p_iddistrito", hospital.getDistrito().getIdDistrito());
			query.setParameter("p_nombre", hospital.getNombre());
			query.setParameter("p_antiguedad", hospital.getAntiguedad());
			query.setParameter("p_area", hospital.getArea());
			query.setParameter("p_idsede", hospital.getSede().getIdSede());
			query.setParameter("p_idgerente", hospital.getGerente().getIdGerente());
			query.setParameter("p_idcondicion", hospital.getCondicion().getIdCondicion());
			query.setParameter("p_fecharegistro", hospital.getFechaRegistro());

			query.execute();

			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}

		// JPAUtil.shutdown();
	}

	@Override
	public void modificarHospital(Hospital hospital) {
		entity.getTransaction().begin();

		try {
			StoredProcedureQuery query = entity.createStoredProcedureQuery("actualizar_hospital");
			query.registerStoredProcedureParameter("p_idhospital", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_iddistrito", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_antiguedad", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_area", Double.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_idsede", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_idgerente", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_idcondicion", Long.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("p_fecharegistro", Date.class, ParameterMode.IN);

			query.setParameter("p_idhospital", hospital.getIdHospital());
			query.setParameter("p_iddistrito", hospital.getDistrito().getIdDistrito());
			query.setParameter("p_nombre", hospital.getNombre());
			query.setParameter("p_antiguedad", hospital.getAntiguedad());
			query.setParameter("p_area", hospital.getArea());
			query.setParameter("p_idsede", hospital.getSede().getIdSede());
			query.setParameter("p_idgerente", hospital.getGerente().getIdGerente());
			query.setParameter("p_idcondicion", hospital.getCondicion().getIdCondicion());
			query.setParameter("p_fecharegistro", hospital.getFechaRegistro());

			query.execute();
			
			entity.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			entity.getTransaction().rollback();
		}
	}

	@Override
	public void eliminarHospital(Hospital hospital) {
		entity.getTransaction().begin();

		try {
			StoredProcedureQuery query = entity.createStoredProcedureQuery("eliminar_hospital");
			query.registerStoredProcedureParameter("p_idhospital", Long.class, ParameterMode.IN);

			query.setParameter("p_idhospital", hospital.getIdHospital());

			query.execute();
			
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
	public Distrito findDistritoById(Long id) {
		Distrito distrito = new Distrito();
		distrito = entity.find(Distrito.class, id);

		return distrito;
	}

	@Override
	public Sede findSedeById(Long id) {
		Sede sede = new Sede();
		sede = entity.find(Sede.class, id);

		return sede;
	}

	@Override
	public Gerente findGerenteById(Long id) {
		Gerente gerente = new Gerente();
		gerente = entity.find(Gerente.class, id);

		return gerente;
	}

	@Override
	public Condicion findCondicionById(Long id) {
		Condicion condicion = new Condicion();
		condicion = entity.find(Condicion.class, id);

		return condicion;
	}

}
