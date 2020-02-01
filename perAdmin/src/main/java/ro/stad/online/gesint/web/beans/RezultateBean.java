package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.Visibility;
import org.primefaces.model.chart.PieChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.lazydata.LazyDataRezultate;
import ro.stad.online.gesint.model.dao.impl.RezultateDAOImpl;
import ro.stad.online.gesint.model.dto.statistica.RezultateDTO;
import ro.stad.online.gesint.model.filters.FiltruRezultat;
import ro.stad.online.gesint.persistence.entities.Partid;
import ro.stad.online.gesint.persistence.entities.PartidRezultateJudete;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.enums.TipAlegeriEnum;
import ro.stad.online.gesint.services.PartidService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.RezultatService;
import ro.stad.online.gesint.services.impl.RegistruActivitateServiceImpl;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;

/**
 * Controlorul operațiunilor legate de gestionarea rezultatelor de la alegeri.
 *
 * @author STAD
 */
@Setter
@Getter
@Controller("rezultatBean")
@Scope(Constante.VIEW)
public class RezultateBean implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = NumarMagic.NUMBERONELONG;

        /**
         * Constanta pentru log
         */
        private static final Logger LOG = LoggerFactory.getLogger(RezultateBean.class.getSimpleName());

        /**
         * Numărul de coloane din tabelul de alerte.
         */
        private static final int NUMBERCOLUMNTABLA = NumarMagic.NUMBERSEVEN;

        /**
         * Variabila care ne arata numarul total de votanti
         */

        private Integer totalVotanti;

        /**
         * Variabila care ne arata numarul total de masndate
         */

        private Integer totalMandate;

        /**
         * Un nou rezultat.
         */
        private transient PartidRezultateJudete partidRezultateJudetene;

        /**
         * Variala utilizata pentru injectarea serviciului de rezultate.
         */
        @Autowired
        private transient RezultatService rezultatService;

        /**
         * Clasa de căutare a rezultatelor.
         */
        private FiltruRezultat filtruRezultat;

        /**
         * LazyModel pentru afișarea paginată a datelor din vizualizare.
         */
        private LazyDataRezultate model;

        /**
         * Listă de partide.
         */
        private List<Partid> listPartide;

        /**
         * Indicați ce colomne apar în listă.
         */
        private List<Boolean> list;

        /**
         * Variala utilizata pentru injectarea serviciului înregistrare a activității.
         */
        @Autowired
        private transient RegistruActivitateServiceImpl registruActivitateService;

        /**
         * Lista de judete.
         */
        private List<Provincia> judete;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Variabila utilizata pentru a injecta serviciul partid.
         *
         */
        @Autowired
        private PartidService partidService;

        /**
         * Variabila utilizata pentru a injecta serviciul partid.
         *
         */
        private String dataAlegerilor;

        /**
         * Lista de partide participante la alegerile judetene.
         */
        private List<Partid> listaPartideJudet;

        /**
         * Lista de partide participante la alegerile judetene.
         */
        private List<Integer> listaAni;

        /**
         * Lista de rezultate alegeri.
         */
        private List<RezultateDTO> listaRezultate;

        /**
         * Variabila pentru serviciul de rezultate.
         */
        @Autowired
        private transient RezultateDAOImpl rezultateDaoService;

        /**
         * Variabila pentru chart de tipul pie
         */
        private PieChartModel graficaRezultatePartide;

        /**
         * Variabila pentru numele tablei
         */
        private String numeTabla;

        /**
         * Variabila pentru numele tablei principale
         */
        private String numeTablaPrincipal;

        /**
         * Căutați rezultate pe baza filtrului de căutare.
         */
        public void cautareRezultate() {

                totalVotanti = 0;
                totalMandate = 0;
                numeTabla = Constante.SPATIU;
                numeTablaPrincipal = Constante.SPATIU;
                if (filtruRezultat.getTipAlegeri() == null) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, "INFO",
                                        "Este obligatoriu să alegeți un tip de alegeri");
                        listaRezultate = new ArrayList<>();
                        this.model = new LazyDataRezultate(this.rezultatService);
                        numeTabla = Constante.SPATIU;
                        numeTablaPrincipal = Constante.SPATIU;
                        LOG.debug("Este obligatoriu să alegeți un tip de alegeri");
                }
                else {
                        if (TipAlegeriEnum.LOCALE_CONSILIU.equals(filtruRezultat.getTipAlegeri())) {
                        }

                        this.model.setBusqueda(this.filtruRezultat);
                        // Obtinem numele tablei principale
                        extractNumeTablaPrincipala();
                        extractBooleanDateGenerale();
                        this.model.load(0, NumarMagic.NUMBERFIFTEEN, null, SortOrder.DESCENDING, null);
                        // Obtinem data alegerilor in format dd/MM/yyyy
                        if (!model.getDatasource().isEmpty()) {
                                dataAlegerilor = Utilitati.getFechaFormateada(
                                                model.getDatasource().get(0).getDataAlegerilor(), Constante.FORMATDATE);
                        }
                        listaRezultate = rezultateDaoService.filterGeneraleRezultate(filtruRezultat);

                        if (filtruRezultat.getSuntGenerale()) {
                                createPieModelRezultate(listaRezultate);
                                numeTabla = "Primele 10 partide la nivel național";
                        }
                        if (!listaRezultate.isEmpty()) {
                                totalVotanti = listaRezultate.get(0).getTotalVoturi();
                                totalMandate = listaRezultate.get(0).getTotalMandate();
                                for (final RezultateDTO rdto : listaRezultate) {
                                        rdto.setNume(Utilitati.convertNumePartid(rdto.getNume()));
                                }

                        }
                }
        }

        /**
         * Boolean date generale
         * @return boolean
         */
        private void extractBooleanDateGenerale() {
                if (filtruRezultat.getIdPartid() == null && Constante.SPATIU.equals(filtruRezultat.getIdProvincia())) {
                        filtruRezultat.setSuntGenerale(true);
                }
                else {
                        filtruRezultat.setSuntGenerale(false);
                }
        }

        /**
         * Nume tabla principala
         */
        private void extractNumeTablaPrincipala() {
                if (filtruRezultat.getTipAlegeri().equals(TipAlegeriEnum.GENERALE)) {
                        numeTablaPrincipal = "GENERALE";
                }
                else if (filtruRezultat.getTipAlegeri().equals(TipAlegeriEnum.PREZIDENTIALE)) {
                        numeTablaPrincipal = "PREZIDENTIALE";
                }
                else if (filtruRezultat.getTipAlegeri().equals(TipAlegeriEnum.LOCALE_PRIMAR)) {
                        numeTablaPrincipal = "PRIMARI";
                }
                else if (filtruRezultat.getTipAlegeri().equals(TipAlegeriEnum.LOCALE_CONSILIU)) {
                        numeTablaPrincipal = "CONSILIERI LOCALI";
                }
                else if (filtruRezultat.getTipAlegeri().equals(TipAlegeriEnum.EUROPENE)) {
                        numeTablaPrincipal = "EUROPARLAMENTARE";
                }
                else if (filtruRezultat.getTipAlegeri().equals(TipAlegeriEnum.JUDET_CONSILIU)) {
                        numeTablaPrincipal = "CONSILIERI JUDEȚENI";
                }
                else {
                        numeTablaPrincipal = "REFERENDUM";
                }
        }

        /**
         * Incarcam datele in modelul pie
         * @param lista List<RezultateDTO>
         */
        private void createPieModelRezultate(final List<RezultateDTO> lista) {
                graficaRezultatePartide = new PieChartModel();
                for (final RezultateDTO rezultat : lista) {
                        graficaRezultatePartide.set(rezultat.getSigla(), rezultat.getVoturiPartid());
                }
                graficaRezultatePartide.setLegendPosition("ne");
                graficaRezultatePartide.setFill(false);
                graficaRezultatePartide.setShowDataLabels(true);
                graficaRezultatePartide.setDiameter(NumarMagic.NUMBERTWOHUNDRED);
                graficaRezultatePartide.setShadow(false);
        }

        /**
         * Metoda care arata pagina cu datele rezultatelor pe localitati
         * @param indicator String
         * @return modificareAlerta
         */
        public String getFormRezultateLocalitati(final String indicator) {
                String redireccion = null;
                redireccion = "/alerte/modificareAlerta?faces-redirect=true";
                return redireccion;
        }

        /**
         * Metoda Init a lui RezultateBean.
         */
        @PostConstruct
        public void init() {
                this.filtruRezultat = new FiltruRezultat();
                this.judete = new ArrayList<>();
                this.list = new ArrayList<>();
                totalVotanti = 0;
                totalMandate = 0;
                numeTabla = Constante.SPATIU;
                judete = provinciaService.fiindAll();
                listaPartideJudet = new ArrayList<>();
                listaPartideJudet = partidService.fiindPartidJudet();
                listaAni = new ArrayList<>();
                listaAni = rezultatService.cautaAni();
                for (int i = 0; i < NUMBERCOLUMNTABLA; i++) {
                        this.list.add(Boolean.TRUE);
                }
                this.model = new LazyDataRezultate(this.rezultatService);
                Utilitati.cautareSesiune("rezultatBean");
        }

        /**
         * Metoda care curăță filtrul de căutare.
         */
        public void cautareCautare() {
                this.filtruRezultat = new FiltruRezultat();
                this.model = new LazyDataRezultate(this.rezultatService);
                listaRezultate = new ArrayList<>();
                numeTabla = Constante.SPATIU;
                numeTablaPrincipal = Constante.SPATIU;
                totalVotanti = 0;
                totalMandate = 0;
        }

        /**
         * Controlează coloanele vizibile în lista rezultatelor motorului de căutare.
         * @param toggle ToggleEvent
         */
        public void onToggle(final ToggleEvent toggle) {
                this.list.set((Integer) toggle.getData(), toggle.getVisibility() == Visibility.VISIBLE);
        }

}
