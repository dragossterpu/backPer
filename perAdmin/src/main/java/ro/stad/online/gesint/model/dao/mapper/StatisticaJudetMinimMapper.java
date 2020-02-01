package ro.stad.online.gesint.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ro.stad.online.gesint.model.dto.statistica.StatisticaJudetMinimDTO;

/**
 * Clasa Mapper de rapoarte statistice care transfera conținutul BD la un DTO.
 *
 * @author STAD
 *
 */
public final class StatisticaJudetMinimMapper implements RowMapper<StatisticaJudetMinimDTO> {

        /**
         * Conversia unui rând de rapoarte de interogări pe corp la o clasă dto.
         *
         * @return fila stadistica
         */
        @Override
        public StatisticaJudetMinimDTO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final StatisticaJudetMinimDTO dto = new StatisticaJudetMinimDTO();
                dto.setMembriiMinim(rs.getInt("membrii_minim"));
                dto.setVoturiMinim(rs.getInt("voturi_minim"));
                return dto;
        }

}
