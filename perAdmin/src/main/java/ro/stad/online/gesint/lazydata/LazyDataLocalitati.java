package ro.stad.online.gesint.lazydata;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.model.filters.FiltruLocalitate;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.services.LocalitateService;

/**
 *
 * Model pentru paginarea de pe server care extinde modelul LazyDataModel al Primefaces.
 *
 * @author STAD
 *
 */

@Setter
@Getter
public class LazyDataLocalitati extends LazyDataModel<Localitate> implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Objeto que contiene los parámetros de búsqueda.
         */
        private FiltruLocalitate filtruLocalitate;

        /**
         * Servicio a usar.
         */
        @Autowired
        private transient LocalitateService serviciu;

        /**
         * Lista que sirve al modelo como fuente de datos.
         */
        private List<Localitate> datasource;

        /**
         * Constructor.
         *
         * @param service Servicio a utilizar
         */
        public LazyDataLocalitati(final LocalitateService service) {
                this.serviciu = service;
                this.filtruLocalitate = new FiltruLocalitate();
        }

        /**
         * Sobreescritura del método getRowData para adaptarlo a nuestro modelo.
         *
         * @param rowKey Fila que se ha seleccionado en la vista
         * @return Link que corresponde a la fila seleccionada
         */
        @Override
        public Localitate getRowData(final String rowKey) {
                Localitate localitate = null;
                for (final Localitate enlace : this.datasource) {
                        if (enlace != null && enlace.getId().toString().equals(rowKey)) {
                                localitate = enlace;
                        }
                }
                return localitate;
        }

        /**
         * Sobreestritura del método getRowKey para adaptarlo a nuestro modelo.
         *
         * @param solicitud Link del que se desea recuperar la clave
         * @return Clave del link
         */
        @Override
        public Object getRowKey(final Localitate solicitud) {
                return solicitud.getId();
        }

        /**
         * Sobreescritura del método load para realizar la carga paginada.
         *
         * @param first primer elemento de la paginación
         * @param pageSize número máximo de registros recuperados
         * @param sortField campo por el que se ordena
         * @param sortOrder sentido de la orientación
         * @param filters que se aplicarán a la búsqueda.
         * @return listado de registros que corresponden a la búsqueda
         */
        @Override
        public List<Localitate> load(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final Map<String, Object> filters) {
                List<Localitate> listado = null;
                if (this.filtruLocalitate != null) {
                        setRowCount(this.serviciu.getCounCriteria(this.filtruLocalitate));
                        listado = this.serviciu.cautareLocalitateCriteria(first, pageSize, sortField, sortOrder,
                                        this.filtruLocalitate);
                        setDatasource(listado);
                }
                else {
                        setRowCount(0);
                }
                return listado;

        }

}
