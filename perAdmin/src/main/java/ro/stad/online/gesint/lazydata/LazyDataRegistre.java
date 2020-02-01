package ro.stad.online.gesint.lazydata;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.model.filters.FiltruRegistru;
import ro.stad.online.gesint.persistence.entities.RegistruActivitate;
import ro.stad.online.gesint.services.RegistruActivitateService;

/**
 * 
 * Modelo para paginación desde servidor extendiendo el modelo LazyDataModel de Primefaces.
 * 
 * @author STAD
 *
 */

@Setter
@Getter
public class LazyDataRegistre extends LazyDataModel<RegistruActivitate> implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Objeto que contiene los parámetros de búsqueda.
         */
        private transient FiltruRegistru busqueda;

        /**
         * Lista que sirve al modelo como fuente de datos.
         */
        private transient List<RegistruActivitate> datasource;

        /**
         * Servicio a usar.
         */
        private transient RegistruActivitateService registruActivitateService;

        /**
         * 
         * Constructor del modelo que recibe el servicio como parámetro.
         * 
         * @param servicio Servicio a emplear
         */
        public LazyDataRegistre(final RegistruActivitateService servicio) {
                this.registruActivitateService = servicio;
        }

        /**
         * Sobreescritura del método getRowData para que funcione con objetos de tipo RegistruActivitate.
         * 
         * @param rowKey Clave de la fila sobre la que se ha hecho click en la vista
         * @return Registro que se corresponde con la clave recibida por parámetro
         * 
         */
        @Override
        public RegistruActivitate getRowData(final String rowKey) {
                RegistruActivitate reg = null;
                for (final RegistruActivitate registro : datasource) {
                        if (registro.getIdRegActividad().toString().equals(rowKey)) {
                                reg = registro;
                        }
                }
                return reg;
        }

        /**
         * Sobrescritura del método getRowKey.
         * 
         * @param solicitud Objeto del que se desea obtener la clave
         * @return Clave del objeto pasado como parámetro
         */

        @Override
        public Object getRowKey(final RegistruActivitate solicitud) {
                return solicitud.getIdRegActividad();
        }

        /**
         * Sobreescritura del método Load para que funcione con un critera y sólo nos devuelva estríctamente el número
         * de registros solicitados.
         * 
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
        public List<RegistruActivitate> load(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final Map<String, Object> filters) {
                List<RegistruActivitate> listado = null;
                if (busqueda == null) {
                        this.setRowCount(0);
                }
                else {
                        this.setRowCount(registruActivitateService.getCounCriteria(busqueda));
                        listado = registruActivitateService.cautareRegistruActivitateCriteria(first, pageSize, sortField,
                                        sortOrder, busqueda);
                        this.datasource = listado;
                }
                return listado;

        }
}
