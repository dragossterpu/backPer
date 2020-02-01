package ro.stad.online.gesint.web.beans.gd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.exceptions.GesintException;
import ro.stad.online.gesint.lazydata.LazyDataDocumente;
import ro.stad.online.gesint.persistence.entities.Documentul;
import ro.stad.online.gesint.persistence.entities.TipDocument;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.repositories.TipDocumentRepository;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.services.impl.DocumentServiceImpl;
import ro.stad.online.gesint.util.FacesUtilities;

/**
 * Bean pentru managerul de documente.
 *
 * @author STAD
 *
 */

@Setter
@Getter
@Controller("gestorDocumentalBean")
@Scope(Constante.SESSION)
public class DocumentManagementBean implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Constante para evitar literales repetidos.
         */
        private static final String CARGAFICHEROS = "Carga de ficheros";

        /**
         * Obiectul de tip Document pentru înregistrarea de noi documente.
         */
        private Documentul document;

        /**
         * Obiect care conține parametrii de căutare pentru documente.
         */
        private FiltruDocument filtruDocument;

        /**
         * Obiect care va conține fișierul care urmează să fie descărcat.
         */
        private transient StreamedContent file;

        /**
         * Numele documentului
         */
        private String numeDoc;

        /**
         * Listează unde sunt stocați utilizatorii care vor fi asociați unui document.
         */
        private List<Utilizator> listaUtilizatori = new ArrayList<>();

        /**
         * Mapa care relaționeză documentele și utilizatorii.
         */
        private Map<Long, String> mapaUsuarios;

        /**
         * Mapa care relaționeză documentele și utilizatorii.
         */
        private Map<Long, Boolean> mapaEdicion;

        /**
         * Lista valorilor booleene pentru vizualizarea coloanelor in pagina.
         */
        private List<Boolean> list;

        /**
         * Servicio de documentos.
         */
        @Autowired
        private transient DocumentServiceImpl documentService;

        /**
         * Repositorio de tipos de documento.
         */
        @Autowired
        private transient TipDocumentRepository tipoDocumentoRepository;

        /**
         * Service de usuarios.
         */
        @Autowired
        private transient UtilizatorService utilizatorService;

        /**
         * Lazy Model para la consulta paginada de documentos en base de datos.
         */
        private LazyDataDocumente model;

        /**
         * Elimina un documento definitivamente.
         * @param doc Documentul a eliminar
         */
        public void borrarDocumento(final Documentul doc) {
                try {
                        doc.setUtilizator(null);
                        this.documentService.delete(doc);
                        this.cautareDocument();
                }
                catch (final DataAccessException e) {
                }
        }

        /**
         * Lanza la búsqueda de documentos en la base de datos que correspondan con los parámetros contenidos en el
         * objeto de búsqueda. SE realiza paginación desde el servidor.
         *
         */
        public void cautareDocument() {
                this.model.setBusqueda(this.filtruDocument);
                this.model.load(0, NumarMagic.NUMBERFIFTEEN, "fechaAlta", SortOrder.DESCENDING, null);
                this.numeDoc = Constante.SPATIU;
        }

        /**
         * Carga un documento que se recibe a través de un evento FileUploadEvent. Esta carga se realiza sobre el objeto
         * documento y no se guarda en base de datos. Se hace una comprobación para verificar si el tipo de documento se
         * corresponde a la realidad.
         * @param event Evento que se lanza en la carga del documento y que contiene el mismo
         */
        public void cargaFichero(final FileUploadEvent event) {
                try {
                        final TipDocument tipo = this.tipoDocumentoRepository.findByNume("OTROS");
                        final UploadedFile uFile = event.getFile();
                        this.document = this.documentService.incarcareDocumentFaraSalvare(uFile, tipo, null);
                        this.numeDoc = uFile.getFileName();
                }
                catch (final GesintException ex) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, CARGAFICHEROS,
                                        "A apărut o eroare la încărcarea fișierului");
                }
        }

        /**
         * Graba un nuevo documento en la base de datos.
         *
         * @param numeDocumento Nombre del documento
         * @param tipDocument Tipo al que pertenece el documento
         * @param descripcion Breve descripción del documento
         * @param materiaIndexada Palabras clave por las que se podrá buscar el documento
         */
        public void creaDocumento(final String numeDocument, final TipDocument tipDocument, final String descriere,
                        final String materiaIndexada) {
                if (!this.numeDoc.isEmpty() && !numeDocument.isEmpty() && (tipDocument != null)) {
                        try {
                                this.document.setNume(numeDocument);
                                this.document.setTipDocument(tipDocument);
                                this.document.setDescriere(descriere);
                                final Utilizator user = this.utilizatorService.fiindOne(
                                                SecurityContextHolder.getContext().getAuthentication().getName());
                                this.document.setUtilizator(user);
                                this.document.setMateriaIndexada(materiaIndexada);
                                this.document.setDateDeleted(null);
                                this.documentService.save(this.document);
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                Constante.INREGISTRARE, "Se a înregistrat cu exit");
                                this.reincarcareLista();
                                RequestContext.getCurrentInstance().reset("formAlta:asociado");
                                this.numeDoc = "";
                        }
                        catch (final DataAccessException e) {
                        }
                }
                else {
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR, "Înregistrare de documente",
                                        "Completați câmpurile necesare înainte de a continua.", null);
                }
        }

        /**
         * Inicia la descarga del documento que se recibe como parámetro.
         * @param document Documentul a descargar
         */
        public void descarcareFisier(final Documentul document) {
                final Documentul docAux = this.documentService.findOne(document.getId());
                this.setFile(null);
                if (docAux != null) {
                        try {
                                this.setFile(this.documentService.descarcareDocument(docAux));
                        }
                        catch (final GesintException e) {
                                FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                Constante.EROAREMESAJ, e.getMessage());
                        }
                }
                else {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, CARGAFICHEROS,
                                        "A apărut o eroare la descărcarea fișierului");
                }
        }

        /**
         * Recupera el documento a modificar e inicia el proceso de modificación.
         *
         * @param doc Documentul a modificar
         * @return URL de la vista de edición
         */
        public String editarDocumento(final Documentul doc) {
                final Documentul docAux = this.documentService.findOne(doc.getId());
                String redireccion = null;

                if (docAux != null) {
                        this.document = docAux;
                        this.numeDoc = this.documentService.obtieneNumeFisier(this.document);
                        redireccion = "/gestorDocumental/editarDocumento?faces-redirect=true";
                }
                else {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, CARGAFICHEROS,
                                        "Se ha producido un error al acceder al documento");
                }
                return redireccion;
        }

        /**
         * Realiza la baja lógica del documento que podrá ser recuperado desde la papelera.
         *
         * @param document Documentul al que se dará de baja lógica
         */
        public void eliminareDocument(final Documentul document) {
                try {
                        document.setDateDeleted(new Date());
                        document.setUserDeleted(SecurityContextHolder.getContext().getAuthentication().getName());
                        this.documentService.save(document);
                        this.cautareDocument();
                }
                catch (final DataAccessException e) {
                }
        }

        /**
         * Inicializa el objeto.
         */
        @PostConstruct
        public void init() {
                this.filtruDocument = new FiltruDocument();
                this.list = new ArrayList<>();
                for (int i = 0; i <= 5; i++) {
                        this.list.add(Boolean.TRUE);
                }
                this.model = new LazyDataDocumente(this.documentService);
                this.mapaEdicion = new HashMap<>();
        }

        /**
         * Reseteo del objeto de búsqueda y limpieza de la lista de resultados.
         */
        public void cautareCautare() {
                this.filtruDocument = new FiltruDocument();
                this.model.setRowCount(0);
        }

        /**
         * Graba el documento modificado.
         */
        public void modificaDocument() {
                try {
                        this.documentService.save(this.document);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, "MODIFCARE",
                                        "Documentul a fost modificat");
                        this.reincarcareLista();
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, "MODIFCARE",
                                        "A apărut o eroare la modificarea documentului");
                }
        }

        /**
         * Inicia la creación de un nuevo documento.
         * @return ruta de la vista
         */
        public String nouDocument() {
                this.document = new Documentul();
                this.numeDoc = "";
                return "/gestorDocumental/nouDocument?faces-redirect=true";
        }

        /**
         * Muestra/oculta las columnas de la tabla de resultados de la búsqueda.
         *
         * @param e La columna a mostrar/ocultar
         */
        public void onToggle(final ToggleEvent e) {
                this.list.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
        }

        /**
         * Recarga la lista de resultados no eliminados.
         */
        public void reincarcareLista() {
                this.filtruDocument.setEliminat(false);
                this.cautareDocument();

        }

        /**
         * Recarga la lista de resultados eliminados.
         */
        public void reincarcareListaEliminati() {
                this.filtruDocument.setEliminat(true);
                this.cautareDocument();
        }

        /**
         * Recupera un documento desde la papelera.
         * @param doc Documentul a recuperar
         */
        public void recuperareDocument(final Documentul doc) {
                try {
                        this.documentService.recupereazaDocument(doc);
                        this.cautareDocument();
                }
                catch (final DataAccessException e) {
                }

        }

        /**
         * Resetea el objeto de búsqueda, limpia la lista de resultados y establece el booleano de eliminado a false
         * para indicar que sólo se van a buscar documentos no eliminados.
         * @return ruta siguiente
         */
        public String resetBusqueda() {
                this.filtruDocument = new FiltruDocument();
                this.model.setRowCount(0);
                this.numeDoc = "";
                this.filtruDocument.setEliminat(false);
                return "/gestorDocumental/buscarDocumento?faces-redirect=true";
        }

        /**
         * Resetea el objeto de búsqueda, limpia la lista de resultados y establece el booleano de eliminado a false
         * para indicar que sólo se van a buscar documentos eliminados.
         * @return ruta
         */
        public String resetBusquedaEliminados() {
                this.numeDoc = "";
                this.filtruDocument.setEliminat(true);
                this.cautareDocument();
                return "/administracion/papelera/papelera?faces-redirect=true";

        }

        /**
         * Vacía la papelera de reciclaje.
         */
        public void vaciarPapelera() {
                try {
                        final List<Documentul> documentosEliminados = this.documentService.golesteCosulGunoi();
                        final StringBuffer nombreFicherosEliminados = new StringBuffer().append("\n\n");
                        for (final Documentul docu : documentosEliminados) {
                                nombreFicherosEliminados.append('-').append(docu.getNume()).append("\n");
                        }
                        this.cautareDocument();
                }
                catch (final DataAccessException e) {
                }
        }

}
