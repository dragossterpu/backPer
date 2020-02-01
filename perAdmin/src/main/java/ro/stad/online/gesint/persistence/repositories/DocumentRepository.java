package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documente;

/**
 * Repository de operațiuni de bază de date pentru entitatea documentului.
 *
 * @author STAD
 *
 */
public interface DocumentRepository extends CrudRepository<Documente, Long> {

        /**
         * Returnează documentele care corespund tipului de document.
         * @param tipo Numele tipului de document
         * @return Lista documentelor
         */
        @Query("select a from Documente a, TipDocument b where a.tipDocument=b.id and b.nume=?1")
        List<Documente> cautaNumeTipDocument(String tip);

        /**
         * Eliminați toate înregistrările a căror dată de eliminare nu este nulă.
         */
        @Transactional(readOnly = false)
        void deleteByDateDeletedIsNotNull();

        /**
         * Căutați toate documentele care nu au fost eliminate logic.
         * @return Lista documentelor selectate
         */
        List<Documente> findByDateDeletedIsNotNull();

        /**
         * Căutați toate documentele care au fost eliminate logic.
         * @return Lista documentelor selectate
         */
        List<Documente> findByDateDeletedIsNull();

        /**
         * Returnează un document localizat după id-ul său.
         * @param id Long Identificatorul documentului
         * @return Documente Documente
         */
        @EntityGraph(value = "Documente.fichero", type = EntityGraph.EntityGraphType.LOAD)
        Documente findById(Long id);

        /**
         * Returneaza lista cu documentele anexate alertei
         * @param alerta
         * @return
         *
         */
        List<Documente> findByAlerta(Alerta alerta);

}
