package ro.stad.online.gesint.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.persistence.entities.Intrebare;
import ro.stad.online.gesint.persistence.repositories.IntrebareRepository;
import ro.stad.online.gesint.services.IntrebareService;

/**
 * Implementarea serviciului de intrebari
 *
 * @author STAD
 */

@Service
@Transactional
public class IntrebareServiceImpl implements IntrebareService {

	/**
	 * Variabilă utilizată pentru a injecta depozitul de intrebari
	 */
	@Autowired
	private IntrebareRepository intrebareRepository;

	/**
	 * Returnează lista de intrebari.
	 * @return List<Intrebare>
	 */
	@Override
	public List<Intrebare> findAll() {
		return (List<Intrebare>) intrebareRepository.findAll();
	}

	/**
	 * Returnează Intrebarea
	 * @return Intrebare
	 */
	@Override
	public Intrebare findById(final Long id) {
		return intrebareRepository.findOne(id);
	}
}
