package ro.stad.online.gesint.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.repositories.ParamEchipaRepository;
import ro.stad.online.gesint.services.ParamEchipaService;

/**
 * Implementación del servicio dePTeamService.
 *
 * @author STAD
 *
 */
@Service
public class ParamEchipaServiceImpl implements ParamEchipaService {

	/**
	 * Repositoriu de Echipa.
	 */
	@Autowired
	private ParamEchipaRepository pteamRepository;

	/**
	 * Devuelve todas las unidades de base de datos.
	 * @return lista de unidades
	 */
	@Override
	public List<ParamEchipa> fiindAll() {
		return (ArrayList<ParamEchipa>) pteamRepository.findAll();
	}

	/**
	 * Devuelve todas las unidades de base de datos.
	 * @return lista de unidades
	 */
	@Override
	public List<ParamEchipa> fiindAllByParam() {
		final String organization = "Conducerea Locală";
		return pteamRepository.findByOrganization(organization);
	}

	/**
	 * Cauta o functie
	 * @param Long idTeam
	 * @return ParamEchipa functia
	 */
	@Override
	public ParamEchipa findById(final Long idTeam) {
		return pteamRepository.findOne(idTeam);
	}

	/**
	 * Cauta o functie
	 * @param Long idTeam
	 * @return ParamEchipa functia
	 */
	@Override
	public ParamEchipa findByIdAndOrganization(final Long idTeam, final String organizatie) {
		return pteamRepository.findByIdAndOrganization(idTeam, organizatie);
	}

	/**
	 * Cauta o functie
	 * @param Long idTeam
	 * @return ParamEchipa functia
	 */
	@Override
	public List<ParamEchipa> findByOrganization(final String organizatie) {
		return pteamRepository.findByOrganization(organizatie);
	}
}
