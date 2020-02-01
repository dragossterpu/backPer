package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.TipSuportEnum;

/**
 *
 * Entitate parametrica para el almacenamiento de intrebare.
 *
 * @author STAD
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Builder
@Getter
@Setter
@Entity
@Table(name = "INTREBARE")
public class Intrebare implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 8133415273601486902L;

        /**
         * ID TEAM.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INTREBARE")
        @SequenceGenerator(name = "SEQ_INTREBARE", sequenceName = "SEQ_INTREBARE", allocationSize = 1)
        @Column(name = "ID", length = 5)
        private Long id;

        /**
         * Descrierea intrebarii.
         */
        @Column(name = "DESCRIERE", length = 200)
        private String descriere;

        /**
         * Daca a fost sau nu utila intrebarea
         */
        @Column(name = "UTIL")
        private Boolean util;

        /**
         * Tipul suportului.
         */
        @Column(name = "TIP_SUPORT", length = 50, nullable = false)
        @Enumerated(EnumType.STRING)
        private TipSuportEnum tipSuport;
}
