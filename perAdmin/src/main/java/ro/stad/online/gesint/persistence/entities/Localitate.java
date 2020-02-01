package ro.stad.online.gesint.persistence.entities;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;

/**
 *
 * Entitate parametrica pentru localitatile unui judet.
 *
 * @author STAD
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Builder
@Getter
@Setter
@Entity
@Table(name = "LOCALITATE")
public class Localitate implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 8133415273601486902L;

        /**
         * ID.
         */
        @Id
        @SequenceGenerator(name = "seq_locality", sequenceName = "seq_locality", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_locality")
        @Column(name = "id")
        private Long id;

        /**
         * Numele localitatii.
         */
        @Column(name = "NAME", length = 100, nullable = false)
        private String name;

        /**
         * Tipul localitatii.
         */
        @Column(name = "typelocality", length = 50, nullable = false)
        @Enumerated(EnumType.STRING)
        private TipLocalitateEnum typelocality;

        /**
         * Indicator.
         */
        @Column(name = "RESIDENCE")
        private Boolean residence;

        /**
         * Judetul localitatii.
         */
        @ManyToOne
        @JoinColumn(name = "cod_judet", foreignKey = @ForeignKey(name = "FK_PROVINCE"), nullable = false)
        private Provincia province;

        /**
         * Sectorul localitatii.
         */
        @Column(name = "SECTOR", length = 10)
        private String sector;

        /**
         * Nivel.
         */
        @Column(name = "nivel")
        private Long nivel;

        /**
         * Populatie.
         */
        @Column(name = "locuitori")
        private Long locuitori;

        /**
         * Voturi minim.
         */
        @Column(name = "voturi_minim")
        private Long voturiMinim;

        /**
         * Membrii minim.
         */
        @Column(name = "membrii_minim")
        private Long membriiMinim;

        /**
         * Fotoografia localitate.
         */
        private byte[] photo;

        /**
         * Metoda care obține imaginea pentru previzualizare în cazul în care documentul este un tip de imagine..
         * @return StreamedContent
         */
        public StreamedContent getImageLocalitate() {
                return new DefaultStreamedContent(new ByteArrayInputStream(this.photo));
        }
}
