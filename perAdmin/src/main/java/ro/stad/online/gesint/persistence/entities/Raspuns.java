package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ro.stad.online.gesint.constante.Constante;

/**
 * Entitate asociata raspunsurilor unui sondaj.
 *
 * @author STAD
 *
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
@Table(name = "RASPUNS")
public class Raspuns implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificator.
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "SEQ_RASPUNS", sequenceName = "SEQ_RASPUNS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RASPUNS")
	@Column(name = "ID", nullable = false, columnDefinition = Constante.NUMERIC, length = 10)
	private Long id;

	/**
	 * Sondaj caruia ii corespund datele.
	 */
	@ManyToOne
	@Id
	@JoinColumn(name = "id_sondaj", foreignKey = @ForeignKey(name = "fk_sondaj"), insertable = false, updatable = false, nullable = false)
	private Sondaj sondaj;

	/**
	 * Raspuns intrebare
	 */

	@Column(name = "VALOARE", nullable = false)
	private String valoare;

}
