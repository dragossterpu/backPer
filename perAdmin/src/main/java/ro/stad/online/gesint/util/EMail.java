package ro.stad.online.gesint.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.error.PebbleException;

import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documente;
import ro.stad.online.gesint.persistence.entities.Utilizator;

/**
 *
 * Clase para el envío de correos electrónicos.
 *
 * @author STAD
 *
 */
public interface EMail {

	void trimitereEmail(String email, String string, String templatecorreorestablecerpassword,
			Map<String, String> paramPlantilla);

	/**
	 * Envío de alerta.
	 * @param alerta Alerta
	 * @param utilizatoriSelectionati Utilizator
	 * @return Date
	 */
	Date send(final Alerta alerta, List<Utilizator> utilizatoriSelectionati, List<Documente> documenteIncarcate,
			String plantilla, Map<String, String> paramPlantilla) throws PebbleException;
}
