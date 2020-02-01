package ro.stad.online.gesint.web.beans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.services.UtilizatorService;
import ro.stad.online.gesint.util.FacesUtilities;

/**
 * Controlator de operatii relationate cu profilul utilizatorului. Schimbare parola.
 *
 * @author STAD
 */
@Getter
@Setter
@Controller("miPerfilBean")
@Scope(Constante.SESSION)
public class ProfilBean implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Constanta patron parola.
         */
        private static final String PASSPATTERN = "^(?=.*?[A-Z])(?=.*?[0-9]).{2,}$";

        /**
         * Parola actuala.
         */
        private String parolaCurenta;

        /**
         * Parola noua.
         */
        private String parolaNoua;

        /**
         * Confirmarea parolei.
         */
        private String parolaConfirmata;

        /**
         * Utilizator.
         */
        private Utilizator user;

        /**
         * Serviciul de user.
         */
        @Autowired
        private UtilizatorService utilizatorService;

        /**
         * Metoda utilizata pentru schimbarea parolei.
         */
        public void schimbareParola() {
                if (this.getParolaNoua().equals(this.getParolaConfirmata()) == Boolean.FALSE) {
                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                        "Parolele introduse nu se potrivesc", null);
                }
                else {
                        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                        if (passwordEncoder.matches(this.getParolaCurenta(), user.getPassword())) {
                                if (validaPass(this.parolaNoua)) {
                                        user.setPassword(passwordEncoder.encode(this.getParolaNoua()));
                                        utilizatorService.save(user);
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_INFO,
                                                        "Parola a fost modificată cu succes", "dialogMessage");
                                }
                                else {
                                        FacesUtilities.setMensajeConfirmacionDialog(FacesMessage.SEVERITY_ERROR,
                                                        "Noua parolă trebuie să aibă cel puțin un număr și o literă mare. Încercați din nou",
                                                        null);
                                }
                        }
                        else {
                                FacesUtilities.setMensajeInformativo(FacesMessage.SEVERITY_ERROR,
                                                "Parola curentă introdusă nu este validă. Încercați din nou", "", null);
                        }
                }

        }

        /**
         * Eliminare utilizator.
         */
        public String eliminareUser(final Utilizator usu) {
                return "/login?faces-redirect=true";
        }

        /**
         * Inițializarea ProfilBean.
         */
        @PostConstruct
        public void init() {
                user = utilizatorService.fiindOne(SecurityContextHolder.getContext().getAuthentication().getName());
        }

        /**
         * Deschide dialogul pentru schimbarea parolei.
         */
        public void deschideDialogParola() {
                final RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogParola').show();");
        }

        /**
         * Metoda care arata daca parola indeplineste sau nu conditiile de validare.
         * @param password String pentru validare
         * @return boolean Valideaza?
         */
        private boolean validaPass(final String password) {
                final Pattern pattern = Pattern.compile(PASSPATTERN);
                final Matcher matcher = pattern.matcher(password);

                return matcher.matches();
        }
}
