package ro.stad.online.gesint.services;

import java.util.List;

import org.primefaces.model.SortOrder;

import ro.stad.online.gesint.model.filters.FiltruRezultat;
import ro.stad.online.gesint.persistence.entities.PartidRezultateJudete;
import ro.stad.online.gesint.persistence.entities.PartidRezultateLocalitate;

/**
 * Declarația metodelor care vor fi utilizate pentru persistența rezultatelor de la alegeri.
 * @author STAD
 *
 */
public interface RezultatService {

        /**
         * Cauta rezultate cu parametrii de cautare.
         * @param filtruRezultat FiltruRezultat
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         *
         * @return List<PartidRezultateJudete>
         *
         *
         */
        List<PartidRezultateJudete> cautareRezultatCriteria(int first, int pageSize, String sortField,
                        SortOrder sortOrder, FiltruRezultat filtruRezultat);

        /**
         * Cauta rezultate cu parametrii de cautare.
         * @param filtruRezultat FiltruRezultat
         * @param sortOrder SortOrder
         * @param sortField String
         * @param pageSize int
         * @param first int
         *
         * @return List<PartidRezultateLocalitate>
         *
         *
         */
        List<PartidRezultateLocalitate> cautareRezultatCriteriaLoc(int first, int pageSize, String sortField,
                        SortOrder sortOrder, FiltruRezultat filtruRezultat);

        /**
         * Cauta rani alegerilor
         * @return List<Integer>
         */
        List<Integer> cautaAni();

        /**
         * Obtine numarul de registre de criteria.
         * @param busqueda FiltruRezultat
         * @return int
         */
        int getCounCriteria(FiltruRezultat busqueda);

        /**
         * Salveaza rezultatul alegerilor.
         * @param rezultat PartidRezultateJudete
         * @return PartidRezultateJudete actualizata
         */
        PartidRezultateJudete save(PartidRezultateJudete rezultat);

        /**
         * Cauta un partid
         * @param idPartid Long
         * @return partid Partid
         */
        PartidRezultateJudete fiindOne(Long idPartid);

}
