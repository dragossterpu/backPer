package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.Mapa;

/**
 * Interfață pentru serviciul de Mapa.
 *
 * @author STAD
 *
 */
public interface MapaService {

	/**
	 * Cauta toate tarile
	 * @return lista de tari.
	 */
	List<Mapa> fiindAll();

}
