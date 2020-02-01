package ro.stad.online.gesint.model.dto.statistica;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase DTO que contiene el resultado de una fila perteneciente a estadisticas de denuncias por provincia.
 *
 * @author EZENTIS
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StatisticaJudetDTO implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = -5468840743886693413L;

        /**
         * code_province.
         */
        private String codJudet;

        /**
         * nume.
         */
        private String nume;

        /**
         * totalLocuitori.
         */
        private Integer totalLocuitori;

        /**
         * totalLocuitori.
         */
        private Integer locuitoriVot;

        /**
         * numarMembrii.
         */
        private Integer numarMembrii;

        /**
         * procentaj.
         */
        private Float procentaj;

        /**
         * nume.
         */
        private String valoare;

        /**
         * eticheta.
         */
        private String eticheta;

        /**
         * membriiMinim.
         */
        private Integer membriiMinim;

        /**
         * voturiMinim.
         */
        private Integer voturiMinim;
}
