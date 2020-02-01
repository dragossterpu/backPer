package ro.stad.online.gesint.model.filters;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.enums.TipAlegeriEnum;

/**
 * Controler al operațiunilor legate de căutarea rezultatelor.. Reseteo de valores de búsqueda.
 *
 * @author STAD
 *
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Controller("rezultatBusquedaBean")
@Scope(Constante.SESSION)
public class FiltruRezultat implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * ID de rezultat.
         */
        private String id;

        /**
         * Variabila care indica daca sunt rezultate totale fara filtru.
         */
        private Boolean suntGenerale;

        /**
         * Id provincia.
         */
        private String idProvincia;

        /**
         * Id de la partid.
         */
        private Long idPartid;

        /**
         * Data alegerilor.
         */
        private Integer anAlegeri;

        /**
         * Filtru tip alegeri.
         */
        private TipAlegeriEnum tipAlegeri;

}
