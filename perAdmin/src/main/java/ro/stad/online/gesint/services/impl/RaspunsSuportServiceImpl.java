package ro.stad.online.gesint.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.persistence.entities.Intrebare;
import ro.stad.online.gesint.persistence.entities.RaspunsSuport;
import ro.stad.online.gesint.persistence.repositories.RaspunsSuportRepository;
import ro.stad.online.gesint.services.RaspunsSuportService;

/**
 * Implementarea serviciului de raspunsuri suport
 *
 * @author STAD
 */

@Service
@Transactional
public class RaspunsSuportServiceImpl implements RaspunsSuportService {

	/**
	 * Variabilă utilizată pentru a injecta depozitul de intrebari
	 */
	@Autowired
	private RaspunsSuportRepository raspunsSuportRepository;

	/**
	 * Returnează raspunsul
	 * @return RaspunsSuport
	 */
	@Override
	public RaspunsSuport findById(final Intrebare intrebare) {
		return raspunsSuportRepository.findByIntrebare(intrebare);
	}

}
