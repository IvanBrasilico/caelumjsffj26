package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named @ConversationScoped
public class ProdutoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	@Inject
	private ProdutoDao dao;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	public void grava() {
		
		if ((produto.getId() == null) || (produto.getId() == 0)) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
	}
	
	
	public void cancela() {
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
	}
	
	public List<Produto> getProdutos(){
		
		if (this.produtos == null){
			System.out.println("Carregando produtos...");
			this.produtos = dao.listaTodos();
		}
		
		return this.produtos;
	}
	
	public void remove(Produto produto){
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	
	public double soma(){
		double soma = 0;
		for (Produto p:this.getProdutos()){
			if (p.getPreco() != null) {
				soma = soma + p.getPreco();
			}
		}
		return soma;
	}

}
