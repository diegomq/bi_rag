package br.com.reindex.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "dim_tempo")
public class Dim_Tempo implements Serializable{

	private static final long serialVersionUID = 689647612214343661L;

	public static final String DATA = "data";

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "data_hora")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name = "dia")
	private int dia;
	
	@Column(name = "mes")
	private int mes;
	
	@Column(name = "ano")
	private int ano;
	
	@Column(name = "descricaoMes")
	private String descricaoMes;
	
	@Column(name = "diaSemana")
	private String diaSemana;
	
	@Column(name = "ehFds")
	private String ehFds;
	
	@Column(name = "faixaHoraDia")
	private String faixaHoraDia;
	
	public Dim_Tempo() {
		
	}
	

	public Dim_Tempo(Date data, int dia, int mes, int ano,
			String descricaoMes, String diaSemana, String ehFds,
			String faixaHoraDia) {
		super();
		this.data = data;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.descricaoMes = descricaoMes;
		this.diaSemana = diaSemana;
		this.ehFds = ehFds;
		this.faixaHoraDia = faixaHoraDia;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public String getDescricaoMes() {
		return descricaoMes;
	}

	public void setDescricaoMes(String descricaoMes) {
		this.descricaoMes = descricaoMes;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getEhFds() {
		return ehFds;
	}

	public void setEhFds(String ehFds) {
		this.ehFds = ehFds;
	}

	public String getFaixaHoraDia() {
		return faixaHoraDia;
	}

	public void setFaixaHoraDia(String faixaHoraDia) {
		this.faixaHoraDia = faixaHoraDia;
	}
	
	
	
}
