package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Optiune;
import ro.stad.online.gesint.persistence.entities.Provincia;

/**
 * Repositoriu pentru entitatea Optiune.
 * @author STAD
 */
public interface OptiuneRepository extends CrudRepository<Optiune, Long> {

        /**
         * Cauta toate optiunile inregistrate ale unui judet
         * @return List<Optiune>)
         */
        List<Optiune> findByProvince(Provincia code);

}
