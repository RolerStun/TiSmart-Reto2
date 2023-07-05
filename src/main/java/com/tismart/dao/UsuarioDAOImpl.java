package com.tismart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.tismart.model.JPAUtil;
import com.tismart.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	EntityManager entity = JPAUtil.getEntityManagedFactory().createEntityManager();

	@Override
	public Usuario getUsuarioByUsername(String username) {
		Query query = entity.createQuery("SELECT u FROM Usuario u WHERE u.username = :username");
		query.setParameter("username", username);
		List<Usuario> usuarios = query.getResultList();
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}

}
