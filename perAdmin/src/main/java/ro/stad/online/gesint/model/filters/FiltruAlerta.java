package ro.stad.online.gesint.model.filters;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.enums.CategorieEnum;

/**
 * Controler al operațiunilor legate de căutarea alertelor. Reseteaza valorile de căutare.
 *
 * @author STAD
 *
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Controller("cautareAlertaBean")
@Scope(Constante.SESSION)
public class FiltruAlerta implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * ID de alerta.
         */
        private String id;

        /**
         * TitluL alertei.
         */
        private String titlu;

        /**
         * Destinatarul alertei.
         */
        private String destinatar;

        /**
         * Data de la trimiterea alertei.
         */
        private Date dateFromSend;

        /**
         * Data până la trimiterea alertei.
         */
        private Date dateUntilSend;

        /**
         * Data de la crearea alertei.
         */
        private Date dateFromCreated;

        /**
         * Data până la crearea alertei.
         */
        private Date dateUntilCreated;

        /**
         * Filtru tip alerta.
         */
        private CategorieEnum tipAlerta;

}
