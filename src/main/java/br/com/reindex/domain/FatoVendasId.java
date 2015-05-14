package br.com.reindex.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FatoVendasId implements Serializable{

	private static final long serialVersionUID = 8395834631413177350L;

	@ManyToOne
	@JoinColumn(name = "fk_dim_tempo")
	private Dim_Tempo tempo;
	
	@ManyToOne
	@JoinColumn(name = "fk_dim_produto")
	private Dim_Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "fk_dim_regiao")
	private Dim_Regiao regiao;
	
	@Column(name = "valor_recebido")
	private Float valorRecebido;
	
	@Column(name = "qtd_venda")
	private int qtdVenda = 1;
	
	@Column(name = "qtd_itens_vendidos")
	private long qtdItensVendidos;
	
	public FatoVendasId(Dim_Produto produto, Dim_Tempo dim_tempo, Dim_Regiao regiao, long qtdItensVendidos) {
		this.produto = produto;
		this.tempo = dim_tempo;
		this.regiao = regiao;
		this.qtdVenda = 1;
		this.qtdItensVendidos = qtdItensVendidos;
		this.valorRecebido = produto.getValor() * qtdItensVendidos;
	}
	
	public FatoVendasId() {
	}

	public Dim_Tempo getTempo() {
		return tempo;
	}

	public void setTempo(Dim_Tempo tempo) {
		this.tempo = tempo;
	}

	public Dim_Produto getProduto() {
		return produto;
	}

	public void setProduto(Dim_Produto produto) {
		this.produto = produto;
	}

	public Float getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Float valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public int getQtdVenda() {
		return qtdVenda;
	}

	public void setQtdVenda(int qtdVenda) {
		this.qtdVenda = qtdVenda;
	}

	public long getQtdItensVendidos() {
		return qtdItensVendidos;
	}

	public void setQtdItensVendidos(long qtdItensVendidos) {
		this.qtdItensVendidos = qtdItensVendidos;
	}

	public Dim_Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Dim_Regiao regiao) {
		this.regiao = regiao;
	}
	
	
	
}
