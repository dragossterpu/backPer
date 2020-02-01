package ro.stad.online.gesint.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ro.stad.online.gesint.model.dto.statistica.RezultateDTO;

/**
 * Clasa Mapper de rapoarte statistice care transfera conținutul BD la un DTO.
 *
 * @author STAD
 *
 */
public final class RezultateMapper implements RowMapper<RezultateDTO> {

        /**
         * Conversia unui rând de rapoarte de interogări pe corp la o clasă dto.
         *
         * @return fila stadistica
         */
        @Override
        public RezultateDTO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final RezultateDTO dto = new RezultateDTO();
                dto.setNume(rs.getString("nume"));
                dto.setMandatePartid(rs.getInt("mandatePartid"));
                dto.setTotalMandate(rs.getInt("totalMandate"));
                dto.setTotalVoturi(rs.getInt("totalVoturi"));
                dto.setVoturiPartid(rs.getInt("voturiPartid"));
                dto.setSigla(rs.getString("sigla"));
                dto.setId(rs.getLong("id"));
                return dto;
        }

}
