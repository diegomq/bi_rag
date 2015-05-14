package br.com.reindex.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tempo")
public class Tempo_BI implements Serializable{

	private static final long serialVersionUID = 689647612214343661L;

	public static final String DATA = "data";

	@Id
	@Column(name = "sk_tempo")
	private Date id;

	
	@Column(name = "dia")
	private int dia;
	
	@Column(name = "mes")
	private int mes;
	
	@Column(name = "ano")
	private int ano;
	
	
	public Tempo_BI() {
		
	}

	public Tempo_BI(Date id, int dia, int mes, int ano) {
		super();
		this.id = id;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}




	public Date getId() {
		return id;
	}


	public void setId(Date id) {
		this.id = id;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
	

		
	
}
