package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.lazydata.LazyDataProiecte;
import ro.stad.online.gesint.model.filters.FiltruProiect;
import ro.stad.online.gesint.persistence.entities.Proiect;
import ro.stad.online.gesint.services.ProiectService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;

/**
 * Clase utilizada pentru a incarca proiectele.
 *
 * @author STAD
 *
 */
@Component("proiectBean")
@Setter
@Getter
@NoArgsConstructor
@Scope(Constante.SESSION)
public class ProiectBean implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Lista de elemente vizibile.
         */
        private List<Boolean> list;

        /**
         * Variala utilizata pentru injectarea serviciului de proiecte.
         *
         */
        @Autowired
        private transient ProiectService proiectService;

        /**
         * Lazy model pentru a pagina in server proiectele
         */
        private transient LazyDataProiecte modelProiect;

        /**
         * Variabila pentru proiecte.
         */
        private transient Proiect proiect;

        /**
         * Clasa de căutare a proiectului.
         */
        private FiltruProiect filtruProiect;

        /**
         * Lista proiecte.
         */
        private List<Proiect> listaProiecte;

        /**
         * Lista pozitiilor proiectelor.
         */
        private List<Proiect> listaPozitie;

        /**
         * Fotografia.
         */
        private byte[] photoSelected;

        /**
         * Deschide dialogul pentru pozitionarea membrilor.
         */
        public void deschideDialogOrdoneazaProiecte() {
                listaProiecte = proiectService.findAll();
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgOrdena').show();");
        }

        /**
         * Căută proiecte pe baza unui filtru.
         */
        @Transactional
        public void cautareProiecte() {
                modelProiect.setFiltruProiect(filtruProiect);
                modelProiect.load(0, NumarMagic.NUMBERFIFTEEN, Constante.DATECREATE, SortOrder.DESCENDING, null);
        }

        /**
         * Eliminarea unui proiect.
         * @param proiect Proiect proiect candidat pentru eliminare
         */
        public void eliminareProiect(final Proiect proiect) {
                try {
                        proiectService.delete(proiect);
                        listaProiecte.remove(proiect);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.ELIMINAREMESAJ, Constante.OKELIMINAREMESAJ);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la eliminarea proiectului."
                                                        .concat(Constante.DESCEROAREMESAJ));
                }

        }

        /**
         * Transmite datele proiectului pe care dorim să le modificăm în formular, astfel încât acestea să schimbe
         * valorile pe care le doresc.
         * @return URL-ul paginii de modificare a proiectului
         */
        public String getFormModificarProiect(final Proiect pr) {
                this.proiect = pr;
                this.photoSelected = null;
                return "/proiecte/modifyProiect?faces-redirect=true";
        }

        /**
         * Inițializarea datelor din ProiectBean.
         */
        @PostConstruct
        public void init() {
                this.filtruProiect = new FiltruProiect();
                this.proiect = new Proiect();
                this.listaProiecte = new ArrayList<>();
                this.list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                        this.list.add(Boolean.TRUE);
                }
                cautareCautare();
                Utilitati.cautareSesiune("proiectBean");
        }

        /**
         * Curăță căutarea proiectelor
         */
        public void cautareCautare() {
                filtruProiect = new FiltruProiect();
                modelProiect = new LazyDataProiecte(proiectService);
                modelProiect.setRowCount(0);
        }

        /**
         * Modifică proiectul indicat.
         */
        public void modificaProiect(final Proiect proie) {
                this.proiect = new Proiect();
                try {
                        this.proiect = proie;
                        ;
                        proiectService.save(proiect);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.MODIFICAREMESAJ, Constante.OKMODIFICAREMESAJ);
                        final RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dlgModifica').hide();");

                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la modificarea proiectului. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }

        }

        /**
         * Reordeneaza proiectul
         *
         *
         */
        public void onReorder() {
                try {
                        reordeneazaProiecte();
                        final FacesContext facesContext = FacesContext.getCurrentInstance();
                        facesContext.addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la salvarea reordenării. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }
        }

        /**
         * Modificarea descrierii unui membru al equipei.
         * @param event eveniment care capturează team de editat
         */
        public void onRowEdit(final RowEditEvent event) {

                try {
                        final Proiect proiect = (Proiect) event.getObject();
                        proiectService.save(proiect);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.MODIFICAREMESAJ, proiect.getTitlu());
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare în încercarea de a modifica proiectul. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }
        }

        /**
         * Metoda care se execută la selectare.
         * @param event SelectEvent
         */
        public void onSelect(final SelectEvent event) {
                this.proiect = (Proiect) event.getObject();
        }

        /**
         * Controlează coloanele vizibile în lista rezultatelor motorului de căutare.
         * @param eve ToggleEvent
         */

        public void onToggle(final ToggleEvent eve) {
                this.list.set((Integer) eve.getData(), eve.getVisibility() == Visibility.VISIBLE);
        }

        /**
         * Acces pentru a inregistra un nou proiect.
         * @return String
         */
        public String proiectNou() {
                proiect = new Proiect();
                return "/proiecte/inregistrareProiect?faces-redirect=true";
        }

        /**
         * Funcție care reorientează pozitia
         * @throws DataAccessException excepție de acces la date
         */
        private void reordeneazaProiecte() {
                try {
                        Proiect proiect;
                        for (int i = 0; i < this.listaProiecte.size(); i++) {
                                proiect = this.listaProiecte.get(i);

                                proiect.setRank(i + 1L);
                                this.proiectService.save(proiect);
                                final RequestContext context = RequestContext.getCurrentInstance();
                                context.execute("PF('dlgOrdena').hide();");
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la salvarea modificărilor. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }
        }

        /**
         * Inregistratrea proiectului.
         * @param proi Proiect
         */
        public void inregistrareProiect(final Proiect proi) {
                try {
                        this.proiect = proi;
                        // Cautam cea mai mare pozitie din lista
                        listaPozitie = proiectService.findAllByOrderByRankDesc();
                        // Adaugam inca una
                        if (listaPozitie.isEmpty()) {
                                proiect.setRank(NumarMagic.NUMBERONELONG);
                        }
                        else {
                                final Long rank = listaPozitie.get(0).getRank() + 1;
                                proiect.setRank(rank);
                        }
                        // Salvam proiectul
                        proiectService.save(proiect);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.INREGISTRAREMESAJ, Constante.OKINREGISTRAREMESAJ);

                }
                catch (

                final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }
}
