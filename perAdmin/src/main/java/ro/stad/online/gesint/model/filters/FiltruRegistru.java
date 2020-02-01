package ro.stad.online.gesint.model.filters;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.RegistruEnum;
import ro.stad.online.gesint.persistence.entities.enums.SectiuniEnum;

/**
 * 
 * Bean pentru a stoca criteriile de căutare în jurnalul de activitate al aplicației..
 * 
 * @author STAD
 * 
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FiltruRegistru implements Serializable {

        /**
        	 * 
        	 */
        private static final long serialVersionUID = 1L;

        /**
         * Fecha desde la que se desea hacer la búsqueda.
         */
        private Date fechaDesde;

        /**
         * Fecha hasta la que se desea hacer la búsqueda.
         */
        private Date fechaHasta;

        /**
         * Sección para la que se desea hacer la búsqueda.
         */
        private SectiuniEnum numeSectiune;

        /**
         * Username del usuario para el que se desea hacer la búsqueda.
         */

        private String usernameRegActividad;

        /**
         * Tipo de registro de la que se desea hacer la búsqueda.
         */
        private RegistruEnum tipRegActivitate;

        /**
         * Id del usuario que introdujo el registro de actividad.
         */
        private String idUtilizator;
}
