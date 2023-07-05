package com.tismart.dao;

import com.tismart.model.Usuario;

public interface UsuarioDAO {
	public Usuario getUsuarioByUsername(String username);
}
