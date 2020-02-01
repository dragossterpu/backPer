package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.TipAlegeriEnum;

/**
 *
 * Entitate pentru rezultate alegeri pe localitati ale partidelor.
 *
 * @author STAD
 *
 */
@TypeDefs({ @TypeDef(name = "double precision", typeClass = java.lang.Float.class) })
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Builder
@Getter
@Setter
@Entity
@Table(name = "PARTID_REZULTAT_LOCALITATE")
public class PartidRezultateLocalitate implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 8133415273601486902L;

        /**
         * ID
         */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_partid_localitate")
        @SequenceGenerator(name = "seq_partid_localitate", sequenceName = "seq_partid_localitate", allocationSize = 1)
        @Column(name = "ID", length = 5)
        private Long id;

        /**
         * Data alegerilor.
         */
        @Column(name = "dataAlegerilor")
        private Date dataAlegerilor;

        /**
         * Tipul de alegeri.
         */
        @Column(name = "tip_alegeri")
        @Enumerated(EnumType.STRING)
        private TipAlegeriEnum tipAlegeri;

        /**
         * Partid.
         */
        @ManyToOne

        @JoinColumn(name = "PARTID", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_U_PARTID_LOC"))
        private Partid partid;

        /**
         * Numarul total de voturi.
         */
        @Column(name = "total_voturi_localitate")
        private Long totalVoturi;

        /**
         * Procentaj total de voturi.
         */
        @Column(name = "procentaj_total_voturi_localitate")
        private Float procentajTotalVoturi;

        /**
         * Numarul total de mandate.
         */
        @Column(name = "numar_mandate")
        private Long numarMandate;

        /**
         * Procentaj total mandate localitate.
         */
        @Column(name = "procentaj_mandate_localitate")
        private Float procentajMandateLocalitate;

        /**
         * Judetul.
         */
        @OneToOne
        @JoinColumn(name = "ID_JUDET", referencedColumnName = "cod_judet", foreignKey = @ForeignKey(name = "FK_U_PROVINCE_LOC"))
        private Provincia judetul;

        /**
         * Localiatea.
         */
        @OneToOne
        @JoinColumn(name = "ID_LOCALITATE", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_U_LOCALITATE"))
        private Localitate localitatea;

        /**
         * Numarul total de voturi ale partidului.
         */
        @Column(name = "total_voturi_judet")
        private Long totalVoturiJudet;

        /**
         * Numarul total de mandate.
         */
        @Column(name = "total_mandate_judet")
        private Long totalMandateJudet;

        /**
         * Anul alegerilor.
         */
        @Column(name = "an_alegeri")
        private Integer anAlegeri;

}
