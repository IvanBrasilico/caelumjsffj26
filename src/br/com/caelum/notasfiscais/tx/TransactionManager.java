package br.com.caelum.notasfiscais.tx;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor @Transacional
public class TransactionManager {
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object execute(InvocationContext ctx){
		Object result = null;
		manager.getTransaction().begin();
		try {
			result = ctx.proceed();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
		manager.getTransaction().commit();
		System.out.println("INTERCEPTOR RULES");                                  
		return result;
	}
	

}
