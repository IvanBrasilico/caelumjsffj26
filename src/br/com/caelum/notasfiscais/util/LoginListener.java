package br.com.caelum.notasfiscais.util;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.modelo.Usuario;

public class LoginListener {
	
	@Inject @EmailComercial
	private String email;
	
	public void onLogin(@Observes Usuario usuario){
		System.out.println("Usuario "+usuario.getLogin()+" logou.");
		System.out.println("Disparar email para "+email);
		
	}

}
