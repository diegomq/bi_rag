package br.com.reindex.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.reindex.domain.Dim_Produto;
import br.com.reindex.repositories.ProdutoService;

@Component
@Scope("request")
@FacesConverter(forClass = Dim_Produto.class, value = "produtoConveter")
public class ProdutoConveter implements Converter{

	
	@Inject
	@Autowired
	private ProdutoService produtoDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String idProduto) {
	
		Dim_Produto retorno = null;

		if (idProduto != null) {
			retorno = produtoDAO.getBy(Long.valueOf(idProduto));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			return ((Dim_Produto) value).getId().toString();
		}
		return null;
	}

}
