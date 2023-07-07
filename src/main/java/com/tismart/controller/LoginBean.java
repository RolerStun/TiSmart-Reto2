package com.tismart.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.tismart.dao.UsuarioDAO;
import com.tismart.dao.UsuarioDAOImpl;
import com.tismart.model.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean loggedIn;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void login() {
		UsuarioDAO uDao = new UsuarioDAOImpl();
		Usuario usuario = uDao.getUsuarioByUsername(username);

		if (usuario != null && usuario.getPassword().equals(password)) {
			loggedIn = true;
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			try {
				externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales inválidas"));
		}
	}

	public void logout() {
		loggedIn = false;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}