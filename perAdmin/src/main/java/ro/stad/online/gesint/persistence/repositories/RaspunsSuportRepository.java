package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Intrebare;
import ro.stad.online.gesint.persistence.entities.RaspunsSuport;

/**
 * Depozitul operațiunilor bazei de date pentru entitatea RaspunsSuport
 *
 * @author ATOS
 *
 */
public interface RaspunsSuportRepository extends CrudRepository<RaspunsSuport, Long> {

        /**
         * Returneaza raspunsul intrebarii
         * @param intrebare
         * @return
         */
        RaspunsSuport findByIntrebare(Intrebare intrebare);

}
