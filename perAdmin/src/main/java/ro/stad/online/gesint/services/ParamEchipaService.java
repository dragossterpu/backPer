package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.ParamEchipa;

/**
 * Interfață pentru serviciul de ParamEchipa.
 *
 * @author STAD
 *
 */
public interface ParamEchipaService {

	/**
	 * Cauta toate functiile
	 *
	 * @return lista de functii.
	 */
	List<ParamEchipa> fiindAll();

	/**
	 * Metoda care cauta toate functile din conducerea locala
	 * @return list
	 *
	 */
	List<ParamEchipa> fiindAllByParam();

	/**
	 * Cauta o functie
	 * @param Long idTeam
	 * @return ParamEchipa localitatea
	 */
	ParamEchipa findById(Long idTeam);

	/**
	 * Metoda care cauta functia din conducerea locala
	 * @return list
	 *
	 */
	ParamEchipa findByIdAndOrganization(Long id, String organizatia);

	/**
	 * Metoda care cauta functia din conducerea locala
	 * @return list
	 *
	 */
	List<ParamEchipa> findByOrganization(String organizatia);
}
