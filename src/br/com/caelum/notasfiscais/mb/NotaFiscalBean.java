package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named @ViewScoped
public class NotaFiscalBean {
	
	
	private NotaFiscal nota = new NotaFiscal();
	
	private Long idProduto;
	private Item item = new Item();
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}


	private List<Item> itens;
	
	@Inject
	private NotaFiscalDao notaDao;
	@Inject
	private ProdutoDao produtoDao;
	
	
	public void grava(){

		notaDao.adiciona(this.nota);
			
	}
	
	public Double soma(){

		return this.nota.getItensSoma();
			
	}
	
	public void addItem(){
		Produto lproduto = produtoDao.buscaPorId(idProduto);
		item.setProduto(lproduto);
		item.setValorUnitario(lproduto.getPreco());
		
		nota.getItens().add(item);
		item.setNotaFiscal(nota);
		
		item = new Item();
		idProduto = null;
	}
	
	public void remove(Item pitem){
		nota.getItens().remove(pitem);
	}
	
	public  List<Item> getItens(){
		if (this.itens == null){
			System.out.println("Carregando itens...");
			this.itens = nota.getItens();
		}
		
		return this.itens;
	}


	public NotaFiscal getNota() {
		return nota;
	}


	public void setNota(NotaFiscal nota) {
		this.nota = nota;
	}


	public Long getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}



	
	
}
