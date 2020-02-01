package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.lazydata.LazyDataRegistre;
import ro.stad.online.gesint.model.filters.FiltruRegistru;
import ro.stad.online.gesint.persistence.entities.RegistruActivitate;
import ro.stad.online.gesint.services.RegistruActivitateService;

/**
 * Controller pentru managementul registrului de activitate
 *
 * @author STAD
 *
 */

@Setter
@Getter
@Controller("registruBean")
@Scope(Constante.SESSION)
public class RegistruActivitateBean implements Serializable {

        /**
        	 *
        	 */
        private static final long serialVersionUID = 1L;

        /**
         * Numărul de coloane din pagină.
         */
        private static final Integer NUMCOLSREGISTRO = 5;

        /**
         * Lista booleanilor pentru a controla afișarea coloanelor din pagină.
         */
        private List<Boolean> list;

        /**
         * Obiect care se utilizeaza pentru afișarea înregistrărilor.
         */
        private RegistruActivitate error;

        /**
         * Obiect cu parametri de căutare.
         */
        private FiltruRegistru filtruRegistru;

        /**
         * LazyModel pentru afișarea paginată a datelor din pagină.
         */
        private LazyDataRegistre model;

        /**
         * Service registru de activitate.
         */
        @Autowired
        private transient RegistruActivitateService regActividadService;

        /**
         * Metodă care returnează o listă de nume de utilizator care conțin șirul de text care este primit ca parametru.
         * @param infoUser Utilizator pentru care se face consultarea
         * @return Lista de resulte
         *
         */
        public List<String> autocompletarUsuario(final String infoUser) {
                return regActividadService
                                .cautareUtilizatorDupaRegistru(Constante.PORCENTAJ + infoUser + Constante.PORCENTAJ);
        }

        /**
         * Metodă care caută în registrul de activități conform criteriilor alese de utilizator în pagina și încărca
         * rezultatele într-o listă pentru vizualizare.
         */
        public void cautareRegActivitate() {
                model.setBusqueda(filtruRegistru);
                model.load(0, NumarMagic.NUMBERFIFTEEN, Constante.DATECREATE, SortOrder.DESCENDING, null);

        }

        /**
         * @PostConstruct Metodă folosită pentru a inițializa RegistruActivitateBean.
         *
         */
        @PostConstruct
        public void init() {
                list = new ArrayList<>();
                for (int i = 0; i < NUMCOLSREGISTRO; i++) {
                        list.add(Boolean.TRUE);
                }
                this.filtruRegistru = new FiltruRegistru();
                model = new LazyDataRegistre(regActividadService);
        }

        /**
         * Metodă care curăță parametrii de căutare și rezultatul.
         */
        public void cautareCautare() {
                this.filtruRegistru = new FiltruRegistru();
                this.model = new LazyDataRegistre(regActividadService);
                this.model.setDatasource(new ArrayList<>());
        }

        /**
         * Metodă care controlează coloanele vizibile din lista de rezultate ale cautatorului.
         * @param toggle ToggleEvent
         */
        public void onToggle(final ToggleEvent toggle) {
                list.set((Integer) toggle.getData(), toggle.getVisibility() == Visibility.VISIBLE);
        }

}
