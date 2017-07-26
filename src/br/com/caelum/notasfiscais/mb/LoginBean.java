package br.com.caelum.notasfiscais.mb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named @RequestScoped
public class LoginBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public String loga(){
		String result = "login?faces-redirect=true";
		Boolean existe = false;
		if (this.getUsuario() != null){
			existe = dao.existe(getUsuario());
		}
		if (existe) {
			usuarioLogado.logar(this.usuario);
			result = "produto?faces-redirect=true";
		} else {
			this.usuario = new Usuario();
			usuarioLogado.logout();
		}
		return result;
	}
	
	public String logout(){
		this.usuario = new Usuario();
		usuarioLogado.logout();
		return "login?faces-redirect=true";
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
