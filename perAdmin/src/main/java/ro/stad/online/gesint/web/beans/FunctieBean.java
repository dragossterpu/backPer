package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.constante.NumarMagic;
import ro.stad.online.gesint.persistence.entities.ParamEchipa;
import ro.stad.online.gesint.services.EchipaService;
import ro.stad.online.gesint.services.FunctieService;
import ro.stad.online.gesint.services.impl.RegistruActivitateServiceImpl;
import ro.stad.online.gesint.util.FacesUtilities;

/**
 * Controlorul operațiunilor legate de gestionarea functiilor.
 *
 * @author STAD
 */
@Setter
@Getter
@Controller("functieBean")
@Scope(Constante.SESSION)
public class FunctieBean implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = NumarMagic.NUMBERONELONG;

        /**
         * ParamEchipa.
         */
        private transient ParamEchipa functie;

        /**
         * Variala utilizata pentru injectarea serviciului de functii.
         */
        @Autowired
        private transient FunctieService functieService;

        /**
         * Variala utilizata pentru injectarea serviciului de utilizatori.
         */
        @Autowired
        private transient EchipaService echipaService;

        /**
         * Listă de functii.
         */
        private List<ParamEchipa> listaFunctii;

        /**
         * Variala utilizata pentru injectarea serviciului înregistrare a activității.
         */
        @Autowired
        private transient RegistruActivitateServiceImpl registruActivitateService;

        /**
         * Inregistrare noua functie
         *
         * @param numeFunctie numele functiei
         * @param descriereFunctie descrierea functiei
         * @param organizatie
         */

        public void altaFunctie(final String numeFunctie, final String descriereFunctie, final String organizatie) {
                try {
                        functie = new ParamEchipa();
                        SecurityContextHolder.getContext().getAuthentication().getName();
                        functie.setDescription(descriereFunctie);
                        functie.setOrganization(organizatie);
                        functie.setName(numeFunctie);
                        functieService.save(functie);
                        listaFunctii.add(functie);
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO, Constante.INREGISTRARE,
                                        "Noua funcție a fost înregistrată cu succes");
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "A apărut o eroare la înregistrarea registrului. "
                                                        .concat(Constante.DESCEROAREMESAJ));
                }
        }

        /**
         * Eliminarea totala a functiei.
         * @param functia care se elimina
         */
        public void eliminaFunctia(final ParamEchipa functia) {
                try {
                        final int tieneUsuarios = echipaService.existsByTeam(functia.getId());
                        if (tieneUsuarios > 0) {
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                                "Nu se poate elimina funcția '" + functia.getName()
                                                                + "' deoarece sunt persoane care o dețin.",
                                                Constante.SPATIU, null);
                        }
                        else {
                                functieService.delete(functia);
                                listaFunctii.remove(functia);
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_INFO,
                                                Constante.ELIMINAREMESAJ, functia.getDescription(),
                                                Constante.OKELIMINAREMESAJ);
                        }
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }
        }

        /**
         * Metodă folosită pentru a inițializa lista functilor care vor fi afișate pe pagină.
         */
        @PostConstruct
        public void init() {
                listaFunctii = functieService.fiindAll();
        }

        /**
         * Modificare de o functie
         * @param event eventcare se recupereaza de la vista
         */
        public void onRowEdit(final RowEditEvent event) {
                final ParamEchipa functia = (ParamEchipa) event.getObject();
                try {
                        functieService.save(functia);
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_INFO, Constante.MODIFICAREMESAJ,
                                        functia.getDescription(), null);
                }
                catch (final DataAccessException e) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        Constante.DESCEROAREMESAJ);
                }

        }

}
