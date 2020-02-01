package ro.stad.online.gesint.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.stad.online.gesint.persistence.entities.Tara;
import ro.stad.online.gesint.persistence.repositories.TaraRepository;
import ro.stad.online.gesint.services.TaraService;

/**
 * Implementación del servicio de tari.
 *
 * @author STAD
 *
 */
@Service
public class TaraServiceImpl implements TaraService {

	/**
	 * Repositoriu de Tara.
	 */
	@Autowired
	private TaraRepository taraRepository;

	/**
	 * Devuelve toate tarile inregistrate in baza de date.
	 * @return lista de tari
	 */
	@Override
	public List<Tara> fiindAll() {
		return (ArrayList<Tara>) taraRepository.findAll();
	}

}
