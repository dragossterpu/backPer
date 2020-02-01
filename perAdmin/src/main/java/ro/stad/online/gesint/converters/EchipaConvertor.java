package ro.stad.online.gesint.converters;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.component.orderlist.OrderList;
import org.springframework.stereotype.Component;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.Echipa;

/**
 * Clasă pentru cartografierea membrilor echipei.
 * @author STAD
 *
 */
@Component("teamConverter")
public class EchipaConvertor implements Converter {

	/**
	 * Metoda care obtine un obiect.
	 */
	@SuppressWarnings(Constante.UNCHECKED)
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
		Object team = new Echipa();
		final Object list = ((OrderList) component).getValue();

		final List<Echipa> listaTeams = (ArrayList<Echipa>) list;

		boolean parada = false;

		for (int i = 0; i < listaTeams.size() && !parada; i++) {
			if (listaTeams.get(i).getId().toString().equals(value)) {
				team = listaTeams.get(i);
				parada = true;
			}
		}

		return team;
	}

	/**
	 * Metoda care obtine un string.
	 */
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
		String idTeam = null;
		final Echipa echipa = (Echipa) value;

		if (echipa != null && echipa.getId() != null) {
			idTeam = echipa.getId().toString();
		}

		return idTeam;
	}

}