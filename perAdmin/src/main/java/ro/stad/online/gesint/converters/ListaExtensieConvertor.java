package ro.stad.online.gesint.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

/**
 * @author STAD
 */
@Component("listaExtensieConvertor")
public class ListaExtensieConvertor implements Converter {

	/**
	 * Delimitador de elementos en cadena de texto que contiene una lista.
	 */
	private static final String SEPARADOR = ", ";

	/**
	 * Transforma una cadena de texto en lista de elementos.
	 */
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String submittedValue) {
		Object respuesta = null;
		if (submittedValue != null) {
			respuesta = new ArrayList<>(Arrays.asList(submittedValue.split(SEPARADOR)));
		}
		return respuesta;
	}

	/**
	 * Transforma una lista de elementos en una cadena de texto.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object modelValue) {
		String respuesta = null;
		if (modelValue != null) {
			respuesta = String.join(SEPARADOR, (List<String>) modelValue);
		}
		return respuesta;
	}

}