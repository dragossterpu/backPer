package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * 
 * Controller pentru proprietățile aplicației.
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
@Table(name = "PROPERTIES")
public class Proprietate implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * ID.
         */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROPERTIES")
        @SequenceGenerator(name = "SEQ_PROPERTIES", sequenceName = "SEQ_PROPERTIES", allocationSize = 1)
        @Column(name = "id", length = 5)
        private Long id;

        /**
         * Numele grupului de proprietăți
         */
        @Column(name = "FILENAME")
        private String filename;

        /**
         * Numele proprietăți.
         */
        @Column(name = "NAME")
        private String name;

        /**
         * Valorarea proprietăți.
         */
        @Column(name = "VALUE")
        private String value;

        /**
         * Scurtă descriere a proprietăți.
         */
        @Column(name = "DESCRIPTION")
        private String descriere;
}
