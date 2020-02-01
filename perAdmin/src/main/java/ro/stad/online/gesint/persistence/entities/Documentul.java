package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entitatea documentului. Orice fișier încărcat în aplicație este stocata în acesta tabla.
 *
 * @author STAD
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOCUMENT")
public class Documentul extends AbstractEntity implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * Identificatorul entității, generat prin secvență.
         */
        @Id
        @SequenceGenerator(name = "SEQ_DOCUMENT", sequenceName = "SEQ_DOCUMENT", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENT")
        @Column(name = "id", nullable = false)
        private Long id;

        /**
         * Fichero enlazado al documento.
         */
        @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "ID_FISIER", foreignKey = @ForeignKey(name = "FK_D_FICHERO"))
        private DocumentBlob fisier;

        /**
         * ContentType din fișierul atașat.
         */
        @Column(name = "tipContinut", nullable = false)
        private String tipContinut;

        /**
         * Numele documentului
         */
        @Column(name = "nume")
        private String nume;

        /**
         * Descriere.
         */
        @Column(name = "descriere", length = 2000)
        private String descriere;

        /**
         * Cuvinte cheie ale documentului.
         */
        @Column(name = "materia_indexada", length = 2000)
        private String materiaIndexada;

        /**
         * Tipul documentului
         */
        @ManyToOne
        @JoinColumn(name = "tipDocument")
        private TipDocument tipDocument;

        /**
         * Utilizatorul care publica proiectul
         */
        @ManyToOne
        @JoinColumn(name = "username")
        private Utilizator utilizator;

        /**
         * Document validat sau nu.
         */
        @Column(name = "validat")
        private Boolean validat;

        /**
         * Proiectul la care este atribuit documentul.
         */
        @ManyToMany
        @JoinTable(name = "documente_proiect", joinColumns = {
                        @JoinColumn(name = "id_document") }, inverseJoinColumns = { @JoinColumn(name = "id_proiect") })
        private List<Proiect> proiect;

        /** The job instance. */
        @ManyToOne
        @JoinColumn(name = "alerta", nullable = true, unique = false)
        private Alerta alerta;

        /**
         * Sondaje asociate documentelor.
         */
        @ManyToMany
        @JoinTable(name = "documente_sondaj", joinColumns = {
                        @JoinColumn(name = "id_document") }, inverseJoinColumns = { @JoinColumn(name = "id_sondaj") })
        private List<Sondaj> sondaje;

}
