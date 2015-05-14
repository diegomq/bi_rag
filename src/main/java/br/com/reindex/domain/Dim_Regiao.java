package br.com.reindex.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dim_regiao")
public class Dim_Regiao implements Serializable {

	private static final long serialVersionUID = -7162846034055772018L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "nome_cidade")
	private String nomeCidade;

	@Column(name = "descricao_ponto")
	private String descricaoPonto;

	public Dim_Regiao() {

	}

	public Dim_Regiao(String nomeCidade, String descricaoPonto) {
		super();
		this.nomeCidade = nomeCidade;
		this.descricaoPonto = descricaoPonto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getDescricaoPonto() {
		return descricaoPonto;
	}

	public void setDescricaoPonto(String descricaoPonto) {
		this.descricaoPonto = descricaoPonto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Dim_Regiao))
			return false;
		Dim_Regiao other = (Dim_Regiao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id.compareTo(other.id) != 0)
			return false;
		return true;
	}

}
