package ro.stad.online.gesint.persistence.entities.raport;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
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
 * Entitate asociata datelor unui raport.
 *
 * @author STAD
 *
 */
@EqualsAndHashCode(of = { "raport", "subzona" })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
@IdClass(DateRaportId.class)
@Table(name = "DATE_RAPORT")
public class DateRaport implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Raport caruia ii corespund datele.
	 */
	@ManyToOne
	@Id
	@JoinColumn(name = "raport_id", foreignKey = @ForeignKey(name = "fk_raport"), insertable = false, updatable = false, nullable = false)
	private Raport raport;

	/**
	 * Subzona date.
	 */
	@ManyToOne
	@Id
	@JoinColumn(name = "subzona_id", foreignKey = @ForeignKey(name = "fk_subzona_rap"), insertable = false, updatable = false, nullable = false)
	private SubZonaRaport subzona;

	/**
	 * Textul datelor din subzona raportului in cod HTML cu stiluri de PrimeFaces.
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "TEXT")
	private String texto;

	/**
	 * Imagini.
	 */
	private byte[] photo;

	/**
	 * Metoda care obține imaginea pentru previzualizare în cazul în care documentul este un tip de imagine..
	 * @return StreamedContent
	 */
	public StreamedContent getImageRaport() {
		return new DefaultStreamedContent(new ByteArrayInputStream(this.photo));
	}

}
