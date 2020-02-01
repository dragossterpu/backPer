package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * Entitate parametrica pentru optiuni.
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
@Table(name = "OPTIUNI_ALEGERI")
public class Optiune implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 8133415273601486902L;

        /**
         * ID TEAM.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_optiuni")
        @SequenceGenerator(name = "seq_optiuni", sequenceName = "seq_optiuni", allocationSize = 1)
        @Column(name = "ID", length = 5)
        private Long id;

        /**
         * Numele optiunii.
         */
        @Column(name = "NUME", length = 150)
        private String nume;

        /**
         * Voturi optinute
         */
        @Column(name = "voturi_obtinute")
        private Long voturiOptinute;

        /**
         * Judetul optiunii.
         */
        @ManyToOne
        @JoinColumn(name = "cod_judet", foreignKey = @ForeignKey(name = "FK_PROVINCE_OPT"), nullable = false)
        private Provincia province;

        /**
         * 
         * Localitatate optiune.
         */
        @ManyToOne
        @JoinColumn(name = "locality_id", foreignKey = @ForeignKey(name = "FK_LOCALITATE_OPT"), nullable = false)
        private Localitate localitate;

}
