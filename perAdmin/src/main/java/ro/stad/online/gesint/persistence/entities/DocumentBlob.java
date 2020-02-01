package ro.stad.online.gesint.persistence.entities;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
import lombok.ToString;

/**
 * Entitate pentru stocarea în baza de date a documentelor în format blob.
 * @author STAD
 */

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "documenteBlob")
public class DocumentBlob implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Identificatorul entității, generat prin secvență.
         */
        @Id
        @SequenceGenerator(name = "seq_documenteblob", sequenceName = "seq_documenteblob", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_documenteblob")
        @Column(name = "id", nullable = false)
        private Long id;

        /**
         * Nume fișier.
         */
        @Column(name = "nombreFichero")
        private String nombreFichero;

        /**
         * Array de byte cu conținutul fișierului.
         */
        @Lob
        @Column(name = "fichero")
        private byte[] fichero;

        /**
         * Metoda care obține imaginea pentru previzualizare în cazul în care documentul este un tip de imagine..
         * @return StreamedContent
         */
        public StreamedContent getImageUser() {
                return new DefaultStreamedContent(new ByteArrayInputStream(this.fichero));
        }

}
