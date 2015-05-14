package br.com.reindex.managed.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.reindex.domain.Dim_Regiao;
import br.com.reindex.repositories.RegiaoService;
import br.com.reindex.suri.framework.controller.ControllerSupport;

@Component
@RequestScoped
@ManagedBean(name = "cadastrarRegiaoBean")
public class CadastrarRegiaoBean extends ControllerSupport implements
		Serializable {

	private static final long serialVersionUID = 4866562323267638757L;

	private Dim_Regiao regiao = new Dim_Regiao();

	@Inject
	@Autowired
	private RegiaoService regiaoDAO;

	private List<Dim_Regiao> todasRegioes;
	
	private List<Dim_Regiao> todasRegioesFiltradas;

	public void salvar() {
		Dim_Regiao regiaoSalvo = regiaoDAO.salvar(regiao);

		todasRegioes.add(regiaoSalvo);
		this.regiao = new Dim_Regiao();
		resetComponent("frm");
	}

	@PostConstruct
	public void init() {
		todasRegioes = regiaoDAO.getAll();
	}

	public void onRowEdit(RowEditEvent event) {
		Dim_Regiao regiaoAlterado = (Dim_Regiao) event.getObject();
		regiaoDAO.atualizar(regiaoAlterado);
		FacesMessage msg = new FacesMessage("regiao Alterado Com Sucesso",
				regiaoAlterado.getNomeCidade());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.todasRegioes = regiaoDAO.getAll();
	}

	public void onRowCancel(RowEditEvent event) {
		Dim_Regiao regiao2 = (Dim_Regiao) event.getObject();
		FacesMessage msg = new FacesMessage("Edição Cancelada",
				regiao2.getNomeCidade());
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

	public Dim_Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Dim_Regiao regiao) {
		this.regiao = regiao;
	}

	public List<Dim_Regiao> getTodasRegioes() {
		return todasRegioes;
	}

	public void setTodasRegioes(List<Dim_Regiao> todasRegioes) {
		this.todasRegioes = todasRegioes;
	}
	public List<Dim_Regiao> getTodasRegioesFiltradas() {
		return todasRegioesFiltradas;
	}

	public void setTodasRegioesFiltradas(List<Dim_Regiao> todasRegioesFiltradas) {
		this.todasRegioesFiltradas = todasRegioesFiltradas;
	}

}
