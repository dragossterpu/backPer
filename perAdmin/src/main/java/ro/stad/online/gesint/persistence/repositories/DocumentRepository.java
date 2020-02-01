package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documentul;

/**
 * Repository de operațiuni de bază de date pentru entitatea documentului.
 *
 * @author STAD
 *
 */
public interface DocumentRepository extends CrudRepository<Documentul, Long> {

        /**
         * Returnează documentele care corespund tipului de document.
         * @param tipo Numele tipului de document
         * @return Lista documentelor
         */
        @Query("select a from Documentul a, TipDocument b where a.tipDocument=b.id and b.nume=?1")
        List<Documentul> cautaNumeTipDocument(String tip);

        /**
         * Eliminați toate înregistrările a căror dată de eliminare nu este nulă.
         */
        @Transactional(readOnly = false)
        void deleteByDateDeletedIsNotNull();

        /**
         * Căutați toate documentele care nu au fost eliminate logic.
         * @return Lista documentelor selectate
         */
        List<Documentul> findByDateDeletedIsNotNull();

        /**
         * Căutați toate documentele care au fost eliminate logic.
         * @return Lista documentelor selectate
         */
        List<Documentul> findByDateDeletedIsNull();

        /**
         * Returnează un document localizat după id-ul său.
         * @param id Long Identificatorul documentului
         * @return Documentul Documentul
         */
        @EntityGraph(value = "Documentul.fichero", type = EntityGraph.EntityGraphType.LOAD)
        Documentul findById(Long id);

        /**
         * Returneaza lista cu documentele anexate alertei
         * @param alerta
         * @return
         *
         */
        List<Documentul> findByAlerta(Alerta alerta);

}
