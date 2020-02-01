package ro.stad.online.gesint.lazydata;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.model.filters.FiltruUtilizator;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.services.UtilizatorService;

/**
 *
 * Model pentru paginarea de pe server care extinde modelul LazyDataModel al Primefaces.
 * @author STAD
 *
 */

@Setter
@Getter
public class LazyDataUtilizatori extends LazyDataModel<Utilizator> implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Obiect care conține parametrii de căutare.
         */
        private transient FiltruUtilizator filtruUtilizator;

        /**
         * Serviciu a usar.
         */
        private transient UtilizatorService utilizatorService;

        /**
         * Data source con el listado de usuarios.
         */
        private Set<Utilizator> dataSource;

        /**
         * Listă care servește modelul ca sursă de date.
         */
        private transient List<Utilizator> dsource;

        /**
         * Constructor al modelului care primește serviciul ca parametru.
         * @param servicio Servicio a emplear
         */
        public LazyDataUtilizatori(final UtilizatorService servicio) {
                utilizatorService = servicio;
        }

        /**
         * Sobreescritura del método getRowData para que funcione con objetos de tipo User.
         * @param rowKey Clave de la fila sobre la que se ha hecho click en la vista
         * @return User que se corresponde con la clave recibida por parámetro
         */
        @SuppressWarnings(Constante.UNCHECKED)
        @Override
        public Utilizator getRowData(final String rowKey) {
                Utilizator us = null;

                final List<Utilizator> listaWrapped = (List<Utilizator>) getWrappedData();
                final Set<Utilizator> setUsuarios = new HashSet<>();
                setUsuarios.addAll(listaWrapped);
                if (getDataSource() != null) {
                        setUsuarios.addAll(getDataSource());
                }
                final Iterator<Utilizator> iteratorUsuarios = setUsuarios.iterator();
                boolean encontrado = false;
                while (iteratorUsuarios.hasNext() && !encontrado) {
                        final Utilizator usuario = iteratorUsuarios.next();
                        if (rowKey.equals(usuario.getUsername())) {
                                us = usuario;
                                encontrado = true;
                        }
                }
                return us;
        }

        /**
         * Sobrescritura del método getRowKey.
         * @param user Objeto del que se desea obtener la clave
         * @return Clave del objeto pasado como parámetro
         */

        @Override
        public Object getRowKey(final Utilizator user) {
                return user.getUsername();
        }

        /**
         * Suprascrieți metoda de încărcare pentru a lucra cu un criteriu și returnați numai numărul de utilizatori
         * solicitați.
         * @param first primul element pe care doriți să îl recuperați
         * @param pageSize numărul maxim de înregistrări pe care dorim să le preluăm pe pagină
         * @param sortField columna por la que se ordenarán los resultados. Corresponde a la propiedad 'sortBy' de la
         * columna de la vista
         * @param sortOrder orden por el que se desea ordenar los resultados
         * @param filters mapa de filtros. Este valor no se utiliza en esta sobreescritura.
         * @return lista de usuarios que corresponden a los criterios de búsqueda
         *
         */

        @Override
        public List<Utilizator> load(final int first, final int pageSize, final String sortField, final SortOrder sortOrder,
                        final Map<String, Object> filters) {
                List<Utilizator> listado = null;
                if (filtruUtilizator == null) {
                        this.setRowCount(0);
                }
                else {

                        this.setRowCount(utilizatorService.getCounCriteria(filtruUtilizator));
                        listado = utilizatorService.cautareUtilizatorCriteria(first, pageSize, sortField, sortOrder,
                                        filtruUtilizator);

                }
                return listado;

        }

}
