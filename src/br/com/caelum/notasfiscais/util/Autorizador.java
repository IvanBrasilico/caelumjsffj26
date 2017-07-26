package br.com.caelum.notasfiscais.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	//@Inject
	//private FacesContext context;
	
	@Override
	public void afterPhase(PhaseEvent ev) {
		FacesContext ctx;
		ctx = ev.getFacesContext();
		if (ctx.getViewRoot().getViewId().equals("/login.xhtml")) return;
		if (!usuarioLogado.isLogado()){
			NavigationHandler handler = ctx.getApplication().getNavigationHandler();
			handler.handleNavigation(ctx, null, "login?faces-redirect=true");
			ctx.renderResponse();
		} 
	}

	@Override
	public void beforePhase(PhaseEvent ev) {
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
