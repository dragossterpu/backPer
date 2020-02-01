package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.exceptions.EMailException;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.util.EMail;
import ro.stad.online.gesint.util.FacesUtilities;
import ro.stad.online.gesint.util.Utilitati;

/**
 * Gestioneaza restaurarea accesului la sistem de către un utilizator care nu-și amintește aliasul sau parola.
 *
 * @author STAD
 */
@Setter
@Getter
@Controller("recoverBean")
@Scope("request")
public class RecuperareBean implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Service de utilizator.
         */
        @Autowired
        private transient UtilizatorService utilizatorService;

        /**
         * Trimiterea de e-mailuri.
         */
        @Autowired
        private transient EMail eMail;

        /**
         * Encryptor pentru parola
         */
        @Autowired
        private transient PasswordEncoder passwordEncoder;

        /**
         * Metodă care caută un utilizator pe baza adresei de e-mail sau a CNP-ului și, dacă este găsit, generați o nouă
         * parolă și o trimite cu numele de utilizator la e-mailul asociat contului.
         * @param email
         * @param cnp cod numeric personal
         * @return pagina recover
         */
        public String claveOlvidada(final String email, final String cnp) {
                String retur = null;
                if (Constante.SPATIU.equals(cnp) && Constante.SPATIU.equals(email)) {
                        FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                        "Trebuie să furnizați e-mailul sau CNP-ul pentru a vă putea identifica", null);
                }
                else {
                        // Recuperăm o listă de utilizatori deoarece pot exista mai mulți cu același e-mail în acest caz
                        // utilizatori temporali
                        final List<Utilizator> listaUser = utilizatorService
                                        .findByCorreoIgnoreCaseOrDocIdentidadIgnoreCase(email, cnp);
                        if (listaUser != null && !listaUser.isEmpty()) {
                                try {
                                        final String password = Utilitati.getPassword();
                                        final String passCod = passwordEncoder.encode(password);
                                        final Map<String, String> paramSablon = new HashMap<>();
                                        paramSablon.put("password", password);
                                        if (listaUser.size() > 1) {
                                                paramSablon.put("login", listaUser.get(0).getEmail());
                                        }
                                        else {
                                                paramSablon.put("login", listaUser.get(0).getUsername());
                                        }
                                        listaUser.forEach((final Utilizator user) -> {
                                                user.setPassword(passCod);
                                        });
                                        utilizatorService.save(listaUser);
                                        eMail.trimitereEmail(listaUser.get(0).getEmail(),
                                                        "Acces restabilit in aplicația GESINT",
                                                        Constante.TEMPLATECORREORESTABLECERPASSWORD, paramSablon);

                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                        "PAROLA",
                                                        "Accesul dvs. la sistem a fost restaurat, noile dvs. credențiale v-au fost trimise prin e-mail");

                                }
                                catch (DataAccessException | EMailException e) {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        Constante.EROAREMESAJ,
                                                        "A apărut o eroare la regenerarea sau la trimiterea parolei");
                                }

                                retur = "/acceso/recuperarPassword";
                        }
                        else {
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR, Constante.EROAREMESAJ,
                                                "Utilizatorul nu există în sistem. Contactați administratorul", null);
                        }

                }
                return retur;
        }

}
