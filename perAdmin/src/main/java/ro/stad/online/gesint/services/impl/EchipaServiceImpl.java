package ro.stad.online.gesint.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Echipa;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.repositories.ParamEchipaRepository;
import ro.stad.online.gesint.persistence.repositories.EchipaRepository;
import ro.stad.online.gesint.services.EchipaService;

/**
 * Implementación del servicio de unidades.
 *
 * @author STAD
 *
 */
@Service
public class EchipaServiceImpl implements EchipaService {

	/**
	 * Repositoriu de Echipa.
	 */
	@Autowired
	private EchipaRepository echipaRepository;

	/**
	 * Repositoriu de Echipa.
	 */
	@Autowired
	private ParamEchipaRepository pteamRepository;

	/**
	 * Elimina un membru al echipei de conducere
	 *
	 * @param echipa membru al echipei de conducere
	 */
	@Override
	public void delete(final Echipa echipa) {
		echipaRepository.delete(echipa);
	}

	/**
	 * Verifica existența utilizatorilor care au atribuit o echipa primit ca parametru.
	 * @param functie
	 * @return resultatul comprobarii
	 */
	@Override
	public int existsByTeam(final Long functieId) {
		return echipaRepository.existsByTeam(functieId);
	}

	/**
	 * Verifica existența utilizatorilor care au atribuit o echipa primit ca parametru.
	 * @param user
	 * @return resultatul comprobarii
	 */
	@Override
	public boolean existsByUser(final Utilizator user) {
		return echipaRepository.existsByUser(user);
	}

	/**
	 * Devuelve todas las unidades de base de datos.
	 * @return lista de unidades
	 */
	@Override
	public List<Echipa> fiindByTeam() {
		return (ArrayList<Echipa>) echipaRepository.findAllByOrderByRankAsc();
	}

	/**
	 * Cauta ultima pozitie din lista
	 */
	@Override
	public List<Echipa> findAllByOrderByRankDesc() {
		return (ArrayList<Echipa>) echipaRepository.findAllByOrderByRankDesc();
	}

	/**
	 * Cauta un registru in baza de date primind ca parametru membrul echipei
	 * @param team
	 * @return
	 */
	@Override
	public Echipa findByUser(final Utilizator team) {
		return echipaRepository.findByUser(team);
	}

	/**
	 * Salvați sau actualizați un team.
	 *
	 * @param echipa
	 * @return Echipa actualizat
	 */
	@Override
	public Echipa save(final Echipa echipa) {
		final Echipa teamActualizado = echipaRepository.save(echipa);
		return teamActualizado;

	}

	/**
	 * Cauta o functie
	 * @param alerta Alerta
	 * @return alerta Alerta
	 */
	@Override
	public ParamEchipa findOne(final Long idTeam) {
		return pteamRepository.findOne(idTeam);
	}
}
