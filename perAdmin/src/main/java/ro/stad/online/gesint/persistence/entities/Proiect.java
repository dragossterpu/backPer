package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.CategorieEnum;

/**
 *
 * Entitate pentru proiecte.
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
@Table(name = "PROIECTE")
public class Proiect extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID .
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROICTE")
	@SequenceGenerator(name = "SEQ_PROICTE", sequenceName = "SEQ_PROICTE", allocationSize = 1)
	@Column(name = "id", length = 5)
	private Long id;

	/**
	 * String cu numele proiectului.
	 */
	@Column(name = "TITLU")
	private String titlu;

	/**
	 * Continutul textului.
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "TEXT")
	private String text;

	/**
	 * Rangul de afisare in pagina.
	 */
	@Column(name = "RANK")
	private Long rank;

	/**
	 * Data publicarii.
	 */
	@Column(name = "date_publish")
	private Date datePublish;

	/**
	 * Educatie utilizator.
	 */
	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)
	private CategorieEnum categoria;

	/**
	 * Validat
	 */
	@Column(name = "VALIDATED")
	private Boolean validated;

	/**
	 * String materie indexata.
	 */
	@Column(name = "MATERIA_INDEXATA")
	private String materiaIndexata;

	/**
	 * Utilizatorul care publica proiectul
	 */
	@ManyToOne
	@JoinColumn(name = "username")
	private Utilizator usuario;

	// /**
	// * Fotoografia publicatie.
	// */
	// @Column(name = "IMAGE")
	// private byte[] image;
	//
	// /**
	// * Metoda care obține imaginea pentru previzualizare în cazul în care documentul este un tip de imagine.
	// * @return StreamedContent
	// */
	// public StreamedContent getImageProject() {
	// return new DefaultStreamedContent(new ByteArrayInputStream(this.image));
	// }

}
