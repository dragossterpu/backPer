package ro.stad.online.gesint.persistence.entities;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.Getter;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.CanalAlertaEnum;
import ro.stad.online.gesint.persistence.entities.enums.StatutCivilEnum;
import ro.stad.online.gesint.persistence.entities.enums.EducatieEnum;
import ro.stad.online.gesint.persistence.entities.enums.SexEnum;

/**
 *
 * Entitate aditionala pentru completarea datelor utilizatorului..
 *
 * @author STAD
 *
 */
@Getter
@Setter
@Embeddable
public class DatePersonale implements Serializable {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        /**
         * Email personal.
         */
        private String personalEmail;

        /**
         * Cuerpo al que pertenece el usuario.
         */
        @ManyToOne
        @JoinColumn(name = "PROVINCE_ID", foreignKey = @ForeignKey(name = "FK_U_PROVINCE"))
        private Provincia province;

        /**
         * Localitatea.
         */
        @ManyToOne
        @JoinColumn(name = "LOCALITY_ID", foreignKey = @ForeignKey(name = "FK_U_LOCALITY"))
        private Localitate locality;

        /**
         * Telefonul.
         */
        private String phone;

        /**
         * Adresa.
         */
        private String address;

        /**
         * CNP utilizator.
         */
        private String idCard;

        /**
         * Numar buletin de identitate utilizator.
         */
        private String numberCard;

        /**
         * Data nasterii utilizator.
         */
        private Date birthDate;

        /**
         * Educatie utilizator.
         */
        @Column(name = "education")
        @Enumerated(EnumType.STRING)
        private EducatieEnum education;

        /**
         * Canal de alertas del usuario.
         */
        @Column(name = "alert_channel", length = 10)
        @Enumerated(EnumType.STRING)
        private CanalAlertaEnum alertChannel;

        /**
         * Loc de munca utilizator.
         */
        private String workplace;

        /**
         * Sex utilizator.
         */
        @Column(name = "sex")
        @Enumerated(EnumType.STRING)
        private SexEnum sex;

        /**
         * Stare civila utilizator.
         */
        @Column(name = "civilStatus")
        @Enumerated(EnumType.STRING)
        private StatutCivilEnum civilStatus;

        /**
         * Fotoografia utilizator.
         */
        private byte[] photo;

        /**
         * Utilizator validat.
         */
        private Boolean validated;

        /**
         * Método que obtiene la imágen para previsualizar en caso de que el documento sea de tipo imágen.
         * @return StreamedContent
         */
        public StreamedContent getImageUser() {
                return new DefaultStreamedContent(new ByteArrayInputStream(this.photo));
        }
}
