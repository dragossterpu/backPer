package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.ParamEchipa;

/**
 * Interfață pentru serviciul de ParamEchipa.
 *
 * @author STAD
 *
 */
public interface FunctieService {

	/**
	 * Elimina un cuerpo de estado.
	 * 
	 * @param cuerpo a eliminar
	 */
	void delete(ParamEchipa functie);

	/**
	 * Cauta toate functiile
	 *
	 * @return lista de functii.
	 */
	List<ParamEchipa> fiindAll();

	/**
	 * Inregistreaza o functie.
	 * @param functie ParamEchipa
	 * @return functie actualizata
	 */
	ParamEchipa save(ParamEchipa functie);

}
