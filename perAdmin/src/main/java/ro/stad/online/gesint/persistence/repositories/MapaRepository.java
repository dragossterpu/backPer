package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Mapa;

/**
 * Repositoriu pentru entitatea Mapa.
 * @author STAD
 */
public interface MapaRepository extends CrudRepository<Mapa, Long> {

}
