package br.com.reindex.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.reindex.domain.Dim_Regiao;
import br.com.reindex.repositories.RegiaoService;

@Component
@Scope("request")
@FacesConverter(forClass = Dim_Regiao.class, value="regiaoConverter")
public class RegiaoConverter implements Converter {

	@Inject
	@Autowired
	private RegiaoService regiaoDAO;
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Dim_Regiao retorno = null;

		if (value != null) {
			retorno = regiaoDAO.getBy(Long.valueOf(value));
		}
		return retorno;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object regiao) {
		if (regiao != null) {
			return ((Dim_Regiao) regiao).getId().toString();
		}
		return null;
	}

}
