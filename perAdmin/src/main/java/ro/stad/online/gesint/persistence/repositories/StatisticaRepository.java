package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Statistica;
import ro.stad.online.gesint.persistence.entities.enums.TipStatisticaEnum;

/**
 * Repositoriu pentru entitatea Statistica.
 * @author STAD
 */
public interface StatisticaRepository extends CrudRepository<Statistica, Long> {

	/**
	 * Cauta judetele din statistica
	 * @param tip TipStatisticaEnum
	 * @param isjudet Boolean
	 * @para mmarca
	 * @return List<Statistica>
	 *
	 */
	List<Statistica> findByTipStatisticaAndMarcaAndIsjudetOrderByProcentajDesc(TipStatisticaEnum tip, String marca,
			Boolean isjudet);
}
