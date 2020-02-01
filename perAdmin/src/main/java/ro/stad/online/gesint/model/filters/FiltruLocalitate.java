package ro.stad.online.gesint.model.filters;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;

/**
 * Controlor de operațiuni legate de căutarea de localitati. Resetați valorile căutării.
 *
 * @author STAD
 */
@Setter
@Getter
public class FiltruLocalitate implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Numele localitatii.
	 */
	private String nume;

	/**
	 * Judete selectionate.
	 */
	private String idProvincia;

	/**
	 * Filtru tipul localitatii utilizator.
	 */
	private TipLocalitateEnum tip;

	/**
	 * Resetați valorile formularului de căutare a localitatilor
	 */
	public void resetValues() {
		setNume(Constante.SPATIU);
		setIdProvincia(Constante.SPATIU);
		setTip(null);
	}

}
