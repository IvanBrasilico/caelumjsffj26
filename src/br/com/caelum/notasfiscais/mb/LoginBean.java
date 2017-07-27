package br.com.caelum.notasfiscais.mb;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Named @ViewScoped
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
	
	@Inject
	private Event<Usuario> eventoLogin;
	
	private String erro;
	

	public String loga() throws ValidatorException {
		String result = "";
		Boolean existe = false;
		setErro("");
		if (this.getUsuario() != null){
			existe = dao.existe(getUsuario());
		}
		if (existe) {
			usuarioLogado.logar(this.usuario);
			eventoLogin.fire(this.usuario);
			result = "produto?faces-redirect=true";
		} else {
			this.usuario = new Usuario();
			System.out.println("Login inválido");
			setErro("Login inválido");
			//throw new ValidatorException(new FacesMessage("Login inválido!!!"));
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

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

}
