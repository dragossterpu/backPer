package ro.stad.online.gesint.model.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ro.stad.online.gesint.model.dao.StatisticaDAO;
import ro.stad.online.gesint.model.dao.mapper.StatisticaMapper;
import ro.stad.online.gesint.model.dto.statistica.StatisticaDTO;
import ro.stad.online.gesint.model.filters.FiltruStatistica;

/**
 * Clasa care implementează metodele de obținere a datelor de statistici.
 *
 * @author STAD
 *
 */
@Repository
public class StatisticaDAOImpl implements StatisticaDAO {

        /**
         * namedParameterJdbcTemplate.
         *
         */
        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

        /**
         * Sursa de date.
         *
         */
        @Override
        @Autowired
        public void setDataSource(final DataSource ds) {
                this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
        }

        /**
         * Datele statisticii generale.
         *
         * @return
         */
        @Override
        public List<StatisticaDTO> filterGeneraleStatistica(final FiltruStatistica filter) {

                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                final StringBuilder sql = new StringBuilder();

                sql.append(" SELECT COUNT(*) AS numarTotal,");
                sql.append("(SELECT COUNT(*) FROM users WHERE education= 'LICEU') AS totalcuLiceu,");
                sql.append("(SELECT COUNT(*) FROM users WHERE education= 'STUDIISUPERIOARE') AS totalStudiiSup,");
                sql.append("(SELECT COUNT(*) FROM users WHERE education= 'BAZIC') AS totalBazice,");
                sql.append("(SELECT SUM(populatie)FROM judet)AS locuitoriTotal,");
                sql.append("(SELECT COUNT(*) FROM users WHERE sex= 'MAN') AS totalBarbati,");
                sql.append("(SELECT COUNT(*) FROM users WHERE sex= 'WOMAN') AS totalFemei,");
                sql.append("(SELECT DISTINCT ROUND((SELECT SUM(populatie)FROM judet)- ((SELECT SUM(populatie)FROM judet)*0.2)) AS locuitori FROM judet ) AS totalVot,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimaLuna()) + "' AND DATE '"
                                + sdf.format(filter.getDataIncepand()) + "') AS totalUltimaLuna,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimDouaLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimaLuna()) + "') AS totalUltimDouaLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimTreiLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimDouaLuni()) + "') AS totalUltimTreiLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimPatruLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimTreiLuni()) + "') AS totalUltimPatruLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimCinciLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimPatruLuni()) + "') AS totalUltimCinciLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimSaseLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimCinciLuni()) + "') AS totalUltimSaseLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimSapteLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimSaseLuni()) + "') AS totalUltimSapteLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimOptLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimSapteLuni()) + "') AS totalUltimOptLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimNouaLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimOptLuni()) + "') AS totalUltimNouaLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimZeceLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimNouaLuni()) + "') AS totalUltimZeceLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimUnspeLuni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimZeceLuni()) + "') AS totalUltimUnspeLuni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimUnspeLuni()) + "') AS totalLuna12,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "' AND DATE '"
                                + sdf.format(filter.getDataIncepand()) + "') AS totalUltimAn,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimaLunaAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimaLunaAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimDouaLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimDouaLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimTreiLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimTreiLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimPatruLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimPatruLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimCinciLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimCinciLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimSaseLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimSaseLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimSapteLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimSapteLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimOptLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimOptLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimNouaLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimNouaLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimZeceLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimZeceLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimUnspeLuniAnTrecut()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimUnspeLuniAnAtras,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimiDoiAni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimAn()) + "') AS totalUltimiDoiAni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimiTreiAni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimiDoiAni()) + "') AS totalUltimiiTreiAni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimiPatruAni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimiTreiAni()) + "') AS totalUltimiiPatruAni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create BETWEEN DATE '"
                                + sdf.format(filter.getDataUltimiCinciAni()) + "' AND DATE '"
                                + sdf.format(filter.getDataUltimiPatruAni()) + "') AS totalUltimiiCinciAni,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE DATE_create <= '"
                                + sdf.format(filter.getDataUltimiCinciAni()) + "') AS totalAntCinciAni,");

                sql.append("(SELECT COUNT(*)  FROM users WHERE birth_date BETWEEN DATE '"
                                + sdf.format(filter.getDataPana25()) + "' AND DATE '"
                                + sdf.format(filter.getDataIncepand()) + "') AS totalPana25,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE  birth_date BETWEEN DATE '"
                                + sdf.format(filter.getDataPana25()) + "' AND DATE '"
                                + sdf.format(filter.getDataIncepand()) + "' and sex= 'MAN') AS totalBarbati25,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE  birth_date BETWEEN DATE '"
                                + sdf.format(filter.getDataPana40()) + "' AND DATE '"
                                + sdf.format(filter.getDataPana25()) + "') AS totalPana40,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE  birth_date BETWEEN DATE '"
                                + sdf.format(filter.getDataPana40()) + "' AND DATE '"
                                + sdf.format(filter.getDataPana25()) + "' and sex= 'MAN') AS totalBarbati40,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE birth_date BETWEEN DATE '"
                                + sdf.format(filter.getDataPana60()) + "' AND DATE '"
                                + sdf.format(filter.getDataPana40()) + "') AS totalPana60,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE  birth_date BETWEEN DATE '"
                                + sdf.format(filter.getDataPana60()) + "' AND DATE '"
                                + sdf.format(filter.getDataPana40()) + "' and sex= 'MAN') AS totalBarbati60,");
                sql.append("(SELECT COUNT(*)  FROM users WHERE birth_date <= '" + sdf.format(filter.getDataPana60())
                                + "') AS totalMayor60,");
                sql.append("(SELECT COUNT(*) FROM users u, localitate l WHERE  u.LOCALITY_ID = l.id AND l.nivel =3)AS mediuRural,");
                sql.append("(SELECT COUNT(*) FROM users u, localitate l WHERE  u.LOCALITY_ID = l.id AND l.nivel in(2,1))AS mediuUrban,");
                sql.append("(SELECT SUM(locuitori) FROM localitate WHERE nivel =3) AS locuitoriTotalRural, ");
                sql.append("(SELECT SUM(locuitori) FROM localitate WHERE nivel in(2,3)) AS locuitoriTotalUrban ");
                sql.append("FROM users ");

                final MapSqlParameterSource parameters = new MapSqlParameterSource();

                parameters.addValue("dataIncepand", filter.getDataIncepand());
                parameters.addValue("dataUltimAn", filter.getDataUltimAn());
                parameters.addValue("dataUltimaLuna", filter.getDataUltimaLuna());
                parameters.addValue("dataUltimDouaLuni", filter.getDataUltimDouaLuni());
                parameters.addValue("dataUltimTreiLuni", filter.getDataUltimTreiLuni());
                parameters.addValue("dataUltimPatruLuni", filter.getDataUltimPatruLuni());
                parameters.addValue("dataUltimCinciLuni", filter.getDataUltimCinciLuni());
                parameters.addValue("dataUltimSaseLuni", filter.getDataUltimSaseLuni());
                parameters.addValue("dataUltimSapteLuni", filter.getDataUltimSapteLuni());
                parameters.addValue("dataUltimOptLuni", filter.getDataUltimOptLuni());
                parameters.addValue("dataUltimNouaLuni", filter.getDataUltimNouaLuni());
                parameters.addValue("dataUltimZeceLuni", filter.getDataUltimZeceLuni());
                parameters.addValue("dataUltimUnspeLuni", filter.getDataUltimUnspeLuni());
                parameters.addValue("dataUltimaLunaAnTrecut", filter.getDataUltimaLunaAnTrecut());
                parameters.addValue("dataUltimaDouaLuniAnTrecut", filter.getDataUltimDouaLuniAnTrecut());
                parameters.addValue("dataUltimaTreiLuniAnTrecut", filter.getDataUltimTreiLuniAnTrecut());
                parameters.addValue("dataUltimaPatruLuniAnTrecut", filter.getDataUltimPatruLuniAnTrecut());
                parameters.addValue("dataUltimaCinciLuniAnTrecut", filter.getDataUltimCinciLuniAnTrecut());
                parameters.addValue("dataUltimaSaseLuniAnTrecut", filter.getDataUltimSaseLuniAnTrecut());
                parameters.addValue("dataUltimaSapteLuniAnTrecut", filter.getDataUltimSapteLuniAnTrecut());
                parameters.addValue("dataUltimaOptLuniAnTrecut", filter.getDataUltimOptLuniAnTrecut());
                parameters.addValue("dataUltimaNouaLuniAnTrecut", filter.getDataUltimNouaLuniAnTrecut());
                parameters.addValue("dataUltimaZeceLuniAnTrecut", filter.getDataUltimZeceLuniAnTrecut());
                parameters.addValue("dataUltimaUnspeLuniAnTrecut", filter.getDataUltimUnspeLuniAnTrecut());
                parameters.addValue("dataUltimiDoiAni", filter.getDataUltimiDoiAni());
                parameters.addValue("dataUltimiTreiAni", filter.getDataUltimiTreiAni());
                parameters.addValue("dataUltimiPatruAni", filter.getDataUltimiPatruAni());
                parameters.addValue("dataUltimiCinciAni", filter.getDataUltimiCinciAni());
                return namedParameterJdbcTemplate.query(sql.toString(), parameters, new StatisticaMapper());
        }

}
