package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.PartidRezultateJudete;

/**
 * Repositoriu de rezultate.
 * @author STAD
 *
 */
public interface RezultatRepository extends CrudRepository<PartidRezultateJudete, Long> {
        /**
         * Cauta anii alegerilor
         * @return List<Integer>
         */
        @Query(value = "select distinct an_alegeri from partid_rezultat_judet", nativeQuery = true)
        List<Integer> cautaAni();

}
