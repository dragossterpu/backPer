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
import ro.stad.online.gesint.model.filters.FiltruProiect;
import ro.stad.online.gesint.persistence.entities.Proiect;
import ro.stad.online.gesint.services.ProiectService;

/**
 * 
 * Model pentru paginarea de pe server care extinde modelul LazyDataModel al Primefaces.
 * @author STAD
 *
 */

@Setter
@Getter
public class LazyDataProiecte extends LazyDataModel<Proiect> implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        /**
         * Obiect care conține parametrii de căutare.
         */
        private transient FiltruProiect filtruProiect;

        /**
         * Listă care servește modelul ca sursă de date.
         */
        private transient List<Proiect> datasource;

        /**
         * Serviciu a usar.
         */
        private transient ProiectService proiectService;

        /**
         * 
         * Constructor al modelului care primește serviciul ca parametru.
         * 
         * @param servicio Servicio a emplear
         */
        public LazyDataProiecte(final ProiectService serviciu) {
                proiectService = serviciu;
        }

        /**
         * Suprascrierea metodei getRowData pentru a lucra cu obiectele utilizatorului.
         * 
         * @param rowKeyCheia rândului pe care a fost vizionat vizualizarea
         * @return Utilizator care corespunde cheii primite de parametru
         * 
         */

        @SuppressWarnings("unlikely-arg-type")
        @Override
        public Proiect getRowData(final String rowKey) {
                Proiect proiect = null;
                @SuppressWarnings("unchecked")
                final List<Proiect> listaWrapped = (List<Proiect>) this.getWrappedData();
                final Set<Proiect> setProiecte = new HashSet<>();
                if (listaWrapped != null) {
                        setProiecte.addAll(listaWrapped);
                }
                if (getDatasource() != null) {
                        setProiecte.addAll(getDatasource());
                }
                final Iterator<Proiect> iteratorProiecte = setProiecte.iterator();
                boolean encontrado = false;
                while (iteratorProiecte.hasNext() && !encontrado) {
                        final Proiect proi = iteratorProiecte.next();
                        if (rowKey.equals(proi.getId())) {
                                proiect = proi;
                                encontrado = true;
                        }
                }
                return proiect;
        }

        /**
         * Sobrescritura del método getRowKey.
         * 
         * @param user Objeto del que se desea obtener la clave
         * @return Clave del objeto pasado como parámetro
         */

        @Override
        public Object getRowKey(final Proiect proiect) {
                return proiect.getId();
        }

        /**
         * Suprascrieți metoda de încărcare pentru a lucra cu un criteriu și returnați numai numărul de utilizatori
         * solicitați.
         * 
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
        public List<Proiect> load(final int first, final int pageSize, final String sortField,
                        final SortOrder sortOrder, final Map<String, Object> filters) {
                List<Proiect> lista = null;
                if (filtruProiect == null) {
                        this.setRowCount(0);
                }
                else {

                        this.setRowCount(proiectService.getCounCriteria(filtruProiect));
                        lista = proiectService.cautareProiecteCriteria(first, pageSize, sortField, sortOrder,
                                        filtruProiect);
                }
                return lista;

        }

}
