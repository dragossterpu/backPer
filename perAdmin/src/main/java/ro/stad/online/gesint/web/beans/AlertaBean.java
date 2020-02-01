package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.mitchellbosecke.pebble.error.PebbleException;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.exceptions.GesintException;
import ro.stad.online.gesint.lazydata.LazyDataAlerte;
import ro.stad.online.gesint.lazydata.LazyDataUtilizatori;
import ro.stad.online.gesint.model.filters.FiltruAlerta;
import ro.stad.online.gesint.model.filters.FiltruEchipa;
import ro.stad.online.gesint.model.filters.FiltruUtilizator;
import ro.stad.online.gesint.persistence.entities.Alerta;
import ro.stad.online.gesint.persistence.entities.Documente;
import ro.stad.online.gesint.persistence.entities.Echipa;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.TipDocument;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.CanalAlertaEnum;
import ro.stad.online.gesint.persistence.entities.enums.EducatieEnum;
import ro.stad.online.gesint.persistence.entities.enums.RolEnum;
import ro.stad.online.gesint.persistence.entities.enums.SectiuniEnum;
import ro.stad.online.gesint.persistence.entities.enums.TipRegistruEnum;
import ro.stad.online.gesint.services.AlertaService;
import ro.stad.online.gesint.services.DocumentService;
import ro.stad.online.gesint.services.EchipaService;
import ro.stad.online.gesint.services.ParamEchipaService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.RegistruActivitateService;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.services.impl.RegistruActivitateServiceImpl;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;

/**
 * Controlorul operațiunilor legate de gestionarea alertelor.
 *
 * @author STAD
 */
