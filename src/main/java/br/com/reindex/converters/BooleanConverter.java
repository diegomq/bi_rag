package br.com.reindex.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "br.com.reindex.converters.BooleanConverter" , forClass = Boolean.class)
public class BooleanConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && value.equalsIgnoreCase("Sim"))
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object booleano) {
		Boolean objeto = (Boolean) booleano;
		if (objeto != null && objeto)
			return "Sim";
		else
			return "Não";
	}

}
