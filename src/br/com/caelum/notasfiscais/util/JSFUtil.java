package br.com.caelum.notasfiscais.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;


@ApplicationScoped
public class JSFUtil {
	

	@Produces @RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	@Produces @RequestScoped
	public NavigationHandler getNavigationHandler() {
		return getFacesContext().getApplication().getNavigationHandler();
	}

}