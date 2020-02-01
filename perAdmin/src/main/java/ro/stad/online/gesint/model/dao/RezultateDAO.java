package ro.stad.online.gesint.model.dao;

import java.util.List;

import ro.stad.online.gesint.model.dto.statistica.RezultateDTO;
import ro.stad.online.gesint.model.filters.FiltruRezultat;

/**
 * Interfața metodelor statistice.
 *
 * @author STAD
 *
 */
public interface RezultateDAO extends GesintDAO {

        /**
         * Metoda de filtrare pentru generale
         * @param filter filtru
         * @return Lista de rezultate
         */
        List<RezultateDTO> filterGeneraleRezultate(FiltruRezultat filter);

}
