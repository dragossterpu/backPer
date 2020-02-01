package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ro.stad.online.gesint.persistence.entities.RegistruActivitate;

/**
 * Repositoriu registrului de activitate.
 * @author STAD
 *
 */
public interface RegistruActivitateRepository extends CrudRepository<RegistruActivitate, Long> {

        /**
         * Returnări sau listă de jurnal de activitate al căror număr coincide cu un șir primar.
         * @param info Usernameul utilizatorului care a inregistrar
         * @return Listă de nume de utilizator ale căror nume corespund unui anumit șir
         *
         */
        @Query("SELECT DISTINCT(usernameRegActividad) FROM RegistruActivitate WHERE UPPER(usernameRegActividad) LIKE UPPER(:info) ORDER BY usernameRegActividad ")
        List<String> cautareUtilizatorDupaRegistru(@Param("info") String info);

}
