package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.Tara;

/**
 * Interfață pentru serviciul de Tara.
 *
 * @author STAD
 *
 */
public interface TaraService {

	/**
	 * Cauta toate tarile
	 * @return lista de tari.
	 * @see ro.mira.per.controller.AdminController.users(HttpServletRequest, Integer) (potential match)
	 */
	List<Tara> fiindAll();

}
