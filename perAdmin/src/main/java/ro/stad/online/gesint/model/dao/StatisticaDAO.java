package ro.stad.online.gesint.model.dao;

import java.util.List;

import ro.stad.online.gesint.model.dto.statistica.StatisticaDTO;
import ro.stad.online.gesint.model.filters.FiltruStatistica;

/**
 * Interfața metodelor statistice.
 *
 * @author STAD
 *
 */
public interface StatisticaDAO extends GesintDAO {

        /**
         * Metoda de filtrare pentru generale
         * @param filter filtru
         * @return Lista de estadistica
         */
        List<StatisticaDTO> filterGeneraleStatistica(FiltruStatistica filter);

}
