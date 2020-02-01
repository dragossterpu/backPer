package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Intrebare;

/**
 * Depozitul operațiunilor bazei de date pentru entitatea Intrebare
 *
 * @author ATOS
 *
 */
public interface IntrebareRepository extends CrudRepository<Intrebare, Long> {

}
