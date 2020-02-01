package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.ParamEchipa;

/**
 * Repositoriu pentru entitatea ParamEchipa.
 * @author STAD
 */
public interface ParamEchipaRepository extends CrudRepository<ParamEchipa, Long> {

	/**
	 * @param organization
	 * @param Long idTeam
	 * @return ParamEchipa functia
	 *
	 */
	ParamEchipa findByIdAndOrganization(Long idTeam, String organizatie);

	/**
	 * @param organization
	 * @return
	 *
	 */
	List<ParamEchipa> findByOrganization(String organization);

}
