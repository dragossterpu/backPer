package ro.stad.online.gesint.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.Proprietate;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.services.ProprietateService;
import ro.stad.online.gesint.services.ProvinciaService;

/**
 * Clasa utilizată pentru încărcarea datelor în contextul aplicației atunci când este pornit serverul..
 *
 * @author STAD
 *
 */
@Component("applicationBean")
@Setter
@Getter
@NoArgsConstructor
public class ApplicationBean implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Constante para cargar la lista de datos para envio mail
         */
        private static String MAIL = "MAIL";

        /**
         * Constante para cargar la lista de extensiones
         */
        private static String EXT = "extensie";

        /**
         * Entity Manager para gestión de la persistencia.
         */
        @PersistenceContext
        private transient EntityManager em;

        /**
         * Mapa que contendrá los parámetros de la aplicación.
         */
        private Map<String, String> mapParametriMail;

        /**
         * Mapa que contendrá los parámetros de la aplicación.
         */
        private Map<String, String> mapExtensi;

        /**
         * Listado de accesos remotos a Elypse.
         */
        private List<Proprietate> listaConfMail;

        /**
         * Listado de extensiones de un document.
         */
        private List<Proprietate> listaExtensie;

        @Autowired
        private ProvinciaService provinciaService;

        /**
         * Lista de judete.
         */
        private List<Provincia> judete;

        /**
         * Servicio de propriedades.
         */

        @Autowired
        private transient ProprietateService proprietateService;

        /**
         * Obtinem extensia documentului
         */
        private void extensie() {
                final Map<String, String> mapaParametri = new HashMap<>();
                for (final Proprietate param : listaExtensie) {
                        mapaParametri.put(param.getName(), param.getValue());
                }
                setMapExtensi(mapaParametri);
        }

        /**
         * Inicialización de datos.
         */
        @PostConstruct
        public void init() {
                this.listaConfMail = new ArrayList<>();
                listaConfMail = proprietateService.findByFilename(MAIL);
                listaExtensie = proprietateService.findByFilename(EXT);
                this.judete = provinciaService.fiindAll();
                paramMail();
                extensie();
        }

        /**
         * Obbtinem parametrii e-mailului
         */
        private void paramMail() {
                final Map<String, String> mapaParametros = new HashMap<>();
                for (final Proprietate param : listaConfMail) {
                        mapaParametros.put(param.getName(), param.getValue());
                }
                setMapParametriMail(mapaParametros);
        }

        /**
         * Metoda de deconectare a backoffice.
         * @throws IOException exceptie
         */
        public void logout() throws IOException {
                final HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
                                .getExternalContext().getRequest();
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().redirect(req.getContextPath() + "/logout");
        }
}
