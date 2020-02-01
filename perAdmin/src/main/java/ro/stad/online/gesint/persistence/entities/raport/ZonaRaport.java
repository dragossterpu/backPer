package ro.stad.online.gesint.persistence.entities.raport;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entitate pentru a pastra zona unui raport.
 *
 * @author STAD
 *
 */
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ZONA_RAPORT")
public class ZonaRaport implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID.
	 */
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_ZONERAPORT", sequenceName = "SEQ_ZONERAPORT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ZONERAPORT")
	private Long id;

	/**
	 * Descriere.
	 */
	@Column(name = "DESCRIPTION", length = 1000, nullable = false)
	private String descripcion;

	/**
	 * Lista de subareas.
	 */
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "zona_id")
	private List<SubZonaRaport> subzone;

	/**
	 * Ordinea in care apar subareas.
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
