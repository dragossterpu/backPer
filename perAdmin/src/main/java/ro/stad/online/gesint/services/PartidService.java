package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.Partid;

/**
 * Interfață pentru serviciul de Partid.
 *
 * @author STAD
 *
 */
public interface PartidService {

	/**
	 * Elimina un partid.
	 *
	 * @param partid
	 */
	void delete(Partid partid);

	/**
	 * Cauta toate partidele
	 *
	 * @return lista de partid.
	 */
	List<Partid> fiindAll();

	/**
	 * Inregistreaza un partid.
	 * @param partid Partid
	 * @return partid actualizat
	 */
	Partid save(Partid partid);

	/**
	 * Cauta un partid
	 * @param idPartid Long
	 * @return partid Partid
	 */
	Partid fiindOne(Long idPartid);

	/**
	 * Cauta partidele participante la alegerile judetene.
	 * @return List<Partid>
	 *
	 */
	List<Partid> fiindPartidJudet();
}
