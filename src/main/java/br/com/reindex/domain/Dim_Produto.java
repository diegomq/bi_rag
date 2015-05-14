package br.com.reindex.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import br.com.reindex.enums.Tipo_Produto;
import br.com.reindex.util.Util;

@Entity
@Component
@Table(name = "dim_produto")
public class Dim_Produto implements Serializable {

	private static final long serialVersionUID = -5216335227307655311L;

	public static final String EM_LOJA = "emLoja";

	public static final String ESTOQUE_ATUAL = "quantidadeEstoqueAtual";

	public static final String DATA_INICIO = "dateInicio";

	public static final String DATA_FIM = "dataFim";

	public static final String NOME = "nome";

	public static final String ID = "id";

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "valor")
	private Float valor;

	@Column(name = "dateInicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInicio;

	@Column(name = "dateFim")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;

	@Column(name = "quantidade_estoque_inicial")
	private Long quantidadeEstoqueInicial;

	@Column(name = "quantidade_estoque_atual")
	private Long quantidadeEstoqueAtual;

	@Column(name = "quantidade_adicao_estoque")
	private Long quantidadeAdicaoEstoque;

	@Column(name = "em_loja")
	private boolean emLoja;

	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private Tipo_Produto tipoProduto;

	public Dim_Produto() {
		this.quantidadeAdicaoEstoque = 0l;
	}

	@SuppressWarnings("deprecation")
	public Dim_Produto(String nome, Float valor, Date dateInicio,
			Long quantidadeEstoqueInicial, boolean emLoja,
			Tipo_Produto tipoProduto) {
		super();
		this.nome = nome;
		this.valor = valor;
		dateInicio.setSeconds(0);
		dateInicio.setMinutes(Util.getMinutos(dateInicio.getMinutes()));
		this.dateInicio = dateInicio;
		Date dataFim = new Date();
		dataFim.setYear(dataFim.getYear() + 2);
		dataFim.setSeconds(0);
		dataFim.setMinutes(Util.getMinutos(dataFim.getMinutes()));
		this.dataFim = dataFim;
		this.quantidadeEstoqueInicial = quantidadeEstoqueInicial;
		this.quantidadeEstoqueAtual = quantidadeEstoqueInicial;
		this.quantidadeAdicaoEstoque = 0l;
		this.emLoja = emLoja;
		this.tipoProduto = tipoProduto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Date getDateInicio() {
		return dateInicio;
	}

	public void setDateInicio(Date dateInicio) {
		this.dateInicio = dateInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Long getQuantidadeEstoqueInicial() {
		return quantidadeEstoqueInicial;
	}

	public void setQuantidadeEstoqueInicial(Long quantidadeEstoqueInicial) {
		this.quantidadeEstoqueInicial = quantidadeEstoqueInicial;
	}

	public Long getQuantidadeEstoqueAtual() {
		return quantidadeEstoqueAtual;
	}

	public void setQuantidadeEstoqueAtual(Long quantidadeEstoqueAtual) {
		this.quantidadeEstoqueAtual = quantidadeEstoqueAtual;
	}

	public Long getQuantidadeAdicaoEstoque() {
		return quantidadeAdicaoEstoque;
	}

	public void setQuantidadeAdicaoEstoque(Long quantidadeAdicaoEstoque) {
		this.quantidadeAdicaoEstoque = quantidadeAdicaoEstoque;
	}

	public boolean isEmLoja() {
		return emLoja;

	}

	public void setEmLoja(boolean emLoja) {
		this.emLoja = emLoja;
	}

	public Tipo_Produto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(Tipo_Produto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	/**
	 * retorna se valor deste produto eh diferente do produto informado
	 * 
	 * @author diego
	 * @param produtoInformado
	 * @return
	 */
	public boolean valorEhDiferenteDo(Dim_Produto produtoInformado) {
		if (this.valor.compareTo(produtoInformado.getValor()) != 0)
			return true;
		else
			return false;
	}

	/**
	 * retorna se situação em loja é diferente do objeto passado
	 * 
	 * @author diego
	 * @param produtoOriginal
	 * @return
	 */
	public boolean situacaoEmLojaEhDiferentedo(Dim_Produto produtoOriginal) {
		if (this.emLoja != produtoOriginal.isEmLoja())
			return true;
		else
			return false;
	}

	/**
	 * retorna se quantidade do estoque atual é maior que do objeto informado
	 * 
	 * @author diego
	 * 
	 * @param produtoOriginal
	 * 
	 * @return
	 */
	public boolean possuiQtdEstoqueAtualMaiorQue(Dim_Produto produtoOriginal) {
		return this.quantidadeEstoqueAtual > produtoOriginal
				.getQuantidadeEstoqueAtual();
	}

	public void teveAdicaoEstoque() {
		this.quantidadeAdicaoEstoque = this.quantidadeAdicaoEstoque + 1;

	}

	/**
	 * se a data fim estiver depois da atual, entao produto esta em aberto
	 * 
	 * @author diego
	 * @return
	 */
	public boolean estaEmAberto() {
		Date now = new Date();
		if (this.dataFim.after(now))
			return true;
		else
			return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Dim_Produto))
			return false;
		Dim_Produto other = (Dim_Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}else if (id.compareTo(other.id)!=0)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		if (this.nome != null)
			return this.nome;
		else
			return super.toString();
	}
}
