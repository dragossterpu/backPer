package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.Intrebare;

/**
 * Interfață de service pentru managementul de intrebari.
 *
 * @author STAD
 *
 */
public interface IntrebareService {

	/**
	 * Returneaza o lista cu toate intrebarile.
	 * @return List<Intrebari>
	 */
	List<Intrebare> findAll();

	/**
	 * Returneaza intrebarea.
	 * @return Intrebare
	 */
	Intrebare findById(Long id);

}
