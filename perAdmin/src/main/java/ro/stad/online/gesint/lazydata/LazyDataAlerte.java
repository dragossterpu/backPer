package ro.stad.online.gesint.lazydata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.model.filters.FiltruAlerta;
import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.services.AlertaService;

/**
 * Model pentru paginarea de pe server care extinde modelul LazyDataModel de Primefaces.
 * @author STAD
 */

@Setter
@Getter
public class LazyDataAlerte extends LazyDataModel<Alerta> implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Obiect care conține parametrii de căutare.
         */
        private transient FiltruAlerta filtruAlerta;

        /**
         * Lista que sirve al modelo como fuente de datos.
         */
        private transient List<Alerta> datasource;

        /**
         * Servicio a usar.
         */
        private transient AlertaService alertaService;

        /**
         * Constructor del modelo que recibe el servicio como parámetro.
         * @param servicio Servicio a emplear
         */
        public LazyDataAlerte(final AlertaService servicio) {
                this.alertaService = servicio;
        }

        /**
         * Sobreescritura del método getRowData para que funcione con objetos de tipo Alerta.
         * @param rowKey Clave de la fila sobre la que se ha hecho click en la vista
         * @return Alerta que se corresponde con la clave recibida por parámetro
         *
         */
        @Override
        public Alerta getRowData(final String rowKey) {
                Alerta alert = null;
                for (final Alerta alerta : datasource) {
                        if (alerta.getId().toString().equals(rowKey)) {
                                alert = alerta;
                        }
                }
                return alert;
        }

        /**
         * Sobrescritura del método getRowKey.
         * @param alerta Objeto del que se desea obtener la clave
         * @return Clave del objeto pasado como parámetro
         */

        @Override
        public Object getRowKey(final Alerta alerta) {
                return alerta.getId();
        }

        /**
         * Sobreescritura del método Load para que funcione con un critera y sólo nos devuelva estríctamente el número
         * de alertas solicitadas.
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
        public List<Alerta> load(final int first, final int pageSize, final String sortField, final SortOrder sortOrder,
                        final Map<String, Object> filters) {
                List<Alerta> lista = new ArrayList<>();
                if (filtruAlerta == null) {
                        this.setRowCount(0);
                }
                else {
                        this.setRowCount(alertaService.getCounCriteria(filtruAlerta));
                        lista = alertaService.cautareAlertaCriteria(first, pageSize, sortField, sortOrder,
                                        filtruAlerta);
                        this.datasource = lista;
                }
                return lista;

        }

}
