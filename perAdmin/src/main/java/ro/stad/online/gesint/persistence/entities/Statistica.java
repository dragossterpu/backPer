package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ro.stad.online.gesint.persistence.entities.enums.RezultatEnum;
import ro.stad.online.gesint.persistence.entities.enums.TipStatisticaEnum;

/**
 * Entitate pentru stocarea înregistrărilor de statistica în baza de date.
 * @author STAD
 */
@TypeDefs({ @TypeDef(name = "double precision", typeClass = java.lang.Float.class) })
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "STATISTICA")
public class Statistica implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Identificador del registro, se genera por medio de una secuencia.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STATISTICA")
        @SequenceGenerator(name = "SEQ_STATISTICA", sequenceName = "SEQ_STATISTICA", allocationSize = 1)
        @Column(name = "id", length = 10, columnDefinition = "NUMERIC")
        private Long id;

        /**
         * Numele tipului de statistica.
         */
        @Column(name = "tip_statistica", length = 20)
        @Enumerated(EnumType.STRING)
        private TipStatisticaEnum tipStatistica;

        /**
         * Descrierea registrului.
         */
        @Column(name = "DESCRIPCION")
        private String descripcion;

        /**
         * Fecha en la que se da de alta el registro.
         */
        @CreatedDate
        @Column(name = "FECHA_ALTA", nullable = false)
        private Date fechaAlta;

        /**
         * Marca registrului ex: Ianuarie 2019.
         */
        @Column(name = "marca")
        private String marca;

        /**
         * Numele registrului ex: Bacau.
         */
        @Column(name = "nume")
        private String nume;

        /**
         * Numele registrului ex: BC.
         */
        @Column(name = "cod_judet")
        private String code_province;

        /**
         * populatie totala.
         */
        @Column(name = "populatie_totala")
        private Long populatieTotala;

        /**
         * populatie totala.
         */
        @Column(name = "membrii")
        private Long membrii;

        /**
         * populatie cu drept de vot.
         */
        @Column(name = "populatie_vot")
        private Long populatieVot;

        /**
         * populatie cu drept de vot.
         */
        @Column(name = "procentaj")
        private Float procentaj;

        /**
         * Numele registrului ex: BC.
         */
        @Column(name = "valoare")
        @Enumerated(EnumType.STRING)
        private RezultatEnum valoare;

        /**
         * Judet boolean.
         */
        @Column(name = "isjudet")
        private Boolean isjudet;
}
