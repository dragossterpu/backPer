package ro.stad.online.gesint.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.stad.online.gesint.persistence.entities.Mapa;
import ro.stad.online.gesint.persistence.repositories.MapaRepository;
import ro.stad.online.gesint.services.MapaService;

/**
 * Implementación del servicio de mapa.
 *
 * @author STAD
 *
 */
@Service
public class MapaServiceImpl implements MapaService {

	/**
	 * Repositoriu de Mapa.
	 */
	@Autowired
	private MapaRepository mapaRepository;

	/**
	 * Devuelve toate registrele inregistrate in baza de date.
	 * @return lista de registre
	 */
	@Override
	public List<Mapa> fiindAll() {
		return (ArrayList<Mapa>) mapaRepository.findAll();
	}

}
