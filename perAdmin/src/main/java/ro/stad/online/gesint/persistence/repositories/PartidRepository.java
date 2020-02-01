package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Partid;

/**
 * Repositoriu pentru entitatea Partid.
 * @author STAD
 */
public interface PartidRepository extends CrudRepository<Partid, Long> {

	/**
	 * @return List<Partid>
	 *
	 */
	@Query(value = "select distinct prj.partid  as id, p.nume,p.independent,p.sigla  from partid_rezultat_judet prj, partid p where prj.partid= p.id order by partid asc", nativeQuery = true)
	List<Partid> fiindPartidJudet();

}
