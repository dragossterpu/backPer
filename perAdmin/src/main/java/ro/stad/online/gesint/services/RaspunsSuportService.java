package ro.stad.online.gesint.services;

import ro.stad.online.gesint.persistence.entities.Intrebare;
import ro.stad.online.gesint.persistence.entities.RaspunsSuport;

/**
 * Interfață de service pentru managementul de intrebari.
 *
 * @author STAD
 *
 */
public interface RaspunsSuportService {

	/**
	 * Returneaza raspunsul.
	 * @return Intrebare
	 */
	RaspunsSuport findById(Intrebare intrebare);

}
