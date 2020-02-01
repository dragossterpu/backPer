package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Alerta;

/**
 * Repositoriu de alerte.
 * @author STAD
 *
 */
public interface AlertaRepository extends CrudRepository<Alerta, Long> {

}
