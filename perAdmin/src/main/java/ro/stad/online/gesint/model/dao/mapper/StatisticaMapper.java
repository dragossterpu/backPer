package ro.stad.online.gesint.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ro.stad.online.gesint.model.dto.statistica.StatisticaDTO;

/**
 * Clasa Mapper de rapoarte statistice care transfera conținutul BD la un DTO.
 *
 * @author STAD
 *
 */
public final class StatisticaMapper implements RowMapper<StatisticaDTO> {

        /**
         * Conversia unui rând de rapoarte de interogări pe corp la o clasă dto.
         *
         * @return fila stadistica
         */
        @Override
        public StatisticaDTO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final StatisticaDTO dto = new StatisticaDTO();

                dto.setNumarTotal(rs.getInt("numarTotal"));
                dto.setTotalcuLiceu(rs.getInt("totalcuLiceu"));
                dto.setTotalStudiiSup(rs.getInt("totalStudiiSup"));
                dto.setTotalBazice(rs.getInt("totalBazice"));
                dto.setLocuitoriTotal(rs.getInt("locuitoriTotal"));
                dto.setTotalBarbati(rs.getInt("totalBarbati"));
                dto.setTotalFemei(rs.getInt("totalFemei"));
                dto.setTotalVot(rs.getInt("totalVot"));
                dto.setTotalUltimaLuna(rs.getInt("totalUltimaLuna"));
                dto.setTotalUltimDouaLuni(rs.getInt("totalUltimDouaLuni"));
                dto.setTotalUltimTreiLuni(rs.getInt("totalUltimTreiLuni"));
                dto.setTotalUltimPatruLuni(rs.getInt("totalUltimPatruLuni"));
                dto.setTotalUltimCinciLuni(rs.getInt("totalUltimCinciLuni"));
                dto.setTotalUltimSaseLuni(rs.getInt("totalUltimSaseLuni"));
                dto.setTotalUltimSapteLuni(rs.getInt("totalUltimSapteLuni"));
                dto.setTotalUltimOptLuni(rs.getInt("totalUltimOptLuni"));
                dto.setTotalUltimNouaLuni(rs.getInt("totalUltimNouaLuni"));
                dto.setTotalUltimZeceLuni(rs.getInt("totalUltimZeceLuni"));
                dto.setTotalUltimUnspeLuni(rs.getInt("totalUltimUnspeLuni"));
                dto.setTotalUltimAn(rs.getInt("totalUltimAn"));
                dto.setTotalUltimaLunaAnAtras(rs.getInt("totalUltimaLunaAnAtras"));
                dto.setTotalUltimDouaLuniAnAtras(rs.getInt("totalUltimDouaLuniAnAtras"));
                dto.setTotalUltimTreiLuniAnAtras(rs.getInt("totalUltimTreiLuniAnAtras"));
                dto.setTotalUltimPatruLuniAnAtras(rs.getInt("totalUltimPatruLuniAnAtras"));
                dto.setTotalUltimCinciLuniAnAtras(rs.getInt("totalUltimCinciLuniAnAtras"));
                dto.setTotalUltimSaseLuniAnAtras(rs.getInt("totalUltimSaseLuniAnAtras"));
                dto.setTotalUltimSapteLuniAnAtras(rs.getInt("totalUltimSapteLuniAnAtras"));
                dto.setTotalUltimOptLuniAnAtras(rs.getInt("totalUltimOptLuniAnAtras"));
                dto.setTotalUltimNouaLuniAnAtras(rs.getInt("totalUltimNouaLuniAnAtras"));
                dto.setTotalUltimZeceLuniAnAtras(rs.getInt("totalUltimZeceLuniAnAtras"));
                dto.setTotalUltimUnspeLuniAnAtras(rs.getInt("totalUltimUnspeLuniAnAtras"));
                dto.setTotalUltimiDoiAni(rs.getInt("totalUltimiDoiAni"));
                dto.setTotalUltimiiTreiAni(rs.getInt("totalUltimiiTreiAni"));
                dto.setTotalUltimiiPatruAni(rs.getInt("totalUltimiiPatruAni"));
                dto.setTotalUltimiiCinciAni(rs.getInt("totalUltimiiCinciAni"));
                dto.setMediuRural(rs.getInt("mediuRural"));
                dto.setMediuUrban(rs.getInt("mediuUrban"));
                dto.setLocuitoriTotalRural(rs.getInt("locuitoriTotalRural"));
                dto.setLocuitoriTotalUrban(rs.getInt("locuitoriTotalUrban"));
                dto.setTotalLuna12(rs.getInt("totalLuna12"));
                dto.setTotalPana25(rs.getInt("totalPana25"));
                dto.setTotalPanaBarbati25(rs.getInt("totalBarbati25"));
                dto.setTotalPana40(rs.getInt("totalPana40"));
                dto.setTotalPanaBarbati40(rs.getInt("totalBarbati40"));
                dto.setTotalPana60(rs.getInt("totalPana60"));
                dto.setTotalPanaBarbati60(rs.getInt("totalBarbati60"));
                dto.setTotalPanaMayor60(rs.getInt("totalMayor60"));
                dto.setTotalAntCinciAni(rs.getInt("totalAntCinciAni"));
                return dto;
        }

}
