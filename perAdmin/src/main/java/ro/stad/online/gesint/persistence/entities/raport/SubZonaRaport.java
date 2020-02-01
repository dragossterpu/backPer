package ro.stad.online.gesint.persistence.entities.raport;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entitate pentru subarea raport.
 *
 * @author STAD
 */
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "SUBZONA_RAPORT")
public class SubZonaRaport implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID.
	 */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_SUBZONARAPORT", sequenceName = "SEQ_SUBZONARAPORT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUBZONARAPORT")
	private Long id;

	/**
	 * Descriere.
	 */
	@Column(name = "DESCRIPTION", length = 1000, nullable = false)
	private String descripcion;

	/**
	 * Zonă din care face parte..
	 */
	//
	@ManyToOne
	@JoinColumn(name = "zona_id", foreignKey = @ForeignKey(name = "fk_zona_raport"))
	private ZonaRaport zona;

	/**
	 * Ordonarea subzonei în zonă.
	 */
	@Column(name = "orden")
	private Integer orden;

	/**
	 * Suprascrierea metodei toString pentru a folosi SelectItemsConvertor într-un mod generic, întotdeauna
	 * întorcându-se cheia principala.
	 */
	@Override
	public String toString() {
		return id.toString();
	}

}
