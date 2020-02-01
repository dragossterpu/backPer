package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
 * Entitate parametrica para el almacenamiento de o functie.
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
@Table(name = "ECHIPA")
public class Echipa implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 8133415273601486902L;

        /**
         * ID TEAM.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_team")
        @SequenceGenerator(name = "seq_team", sequenceName = "seq_team", allocationSize = 1)
        @Column(name = "ID", length = 5)
        private Long id;

        /**
         * Utilizator.
         */
        @OneToOne(fetch = FetchType.EAGER)
        private Utilizator user;

        /**
         * Departamento al que pertenece el documento
         */
        @ManyToOne
        @JoinColumn(name = "TEAM")
        private ParamEchipa team;

        /**
         * Ordinea de aparitie.
         */
        @Column(name = "RANK", length = 2)
        private Long rank;

}
