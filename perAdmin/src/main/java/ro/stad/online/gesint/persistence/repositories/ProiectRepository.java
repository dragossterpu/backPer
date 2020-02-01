package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Proiect;

/**
 * Repositorio de operaciones de base de datos para la entidad Proiect.
 *
 * @author ATOS
 *
 */
public interface ProiectRepository extends CrudRepository<Proiect, Long> {

	/**
	 * Devuelve todas las unidades de base de datos.
	 * @return lista de unidades
	 */
	Iterable<Proiect> findAllByOrderByRankAsc();

}
