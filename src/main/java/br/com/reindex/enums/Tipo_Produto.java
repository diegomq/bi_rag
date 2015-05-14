package br.com.reindex.enums;

public enum Tipo_Produto {
	
	CARTA("Carta"),
	CONSUMIVEIS("Consumíveis"),
	EQUIPAMENTOS("Equipamentos"),
	ETC("Etc");
	
	
	private Tipo_Produto(String descricao) {
		this.descricao = descricao;
	}
	
	private Tipo_Produto() {
		
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	

}
