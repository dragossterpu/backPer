package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Echipa;
import ro.stad.online.gesint.persistence.entities.Utilizator;

/**
 * Repositoriu pentru entitatea Echipa.
 * @author STAD
 */
public interface EchipaRepository extends CrudRepository<Echipa, Long> {

	/**
	 * Elimina un membru al echipei primit ca parametru.
	 * @param user
	 * @return resultatul eliminarii
	 */
	@Override
	void delete(Echipa echipa);

	/**
	 * Verificați existența utilizatorilor care au atribuit o echipa primit ca parametru.
	 * @param functie
	 * @return resultatul comprobarii
	 */
	@Query(value = "select count(*) from team where team =1", nativeQuery = true)
	int existsByTeam(Long functieId);

	/**
	 * Verificați existența utilizatorilor care au atribuit o echipa primit ca parametru.
	 * @param user
	 * @return resultatul comprobarii
	 */
	boolean existsByUser(Utilizator user);

	/**
	 * Devuelve todas las unidades de base de datos.
	 * @return lista de unidades
	 */
	Iterable<Echipa> findAllByOrderByRankAsc();

	/**
	 * Devuelve todas las unidades de base de datos.
	 * @return lista de unidades
	 */
	Iterable<Echipa> findAllByOrderByRankDesc();

	/**
	 * Cauta un registru in baza de date primind ca parametru membrul echipei
	 * @param team
	 */
	Echipa findByUser(Utilizator team);
}
