package ro.stad.online.gesint.services;

import java.util.List;

import org.primefaces.model.SortOrder;

import ro.stad.online.gesint.model.filters.FiltruSondaj;
import ro.stad.online.gesint.persistence.entities.Sondaj;

/**
 * Declarația metodelor care vor fi utilizate pentru persistența sondajelor.
 * @author STAD
 *
 */
public interface SondajService {

        /**
         * Cauta sondaje cu parametrii de cautare.
         * @param filtruSondaj FiltruSondaj
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         *
         * @return List<Sndaj>
         *
         *
         */
        List<Sondaj> cautareSondajeCriteria(int first, int pageSize, String sortField, SortOrder sortOrder,
                        FiltruSondaj filtruSondaj);

        /**
         * Cauta un sondaj
         * @param sondaj Sondaj
         * @return sondaj Sondaj
         */
        Sondaj fiindOne(Sondaj sondaj);

        /**
         * Obtiene el conteo de criteria.
         * @param cautare FiltruSondaj
         * @return int
         */
        int getCounCriteria(FiltruSondaj cautare);

        /**
         * Inregistreaza un sondaj.
         * @param sondaj Sondaj
         * @return Sondaj actualizat
         */
        Sondaj save(Sondaj sondaj);

}
