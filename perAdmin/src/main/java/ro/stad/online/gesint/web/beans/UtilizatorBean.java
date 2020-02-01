package ro.stad.online.gesint.web.beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.Visibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.lazydata.LazyDataUtilizatori;
import ro.stad.online.gesint.model.filters.FiltruEchipa;
import ro.stad.online.gesint.model.filters.FiltruUtilizator;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.CanalAlertaEnum;
import ro.stad.online.gesint.persistence.entities.enums.EducatieEnum;
import ro.stad.online.gesint.persistence.entities.enums.SectiuniEnum;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;
import ro.stad.online.gesint.services.LocalitateService;
import ro.stad.online.gesint.services.ParamEchipaService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.RegistruActivitateService;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;

/**
 * Controlor de operațiuni legate de gestionarea utilizatorilor. Înregistrarea utilizatorilor, modificarea
 * utilizatorilor, ștergerea utilizatorilor, căutarea utilizatorilor, parola de căutare și restaurare.
 *
 * @author STAD
 */

@Setter
@Getter
@Controller("userBean")
@Scope(Constante.SESSION)

public class UtilizatorBean implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = NumarMagic.NUMBERELEVENLONG;

        /**
         * Constanta pentru logs
         */
        private static final Logger LOG = LoggerFactory.getLogger(UtilizatorBean.class.getSimpleName());

        /**
         * Utilizator/Membru.
         */
        private Utilizator user;

        /**
         * Obiectul de cautare al utilizatorilor.
         */
        private FiltruUtilizator filtruUtilizator;

        /**
         * Obiectul de cautare al echipei.
         */
        private FiltruEchipa filtruEchipa;

        /**
         * Lista de judete.
         */
        private List<Provincia> judete;

        /**
         * Lista de utilizatori locali.
         */
        private List<Utilizator> listUsersLocal;

        /**
         * Judet.
         */
        private Provincia provinciSelec;

        /**
         * Judet.
         */
        private Provincia provincia;

        /**
         * Lista Booleans pentru controlul afișării coloanelor din vedere.
         */
        private List<Boolean> list;

        /**
         * Numărul maxim de coloane vizibile.
         */
        private static final int NUMARTCOLOANELISTAUTILIZATORI = NumarMagic.NUMBERSEVEN;

        /**
         * LazyModel pentru paginarea de pe serverul de date de căutare a utilizatorilor.
         */
        private LazyDataUtilizatori model;

        /**
         * Serviciu de utilizatori.
         */
        @Autowired
        private UtilizatorService utilizatorService;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Variabila utilizata pentru a injecta serviciul localitatilor.
         *
         */
        @Autowired
        private LocalitateService localitateService;

        /**
         * Variabila utilizata pentru a injecta serviciul functilor
         *
         */
        @Autowired
        private ParamEchipaService pteamService;

        /**
         * Serviciul de registu de activitate.
         */
        @Autowired
        private RegistruActivitateService registruActivitateService;

        /**
         * Lista de localitati.
         */
        private List<Localitate> localitati;

        /**
         * Lista de localitati selectat.
         */
        private List<Localitate> localitatiSelectate;

        /**
         * Localitate aleasa.
         */
        private Localitate localidadSelected;

        /**
         * Utilizator.
         */
        private Utilizator usuario;

        /**
         * Educatie
         */
        private EducatieEnum education;

        /**
         * Encryptor pentru cuvinte cheie.
         */
        @Autowired
        private transient BCryptPasswordEncoder passwordEncoder;

        /**
         * Mesaj de eroare afișat utilizatorului.
         */
        private transient String mesajEroare;

        /**
         * Variabilă folosită pentru a stoca grupul localitatii selectate.
         *
         */
        private Provincia grupLocalitatiSelectate;

        /**
         * Variabilă folosită pentru a stoca tipul localitate selectat.
         *
         */
        private TipLocalitateEnum tipLocalitateSelectat;

        /**
         * Numele documentului.
         */
        private String numeDoc;

        /**
         * Fotoografia utilizator.
         */
        private byte[] photoSelected;

        /**
         * Identificatorul judetului
         */
        private String idProvincia;

        /**
         * Identificatorul localitatii
         */
        private Long idLocalidad;

        /**
         * Data actuala
         */
        private Date currentDate;

        /**
         * eliminat
         */
        private Boolean eliminado;

        /**
         * Variabila pentru mesaj
         */
        private String mesaje;

        /**
         * Variabila pentru lista de functii existente in bbdd
         */
        private List<ParamEchipa> listaFunctii;

        /**
         * Variabila pentru lista de functii existente in bbdd
         */
        private List<ParamEchipa> listaFunctiiLocal;

        /**
         * Variabila pentru o functie existente in bbdd
         */
        private ParamEchipa pteam;

        /**
         * Cadena que identifica la pestaña activa del formulario de estadísticas.
         */
        private String pestaniaActiva;

        /**
         * Numar de membri in conducerea locala.
         */
        private int rowCountLocal;

        /**
         * Numar de membri in conducerea centrala.
         */
        private int rowCountCentral;

        /**
         * Variabila pentru lista de functii existente in bbdd
         */
        private List<ParamEchipa> listaFunctiiCentral;

        /**
         * Variabila pentru lista de functii existente in bbdd
         */
        private List<Utilizator> listUsersCentral;

        /**
         * Variabila pentru id-ul unui membru
         */
        private Long teamIdUser;

        /**
         * Componente de utilidades.
         */
        @Autowired
        private Utilitati utilitati;

        /**
         * Deschide dialogul pentru pozitionarea membrilor.
         */
        public void deschideDialogOrdenaMembru(final List<Utilizator> lista, final String provin) {

                if (provin.equals(Constante.SPATIU)) {
                        listUsersLocal = new ArrayList<>();
                        filtruEchipa = new FiltruEchipa();
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "Pentru a putea poziționa membrii din conducerea unei organizații locale, trebuie să alegeți una!");
                }
                else {
                        listUsersLocal = lista;
                        final RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dlgOrdena').show();");
                }
        }

        /**
         * Deschide dialogul pentru pozitionarea membrilor.
         */
        public void deschideDialogOrdenaMembruCC(final List<Utilizator> lista) {
                listUsersCentral = lista;
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgOrdenaCC').show();");
        }

        /**
         * Returnează o listă a localităților care aparțin unui judet. Acesta este folosit pentru a reîncărca lista
         * localităților în funcție de judetul selectat.
         * @param List<Localitate> lista de localitati
         */
        public List<Localitate> actualizareLocalitati(final String prov) {

                localitati = new ArrayList<>();
                this.provincia = provinciaService.findById(prov);
                if (this.provincia != null) {
                        try {
                                localitati = localitateService.findByProvince(provincia);
                        }
                        catch (final DataAccessException e) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                LOG.error("actualizareLocalitati: ".concat(String.valueOf(e)));
                        }
                }
                return localitati;
        }

        /**
         * Returnează o listă a localităților care aparțin unui judet. Acesta este folosit pentru a reîncărca lista
         * localităților în funcție de judetul selectat.
         * @param List<Localitate> lista de localitati
         */
        public List<Localitate> actualLocalitati(final String prov) {
                localitati = new ArrayList<>();
                this.provincia = provinciaService.findByName(prov);
                if (this.provincia != null) {
                        try {
                                localitati = localitateService.findByProvince(provincia);
                        }
                        catch (final DataAccessException e) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ, Constante.DESCEROAREMESAJ);
                                LOG.error("actualLocalitati: ".concat(String.valueOf(e)));
                        }
                }
                return localitati;
        }

        /**
         * Inregistratrea utilizatorului.
         * @param usu User
         */
        public void inregistrareUtilizator(final Utilizator usu) {
                try {
                        this.usuario = new Utilizator();
                        this.usuario = usu;
                        final String valida = Constante.INREGISTRAREMESAJ;
                        // Validam noul utilizator
                        if (validar(valida)) {
                                usuario.setLocality(localitateService.findById(idLocalidad));
                                usuario.setEmail(usuario.getUsername());
                                usuario.setValidated(true);
                                usuario.setProvince(provinciaService.findById(idProvincia));
                                usuario.setAlertChannel(CanalAlertaEnum.EMAIL);
                                usuario.setPassword("$2a$10$tDGyXBpEASeXlAUCdKsZ9u3MBBvT48xjA.v0lrDuRWlSZ6yfNsLve");
                                utilizatorService.save(usuario);
                                final String mesaj = "A fost intregistrat utilizatorul :" + usuario.getNombreCompleto();
                                registruActivitateService.salveazaRegistruInregistrareModificare(null,
                                                SectiuniEnum.USERS.getDescriere(), Constante.INREGISTRAREMESAJ, mesaj);
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                Constante.SCHIMBDATE, Constante.OKMODIFICAREMESAJ);
                        }
                        else {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ, this.mesajEroare);
                                LOG.info("inregistrareUtilizator: membrul nou: ".concat(usuario.getName().concat(" "))
                                                .concat(usuario.getLastName()).concat(" nu a fost validat"));

                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        LOG.error("inregistrareUtilizator: ".concat(String.valueOf(e)));
                }
        }

        /**
         * Caută utilizatori în funcție de filtrele introduse în formularul de căutare.
         */
        public void cautareUtilizator() {
                if (user.getRole().getDescription().equals("Administrator")) {
                        model.setFiltruUtilizator(filtruUtilizator);

                }
                else {
                        filtruUtilizator.setIdProvincia(user.getProvince().getIndicator());
                        model.setFiltruUtilizator(filtruUtilizator);

                }
                model.load(0, NumarMagic.NUMBERFIFTEEN, Constante.DATECREATE, SortOrder.DESCENDING, null);
        }

        /**
         * Caută utilizatori în funcție de filtrele introduse în formularul de căutare.
         */
        public void cautareUtilizatorLocal() {
                rowCountLocal = 0;
                if (filtruEchipa.getIdFunctia() == null) {
                        List<ParamEchipa> lista = new ArrayList<>();
                        lista = incarcamToateFunctileLocale();
                        filtruEchipa.setListTeam(lista);
                }
                listUsersLocal = utilizatorService.cautareUtilizatorCriteriaLocal(filtruEchipa);
                rowCountLocal = listUsersLocal.size();
        }

        /**
         * Cautată un utilizator cu cnp-ul și returneaza adevărat sau fals.
         * @return boolean
         */
        private boolean cautareUtilizatorPorNif() {
                Boolean resultat = true;
                final Utilizator use = this.utilizatorService.findByIdCard(this.usuario.getIdCard());
                if (use != null && !use.getIdCard().equals(this.usuario.getIdCard())) {
                        resultat = false;
                }
                return resultat;
        }

        /**
         * Caută utilizatori în funcție de filtrele introduse în formularul de căutare.
         */
        public List<Utilizator> cautareUtilizatoriCentral() {
                List<Utilizator> sefi = new ArrayList<>();
                rowCountCentral = 0;
                listUsersCentral = new ArrayList<>();
                filtruEchipa = new FiltruEchipa();
                List<ParamEchipa> lista = new ArrayList<>();
                lista = incarcamToateFunctileCentrale();
                filtruEchipa.setListTeam(lista);
                sefi = utilizatorService.cautareUtilizatorCriteriaLocal(filtruEchipa);
                rowCountCentral = sefi.size();
                listUsersCentral = sefi;
                return listUsersCentral;
        }

        /**
         * Carga un documento que se recibe a través de un evento FileUploadEvent. Esta carga se realiza sobre el objeto
         * documento y no se guarda en base de datos. Se hace una comprobación para verificar si el tipo de documento se
         * corresponde a la realidad.
         *
         * @param event Evento que se lanza en la carga del documento y que contiene el mismo
         * @throws IOException
         */
        public void incarcareImagine(final FileUploadEvent event) throws IOException {

                final UploadedFile uFile = event.getFile();
                this.usuario = utilizatorService
                                .incarcareImaginaFaraStocare(IOUtils.toByteArray(uFile.getInputstream()), usuario);
                numeDoc = uFile.getFileName();
        }

        /**
         * Incarcam datele personale ale utilizatorului
         * @param provincia
         * @param nuevaLocalidad
         * @param usuario
         * @return usuario
         */
        private void incarcareDatePersonaleUser(final Provincia provincia, final Localitate nuevaLocalidad,
                        final Utilizator usuario) {
                usuario.setLocality(nuevaLocalidad);
                usuario.setProvince(provincia);

        }

        /**
         * Metoda de validare a unicității numelui de utilizator.
         * @return boolean
         */
        private boolean cnpCorect() {
                boolean resultat = true;
                if (validaSex() && valideazaAn()) {
                        resultat = true;
                }
                else {
                        resultat = false;
                }
                return resultat;
        }

        /**
         * @param usu
         *
         */
        private void eliminaAdaugaMembruListaConducere(final Utilizator usu) {
                if (teamIdUser != usu.getTeam().getId()) {
                        final ParamEchipa vecheFunctie = pteamService.findById(teamIdUser);
                        if (teamIdUser != NumarMagic.NUMBERTHIRTYL
                                        && vecheFunctie.getOrganization().equals(Constante.CONDUCERECENTRALA)) {
                                listUsersCentral = cautareUtilizatoriCentral();
                                listUsersCentral.remove(usu);
                                reordonareMembruCC();
                                usu.setRank(null);
                        }
                        if (teamIdUser != NumarMagic.NUMBERTHIRTYL
                                        && vecheFunctie.getOrganization().equals(Constante.CONDUCERELOCALA)) {
                                listUsersLocal.remove(usu);
                                reordonareMembru();
                                usu.setRank(null);
                        }
                        final ParamEchipa nouaFunctie = pteamService.findById(usu.getTeam().getId());
                        if (vecheFunctie.getId() != NumarMagic.NUMBERTHIRTYL
                                        && nouaFunctie.getOrganization().equals(Constante.CONDUCERECENTRALA)) {
                                listUsersCentral.add(usu);
                                usu.setRank(Long.valueOf(listUsersCentral.size() + 1));
                        }
                        if (vecheFunctie.getId() != NumarMagic.NUMBERTHIRTYL
                                        && nouaFunctie.getOrganization().equals(Constante.CONDUCERELOCALA)) {
                                listUsersLocal.add(usu);
                                usu.setRank(Long.valueOf(listUsersLocal.size() + 1));
                        }
                }
        }

        /**
         * Eliminación de un usuario.
         * @param usuario a eliminar
         * @see webapp.administracion.usuario.usuarioBusqueda.xhtml (2 matches)
         * @see webapp.administracion.usuario.usuarios.xhtml (2 matches)
         */
        public void eliminarUsuario(final Utilizator usu) {
                try {
                        this.usuario = usu;
                        usuario.setDateDeleted(new Date());
                        utilizatorService.save(usuario);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.ELIMINAREMESAJ, Constante.OKELIMINAREMESAJ);
                        LOG.info("eliminarUsuario: userul:".concat(usuario.getNombreCompleto())
                                        .concat(" afost eliminat de: ")
                                        .concat(utilitati.getUsuarioLogado().getUsername()));
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        LOG.error("eliminarUsuario: ".concat(String.valueOf(e)));
                }
        }

        /**
         * Metoda care verifica daca mai exista un presedinte de filiala
         * @param usu
         * @return
         */
        private boolean existPresedinte(final Utilizator usu) {
                Boolean validaPresedinte = true;
                // Verificam ca persoana care se doreste a modifica nu este presedinte de filiala
                if (usu.getTeam() == null) {
                        final ParamEchipa functia = pteamService.findById(NumarMagic.NUMBERTHIRTYL);
                        usu.setTeam(functia);
                }
                if (usu.getTeam().getId() == NumarMagic.NUMBERONELONG) {
                        final ParamEchipa functia = pteamService.findById(NumarMagic.NUMBERONELONG);
                        final Utilizator presedinte = utilizatorService.findByTeam(functia);
                        if (presedinte != usu && presedinte != null) {
                                validaPresedinte = false;
                                mesaje = "Există un președinte al partidului. Dacă doriți să înlocuiți președintele actual trebuie să modificați membrul "
                                                + presedinte.getNombreCompleto();
                        }
                }
                return validaPresedinte;
        }

        /**
         * Metoda care verifica daca mai exista un presedinte de filiala
         * @param usu
         * @return
         */
        private boolean existPresedinteLocal(final Utilizator usu, final Provincia prov) {
                Boolean validaPresedinte = true;
                // Verificam ca persoana care se doreste a modifica nu este presedinte de filiala
                if (usu.getTeam() == null) {
                        final ParamEchipa functia = pteamService.findById(NumarMagic.NUMBERTHIRTYL);
                        usu.setTeam(functia);
                }
                if (usu.getTeam().getId() == NumarMagic.NUMBERTWENTYONEL) {
                        final ParamEchipa functia = pteamService.findById(NumarMagic.NUMBERTWENTYONEL);
                        final Utilizator presedinte = utilizatorService.findByTeamAndProvince(functia, prov);
                        if (presedinte != usu && presedinte != null) {
                                validaPresedinte = false;
                                mesaje = "Există un președinte al organizației " + presedinte.getProvince().getName()
                                                + " . Dacă doriți să înlocuiți președintele actual trebuie să modificați membrul "
                                                + presedinte.getNombreCompleto();
                        }
                }
                return validaPresedinte;
        }

        /**
         * Metoda care obtine localitatile dupa un judet
         *
         */
        private void extractLocalitati() {
                if (this.filtruUtilizator.getProvinciaSelected() != null) {
                        try {
                                localitati = localitateService.findByProvince(grupLocalitatiSelectate);
                        }
                        catch (final DataAccessException e) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ,
                                                "A apărut o eroare în căutatrea localităților care aparțin județului "
                                                                .concat(Constante.DESCEROAREMESAJ));
                                LOG.info("extractLocalitati: ".concat(
                                                "A apărut o eroare în căutatrea localităților care aparțin județului "));
                        }
                }
        }

        /**
         * Metodă care ne duce la formularul de înregistrare a utilizatorilor noi, inițializând tot ceea ce este necesar
         * pentru afișarea corectă a paginii (enums, utilizator nou,..etc). Se apeleaza din pagina căutare utilizator.
         *
         * @return url-ul páginii de inregistrare utilizator
         */
        public String getFormInregistrareUtilizator() {
                this.usuario = new Utilizator();
                this.photoSelected = null;
                provinciSelec = new Provincia();
                localitati = new ArrayList<>();
                this.idLocalidad = null;
                this.idProvincia = null;
                listaFunctii = new ArrayList<>();
                listaFunctii = pteamService.fiindAll();
                try {
                        setJudete(provinciaService.fiindAll());
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        LOG.error("getFormInregistrareUtilizator: setjudete: ".concat(String.valueOf(e)));
                }
                // Minor de varsta
                final Calendar date = Calendar.getInstance();
                date.add(Calendar.YEAR, -NumarMagic.NUMBEREIGHTEEN);
                this.currentDate = date.getTime();
                return "/users/saveUser.xhtml?faces-redirect=true";
        }

        /**
         * Transmite datele utilizatorului pe care dorim să le modificăm în formular, astfel încât acestea să schimbe
         * valorile pe care le doresc.
         *
         * @param usuario Utilizator recuperat din formularul de căutare al utilizatorului
         * @return URL-ul paginii de modificare a utilizatorului
         */
        public String getFormModificareUser(final Utilizator usua) {
                teamIdUser = 0L;
                final Utilizator usu = utilizatorService.fiindOne(usua.getUsername());
                listaFunctii = new ArrayList<>();
                listaFunctii = pteamService.fiindAll();
                String redireccion = null;
                if (usu != null) {
                        teamIdUser = usu.getTeam().getId();
                        this.usuario = usua;
                        provinciSelec = usuario.getProvince();
                        setLocalitati(localitateService.findByProvince(usuario.getProvince()));
                        if (usuario.getLocality() == null) {
                                for (final Localitate lacal : localitati) {
                                        if (lacal.getResidence()) {
                                                usuario.setLocality(lacal);
                                                break;
                                        }
                                }

                        }

                        redireccion = "/users/modifyUser?faces-redirect=true";
                }
                else {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                        Constante.MODIFICAREMESAJ,
                                        "A apărut o eroare la accesarea membrului. Membrul nu există.");
                        LOG.info("getFormModificareUser: "
                                        .concat("A apărut o eroare la accesarea membrului. Membrul nu există"));
                }
                return redireccion;
        }

        /**
         * Metoda care obține imaginea pentru a previzualiza în cazul în care documentul este de tip imagine..
         * @return StreamedContent
         */
        public StreamedContent getImageUserSelected() {
                return new DefaultStreamedContent(new ByteArrayInputStream(this.photoSelected));
        }

        /**
         * Returnează formularul de căutare utilizator în starea sa inițială și șterge rezultatele căutărilor anterioare
         * dacă este navigat din meniu sau dintr-o altă secțiune.
         *
         * @return pagina de căutare a utilizatorilor
         */
        public String getSearchFormUsers() {
                cautareCautare();
                return "/users/users.xhtml?faces-redirect=true";
        }

        /**
         * Afișează profilul utilizatorului
         *
         * @return URL-ul paginii unde se vede profilul utilizatorului.
         */
        public String getUserProfil() {
                final String username = SecurityContextHolder.getContext().getAuthentication().getName();
                try {
                        user = utilizatorService.fiindOne(username);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroar în căutatrea membrului.".concat(Constante.DESCEROAREMESAJ));
                        LOG.error("getUserProfil: ".concat("A apărut o eroar în căutatrea membrului.")
                                        .concat(String.valueOf(e)));
                }
                return "/principal/miPerfil?faces-redirect=true";
        }

        /**
         * @param lista
         * @return
         *
         */
        private List<ParamEchipa> incarcamToateFunctileCentrale() {
                new ArrayList<>();
                return pteamService.findByOrganization(Constante.CONDUCERECENTRALA);
        }

        /**
         * @param lista
         * @return
         *
         */
        private List<ParamEchipa> incarcamToateFunctileLocale() {
                new ArrayList<>();
                return pteamService.findByOrganization(Constante.CONDUCERELOCALA);
        }

        /**
         * Inicializeaza bean-ul.
         */
        @PostConstruct
        public void init() {
                this.tipLocalitateSelectat = null;
                usuario = new Utilizator();
                eliminado = false;
                judete = new ArrayList<>();
                user = utilizatorService.fiindOne(SecurityContextHolder.getContext().getAuthentication().getName());
                if (user.getRole().getDescription().equals("Administrator")) {
                        this.judete = provinciaService.fiindAll();
                }
                else {
                        judete.add(user.getProvince());
                }
                listUsersLocal = new ArrayList<>();
                localitati = new ArrayList<>();
                provincia = new Provincia();
                provinciSelec = new Provincia();
                filtruUtilizator = new FiltruUtilizator();
                filtruEchipa = new FiltruEchipa();
                rowCountLocal = 0;
                cautareCautare();
                list = new ArrayList<>();
                for (int i = 0; i <= NUMARTCOLOANELISTAUTILIZATORI; i++) {
                        list.add(Boolean.TRUE);
                }
                this.model = new LazyDataUtilizatori(utilizatorService);

                extractLocalitati();
                listaFunctii = new ArrayList<>();
                listaFunctii = pteamService.fiindAll();
                listaFunctiiLocal = new ArrayList<>();
                listaFunctiiLocal = pteamService.fiindAllByParam();
                listUsersCentral = new ArrayList<>();
                Utilitati.cautareSesiune("userBean");
        }

        /**
         * Muestra el diálogo de nueva imágen.
         * @param relacion ActividadPuesto
         * @param tipoImg int
         */
        public Boolean isLocal(final Utilizator usu) {
                Boolean isuserLocal = false;
                final ParamEchipa functie = pteamService.findByIdAndOrganization(usu.getTeam().getId(),
                                Constante.CONDUCERELOCALA);
                if (functie != null) {
                        isuserLocal = true;
                }

                return isuserLocal;
        }

        /**
         * Șterge rezultatele căutărilor anterioare.
         *
         */
        public void cautareCautare() {
                filtruUtilizator = new FiltruUtilizator();
                filtruEchipa = new FiltruEchipa();
                listUsersLocal = new ArrayList<>();
                listUsersCentral = new ArrayList<>();
                rowCountLocal = 0;
                this.model = new LazyDataUtilizatori(this.utilizatorService);
                model.setRowCount(0);
        }

        /**
         * Salvați modificările utilizatorului
         * @param usu User
         */
        public void modificareUtilizator(final Utilizator usu, final String prov, final String local) {
                eliminaAdaugaMembruListaConducere(usu);
                Boolean validamLocal = true;
                Boolean validamCentral = true;
                obtinemJudetul(prov);
                mesaje = Constante.SPATIU;
                if (usu.getTeam().getId() == NumarMagic.NUMBERTWENTYONEL) {
                        validamLocal = existPresedinteLocal(usu, provincia);
                }
                if (usu.getTeam().getId() == NumarMagic.NUMBERONELONG) {
                        validamCentral = existPresedinte(usu);
                }

                final String valida = Constante.MODIFICAREMESAJ;
                localidadSelected = localitateService.localidadByNameIgnoreCaseAndProvincia(local, provincia);
                try {
                        this.usuario = usu;
                        if (validamLocal && validamCentral) {
                                if (validar(valida)) {
                                        if (eliminado == false) {
                                                usuario.setDateDeleted(null);
                                        }
                                        else {

                                                usuario.setDateDeleted(new Date());
                                        }
                                        usuario.setLocality(localidadSelected);
                                        usuario.setProvince(provincia);
                                        final Boolean esteLocal = isLocal(usuario);
                                        if (usuario.getRank() == null
                                                        && usuario.getTeam().getId() != NumarMagic.NUMBERTHIRTYL) {
                                                if (esteLocal) {
                                                        List<ParamEchipa> lista = new ArrayList<>();
                                                        lista = incarcamToateFunctileLocale();
                                                        filtruEchipa.setListTeam(lista);
                                                        listUsersLocal = utilizatorService
                                                                        .findByProvinceAndTeam(provincia, lista);
                                                        usuario.setRank(Long.valueOf(listUsersLocal.size() + 1));
                                                }
                                                else {
                                                        listUsersCentral = cautareUtilizatoriCentral();
                                                        usuario.setRank(Long.valueOf(listUsersCentral.size() + 1));
                                                }

                                        }
                                        utilizatorService.save(usuario);
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                        Constante.SCHIMBDATE, Constante.REGMODOK);
                                        LOG.info("modificareUtilizator: user: ".concat(usuario.getNombreCompleto()));
                                }
                                else {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.SCHIMBDATE, this.mesajEroare);
                                        LOG.info("modificareUtilizator: Eroare-No valida user");
                                }
                        }
                        else {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                "Eroare în modificarea registrului", mesaje);
                                LOG.info("modificareUtilizator: Eroare-No valida validam local si validam central");
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.SCHIMBDATE,
                                        "");
                        LOG.error("modificareUtilizator: ".concat(String.valueOf(e)));
                }
        }

        /**
         * Muestra el diálogo de nueva imágen.
         * @param relacion ActividadPuesto
         * @param tipoImg int
         */
        public void arataDialogulNouaImagine(final Utilizator usuario) {
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg').show();");
        }

        /**
         * Guarda un nuevo municipio.
         *
         * @param nombre del municipio nuevo
         * @param provincia a la que se asocia el nuevo municipio
         */
        public void nuevoMunicipio(final String nombre, final String prov, final TipLocalitateEnum tipLoclalitate,
                        final Utilizator usuario) {
                Boolean existeLocalidad = false;
                this.provincia = provinciaService.findByName(prov);
                if (provincia == null) {
                        this.provincia = provinciaService.findById(prov);
                }
                try {
                        existeLocalidad = localitateService.existeByNameIgnoreCaseAndProvincia(nombre.trim(),
                                        provincia);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                        "S-a produs o erroare în căutarea localitații. "
                                                        .concat(Constante.DESCEROAREMESAJ),
                                        null, "inputNume");
                        LOG.error("nuevoMunicipio: S-a produs o erroare în căutarea localitații "
                                        .concat(String.valueOf(e)));
                }
                this.tipLocalitateSelectat = null;
                tipLocalitateSelectat = tipLoclalitate;
                if (existeLocalidad) {
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                        "Acțiunea nu este permisă. Există deja o localitate care aparține aceluiași județ cu același nume.",
                                        null, "inputNume");
                        LOG.info("nuevoMunicipio: Acțiunea nu este permisă. Există deja o localitate care aparține aceluiași județ cu același nume");
                }
                else {
                        Localitate nuevaLocalidad;
                        try {
                                nuevaLocalidad = localitateService.crearLocalidad(nombre, provincia,
                                                tipLocalitateSelectat);
                                setLocalitati(localitateService.findByProvince(provincia));
                                // Collections.sort(localitatiSelectate, (o1, o2) -> Long.compare(o1.getId(),
                                // o2.getId()));
                                // Incarcam si salvam noua localitate in datele utilizatorului
                                incarcareDatePersonaleUser(provincia, nuevaLocalidad, usuario);
                                // utilizatorService.save(usuario);
                        }
                        catch (final DataAccessException e) {
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                                "Eroare în salvarea localității. ".concat(Constante.DESCEROAREMESAJ),
                                                null, "inputNume");
                                LOG.error("nuevoMunicipio: nuevaLocalidad: ".concat(String.valueOf(e)));
                        }
                }
        }

        /**
         * @param prov
         *
         */
        private void obtinemJudetul(final String prov) {
                if (prov.equals(Constante.SPATIU)) {
                        provincia = provinciSelec;
                }
                else {
                        provincia = provinciaService.findByName(prov);
                }
        }

        /**
         * Metoda care execută pozitionarea.
         * @param event SelectEvent
         */
        public void onReorderCC() {
                try {
                        reordonareMembruCC();
                        cautareCautare();
                        FacesContext.getCurrentInstance();
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.OKMODIFICAREMESAJ,
                                        "Modificarea ordinii în listă a fost realizată cu succes!");
                        LOG.info("onReorderCC: Modificarea ordinii în listă a fost realizată cu succes!");
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        LOG.error("onReorderCC: ".concat(String.valueOf(e)));
                }
        }

        /**
         * Metoda care execută pozitionarea.
         * @param event SelectEvent
         */
        public void onReorderCL() {
                try {
                        reordonareMembru();
                        cautareCautare();
                        FacesContext.getCurrentInstance();
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.OKMODIFICAREMESAJ,
                                        "Modificarea ordinii în listă a fost realizată cu succes!");
                        LOG.info("onReorderCL: Modificarea ordinii în listă a fost realizată cu succes!");
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        LOG.error("onReorderCL: ".concat(String.valueOf(e)));
                }
        }

        /**
         * Metoda care se execută la selectare.
         * @param event SelectEvent
         */
        public void onSelect(final SelectEvent event) {
                usuario = (Utilizator) event.getObject();
        }

        /**
         * Activați / dezactivați vizibilitatea coloanelor din tabelul cu rezultate.
         *
         * @param e checkbox al coloanei selectate
         */
        public void onToggle(final ToggleEvent e) {
                list.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
        }

        /**
         * Funcție care reorientează pozitia
         * @throws DataAccessException excepție de acces la date
         */
        private void reordonareMembru() {
                try {
                        Utilizator user = new Utilizator();
                        for (int i = 0; i < this.listUsersLocal.size(); i++) {
                                user = listUsersLocal.get(i);
                                user.setRank(i + NumarMagic.NUMBERONELONG);
                                utilizatorService.save(user);
                                final RequestContext context = RequestContext.getCurrentInstance();
                                context.execute("PF('dlgOrdena').hide();");
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        LOG.error("reordonareMembru: ".concat(String.valueOf(e)));
                }
        }

        /**
         * Funcție care reorientează pozitia
         * @throws DataAccessException excepție de acces la date
         */
        private void reordonareMembruCC() {
                try {
                        Utilizator user = new Utilizator();
                        for (int i = 0; i < this.listUsersCentral.size(); i++) {
                                user = listUsersCentral.get(i);
                                user.setRank(i + NumarMagic.NUMBERONELONG);
                                utilizatorService.save(user);
                                final RequestContext context = RequestContext.getCurrentInstance();
                                context.execute("PF('dlgOrdenaCC').hide();");
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        LOG.error("reordonareMembruCC: ".concat(String.valueOf(e)));
                }
        }

        /**
         * Se generează o nouă parolă și se trimite prin poștă către utilizator.
         * @return String
         */
        public String restaurarClave() {
                try {
                        final String password = Utilitati.getPassword();
                        this.usuario.setPassword(this.passwordEncoder.encode(password));
                        // final String cuerpoCorreo = "Noua dvs. parolă este: " + password;
                        this.utilizatorService.save(this.usuario);
                        // this.correoService.trimitereEmail(this.usuario.getUsername(), "Restauración de la
                        // contraseña",
                        // cuerpoCorreo);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.PAROLA,
                                        "Un e-mail a fost trimis utilizatorului cu noua parolă");
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.PAROLA,
                                        "A apărut o eroare în regenerarea sau trimiterea parolei. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }
                return "/users/modifyUser?faces-redirect=true";
        }

        /**
         * Evento para el control de cambio de pestaña de grupo de datos(Evaluat, Autoprevent o Usuarios).
         * @param event TabChangeEvent
         */
        public void schimbTipMembri(final TabChangeEvent event) {
                if ("membriSimpatizanti".equals(event.getTab().getId())) {
                        filtruUtilizator = new FiltruUtilizator();
                        pestaniaActiva = Constante.TABMEMBRI;
                        cautareCautare();
                }
                else if ("conducereaLocala".equals(event.getTab().getId())) {
                        filtruUtilizator = new FiltruUtilizator();
                        pestaniaActiva = Constante.TABLOCAL;
                        cautareCautare();
                }
                else {
                        filtruUtilizator = new FiltruUtilizator();
                        pestaniaActiva = Constante.TABCONDUCERE;
                        cautareCautare();
                        listUsersCentral = cautareUtilizatoriCentral();
                }
        }

        /**
         * Método con las validaciones llevadas a cabo al guardar los datos de un usuario 1. Username no repetido 2. Nif
         * no repetido 3. Nif valido
         *
         * @return boolean
         */
        private boolean validar(final String valida) {
                boolean validado = true;
                if (!valideazaUsername()) {
                        LOG.info("validar valideazaUsername: Membrul există deja în sistem");
                        this.mesajEroare = "Membrul există deja în sistem";

                        validado = false;
                }
                if (!validarNifUnico()) {
                        if (valida.equals(Constante.INREGISTRAREMESAJ)) {
                                this.mesajEroare = "CNP-ul există deja în sistem";
                                LOG.info("validar validarNifUnico: CNP-ul există deja în sistem");
                        }
                        else {
                                this.mesajEroare = "CNP-ul nu este corect.";
                                LOG.info("validar validarNifUnico: CNP-ul nu este corect");
                        }
                        validado = false;
                }

                return validado;
        }

        /**
         * Metodă de validare a unicității CNP.
         * @return boolean
         */
        private boolean validarNifUnico() {
                boolean resultat = true;
                if (!StringUtils.isEmpty(this.usuario.getIdCard()) && this.usuario.getIdCard() != null) {
                        try {

                                if (cnpCorect()) {
                                        resultat = cautareUtilizatorPorNif();
                                }
                                else {
                                        resultat = false;
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ,
                                                        " Datele introduse pentru validarea cnp-ului nu sunt corecte. Verificați aceste date și încercați din nou.");
                                        LOG.info("validarNifUnico: Datele introduse pentru validarea cnp-ului nu sunt corecte. Verificați aceste date și încercați din nou.");
                                }
                        }
                        catch (final DataAccessException e) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ,
                                                "A apărut o eroare la validarea CNP-ului membrului, încercați din nou mai târziu");
                                LOG.error("validarNifUnico: ".concat(String.valueOf(e)));
                        }
                }
                return resultat;
        }

        /**
         * Metoda de validare a unicității numelui de utilizator.
         * @return boolean
         */
        private boolean valideazaUsername() {
                boolean resultat = true;
                final Utilizator use = this.utilizatorService.fiindOne(this.usuario.getUsername());
                if (use != null && !use.getUsername().equals(this.usuario.getUsername())) {
                        resultat = false;
                }
                return resultat;
        }

        /**
         * Metoda de validare a unicității numelui de utilizator.
         * @return boolean
         */
        private boolean validaSex() {
                boolean resultat = true;
                final String cnp = this.usuario.getIdCard().substring(0, 1);
                final String sex = this.usuario.getSex().getName();
                if (sex.equals("MAN") && cnp.equals("1") || sex.equals("WOMAN") && cnp.equals("2")) {
                        resultat = true;
                }
                else {
                        resultat = false;
                }
                return resultat;
        }

        /**
         * Metoda de validare a unicității numelui de utilizator.
         * @return boolean
         */
        private boolean valideazaAn() {
                boolean resultat = true;
                if (this.usuario.getIdCard().length() > NumarMagic.NUMBERSEVEN) {
                        final String an = this.usuario.getIdCard().substring(1, NumarMagic.NUMBERTHREE);
                        final String luna = this.usuario.getIdCard().substring(NumarMagic.NUMBERTHREE,
                                        NumarMagic.NUMBERFIVE);
                        final String zi = this.usuario.getIdCard().substring(NumarMagic.NUMBERFIVE,
                                        NumarMagic.NUMBERSEVEN);
                        final Date fecha = this.usuario.getBirthDate();
                        final SimpleDateFormat sdf = new SimpleDateFormat("yy");
                        final SimpleDateFormat lsdf = new SimpleDateFormat(Constante.MM);
                        final SimpleDateFormat zsdf = new SimpleDateFormat("dd");
                        final String anString = sdf.format(fecha);
                        final String lunaString = lsdf.format(fecha);
                        final String ziString = zsdf.format(fecha);
                        if (an.equals(anString) && luna.equals(lunaString) && zi.equals(ziString)) {
                                resultat = true;
                        }
                        else {
                                resultat = false;
                        }
                }
                else {
                        resultat = false;
                }
                return resultat;
        }
}
