package ro.stad.online.gesint.lazydata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.model.filters.FiltruSondaj;
import ro.stad.online.gesint.persistence.entities.Sondaj;
import ro.stad.online.gesint.services.SondajService;

/**
 * Model pentru paginarea de pe server care extinde modelul LazyDataModel de Primefaces.
 * @author STAD
 */

@Setter
@Getter
public class LazyDataSondaje extends LazyDataModel<Sondaj> implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Obiect care conține parametrii de căutare.
         */
        private transient FiltruSondaj cautare;

        /**
         * Lista que sirve al modelo como fuente de datos.
         */
        private transient List<Sondaj> datasource;

        /**
         * Servicio a usar.
         */
        private transient SondajService sondajService;

        /**
         * Constructor de model care primește serviciul ca parametru.
         * @param serviciu Serviciu a angaja
         */
        public LazyDataSondaje(final SondajService servicio) {
                this.sondajService = servicio;
        }

        /**
         * Suprascrierea metodei getRowData entru a functiona cu obiecte de tipul Sondaj.
         * @param rowKey Cheia rândului pe care s-a făcut clic pe vizualizare
         * @return Sondaj care corespunde cu cheia primita ca parametru
         *
         */
        @Override
        public Sondaj getRowData(final String rowKey) {
                Sondaj sonda = new Sondaj();
                for (final Sondaj sondaj : datasource) {
                        if (sondaj.getId().toString().equals(rowKey)) {
                                sonda = sondaj;
                        }
                }
                return sonda;
        }

        /**
         * Suprascrierea metodei getRowKey.
         * @param sonda Obiect de la care doriți să obțineți cheia
         * @return Cheia obiectului trecut ca parametru
         */

        @Override
        public Object getRowKey(final Sondaj sonda) {
                return sonda.getId();
        }

        /**
         * Suprascrierea metodei Load astfel încât să funcționeze cu un criter și doar returnează strict numărul de
         * sondaje solicitate.
         * @param first primul element de recuperat
         * @param pageSize numărul maxim de înregistrări pe care dorim să le prelucrăm pe pagină
         * @param sortField coloana în care se vor sorta rezultatele. Corespunde proprietății „sortBy” a proprietății
         * coloana din fereastra
         * @param sortOrder ordinea prin care doriți să sortați rezultatele
         * @param filters mapa filtrului Această valoare nu este utilizată în această suprascriere.
         * @return lista registrelor care corespund criteriilor de căutare
         *
         */

        @Override
        public List<Sondaj> load(final int first, final int pageSize, final String sortField, final SortOrder sortOrder,
                        final Map<String, Object> filters) {
                List<Sondaj> lista = new ArrayList<>();
                if (cautare == null) {
                        this.setRowCount(0);
                }
                else {
                        this.setRowCount(sondajService.getCounCriteria(cautare));
                        lista = sondajService.cautareSondajeCriteria(first, pageSize, sortField, sortOrder, cautare);
                        this.datasource = lista;
                }
                return lista;

        }

}
