package ro.stad.online.gesint.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.lazydata.LazyDataUtilizatori;
import ro.stad.online.gesint.model.filters.FiltruUtilizator;
import ro.stad.online.gesint.persistence.entities.DatePersonale;
import ro.stad.online.gesint.persistence.entities.Echipa;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.CanalAlertaEnum;
import ro.stad.online.gesint.persistence.entities.enums.EducatieEnum;
import ro.stad.online.gesint.persistence.entities.enums.RolEnum;
import ro.stad.online.gesint.persistence.entities.enums.SexEnum;
import ro.stad.online.gesint.persistence.entities.enums.StatutCivilEnum;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;
import ro.stad.online.gesint.services.EchipaService;
import ro.stad.online.gesint.services.LocalitateService;
import ro.stad.online.gesint.services.ParamEchipaService;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Generator;
import ro.stad.online.gesint.util.Utilitati;

/**
 * Clase utilizada pentru a incarca date in pagina de echipa PER.
 *
 * @author STAD
 *
 */
@Component("echipaBean")
@Setter
@Getter
@NoArgsConstructor
@Scope(Constante.SESSION)
public class EchipaBean implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Lista de elemente vizibile.
         */
        private List<Boolean> list;

        /**
         * Variala utilizata pentruinjectarea serviciului de echipa.
         *
         */
        @Autowired
        private transient EchipaService echipaService;

        /**
         * Variala utilizata pentruinjectarea serviciului de echipa.
         *
         */
        @Autowired
        private transient ParamEchipaService pteamService;

        /**
         * Lista utilizatorilor selectați.
         */
        private List<Utilizator> utilizatoriSelectionatiFinali;

        /**
         * Numele de utilizator care va fi folosit pentru al inregistra in echipa.
         */
        private String numeUtilizator = Constante.SPATIU;

        /**
         * Lazy model pentru utilizatori.
         */
        private transient LazyDataUtilizatori modelUser;

        /**
         * Service de utilizatori.
         */
        @Autowired
        private transient UtilizatorService utilizatorService;

        /**
         * Membru nou in echipa de conducere.
         */
        private transient Echipa echipa;

        /**
         * Functii in echipa de conducere.
         */
        private transient ParamEchipa functia;

        /**
         * Clasa de căutare a utilizatorului.
         */
        private FiltruUtilizator filtruUtilizator;

        /**
         * Lista utilizatorilor selectați.
         */
        private List<Utilizator> utilizatoriSelectionati;

        /**
         * Lista judetelor.
         */
        private List<Provincia> listaProvincias;

        /**
         * Judetul selectațat.
         */
        private Provincia provinciaSelect;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Variabila utilizata pentru a injecta serviciul provinciei.
         *
         */
        @Autowired
        private LocalitateService localitateService;

        /**
         * Lista numelor din echipa de conducere.
         */
        private List<Echipa> listaTeams;

        /**
         * Lista pozitiilor membrilor din echipa de conducere.
         */
        private List<Echipa> listaPozitie;

        /**
         * Lista numelor din echipa de conducere.
         */
        private List<ParamEchipa> listaFunctii;

        /**
         * Indică dacă doriți să căutați după datele utilizatorului (opțiunea 1).
         */
        private Integer opcion = NumarMagic.NUMBERONE;

        /**
         * Variabila utilizata pentru un utilizator.
         *
         */
        private Utilizator user;

        /**
         * Fotografia utilizator.
         */
        private byte[] photoSelected;

        /**
         * Lista de localitati.
         */
        private List<Localitate> localitati;

        /**
         * Lista de localitati.
         */
        private List<Localitate> localitatiSelectate;

        /**
         * Lista de judete.
         */
        private List<Provincia> judete;

        /**
         * Variabilă folosită pentru a stoca TipLocalitateEnum.
         *
         */
        private TipLocalitateEnum tipLocalitateSelectat;

        /**
         * Numele documentului
         */
        private String numeDoc;

        /**
         * Mesaj de eroare afișat utilizatorului.
         */
        private transient String mesajEroare;

        /**
         * Deschide dialogul de căutare pentru utilizatori.
         */
        public void deschideDialogCautareUtilizatori() {
                functia = new ParamEchipa();
                listaProvincias = provinciaService.fiindAll();
                listaTeams = echipaService.fiindByTeam();
                listaFunctii = pteamService.fiindAll();
                modelUser = new LazyDataUtilizatori(utilizatorService);
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute(Constante.DIALOGBUSQUEDASHOW);
        }

        /**
         * Deschide dialogul pentru pozitionarea membrilor.
         */
        public void abrirDialogoModificaMembru(final Echipa tea) {
                this.echipa = new Echipa();
                this.echipa = tea;
                this.listaFunctii = new ArrayList<>();
                listaFunctii = pteamService.fiindAll();
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgModifica').show();");
        }

        /**
         * Deschide dialogul pentru pozitionarea membrilor.
         */
        public void deschideDialogOrdenaMembru() {
                listaTeams = echipaService.fiindByTeam();
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgOrdena').show();");
        }

        public void alta() {
                String sex = null;
                Date fecha = null;
                int numero = 0;
                for (int i = 0; i < 1000; i++) {
                        numero = i;
                        final Utilizator user = new Utilizator();
                        user.setDateCreate(Generator.obtenerFechaRegistru());
                        user.setName(Generator.apellidoFinal3().toUpperCase());

                        user.setPassword("$2a$10$tDGyXBpEASeXlAUCdKsZ9u3MBBvT48xjA.v0lrDuRWlSZ6yfNsLve");
                        fecha = Generator.obtenerFechaNastere();
                        user.setBirthDate(fecha);
                        user.setSex(SexEnum.randomLetter());
                        final Provincia pro = new Provincia();
                        pro.setIndicator(Generator.provinciasFinal());
                        user.setProvince(pro);
                        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        final String anString = sdf.format(fecha);
                        if (user.getSex().getName().equals("MAN")) {
                                user.setLastName(Generator.nombreFinalHombre());
                                if (Integer.valueOf(anString) >= 2000) {
                                        sex = "5";
                                }
                                else {
                                        sex = "1";
                                }

                        }
                        else {
                                user.setLastName(Generator.nombreFinal());
                                if (Integer.valueOf(anString) >= 2000) {
                                        sex = "6";
                                }
                                else {
                                        sex = "2";
                                }
                        }
                        user.setEmail(mail(user.getName(), user.getLastName(), numero));
                        user.setIdCard(Generator.generaCnp(sex, fecha, pro));
                        user.setAddress(Generator.nombresCalleFinal().concat("  Nr: ")
                                        .concat(Generator.getNumeroCalle()));

                        user.setCivilStatus(StatutCivilEnum.randomLetter());
                        if (user.getCivilStatus().equals(StatutCivilEnum.WIDOWED)) {
                                user.setCivilStatus(StatutCivilEnum.MARRIED);
                        }
                        user.setEducation(EducatieEnum.randomLetter());

                        List<Localitate> loc = new ArrayList<>();
                        Long indice = Long.valueOf(Generator.getNumero());
                        if (pro.getIndicator().equals(Constante.IF)) {
                                indice = 2L;
                        }
                        loc = localitateService.findByProvinceAndNivel(pro, indice);
                        Localitate locality = new Localitate();
                        if (pro.getIndicator().equals(Constante.B)) {
                                Long id = null;
                                if (user.getIdCard().substring(6, 8).equals("41")) {
                                        id = 72L;
                                }
                                else if (user.getIdCard().substring(6, 8).equals("42")) {
                                        id = 73L;
                                }
                                else if (user.getIdCard().substring(6, 8).equals("43")) {
                                        id = 74L;
                                }
                                else if (user.getIdCard().substring(6, 8).equals("44")) {
                                        id = 75L;
                                }
                                else if (user.getIdCard().substring(6, 8).equals("45")) {
                                        id = 76L;
                                }
                                else {
                                        id = 77L;
                                }
                                locality = localitateService.findById(Long.valueOf(id));
                        }
                        else {

                                final Random rand = new Random();
                                locality = loc.get(rand.nextInt(loc.size()));
                        }
                        user.setLocality(locality);
                        user.setNumberCard(Generator.getDni());
                        user.setPersonalEmail(user.getEmail());
                        user.setPhone(Generator.getTelefon());
                        user.setAlertChannel(CanalAlertaEnum.EMAIL);
                        user.setValidated(true);
                        user.setWorkplace(Generator.meserii());
                        user.setUsername(user.getPersonalEmail());
                        user.setRole(RolEnum.ROLE_MEMBRU);
                        user.setUserCreate("system");
                        utilizatorService.save(user);
                }

        }

        /**
         * Cautată un utilizator cu cnp-ul și returneaza adevărat sau fals.
         * @return boolean
         */
        private boolean cautareUtilizatorPorNif() {
                Boolean resultat = true;
                final Utilizator use = this.utilizatorService.findByIdCard(this.user.getIdCard());
                if (use != null && !use.getIdCard().equals(this.user.getIdCard())) {
                        resultat = false;
                }
                return resultat;
        }

        /**
         * Căută utilizatori pe baza unui filtru.
         */
        public void cautareUtilizatori() {
                modelUser.setFiltruUtilizator(filtruUtilizator);
                modelUser.load(0, NumarMagic.NUMBERFIFTEEN, Constante.DATECREATE, SortOrder.DESCENDING, null);
        }

        /**
         * Căută utilizatori pe baza unui filtru.
         * @return
         */
        public List<Utilizator> cautareUtilizatoriL() {
                modelUser.setFiltruUtilizator(filtruUtilizator);
                return modelUser.load(0, NumarMagic.NUMBERFIFTEEN, Constante.DATECREATE, SortOrder.DESCENDING, null);
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
                this.numeDoc = Constante.SPATIU;
                final UploadedFile uFile = event.getFile();
                user = utilizatorService.incarcareImaginaFaraStocare(IOUtils.toByteArray(uFile.getInputstream()), user);
                numeDoc = uFile.getFileName();
        }

        /**
         * Eliminarea unui membru al echipei.
         * @param echipa membru candidat pentru eliminare
         */
        public void eliminarMembru(final Utilizator team) {
                try {
                        Echipa te = new Echipa();
                        te = echipaService.findByUser(team);
                        echipaService.delete(te);
                        listaTeams.remove(te);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                        Constante.ELIMINAREMESAJ, Constante.OKELIMINAREMESAJ);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la eliminarea membrului echipei de conducere. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }

        }

        /**
         * Metoda de stabilire a utilizatorilor din lista generală.
         */
        public void stabilireUtilizatoriFinali() {
                utilizatoriSelectionatiFinali.add(user);
                modelUser.setDsource(utilizatoriSelectionatiFinali);
                this.utilizatoriSelectionatiFinali = utilizatoriSelectionati;
        }

        /**
         * Transmite datele utilizatorului pe care dorim să le modificăm în formular, astfel încât acestea să schimbe
         * valorile pe care le doresc.
         *
         * @param usuario Utilizator recuperat din formularul de căutare al utilizatorului
         * @return URL-ul paginii de modificare a utilizatorului
         */
        public String getFormModificareUser(final Echipa tm) {
                this.echipa = tm;
                this.user = tm.getUser();
                this.photoSelected = null;
                this.provinciaSelect = new Provincia();
                provinciaSelect = user.getProvince();
                this.localitati = new ArrayList<>();
                localitatiSelectate = localitateService.findByProvince(provinciaSelect);
                this.judete = provinciaService.fiindAll();
                return "/teams/modifyTeam?faces-redirect=true";
        }

        /**
         * Inițializarea datelor.
         */
        @PostConstruct
        public void init() {
                this.filtruUtilizator = new FiltruUtilizator();
                this.user = new Utilizator();
                this.listaTeams = new ArrayList<>();
                this.listaTeams = echipaService.fiindByTeam();
                this.functia = new ParamEchipa();
                this.list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                        this.list.add(Boolean.TRUE);
                }

                cautareFiltre();
                Utilitati.cautareSesiune("echipaBean");
        }

        /**
         * Curăță căutarea utilizatorilor
         */
        public void cautareFiltre() {
                filtruUtilizator = new FiltruUtilizator();
                user = new Utilizator();
                utilizatoriSelectionati = new ArrayList<>();
                modelUser = new LazyDataUtilizatori(utilizatorService);
        }

        /**
         * Curăță câmpurile utilizatorilor selectați și lista de utilizatori.
         */
        public void curatareCampuriNouaEchipa() {
                utilizatoriSelectionatiFinali = new ArrayList<>();
                modelUser = new LazyDataUtilizatori(utilizatorService);
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute(Constante.DIALOGMESGHIDE);
        }

        /**
         * Guardar cambios del usuario.
         * @param usu User
         */
        public void modificareUtilizator(final Utilizator usu) {
                try {
                        this.user = usu;

                        if (valideaza()) {
                                new DatePersonale();
                                user.setLocality(localitatiSelectate.get(0));
                                utilizatorService.save(user);
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                Constante.SCHIMBDATE, Constante.OKMODIFICAREMESAJ);
                        }
                        else {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.SCHIMBDATE, Constante.DESCEROAREMESAJ);
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.SCHIMBDATE,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Înregistrează utilizatorul indicat.
         * @param tea Echipa
         */
        public void modificaTeam(final Echipa tea) {
                this.echipa = new Echipa();
                try {
                        this.echipa = tea;
                        ;
                        echipaService.save(tea);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.SCHIMBDATE,
                                        Constante.REGMODOK);
                        final RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dlgModifica').hide();");

                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }

        }

        /**
         * Acces pentru a inregistra un nou membru.
         * @return String
         */
        public String nuevoMembru() {
                echipa = new Echipa();
                functia = new ParamEchipa();
                utilizatoriSelectionati = new ArrayList<>();
                utilizatoriSelectionatiFinali = new ArrayList<>();
                modelUser = new LazyDataUtilizatori(utilizatorService);
                return "/teams/newTeam?faces-redirect=true";
        }

        /**
         * Metoda de a adăuga noi utilizatori la lista de utilizatori selectați.
         */
        public void schimbarePaginaUtilizatori() {
                if (utilizatoriSelectionati != null && !utilizatoriSelectionati.isEmpty()) {
                        utilizatoriSelectionatiFinali.addAll(utilizatoriSelectionati);
                        utilizatoriSelectionati = new ArrayList<>(utilizatoriSelectionatiFinali);
                }
        }

        public void onReorder() {
                try {
                        reordonareMembru();

                        final FacesContext facesContext = FacesContext.getCurrentInstance();
                        facesContext.addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Modificarea descrierii unui membru al equipei.
         * @param event eveniment care capturează echipa de editat
         */
        public void onRowEdit(final RowEditEvent event) {

                try {
                        final Echipa echipa = (Echipa) event.getObject();
                        echipaService.save(echipa);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.REGMODOK,
                                        echipa.getTeam().getDescription());
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Metodă care asociază o inspecție când selectați caseta de selectare.
         *
         * @param event eveniment lansat care conține utilizatorul
         */

        public void onRowSelectedUser(final SelectEvent event) {
                this.user = (Utilizator) event.getObject();
                utilizatoriSelectionatiFinali.add(user);
                modelUser.setDsource(utilizatoriSelectionatiFinali);
        }

        /**
         * Metoda care se execută la selectare.
         * @param event SelectEvent
         */
        public void onSelect(final SelectEvent event) {
                this.echipa = (Echipa) event.getObject();
        }

        /**
         *
         * Controlează coloanele vizibile în lista rezultatelor motorului de căutare.
         *
         * @param eve ToggleEvent
         *
         */

        public void onToggle(final ToggleEvent eve) {
                this.list.set((Integer) eve.getData(), eve.getVisibility() == Visibility.VISIBLE);
        }

        /**
         * Metodă care captează evenimentul "Selectați toate" sau "Deselectați toate" în vizualizarea Echipa.
         *
         * @param toogleEvent ToggleSelectEvent
         */
        public void onToggleSelectUsers(final ToggleSelectEvent toogleEvent) {
                if (toogleEvent.isSelected()) {
                        utilizatoriSelectionatiFinali = cautareUtilizatoriL();
                        modelUser.setDsource(utilizatoriSelectionatiFinali);
                        utilizatoriSelectionati = new ArrayList<>(utilizatoriSelectionatiFinali);
                }
        }

        /**
         * Elimina un utilizator din lista utilizatorilor selectați pentru a fi in echipa de conducere.
         * @param usuario Utilizator
         */
        public void eliminareUtilizator(final Utilizator user) {
                utilizatoriSelectionatiFinali.remove(user);
        }

        /**
         * Funcție care reorientează pozitia
         * @throws DataAccessException excepție de acces la date
         */
        private void reordonareMembru() {
                try {
                        Echipa echipa;
                        for (int i = 0; i < this.listaTeams.size(); i++) {
                                echipa = this.listaTeams.get(i);

                                echipa.setRank(i + 1L);
                                this.echipaService.save(echipa);
                                final RequestContext context = RequestContext.getCurrentInstance();
                                context.execute("PF('dlgOrdena').hide();");
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Înregistrează utilizatorul indicat.
         */
        public String save() {
                String volver = null;

                try {
                        if (utilizatoriSelectionatiFinali.isEmpty()) {
                                final Utilizator usuario = utilizatorService.fiindOne(numeUtilizator);
                                if (usuario != null) {
                                        utilizatoriSelectionatiFinali.add(usuario);
                                }
                        }
                        for (final Utilizator user : utilizatoriSelectionatiFinali) {
                                final boolean existeUsuario = echipaService.existsByUser(user);
                                if (existeUsuario) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        " Nu se poate înregistra utilizatorul  '"
                                                                        + user.getName().concat(Constante.PUNCT)
                                                                                        .concat(user.getLastName())
                                                                        + "'  deoarece acesta există în echipa de conducere ",
                                                        "");

                                }
                                else {
                                        final Echipa tea = new Echipa();
                                        tea.setUser(user);
                                        final ParamEchipa pteam = new ParamEchipa();
                                        pteam.setId(echipa.getId());
                                        tea.setTeam(pteam);
                                        listaPozitie = echipaService.findAllByOrderByRankDesc();
                                        final Long rank = listaPozitie.get(0).getRank() + 1;
                                        tea.setRank(rank);
                                        echipaService.save(tea);
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                        Constante.INREGISTRAREMESAJ, Constante.OKINREGISTRAREMESAJ);
                                        curatareCampuriNouaEchipa();
                                        listaTeams = echipaService.fiindByTeam();
                                        final RequestContext context = RequestContext.getCurrentInstance();
                                        context.execute("PF('dlgBusqueda').hide();");
                                        volver = "/teams/teams?faces-redirect=true";
                                }

                        }

                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                        volver = "/teams/newTeam?faces-redirect=true";
                }
                return volver;

        }

        /**
         * Metoda cu validările efectuate la salvarea datelor unui utilizator 1. Nume de utilizator nu se repetă 2. CNP
         * nu se repetă 3. CNP valabil
         *
         * @return boolean
         */
        private boolean valideaza() {
                boolean validat = true;

                if (!valideazaUsername()) {
                        this.mesajEroare = "Membrul există deja în sistem";
                        validat = true;
                }
                if (!valideazaCnpUnic()) {
                        this.mesajEroare = "CNP-ul există deja în sistem";
                        validat = true;
                }
                return validat;
        }

        /**
         * Metodă de validare a unicității CNP.
         * @return boolean
         */
        private boolean valideazaCnpUnic() {
                boolean resultat = true;
                if (!StringUtils.isEmpty(this.user.getIdCard()) && this.user.getIdCard() != null) {
                        try {
                                resultat = cautareUtilizatorPorNif();
                        }
                        catch (final DataAccessException e) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ,
                                                "A apărut o eroare la validarea cnp-ului membrului. "
                                                                .concat(Constante.DESCEROAREMESAJ));
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
                final Utilizator use = this.utilizatorService.fiindOne(this.user.getUsername());
                if (use != null && !use.getUsername().equals(this.user.getUsername())) {
                        resultat = false;
                }
                return resultat;
        }

        /**
         * Obtener DNI.
         * @param prenume
         * @param nume
         * @return dni + letra
         */
        public static String mail(final String nume, final String prenume, final int numar) {
                final String numeCurat = Normalizer.normalize(nume.toLowerCase(), Normalizer.Form.NFD);
                final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
                final String nnume = pattern.matcher(numeCurat).replaceAll(Constante.SPATIU);
                final String prenumaCurat = Normalizer.normalize(prenume.toLowerCase(), Normalizer.Form.NFD);
                final String pprenume = pattern.matcher(prenumaCurat).replaceAll(Constante.SPATIU);
                if (numar % NumarMagic.NUMBERTWO == 0) {
                        return nnume.concat(Constante.PUNCT).concat(pprenume.concat(Generator.nombresMail()));
                }
                else {
                        if (numar % NumarMagic.NUMBERSEVENTEEN == 0) {
                                return pprenume.substring(0, 1).concat(nnume.concat(Generator.nombresMail()));
                        }
                        return pprenume.concat(nnume.concat(Generator.nombresMail()));
                }
        }
}
