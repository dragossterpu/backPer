package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * Entitate parametrica pentru tari.
 *
 * @author STAD
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "code")
@Builder
@Getter
@Setter
@Entity
@Table(name = "TARA")
public class Tara implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 8133415273601486902L;

        /**
         * Id.
         */
        @Id
        @Column(name = "CODE", length = 4)
        private String code;

        /**
         * Numele tarii.
         */
        @Column(name = "NAME", length = 100)
        private String name;

}
