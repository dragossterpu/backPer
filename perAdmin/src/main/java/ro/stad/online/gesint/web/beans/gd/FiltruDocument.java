package ro.stad.online.gesint.web.beans.gd;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.TipDocument;
import ro.stad.online.gesint.persistence.entities.Utilizator;

/**
 * POJO pentru a stoca parametrii de căutare pentru documente.
 *
 * @author STAD
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class FiltruDocument implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        /**
         * Data de la care doriți să faceți căutarea.
         */
        private Date fechaDesde;

        /**
         * Data până când doriți să faceți căutarea.
         */
        private Date fechaHasta;

        /**
         * Numele prin care doriți să faceți căutarea.
         */
        private String nombre;

        /**
         * Tipul documentului care trebuie căutat.
         */
        private TipDocument tipDocument;

        /**
         * Descrierea prin care doriți să faceți căutarea.
         */
        private String descripcion;

        /**
         * Cuvinte cheie pentru care doriți să faceți căutarea.
         */
        private String materiaIndexada;

        /**
         * Utilizatorul pe care doriți să faceți căutarea.
         */
        private Utilizator usuario;

        /**
         * Discriminarea documentelor a fost șters / nu a fost șters în căutare.
         */
        private boolean eliminat;

        /**
         * Document validat sau nu.
         */
        private boolean validated;

}
