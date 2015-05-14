package br.com.reindex.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fato_venda")
public class Fato_Vendas implements Serializable{

	private static final long serialVersionUID = 5393839218613577641L;
	@Id
	private FatoVendasId id;
	
	
	public Fato_Vendas(Dim_Produto produto, Dim_Tempo dim_tempo,Dim_Regiao regiao, long qtdItensVendidos) {
		this.id = new FatoVendasId(produto,dim_tempo,regiao, qtdItensVendidos);
	}
	public Fato_Vendas() {
		this.id = new FatoVendasId();
	}
	public FatoVendasId getId() {
		return id;
	}
	public void setId(FatoVendasId id) {
		this.id = id;
	}
	
	
	
}
