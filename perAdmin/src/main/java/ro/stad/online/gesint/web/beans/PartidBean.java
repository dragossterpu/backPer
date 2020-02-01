package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.persistence.entities.Partid;
import ro.stad.online.gesint.services.PartidService;
import ro.stad.online.gesint.services.impl.RegistruActivitateServiceImpl;
import ro.stad.online.gesint.util.FacesUtilities;

/**
 * Controlorul operațiunilor legate de gestionarea partidelor.
 *
 * @author STAD
 */
@Setter
@Getter
@Controller("partidBean")
@Scope(Constante.SESSION)
public class PartidBean implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = NumarMagic.NUMBERONELONG;

        /**
         * Partid.
         */
        private transient Partid partid;

        /**
         * Variala utilizata pentru injectarea serviciului de partid.
         */
        @Autowired
        private transient PartidService partidService;

        /**
         * Listă de Partide.
         */
        private List<Partid> listaPartide;

        /**
         * Variala utilizata pentru injectarea serviciului înregistrare a activității.
         */
        @Autowired
        private transient RegistruActivitateServiceImpl registruActivitateService;

        /**
         * Creare partid.
         * @param numePartid numele partidului
         * @param sigla sigla partidului
         */

        public void altaPartid(final String numePartid, final String sigla) {
                try {
                        partid = new Partid();
                        partid.setName(numePartid);
                        partid.setSigla(sigla);
                        partidService.save(partid);
                        listaPartide.add(partid);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.INREGISTRARE,
                                        "Noul partid a fost înregistrat cu succes");
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la înregistrarea registrului. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }
        }

        /**
         * Eliminarea totala a partidului.
         * @param partid care se elimina
         */
        public void eliminaPartid(final Partid partid) {
                try {
                        partidService.delete(partid);
                        listaPartide.remove(partid);
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_INFO, Constante.ELIMINAREMESAJ,
                                        partid.getName(), Constante.OKELIMINAREMESAJ);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Metodă folosită pentru a inițializa PartidBean.
         */
        @PostConstruct
        public void init() {
                listaPartide = partidService.fiindAll();
        }

        /**
         * Modificare de o functie
         * @param event event care se recupereaza din pagina
         */
        public void onRowEdit(final RowEditEvent event) {
                final Partid partid = (Partid) event.getObject();
                try {
                        partidService.save(partid);
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_INFO, Constante.MODIFICAREMESAJ,
                                        partid.getName(), null);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }

        }

}
