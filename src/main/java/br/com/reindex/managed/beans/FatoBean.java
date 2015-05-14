package br.com.reindex.managed.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.reindex.domain.Dim_Produto;
import br.com.reindex.domain.Dim_Regiao;
import br.com.reindex.domain.Dim_Tempo;
import br.com.reindex.domain.FatoVendasId;
import br.com.reindex.domain.Fato_Vendas;
import br.com.reindex.repositories.FatoService;
import br.com.reindex.repositories.ProdutoService;
import br.com.reindex.repositories.RegiaoService;
import br.com.reindex.repositories.TempoService;
import br.com.reindex.suri.framework.controller.ControllerSupport;
import br.com.reindex.util.Util;

@Component
@RequestScoped
@ManagedBean(name = "fatoBean")
public class FatoBean extends ControllerSupport implements Serializable {

	private static final long serialVersionUID = 486500750020754654L;

	private Fato_Vendas fato = new Fato_Vendas();
	private List<Fato_Vendas> todasVendas;
	private List<Dim_Produto> produtosDisponiveis;
	private List<Dim_Regiao> listaRegioes;
	private Date dataVenda = new Date();

	@Inject
	@Autowired
	private ProdutoService produtoDAO;

	@Inject
	@Autowired
	private RegiaoService regiaoDAO;

	@Inject
	@Autowired
	private FatoService fatoDAO;

	@Inject
	@Autowired
	private TempoService tempoDAO;

	private Long quantidadeMaxima = 0l;

	public Fato_Vendas getFato() {
		return fato;
	}

	public void setFato(Fato_Vendas fato) {
		this.fato = fato;
	}

	public List<Fato_Vendas> getTodasVendas() {
		return todasVendas;
	}

	public void setTodasVendas(List<Fato_Vendas> todasVendas) {
		this.todasVendas = todasVendas;
	}

	public List<Dim_Produto> getProdutosDisponiveis() {
		return produtosDisponiveis;
	}

	public void setProdutosDisponiveis(List<Dim_Produto> produtosDisponiveis) {
		this.produtosDisponiveis = produtosDisponiveis;
	}

	public List<Dim_Regiao> getListaRegioes() {
		return listaRegioes;
	}

	public void setListaRegioes(List<Dim_Regiao> listaRegioes) {
		this.listaRegioes = listaRegioes;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@SuppressWarnings("deprecation")
	public void salvar() {
		FatoVendasId fatoVendasId = fato.getId();

		dataVenda.setMinutes(Util.getMinutos(dataVenda.getMinutes()));
		dataVenda.setSeconds(0);
		Dim_Tempo tempo = tempoDAO.getTempoBy(dataVenda);

		fatoVendasId.setTempo(tempo);

		long qtdItensVendidos = fatoVendasId.getQtdItensVendidos();
		Dim_Produto produto = fatoVendasId.getProduto();
		float valorRecebido = produto.getValor() * qtdItensVendidos;
		fatoVendasId.setValorRecebido(valorRecebido);
		fato.setId(fatoVendasId);
		fatoDAO.salvar(fato);

		long qtdEstoqueAtualRestante = produto.getQuantidadeEstoqueAtual()
				- qtdItensVendidos;
		produto.setQuantidadeEstoqueAtual(qtdEstoqueAtualRestante);

		if (qtdEstoqueAtualRestante == 0) {
			produto.setDataFim(dataVenda);
			produto.setEmLoja(false);
			Dim_Produto produtoNovo = new Dim_Produto(produto.getNome(),
					produto.getValor(), dataVenda, 0l, false,
					produto.getTipoProduto());
			produtoDAO.salvar(produtoNovo);
		}
		produtoDAO.atualizar(produto);
		todasVendas.add(fato);

		this.fato = new Fato_Vendas();
		this.quantidadeMaxima = 0l;

		produtosDisponiveis = produtoDAO
				.getProdutosEmLojaComEstoqueAtualMaiorQueZero();
	}

	public void obterValorMaximoPermitido() {
		FatoVendasId id = this.fato.getId();
		if (id != null && id.getProduto() != null) {
			Dim_Produto produto = id.getProduto();
			this.quantidadeMaxima = produto.getQuantidadeEstoqueAtual();
		}
	}

	public void fireSelection(ValueChangeEvent event) {
	    System.out.println("New: "+event.getNewValue()+", Old: "+event.getOldValue());
	}
	
	public Long getQuantidadeMaxima() {
		return quantidadeMaxima;
	}

	public void setQuantidadeMaxima(Long quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}

	

	public void onRowEdit(RowEditEvent event) {
		Fato_Vendas vendaAlterada = (Fato_Vendas) event.getObject();
		Dim_Tempo dimTempo = tempoDAO.getTempoBy(vendaAlterada.getId().getTempo().getData());
		vendaAlterada.getId().setTempo(dimTempo);
		fatoDAO.atualizar(vendaAlterada);
		FacesMessage msg = new FacesMessage("Venda Alterado Com Sucesso");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada");
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
	
	public void atualizarDados(){
		this.produtosDisponiveis = produtoDAO.getProdutosEmLojaComEstoqueAtualMaiorQueZero();
		this.listaRegioes = regiaoDAO.getAll();
		this.todasVendas = fatoDAO.getALL();
	}
}
