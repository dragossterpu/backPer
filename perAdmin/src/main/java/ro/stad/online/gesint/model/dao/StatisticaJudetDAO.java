package ro.stad.online.gesint.model.dao;

import java.util.List;

import ro.stad.online.gesint.model.dto.statistica.StatisticaJudetDTO;
import ro.stad.online.gesint.model.dto.statistica.StatisticaJudetMinimDTO;
import ro.stad.online.gesint.model.filters.FiltruStatisticaJudete;

/**
 * Interfața metodelor statistice.
 *
 * @author STAD
 *
 */
public interface StatisticaJudetDAO extends GesintDAO {

        /**
         * Metoda de obtinere a datelor minim
         * @return StatisticaJudetMinimDTO
         */
        List<StatisticaJudetMinimDTO> dateMinime();

        /**
         * Metoda de filtrare pentru generale
         * @param filter filtru
         * @return Lista de estadistica
         */
        List<StatisticaJudetDTO> filterStatisticaJudet(FiltruStatisticaJudete filter);

        /**
         * Metoda de filtrare pentru generale
         * @param filter filtru
         * @return Lista de estadistica
         */
        List<StatisticaJudetDTO> filterStatisticaJudetProcentaj(FiltruStatisticaJudete filter);
}
