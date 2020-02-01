package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Sondaj;

/**
 * Repositoriu de sondaje.
 * @author STAD
 *
 */
public interface SondajRepository extends CrudRepository<Sondaj, Long> {

}
