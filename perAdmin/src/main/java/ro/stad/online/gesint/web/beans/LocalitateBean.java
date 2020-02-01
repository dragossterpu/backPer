package ro.stad.online.gesint.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.lazydata.LazyDataLocalitati;
import ro.stad.online.gesint.model.filters.FiltruLocalitate;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;
import ro.stad.online.gesint.services.LocalitateService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.util.FacesUtilities;

/**
 * Controller de gestiune ale localitatilor.
 *
 * @author STAD
 */
@Setter
@Getter
@ManagedBean
@Controller("localitateBean")
@Scope(Constante.SESSION)
public class LocalitateBean implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Variabila utilizata pentru injectarea serviciului de localitati
         */
        @Autowired
        private transient LocalitateService localitateService;

        /**
         * Lista de localitati.
         */
        private List<Localitate> listaLocalitati;

        /**
         * Localitatea
         */
        private Localitate localitatea;

        /**
         * Tara.
         */
        private Provincia judetul;

        /**
         * Fotoografia localitate.
         */
        private byte[] photoSelected;

        /**
         * Nombre del documento.
         */
        private String numeDoc;

        /**
         * Obiect care conține parametrii de căutare.
         */
        private FiltruLocalitate filtruLocalitate;

        /**
         * Lista de judete.
         */
        private List<Provincia> listaJudete;

        /**
         * Judetul selectionat.
         */
        private String idProvincia;

        /**
         * Variabila utilizata pentru injectarea serviciului de judete
         */
        @Autowired
        private transient ProvinciaService provinciaService;

        /**
         * LazyModel pentru afișarea paginată a datelor din pagina.
         */
        private LazyDataLocalitati model;

        /**
         * Serviciu utilizatorului
         */
        @Autowired
        private UtilizatorService utilizatorService;

        /**
         * Metoda init() de LocalitateBean.
         */
        @PostConstruct
        public void init() {
                filtruLocalitate = new FiltruLocalitate();
                this.idProvincia = Constante.SPATIU;
                judetul = new Provincia();
                localitatea = new Localitate();
                listaLocalitati = new ArrayList<>();
                listaJudete = provinciaService.fiindAll();
                listaLocalitati = localitateService.fiindAll();
        }

        /**
         * Metoda care caută locații pe baza câmpurilor completate în formularul de filtrare..
         *
         */
        public void cautareLocalitati() {
                this.model = new LazyDataLocalitati(this.localitateService);
                this.model.setFiltruLocalitate(this.filtruLocalitate);
                this.model.load(0, 10, Constante.ID, SortOrder.DESCENDING, null);
        }

        /**
         * Metoda care recupereaza valorile introduse în formular și modifica localitatea în baza de date.
         * @param loca Localitate.
         */
        public void modificaLocalitate(final Localitate loca) {
                try {
                        this.localitatea = loca;
                        this.localitateService.save(this.localitatea);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.SCHIMBDATE,
                                        Constante.REGMODOK);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Metoda care recupereaza valorile introduse în formular și înregistrați localitatea în baza de date.
         * @param loca Localitate
         * @param indicator String
         */
        public void inregistrareLocalitate(final Localitate loca, final String indicator) {
                try {

                        this.localitatea = loca;
                        if (indicator != null) {
                                localitatea.setProvince(provinciaService.findById(indicator));
                        }
                        if (localitatea.getTypelocality().equals(TipLocalitateEnum.MUNICIPALITY)) {
                                localitatea.setNivel(NumarMagic.NUMBERONELONG);
                        }
                        else if (localitatea.getTypelocality().equals(TipLocalitateEnum.CITY)) {
                                localitatea.setNivel(NumarMagic.NUMBERTWOLONG);
                        }
                        else {
                                localitatea.setNivel(NumarMagic.NUMBERTHREEL);
                        }
                        this.localitateService.save(this.localitatea);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.SCHIMBDATE,
                                        Constante.REGMODOK);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Metoda care obtine datele localitatiii care dorim sa fie modificata
         * @param loca Localitate recuperata
         * @return vista modificareLocalitate
         */
        public String getFormModificarLocalitate(final Localitate loca) {
                this.localitatea = loca;
                this.idProvincia = localitatea.getProvince().getIndicator();
                this.photoSelected = null;
                return "/localitati/modificareLocalitate?faces-redirect=true";
        }

        /**
         * Metoda care deschide formularul de inregistrare a noii localitati
         * @return vista nouaLocalitate
         */
        public String getFormAltaLocalitate() {
                this.idProvincia = Constante.SPATIU;
                this.localitatea = new Localitate();
                this.photoSelected = null;
                return "/localitati/nouaLocalitate?faces-redirect=true";
        }

        /**
         * Metoda care deschide dialogul de noua imagine.
         */
        public void arataDialogulNouaImagine() {
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg').show();");
        }

        /**
         * Metoda care incărca un document prin FileUploadEvent. Această încărcare se face pe obiectul document și nu
         * este salvată în baza de date. Se verifică dacă tipul de document corespunde realității.
         * @param event Event care este lansat în încărcarea documentului și care conține același lucru
         * @throws IOException
         */
        public void incarcareImagine(final FileUploadEvent event) throws IOException {
                this.numeDoc = Constante.SPATIU;
                final UploadedFile uFile = event.getFile();
                localitatea = localitateService.incarcareImaginaFaraStocare(IOUtils.toByteArray(uFile.getInputstream()),
                                localitatea);
                numeDoc = uFile.getFileName();
        }

        /**
         * Returnează formularul de căutare locală în starea inițială și șterge rezultatele căutării anterioare
         */
        public void cautareCautare() {
                this.filtruLocalitate = new FiltruLocalitate();
                model.setRowCount(0);
                model = null;
        }

        /**
         * Elimina o localitate
         * @param loca Localitate care se va elimina
         */
        public void eliminaLocalitate(Localitate loca) {
                List<Utilizator> membrii = new ArrayList<>();
                membrii = utilizatorService.findByLocality(loca);
                if (!membrii.isEmpty()) {
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                        "Nu se poate elimina localitatea. Sunt înregistrate persoane în acesta localitate. Modificații și după încercați iarăși.",
                                        Constante.SPATIU, "username");
                }
                else {
                        try {
                                localitateService.delete(loca);
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                Constante.ELIMINAREMESAJ, Constante.REGMODOK);
                        }
                        catch (DataAccessException e) {

                        }
                }

        }
}
