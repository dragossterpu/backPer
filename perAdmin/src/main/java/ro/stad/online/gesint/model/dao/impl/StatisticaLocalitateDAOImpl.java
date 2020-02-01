package ro.stad.online.gesint.model.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ro.stad.online.gesint.model.dao.StatisticaLocalitateDAO;
import ro.stad.online.gesint.model.dao.mapper.StatisticaLocalitateMapper;
import ro.stad.online.gesint.model.dto.statistica.StatisticaLocalitateDTO;
import ro.stad.online.gesint.model.filters.FiltruStatisticaJudete;

/**
 * Clasa care implementează metodele de obținere a datelor de statistici.
 *
 * @author STAD
 *
 */
@Repository
public class StatisticaLocalitateDAOImpl implements StatisticaLocalitateDAO {

        /**
         * namedParameterJdbcTemplate.
         *
         */
        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

        /**
         * Datele statisticii generale.
         * @param FiltruStatisticaJudete filter
         * @return List<StatisticaJudetDTO>
         */
        @Override
        public List<StatisticaLocalitateDTO> filterStatisticaLocalitateProcentaj(final FiltruStatisticaJudete filter) {
                final StringBuilder sql = new StringBuilder();

                sql.append("SELECT  totalMembrii,numeprovincie, cod_judet,nume,id, sector,  locuitori,membrii_minim,voturi_minim,numero, locuitoriVot,procentaj"
                                + " FROM (select fff.totalMembrii,fff.numeprovincie,fff.cod_judet,fff.name,fff.id,fff.sector,fff.locuitori,fff.membrii_minim,fff.voturi_minim,"
                                + " fff.numero,fff.locuitoriVot,ROUND((fff.numero*100.0)/(fff.membrii_minim),2) AS procentaj FROM ( "
                                + " SELECT  totalMembrii,numeprovincie, cod_judet,name,id, sector,  locuitori,membrii_minim,voturi_minim,numero, locuitoriVot"
                                + " FROM ( SELECT (select count(*) from users where cod_judet='"
                                + filter.getCode_province()
                                + "') as totalMembrii, (select name from judet where cod_judet='"
                                + filter.getCode_province() + "') as numeprovincie," + "'" + filter.getCode_province()
                                + "' as cod_judet,  ss.name, ss.id, ss.sector, ss.locuitori,ss.membrii_minim,ss.voturi_minim, "
                                + " (select count(*) from users where cod_judet='" + filter.getCode_province()
                                + "' and locality_id = ss.id) as numero,"
                                + " ( select ROUND(ss.locuitori-(ss.locuitori*0.21)) as locuitori) as locuitoriVot"
                                + " FROM ( SELECT l.name,l.id,l.locuitori,l.sector, l.membrii_minim, l.voturi_minim FROM USERS U, localitate l  WHERE l.cod_judet='"
                                + filter.getCode_province() + "'"
                                + " GROUP BY l.id, l.name, l.locuitori,l.sector , l.membrii_minim, l.voturi_minim ORDER BY voturi_minim desc) as SS) as dd ORDER BY dd.voturi_minim DESC) as fff"
                                + " ) as pp ORDER BY pp.procentaj DESC");

                final MapSqlParameterSource parameters = new MapSqlParameterSource();

                parameters.addValue("fechaDesde", filter.getFechaDesde());

                return namedParameterJdbcTemplate.query(sql.toString(), parameters, new StatisticaLocalitateMapper());
        }

        /**
         * Sursa de date.
         *
         */
        @Override
        @Autowired
        public void setDataSource(final DataSource ds) {
                this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
        }

}
