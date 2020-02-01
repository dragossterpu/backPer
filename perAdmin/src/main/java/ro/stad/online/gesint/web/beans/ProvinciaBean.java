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
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Tara;
import ro.stad.online.gesint.services.ProvinciaService;
import ro.stad.online.gesint.util.FacesUtilities;

/**
 * Bean de gestiune ale judetelor.
 *
 * @author STAD
 */
@Setter
@Getter
@ManagedBean
@Controller("provinciaBean")
@Scope(Constante.SESSION)
public class ProvinciaBean implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Variabila utilizata pentru injectarea serviciului de judete
         */
        @Autowired
        private transient ProvinciaService provinciaService;

        /**
         * Lista de judete.
         */
        private List<Provincia> listaJudete;

        /**
         * Judetul
         */
        private Provincia judetul;

        /**
         * Tara.
         */
        private Tara tara;

        /**
         * Fotoografia judet.
         */
        private byte[] photoSelected;

        /**
         * Numele documentului
         */
        private String numeDoc;

        /**
         * @PostConstruct Metoda inițială a ProvinciaBean.
         */
        @PostConstruct
        public void init() {
                judetul = new Provincia();
                listaJudete = new ArrayList<>();
                listaJudete = provinciaService.fiindAll();
        }

        /**
         * Metodă care recuperează valorile introduse în formular și înregistrează un județ în baza de date.
         * @param jud Provincia
         */
        public void modificaJudet(final Provincia jud) {
                try {
                        this.judetul = jud;
                        this.provinciaService.save(this.judetul);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.SCHIMBDATE,
                                        Constante.REGMODOK);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Metodă care deschide pagina de modificare si care recuperează datele judetului care dorim să fie modificat
         * @param prov Judetul recuperat
         * @return pagina modificareJudet
         */
        public String getFormModificarProvince(final Provincia prov) {
                this.judetul = prov;
                this.photoSelected = null;
                return "/judete/modificareJudet?faces-redirect=true";
        }

        /**
         * Metodă care deschide dialogul de noua imagine.
         */
        public void arataDialogulNouaImagine() {
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg').show();");
        }

        /**
         * Metodă care încărcă un document care este primit printr-un eveniment FileUploadEvent. Această încărcare se
         * face pe obiectul document și nu este salvată în baza de date. Se verifică dacă tipul de document corespunde
         * realității.
         *
         * @param event FileUploadEvent care este lansat în încărcarea documentului.
         * @throws IOException
         */
        public void incarcareImagine(final FileUploadEvent event) throws IOException {
                this.numeDoc = Constante.SPATIU;
                final UploadedFile uFile = event.getFile();
                judetul = provinciaService.incarcareImaginaFaraStocare(IOUtils.toByteArray(uFile.getInputstream()),
                                judetul);
                numeDoc = uFile.getFileName();
        }
}
