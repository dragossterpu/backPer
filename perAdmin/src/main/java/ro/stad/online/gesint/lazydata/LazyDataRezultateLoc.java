package ro.stad.online.gesint.lazydata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.model.filters.FiltruRezultat;
import ro.stad.online.gesint.persistence.entities.PartidRezultateLocalitate;
import ro.stad.online.gesint.services.RezultatService;

/**
 * Model pentru paginarea de pe server care extinde modelul LazyDataModel de Primefaces.
 * @author STAD
 */

@Setter
@Getter
public class LazyDataRezultateLoc extends LazyDataModel<PartidRezultateLocalitate> implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Obiect care conține parametrii de căutare.
         */
        private transient FiltruRezultat busqueda;

        /**
         * Lista que sirve al modelo como fuente de datos.
         */
        private transient List<PartidRezultateLocalitate> datasource;

        /**
         * Serviciur.
         */
        private transient RezultatService rezultatService;

        /**
         * Constructor del modelo que recibe el servicio como parámetro.
         * @param rezultatService Servicio a emplear
         */
        public LazyDataRezultateLoc(final RezultatService rezultatService) {
                this.rezultatService = rezultatService;
        }

        /**
         * Sobreescritura del método getRowData para que funcione con objetos de tipo PartidRezultateJudete.
         * @param rowKey Clave de la fila sobre la que se ha hecho click en la vista
         * @return PartidRezultate que se corresponde con la clave recibida por parámetro
         *
         */
        @Override
        public PartidRezultateLocalitate getRowData(final String rowKey) {
                PartidRezultateLocalitate rezultate = null;
                for (final PartidRezultateLocalitate rezultat : datasource) {
                        if (rezultat.getId().toString().equals(rowKey)) {
                                rezultate = rezultat;
                        }
                }
                return rezultate;
        }

        /**
         * Sobrescritura del método getRowKey.
         * @param rezultat Objeto del que se desea obtener la clave
         * @return Clave del objeto pasado como parámetro
         */

        @Override
        public Object getRowKey(final PartidRezultateLocalitate rezultat) {
                return rezultat.getId();
        }

        /**
         * Sobreescritura del método Load para que funcione con un critera y sólo nos devuelva estríctamente el número
         * de los rezultate solicitadas.
         * @param first primer elemento que se desea recuperar
         * @param pageSize número máximo de registros que deseamos recuperar por página
         * @param sortField columna por la que se ordenarán los resultados. Corresponde a la propiedad 'sortBy' de la
         * columna de la vista
         * @param sortOrder orden por el que se desea ordenar los resultados
         * @param filters mapa de filtros. Este valor no se utiliza en esta sobreescritura.
         * @return lista de registros que corresponden a los criterios de búsqueda
         *
         */

        @Override
        public List<PartidRezultateLocalitate> load(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final Map<String, Object> filters) {
                List<PartidRezultateLocalitate> lista = new ArrayList<>();
                if (busqueda == null) {
                        this.setRowCount(0);
                }
                else {
                        this.setRowCount(rezultatService.getCounCriteria(busqueda));
                        lista = rezultatService.cautareRezultatCriteriaLoc(first, pageSize, sortField, sortOrder,
                                        busqueda);
                        this.datasource = lista;
                }
                return lista;

        }

}
