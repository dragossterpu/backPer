package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.enums.CanalAlertaEnum;
import ro.stad.online.gesint.persistence.entities.enums.CategorieEnum;

/**
 * Entitate pentru alerte.
 * @author STAD
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Builder
@ToString
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ALERT")
public class Alerta extends AbstractEntity implements Serializable {

        /**
         * Serial ID.
         */
        private static final long serialVersionUID = 1L;

        /** Identificator. */
        @Id
        @SequenceGenerator(allocationSize = 1, name = "SEQ_ALERT", sequenceName = "SEQ_ALERT")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ALERT")
        @Column(name = "ID", nullable = false, columnDefinition = Constante.NUMERIC, length = 10)
        private Long id;

        /** Titlu. */
        @Column(name = "TITLU", nullable = false, length = 256)
        private String titlu;

        /**
         * Corpul mesajului
         */

        @Column(name = "BODY", nullable = false, length = 4000)
        private String descriere;

        /**
         * The channel.
         */
        @Column(name = "CHANNEL")
        @Enumerated(EnumType.STRING)
        private CanalAlertaEnum channel;

        /** The sended on. */
        @Column(name = "SENDED_ON", length = 19)
        private Date dataTrimiteri;

        /**
         * Lista cu destinatarii alertei.
         */
        @Lob
        @Type(type = "org.hibernate.type.TextType")
        @Column(name = "destinatari")
        private String destinatari;

        /**
         * Trimitere automatica.
         */
        @Column(name = "auto")
        private Boolean automatic;

        /**
         * Data planificata.
         */
        @Column(name = "data_planificata")
        private Date dataPlanificata;

        /**
         * Tip alerta.
         */
        @Column(name = "tip_alerta")
        @Enumerated(EnumType.STRING)
        private CategorieEnum tipAlerta;

}