@Setter
@Getter
@Controller("alertaBean")
@Scope(Constante.SESSION)
public class AlertaBean implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = NumarMagic.NUMBERONELONG;

        /**
         * Numărul de coloane din tabelul de alerte.
         */
        private static final int NUMBERCOLUMNSTABLE = NumarMagic.NUMBERFIVE;

        /**
         * Alerta noua.
         */
        private transient Alerta alerta;

        /**
         * Variala utilizata pentru injectarea serviciului de alerte.
         */
        @Autowired
        private transient AlertaService alertaService;

        /**
         * Variala utilizata pentru injectarea serviciului de utilizatori.
         */
        @Autowired
        private transient UtilizatorService utilizatorService;

        /**
         * Lazy model pentru utilizatori.
         */
        private transient LazyDataUtilizatori modelUser;

        /**
         * Clasa de căutare a utilizatorilor.
         */
        private FiltruUtilizator filtruUtilizator;

        /**
         * Clasa de căutare a alertelor.
         */
        private FiltruAlerta filtruAlerta;

        /**
         * LazyModel pentru afișarea paginată a datelor din vizualizare.
         */
        private LazyDataAlerte model;

        /**
         * Listă de alerte per utilizator.
         */
        private List<Alerta> lstAlerte;

        /**
         * Utilizator care se modifică în acest moment.
         */
        private Alerta alertaActual;

        /**
         * Indicați ce colomne apar în listă.
         */
        private List<Boolean> list;

        /**
         * Lista utilizatorilor selectați.
         */
        private List<Utilizator> utilizatoriSelectionati;

        /**
         * Lista utilizatorilor externi selectați.
         */
        private List<Utilizator> utilizatoriExterniSelectionati;

        /**
         * Lista utilizatorilor selectați final.
         */
        private List<Utilizator> utilizatoriSelectionatiFinali;

        /**
         * Numele de utilizator care va fi folosit pentru a trimite e-mailul unui singur destinatar.
         */
        private String numeUtilizator = Constante.SPATIU;

        /**
         * Indicați dacă doriți să căutați după echipa de conducere (opțiunea 1), membrii (opțiunea 2) sau destinatari
         * externi (opțiunea 3)
         */
        private Integer opcion = 1;

        /**
         * Lista numelor din echipa de conducere.
         */
        private List<Echipa> listaTeams;

        /**
         * Variala utilizata pentru injectarea serviciului pentru echipa de conducere.
         *
         */
        @Autowired
        private transient EchipaService echipaService;

        /**
         * Variala utilizata pentru injectarea serviciului înregistrare a activității.
         */
        @Autowired
        private transient RegistruActivitateServiceImpl registruActivitateService;

        /**
         * Obiectul echipei de conducere.
         */
        private Echipa echipa;

        /**
         * Numar de membri
         */
        private Integer numarMembrii;

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
         * Variabila utilizata pentru a recupera emailul unor utilizatori externi.
         *
         */
        private String utilizatorExtern;

        /**
         * Lista de documente asociate cererii.
         */
        private List<Documente> listaDocumente;

        /**
         * Serviciul de documente.
         */
        @Autowired
        private transient DocumentService documentService;

        /**
         * Extensiile de fișier acceptate în sistem..
         */
        private Map<String, String> extensie;

        /**
         * Serviciul de înregistrare a activității.
         */
        @Autowired
        private transient RegistruActivitateService regActividadService;

        /**
         * Bean de date comune de aplicatie.
         */
        @Autowired
        private transient ApplicationBean applicationBean;

        /**
         * Fișier încărcat sau descărcat.
         */
        private transient StreamedContent file;

        /**
         * Lista documentelor încărcate.
         */
        private List<Documente> documenteIncarcate;

        /**
         * Dias inactividad de un usuario.
         */
        private long zileInactivitate;

        /**
         * Data actuala
         */
        private Date currentDate;

        /**
         * Variabila pentru lista de functii existente in bbdd
         */
        private List<Utilizator> listUsersCentral;

        /**
         * Variabila pentru lista de functii existente in bbdd
         */
        private List<Utilizator> listUsersLocal;

        /**
         * Variabila utilizata pentru a injecta serviciul functilor
         *
         */
        @Autowired
        private ParamEchipaService pteamService;

        /**
         * Objeto de búsqueda de usuario.
         */
        private FiltruEchipa filtruEchipa;

        /**
         * Numar de membri in conducerea locala.
         */
        private int rowCountLocal;

        /**
         * Numar de membri in conducerea centrala.
         */
        private int rowCountCentral;

        /**
         * Lista functilor locale.
         */
        private List<ParamEchipa> listaFunctiiLocal;

        /**
         * Deschideți dialogul pentru căutarea utilizatorilor.
         */
        public void deschideDialogCautareUtilizatori() {

                this.filtruEchipa = new FiltruEchipa();
                filtruUtilizator = new FiltruUtilizator();
                this.modelUser = new LazyDataUtilizatori(this.utilizatorService);
                this.opcion = NumarMagic.NUMBERONE;
                this.judete = this.provinciaService.fiindAll();
                this.listUsersLocal = new ArrayList<>();
                listaFunctiiLocal = incarcamToateFunctileLocale();
                this.numarMembrii = this.listaTeams.size();
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute(Constante.DIALOGBUSQUEDASHOW);
                List<ParamEchipa> lista = new ArrayList<>();
                lista = incarcamToateFunctileCentrale();
                filtruEchipa.setListTeam(lista);
                listUsersCentral = utilizatorService.cautareUtilizatorCriteriaLocal(filtruEchipa);
                rowCountCentral = listUsersCentral.size();
        }

        /**
         * Deschide dialogul pentru incarcarea documentelor.
         */
        public void deschideDialogIncarcarDocument() {
                this.documenteIncarcate = new ArrayList<>();
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgIncarcareDocument').show();");
        }

        /**
         * Găsiți alerta din matricea de alertă locală.
         * @param id Long
         * @return Alerta
         */
        private Alerta cautareAlerteLocal(final Long id) {
                Alerta result = new Alerta();
                for (final Alerta alertaLocal : this.lstAlerte) {
                        if (alertaLocal.getId() == id) {
                                result = alertaLocal;
                                break;
                        }
                }
                return result;
        }

        /**
         * Căutați alerte pe baza filtrului de căutare.
         */
        public void cautareAlerte() {
                this.model.setFiltruAlerta(this.filtruAlerta);
                this.model.load(0, NumarMagic.NUMBERFIFTEEN, Constante.DATECREATE, SortOrder.DESCENDING, null);
        }

        /**
         * Căutați utilizatori pe baza unui filtru.
         */
        public void cautareUtilizatori() {
                if (this.opcion == NumarMagic.NUMBERONE) {
                        this.modelUser.setFiltruUtilizator(this.filtruUtilizator);
                        this.modelUser.load(0, NumarMagic.NUMBERFIFTEEN, Constante.DATECREATE, SortOrder.DESCENDING,
                                        null);
                }
                else if (this.opcion == NumarMagic.NUMBERTWO) {
                        if (filtruUtilizator.getIdFunctia() != null) {
                                filtruEchipa.setIdFunctia(filtruUtilizator.getIdFunctia());
                                final List<ParamEchipa> lista = new ArrayList<>();
                                filtruEchipa.setListTeam(lista);
                        }
                        else {
                                filtruEchipa.setListTeam(listaFunctiiLocal);
                        }
                        if (filtruUtilizator.getName() != null) {
                                filtruEchipa.setName(filtruUtilizator.getName());
                        }
                        if (filtruUtilizator.getIdProvincia() != null) {
                                filtruEchipa.setIdProvincia(filtruUtilizator.getIdProvincia());
                        }
                        listUsersLocal = utilizatorService.cautareUtilizatorCriteriaLocal(filtruEchipa);
                        rowCountLocal = listUsersLocal.size();
                }

        }

        /**
         * Metoda de încărcare a documentelor.
         */
        public void incarcareDocument() {
                for (final Documente doc : this.listaDocumente) {
                        this.documenteIncarcate.add(doc);
                }

        }

        /**
         * Șterge alerta curentă care este vizionată.
         */
        public void clearAlerta() {
                this.alertaActual = new Alerta();
        }

        /**
         * Descărcați un document încărcat de utilizator.
         * @param documente documentul selectat
         */
        public void descarcareFisier(final Documente documente) {
                setFile(null);
                try {
                        setFile(documentService.descarcareDocument(documente));
                }
                catch (final GesintException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la descărcarea fișierului");
                }
        }

        /**
         * Încarcă alerta cu identificator ei.
         * @param alertaLocal Alerta
         */
        public void detaliuAlerta(final Alerta alertaLocal) {
                this.alertaActual = this.cautareAlerteLocal(alertaLocal.getId());
        }

        /**
         * Eliminarea unei alerte.
         * @param alerta aleasa pentru eliminare
         */
        public void eliminareAlerta(final Alerta alert) {
                try {
                        this.alerta = alert;
                        final List<Documente> documents = documentService.findByAlerta(alerta);
                        if (!documents.isEmpty()) {
                                for (final Documente doc : documents) {
                                        documentService.delete(doc);
                                }
                        }
                        alertaService.delete(alerta.getId());

                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.ELIMINAREMESAJ, Constante.OKELIMINAREMESAJ);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Realizează eliminarea logică a documentului care poate fi recuperat din coșul de gunoi.
         * @param document Documente care o sa fie eliminat logic.
         */
        public void eliminareDocument(final Documente document) {
                try {
                        this.listaDocumente.remove(document);
                        this.documentService.delete(document);
                }
                catch (final DataAccessException e) {
                }
        }

        /**
         * Realizează eliminarea logică a documentului care poate fi recuperat din coșul de gunoi.
         * @param document Documente care o sa fie eliminat logic.
         */
        public void eliminareDocumentFinal(final Documente document) {
                try {
                        this.documenteIncarcate.remove(document);
                        this.documentService.delete(document);
                }
                catch (final DataAccessException e) {
                }
        }

        /**
         * Trimiteți alerte destinatarilor indicați.
         * @throws PebbleException
         */
        public String trimitereAlerta() throws PebbleException {
                final Map<String, String> paramPlantilla = new HashMap<>();
                String destina = Constante.SPATIU;
                final StringBuilder destinatari = new StringBuilder();
                try {
                        if (this.utilizatoriSelectionatiFinali.isEmpty()) {
                                Utilizator utilizator = this.utilizatorService.fiindOne(this.numeUtilizator);
                                if (utilizator == null) {
                                        utilizator = new Utilizator();
                                        utilizator.setAlertChannel(CanalAlertaEnum.EMAIL);
                                        utilizator.setUsername(this.numeUtilizator);
                                }
                                this.utilizatoriSelectionatiFinali.add(utilizator);

                        }

                        final String titlu = alerta.getTipAlerta().getDescription().concat(Constante.PUNCTSPATIU)
                                        .concat(alerta.getTitlu());
                        paramPlantilla.put("titlu", titlu);
                        paramPlantilla.put("secretariat", "secretariat@per.ro");
                        paramPlantilla.put("telefon", "0733.061.651");
                        paramPlantilla.put("cuerpo", alerta.getDescriere());

                        this.alertaService.sendAlert(this.alerta, this.utilizatoriSelectionatiFinali,
                                        documenteIncarcate, Constante.TEMPLATEEMAILBASE, paramPlantilla);
                        alerta.setTitlu(alerta.getTipAlerta().getDescription().concat(Constante.PUNCTSPATIU)
                                        .concat(alerta.getTitlu()));
                        alerta.setDataTrimiteri(new Date());
                        alerta.setChannel(CanalAlertaEnum.EMAIL);
                        for (final Utilizator usu : utilizatoriSelectionatiFinali) {
                                destinatari.append(Constante.VIRGULA);
                                destinatari.append(usu.getUsername());
                        }
                        destina = destinatari.toString().substring(1);
                        alerta.setDestinatari(destina);
                        this.alertaService.save(alerta);
                        // Comprbar que la lista no es vacia
                        if (!documenteIncarcate.isEmpty()) {
                                for (final Documente doc : documenteIncarcate) {
                                        doc.setAlerta(alerta);
                                        doc.setValidat(true);
                                        doc.setDescriere("Document anexat la trimiterea e-mailului :"
                                                        .concat(alerta.getTitlu()));
                                        documentService.save(doc);
                                }
                        }
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.TRIMITEREALERTA, "Corespondența electronică a fost trimisă corect.");
                        this.curatareFiltre();
                }
                catch (

                final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la trimiterea corespondenței electronice"
                                                        .concat(Constante.DESCEROAREMESAJ));
                }
                return "/alerte/alerte?faces-redirect=true";
        }

        /**
         * Verificați dacă un fișier corespunde oricărui document solicitat atât în nume, cât și în extensie.
         * @param fisier incarcat
         * @return booleano da sau nu
         */
        private boolean esteDocumentatie(final UploadedFile fisier) {
                final String numeFisier = fisier.getFileName();
                boolean esValabil = false;
                esValabil = numeFisier.toLowerCase().startsWith(fisier.getFileName().toLowerCase());
                return esValabil;
        }

        /**
         * Metoda de stabilire a utilizatorilor în lista generală.
         */
        public void stabilireUtilizatoriFinali() {
                final Utilizator usua = new Utilizator();
                if (this.opcion == NumarMagic.NUMBERTWO) {
                        for (final Utilizator user : this.utilizatoriSelectionati) {
                                user.getUsername();
                                if (!this.utilizatoriSelectionatiFinali.contains(user)) {
                                        this.utilizatoriSelectionatiFinali.add(user);
                                }
                        }
                }
                else if (this.opcion == NumarMagic.NUMBERTHREE) {
                        if (utilizatorExtern != null) {
                                final String[] chei = utilizatorExtern.split(Constante.VIRGULA);
                                for (final String cheie : chei) {
                                        final String nume = cheie.trim();
                                        final Utilizator usu = utilizatorService.fiindOne(nume);
                                        if (usu == null) {
                                                usua.setUsername(nume);
                                                usua.setDestinatarExtern(true);
                                                usua.setAddress(null);
                                                usua.setAlertChannel(CanalAlertaEnum.EMAIL);
                                                usua.setBirthDate(new Date());
                                                usua.setCivilStatus(null);
                                                usua.setEducation(EducatieEnum.NESPECIFICAT);
                                                usua.setEmail(nume);
                                                usua.setProvince(null);
                                                usua.setIdCard(null);
                                                usua.setLastName("VIA EMAIL");
                                                usua.setLocality(null);
                                                usua.setName(Constante.DESTINATAR);
                                                usua.setNumberCard(null);
                                                usua.setPassword(
                                                                "$2a$10$tDGyXBpEASeXlAUCdKsZ9u3MBBvT48xjA.v0lrDuRWlSZ6yfNsLve");
                                                usua.setPersonalEmail(nume);
                                                usua.setPhone(null);

                                                usua.setRole(RolEnum.ROLE_SIMPATIZANT);
                                                usua.setSex(null);
                                                usua.setValidated(false);
                                                usua.setWorkplace(null);
                                                utilizatorService.save(usua);
                                                if (!this.utilizatoriSelectionatiFinali.contains(usua)) {
                                                        this.utilizatoriSelectionatiFinali.add(usua);
                                                }
                                        }

                                        else {
                                                if (!this.utilizatoriSelectionatiFinali.contains(usu)) {
                                                        this.utilizatoriSelectionatiFinali.add(usu);
                                                }
                                        }
                                }
                        }
                }
                else {
                        for (final Utilizator user : this.utilizatoriSelectionati) {
                                user.getUsername();
                                if (!this.utilizatoriSelectionatiFinali.contains(user)) {
                                        this.utilizatoriSelectionatiFinali.add(user);
                                }
                        }
                }
        }

        /**
         * Metoda care salveaza un fișier încărcat de utilizator ca document al cererii, după ce ați confirmat că nu
         * este un fișier corupt și că se potrivește cu oricare dintre cele solicitate.
         * @param event eveniment lansat din formular
         * @return ruta traseu
         */
        public void gestioneazaIncarcareDocument(final FileUploadEvent event) {
                try {
                        final UploadedFile fisier = event.getFile();
                        // 19 es el id del tipodocumento para "template"
                        final TipDocument tip = TipDocument.builder().id(19L).build();
                        final Utilizator utilizator = (Utilizator) SecurityContextHolder.getContext()
                                        .getAuthentication().getPrincipal();
                        if (this.esteDocumentatie(fisier)) {
                                final Documente documente = this.documentService.cargaDocumento(fisier, tip,
                                                utilizator);
                                this.listaDocumente.add(documente);
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_INFO, Constante.INREGISTRARE,
                                                "Fișierul/ele încărcat/e cu succes", Constante.MSGS);
                        }
                        else {
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                                Constante.INCARCAREFISIER,
                                                "Fișierul încărcat " + fisier.getFileName()
                                                                + " nu este valabil, numele sau extensia nu corespunde cu fișierul încărcat.",
                                                Constante.MSGS);
                        }
                }

                catch (DataAccessException | GesintException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                        TipRegistruEnum.EROARE.name(), "A apărut o eroare la încărcarea fișierului. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                        final String descripcion = "A apărut o eroare la încărcarea fișierului";
                        this.regActividadService.salveazaRegistruEroare(descripcion,
                                        SectiuniEnum.GESTORDOCUMENTAL.getDescriere(), e);
                }

        }

        /**
         * Metoda care salveaza un fișier încărcat de utilizator ca document al cererii, după ce ați confirmat că nu
         * este un fișier corupt și că se potrivește cu oricare dintre cele solicitate.
         * @param event eveniment lansat din formular
         * @return ruta traseu
         */
        public void gestioneazaIncarcareDocumentMod(final FileUploadEvent event) {
                try {

                        final UploadedFile fisier = event.getFile();
                        // 19 es el id del tipodocumento para "template"
                        final TipDocument tip = TipDocument.builder().id(19L).build();
                        final Utilizator utilizator = (Utilizator) SecurityContextHolder.getContext()
                                        .getAuthentication().getPrincipal();
                        if (this.esteDocumentatie(fisier)) {
                                final Documente documente = this.documentService.cargaDocumento(fisier, tip,
                                                utilizator);
                                this.listaDocumente.add(documente);
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_INFO, Constante.INREGISTRARE,
                                                "Fișierul/ele încărcat/e cu succes", Constante.MSGS);
                        }
                        else {
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                                Constante.INCARCAREFISIER,
                                                "Fișierul încărcat " + fisier.getFileName()
                                                                + " nu este valabil, numele sau extensia nu corespunde cu fișierul încărcat.",
                                                Constante.MSGS);
                        }
                }

                catch (DataAccessException | GesintException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                        TipRegistruEnum.EROARE.name(), "A apărut o eroare la încărcarea fișierului. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                        final String descripcion = "A apărut o eroare la încărcarea fișierului";
                        this.regActividadService.salveazaRegistruEroare(descripcion,
                                        SectiuniEnum.GESTORDOCUMENTAL.getDescriere(), e);
                }

        }

        /**
         * Transmite datele utilizatorului pe care dorim să le modificăm în formular, astfel încât acestea să schimbe
         * valorile pe care le doresc.
         *
         * @param usuario Utilizator recuperat din formularul de căutare al utilizatorului
         * @return URL-ul paginii de modificare a utilizatorului
         */
        public String getFormModificareAlerta(final Alerta aler) {

                final Alerta acomun = alertaService.fiindOne(aler);
                String redirectionare = null;
                this.listaDocumente = new ArrayList<>();
                this.documenteIncarcate = new ArrayList<>();
                this.documenteIncarcate = documentService.findByAlerta(acomun);
                if (acomun != null) {
                        this.alerta = acomun;
                        redirectionare = "/alerte/modificareAlerta?faces-redirect=true";
                }
                else {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                        Constante.MODIFICAREMESAJ,
                                        "A apărut o eroare la accesarea alertei. Alerta nu există.");
                }
                return redirectionare;
        }

        /**
         * Intrarea în pagină pentru a trimite o nouă alertă.
         * @return String
         */
        public String getFormNouaCorespondenta() {
                this.alerta = new Alerta();
                this.utilizatorExtern = Constante.SPATIU;
                this.listaTeams = new ArrayList<>();
                this.utilizatoriSelectionati = new ArrayList<>();
                this.listaDocumente = new ArrayList<>();
                this.documenteIncarcate = new ArrayList<>();
                this.utilizatoriSelectionatiFinali = new ArrayList<>();
                this.modelUser = new LazyDataUtilizatori(this.utilizatorService);
                final Calendar date = Calendar.getInstance();
                date.add(Calendar.DAY_OF_YEAR, 1);
                this.currentDate = date.getTime();
                return "/alerte/nouaAlerta?faces-redirect=true";
        }

        /**
         * Salvati alerta in baza de date.
         */
        public String salvareAlerta() {
                try {
                        if (!this.utilizatoriSelectionatiFinali.isEmpty() || alerta.getDestinatari() != null) {

                                // Daca venim de modificare
                                if (alerta.getDestinatari() == null) {
                                        String destina = Constante.SPATIU;
                                        final StringBuilder destinatari = new StringBuilder();
                                        for (final Utilizator usu : utilizatoriSelectionatiFinali) {
                                                destinatari.append(Constante.VIRGULA);
                                                destinatari.append(usu.getUsername());
                                        }
                                        destina = destinatari.toString().substring(1);
                                        alerta.setDestinatari(destina);
                                }
                                alerta.setTitlu(alerta.getTipAlerta().getDescription().concat(Constante.PUNCTSPATIU)
                                                .concat(alerta.getTitlu()));
                                alerta.setChannel(CanalAlertaEnum.EMAIL);
                                alerta.setDescriere(this.alerta.getDescriere());
                                if (this.alerta.getAutomatic()) {
                                        alerta.setAutomatic(true);
                                        alerta.setDataTrimiteri(this.alerta.getDataTrimiteri());
                                }
                                else {
                                        alerta.setAutomatic(false);
                                }
                                alertaService.save(alerta);
                                if (!this.documenteIncarcate.isEmpty()) {
                                        for (final Documente documente : documenteIncarcate) {
                                                documente.setAlerta(alerta);
                                                documente.setValidat(true);
                                                documentService.save(documente);
                                        }
                                }
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                Constante.INREGISTRARE,
                                                "Comunicarea electonică a fost salvată cu succes.");
                        }
                        else {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ, "Nu se pot salva comunicări fara destinatari.");
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la salvarea Alertei/Comunicării "
                                                        .concat(Constante.DESCEROAREMESAJ));
                        // this.registruActivitateService.salveazaRegistruEroare(SectiuniEnum.ALERTAS.name(),
                        // Constante.ALERTA, e);
                }
                return "/alerte/alerte?faces-redirect=true";
        }

        /**
         * Metoda care recupereaza toate functiile centrale
         * @return List<ParamEchipa>
         *
         */
        private List<ParamEchipa> incarcamToateFunctileCentrale() {
                new ArrayList<>();
                return pteamService.findByOrganization(Constante.CONDUCERECENTRALA);
        }

        /**
         * Metoda care recupereaza toate functiile locale
         * @return List<ParamEchipa>
         *
         */
        private List<ParamEchipa> incarcamToateFunctileLocale() {
                new ArrayList<>();
                return pteamService.findByOrganization(Constante.CONDUCERELOCALA);
        }

        /**
         * Metoda Init AlertaBean.
         */
        @PostConstruct
        public void init() {
                this.alerta = new Alerta();
                this.filtruAlerta = new FiltruAlerta();
                this.filtruUtilizator = new FiltruUtilizator();
                this.utilizatoriExterniSelectionati = utilizatorService.findByName();
                this.utilizatoriSelectionatiFinali = new ArrayList<>();
                this.echipa = new Echipa();
                this.numarMembrii = 0;
                this.judete = new ArrayList<>();
                this.listaFunctiiLocal = new ArrayList<>();
                this.list = new ArrayList<>();
                for (int i = 0; i < NUMBERCOLUMNSTABLE; i++) {
                        this.list.add(Boolean.TRUE);
                }
                this.model = new LazyDataAlerte(this.alertaService);
                Utilitati.cautareSesiune("alertaBean");
        }

        /**
         * Acesta curăță utilizatorul de date și cel de fișiere.
         */
        public void curatareFiltre() {
                this.filtruUtilizator = new FiltruUtilizator();
                this.utilizatoriSelectionati = new ArrayList<>();
                this.modelUser = new LazyDataUtilizatori(this.utilizatorService);
        }

        /**
         * Curățați filtre de căutarea.
         */
        public void cautareCautare() {
                this.filtruAlerta = new FiltruAlerta();
                this.model = new LazyDataAlerte(this.alertaService);
        }

        /**
         * Afișează caseta de dialog Alertă cu alerta curentă.
         * @param a Alerta
         */
        public void deschidereDialogAlertaCuDate(final Alerta alert) {
                this.alertaActual = alert;
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute(Constante.DIALOGALERTAS);
        }

        /**
         * Afișează caseta de dialog Alertă.
         */
        public void deschidereDialogAlerte() {
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute(Constante.DIALOGALERTAS);
        }

        /**
         * Metoda de a adăuga noi utilizatori la lista de utilizatori selectați.
         */
        public void schimbarePaginaUtilizatori() {
                if ((this.utilizatoriSelectionati != null) && !this.utilizatoriSelectionati.isEmpty()) {
                        this.utilizatoriSelectionatiFinali.addAll(this.utilizatoriSelectionati);
                        this.utilizatoriSelectionati = new ArrayList<>(this.utilizatoriSelectionatiFinali);
                }
        }

        /**
         * Metodă care asociază un utilizator când își selectează caseta de selectare pentru echipa de conducere.
         * @param event eveniment lansat care conține alerta
         */
        public void onRowSelectedTeam(final SelectEvent event) {
                final Echipa echipa = (Echipa) event.getObject();
                this.utilizatoriSelectionatiFinali.add(echipa.getUser());
                this.modelUser.setDsource(this.utilizatoriSelectionatiFinali);
        }

        /**
         * Metodă care asociază un utilizator când își selectează caseta de selectare pentru membrii.
         * @param event eveniment lansat care conține alerta
         */
        public void onRowSelectedUser(final SelectEvent event) {
                final Utilizator usu = (Utilizator) event.getObject();
                this.utilizatoriSelectionatiFinali.add(usu);
                this.modelUser.setDsource(this.utilizatoriSelectionatiFinali);
        }

        /**
         * Metodă care dezasociază un utilizator când deselectează caseta de selectare pentru echipa de conducere.
         * @param event eveniment lansat care conține alerta
         */
        public void onRowUnSelectedTeam(final UnselectEvent event) {
                final Echipa echipa = (Echipa) event.getObject();
                this.utilizatoriSelectionatiFinali.remove(echipa.getUser());
                this.modelUser.setDsource(this.utilizatoriSelectionatiFinali);
        }

        /**
         * Metodă care dezasociază un utilizator când deselectează caseta de selectare pentru membrii.
         * @param event eveniment lansat care conține alerta
         */
        public void onRowUnSelectedUser(final UnselectEvent event) {
                final Utilizator us = (Utilizator) event.getObject();
                this.utilizatoriSelectionatiFinali.remove(us);
                this.modelUser.setDsource(this.utilizatoriSelectionatiFinali);
        }

        /**
         * Controlează coloanele vizibile în lista rezultatelor motorului de căutare.
         * @param e ToggleEvent
         */
        public void onToggle(final ToggleEvent e) {
                this.list.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
        }

        /**
         * Metodă care captează evenimentul "Selectați toate" sau "Deselectați toate" membrii equipei în vizualizarea
         * Avertizări.
         * @param toogleEvent ToggleSelectEvent
         */
        public void onToggleSelectTeam(final ToggleSelectEvent toogleEvent) {

                if (toogleEvent.isSelected()) {
                }
                else {
                        this.utilizatoriSelectionati = new ArrayList<>();
                }
                this.modelUser.setDsource(this.utilizatoriSelectionatiFinali);
        }

        /**
         * Metodă care captează evenimentul "Selectați toate" sau "Deselectați toate" membrii în vizualizarea
         * Avertizări.
         * @param toogleEvent ToggleSelectEvent
         */
        public void onToggleSelectUsers(final ToggleSelectEvent toogleEvent) {
                if (toogleEvent.isSelected()) {
                        this.utilizatoriSelectionati = new ArrayList<>(
                                        this.utilizatorService.cautareUtilizatorCriteria(this.filtruUtilizator));
                        for (final Utilizator user : this.utilizatoriSelectionati) {
                                user.getUsername();
                                if (!this.utilizatoriSelectionatiFinali.contains(user)) {
                                        this.utilizatoriSelectionatiFinali.add(user);
                                }
                        }
                }
                else {
                        this.utilizatoriSelectionati = new ArrayList<>();
                }
                this.modelUser.setDsource(this.utilizatoriSelectionatiFinali);
        }

        /**
         * Metodă care captează evenimentul "Selectați toate" sau "Deselectați toate" membrii în vizualizarea
         * Avertizări.
         * @param toogleEvent ToggleSelectEvent
         */
        public void onToggleSelectUsersCL(final ToggleSelectEvent toogleEvent) {
                if (toogleEvent.isSelected()) {
                        this.utilizatoriSelectionati = new ArrayList<>(listUsersLocal);
                        for (final Utilizator user : this.utilizatoriSelectionati) {
                                user.getUsername();
                                if (!this.utilizatoriSelectionatiFinali.contains(user)) {
                                        this.utilizatoriSelectionatiFinali.add(user);
                                }
                        }
                }
                else {
                        this.utilizatoriSelectionati = new ArrayList<>();
                }
                this.modelUser.setDsource(this.utilizatoriSelectionatiFinali);
        }

        /**
         * Eliminați un utilizator din lista utilizatorilor selectați pentru a primi alerta.
         * @param usuario User
         */
        public void eliminareUtilizator(final Utilizator utili) {
                this.utilizatoriSelectionatiFinali.remove(utili);
                final List<Utilizator> utilizatorEliminare = new ArrayList<>();
                for (final Utilizator usu : this.utilizatoriSelectionatiFinali) {
                        if (usu.getUsername().equals(utili.getUsername())) {
                                utilizatorEliminare.add(usu);
                        }
                }
                this.utilizatoriSelectionatiFinali.removeAll(utilizatorEliminare);
        }

        /**
         * Guarda en una varible el número de días de inactividad de un usuario pasado por parámetro.
         * @param fecha usuario a consultar
         */
        public void setDiasInactivo(final Date data) {
                setZileInactivitate(Utilitati.getDiasHastaHoy(data));
        }

}
