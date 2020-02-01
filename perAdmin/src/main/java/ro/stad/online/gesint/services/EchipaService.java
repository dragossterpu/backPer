package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Echipa;
import ro.stad.online.gesint.persistence.entities.Utilizator;

/**
 * Interfață pentru serviciul de Echipa.
 *
 * @author STAD
 *
 */
public interface EchipaService {

	/**
	 * Elimina un membru al echipei de conducere
	 *
	 * @param echipa membru al echipei de conducere
	 */
	void delete(Echipa echipa);

	/**
	 *
	 * @param functie ParamEchipa
	 * @return boolean
	 */
	int existsByTeam(Long functieId);

	/**
	 *
	 * @param user Utilizator
	 * @return boolean
	 */
	boolean existsByUser(Utilizator user);

	/**
	 * Cauta toate functiile
	 *
	 * @return lista de functii.
	 */
	List<Echipa> fiindByTeam();

	/**
	 * Obtinem nivelul cel mai mare
	 *
	 * @param team
	 * @return Echipa actualizat
	 */
	List<Echipa> findAllByOrderByRankDesc();

	/**
	 * Cauta un registru in baza de date primind ca parametru membrul echipei
	 * @param team
	 * @return
	 */
	Echipa findByUser(Utilizator team);

	/**
	 * Salvați sau actualizați un team.
	 *
	 * @param echipa
	 * @return Echipa actualizat
	 */
	Echipa save(Echipa echipa);

	/**
	 * @param idTeam
	 * @return
	 *
	 */
	ParamEchipa findOne(Long idTeam);

}
