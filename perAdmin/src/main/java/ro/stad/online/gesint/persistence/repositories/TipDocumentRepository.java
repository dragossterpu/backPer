package ro.stad.online.gesint.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.TipDocument;

/**
 * 
 * Repository cu operațiunile BDD pentru documentul de entitate TipDocument.
 * 
 * @author STAD
 *
 */
public interface TipDocumentRepository extends CrudRepository<TipDocument, Long> {

        /**
         * Returnează de la BDD un obiect TypeDocument identificat prin numele său.
         * @param nombre Numele tipului de document
         * @return Obiectul stocat în baza de date
         */
        TipDocument findByNume(String nume);

}
