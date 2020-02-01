package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.Optiune;
import ro.stad.online.gesint.persistence.entities.Provincia;

/**
 * Interfață pentru serviciul de optiuni.
 *
 * @author STAD
 *
 */
public interface OptiuneService {

	/**
	 * Elimina o optiune.
	 *
	 * @param partid
	 */
	void delete(Optiune optiune);

	/**
	 * Cauta toate optiunile
	 *
	 * @return lista de optiuni.
	 */
	List<Optiune> fiindAll();

	/**
	 * Cautam optiunile dupa judet.
	 * @param code String
	 * @return List<Optiune> listaOptiuni
	 */
	List<Optiune> findByCodeProvince(Provincia code);

	/**
	 * Inregistreaza o optiune.
	 * @param optiune Optiune
	 * @return optiune actualizata
	 */
	Optiune save(Optiune optiune);

}
