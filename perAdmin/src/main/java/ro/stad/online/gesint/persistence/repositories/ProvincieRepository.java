package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Provincia;

/**
 * Repositoriu pentru entitatea Provincia.
 * @author STAD
 */
public interface ProvincieRepository extends CrudRepository<Provincia, String> {

	/**
	 * Cauta toate judetele ordonate dupa nume
	 * @return List<Provincia>
	 *
	 */
	List<Provincia> findAllByOrderByNameAsc();

	/**
	 * Cauta un judet dupa numele acestuia
	 * @param descripcion
	 * @return provincia
	 *
	 */
	Provincia findByName(String descripcion);

}
