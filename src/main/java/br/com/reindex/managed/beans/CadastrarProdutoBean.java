package br.com.reindex.managed.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.reindex.domain.Dim_Produto;
import br.com.reindex.enums.Tipo_Produto;
import br.com.reindex.repositories.ProdutoService;
import br.com.reindex.suri.framework.controller.ControllerSupport;
import br.com.reindex.util.Util;

@Component
@RequestScoped
@ManagedBean(name = "cadastrarProdutoBean")
public class CadastrarProdutoBean extends ControllerSupport implements
		Serializable {

	private static final long serialVersionUID = -2264940220491346834L;

	private Dim_Produto produto = new Dim_Produto();

	@Inject
	@Autowired
	private ProdutoService produtoDAO;

	private List<Dim_Produto> todosProdutos;

	private List<Dim_Produto> produtosFiltrados;

	public Dim_Produto getProduto() {
		return produto;
	}

	public void setProduto(Dim_Produto produto) {
		this.produto = produto;
	}

	public Tipo_Produto[] getTiposProdutos() {
		return Tipo_Produto.values();
	}

	public void salvar() {
		Dim_Produto produtoSalvo = produtoDAO.create(produto);
		todosProdutos.add(produtoSalvo);
		this.produto = new Dim_Produto();
		resetComponent("frm");
	}

	public List<Dim_Produto> getTodosProdutos() {
		return todosProdutos;
	}

	public void setTodosProdutos(List<Dim_Produto> todosProdutos) {
		this.todosProdutos = todosProdutos;
	}

	@SuppressWarnings("deprecation")
	public void onRowEdit(RowEditEvent event) {
		Dim_Produto produtoAlterado = (Dim_Produto) event.getObject();
		boolean produtoAlteradoEstaEmAberto = produtoAlterado.estaEmAberto();
		if (produtoAlteradoEstaEmAberto) {
			Dim_Produto produtoOriginal = produtoDAO.getBy(produtoAlterado.getId());
			boolean teveMudancaValor = produtoAlterado.valorEhDiferenteDo(produtoOriginal);
			boolean teveMudancaEmLoja = produtoAlterado.situacaoEmLojaEhDiferentedo(produtoOriginal);
			boolean aumentouQuantidadeEstoqueAtual = produtoAlterado.possuiQtdEstoqueAtualMaiorQue(produtoOriginal);
			
			long diferencaEstoqueInicial = produtoAlterado.getQuantidadeEstoqueInicial() - produtoOriginal.getQuantidadeEstoqueInicial();
			produtoAlterado.setQuantidadeEstoqueAtual(produtoAlterado.getQuantidadeEstoqueAtual() + diferencaEstoqueInicial);
			if (aumentouQuantidadeEstoqueAtual)
				produtoAlterado.teveAdicaoEstoque();
			if ((teveMudancaValor || teveMudancaEmLoja)
					&& produtoAlterado.getQuantidadeEstoqueAtual() > 0) {
				Date agora = new Date();
				agora.setSeconds(0);
				agora.setMinutes(Util.getMinutos(agora.getMinutes()));
				if(produtoOriginal.getDataFim().after(agora)){
					produtoOriginal.setDataFim(agora);
				}
				if(!produtoAlterado.isEmLoja())
					produtoOriginal.setEmLoja(false);
				produtoAlterado.setDateInicio(agora);
				produtoAlterado.setId(null);
				produtoOriginal.setEmLoja(false);

				produtoDAO.salvar(produtoAlterado);
				produtoDAO.atualizar(produtoOriginal);
			} else {
				produtoDAO.atualizar(produtoAlterado);
			}

			FacesMessage msg = new FacesMessage("Produto Alterado Com Sucesso",
					produtoAlterado.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			this.todosProdutos = produtoDAO.getAll();
		} else {
			addMsg("Produto está fechado");
		}
	}

	public void onRowCancel(RowEditEvent event) {
		Dim_Produto produto2 = (Dim_Produto) event.getObject();
		FacesMessage msg = new FacesMessage("Edição Cancelada",produto2.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
	}

	public List<Dim_Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Dim_Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}
	
	public void atualizarListaProdutos(){
		this.todosProdutos = produtoDAO.getAll();
	}

}
