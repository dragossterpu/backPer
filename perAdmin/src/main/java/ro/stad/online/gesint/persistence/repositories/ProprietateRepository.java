package ro.stad.online.gesint.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ro.stad.online.gesint.persistence.entities.Proprietate;

/**
 * Repositorio de operații de bază de date pentru entitatea Proprietate.
 * 
 * @author STAD
 *
 */
public interface ProprietateRepository extends CrudRepository<Proprietate, Long> {

        /**
         * Devuelve lista de proprietati ordonate pentru numele ascendent.
         * @param filename numele proprietati
         * @return List<Proprietate> lista de proprietati.
         */
        List<Proprietate> findByFilename(String filename);

        /**
         * Gaseste un registru dupa nume.
         * @param numeParametru String
         * @return Parametro
         */
        Proprietate findOneByName(String numeParametru);

}
