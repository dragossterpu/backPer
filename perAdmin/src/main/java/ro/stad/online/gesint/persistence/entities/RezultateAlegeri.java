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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.TipAlegeriEnum;

/**
 *
 * Entitate parametrica pentru datele rezultate din alegeri.
 *
 * @author STAD
 *
 */
@TypeDefs({ @TypeDef(name = "double precision", typeClass = java.lang.Float.class) })
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Builder
@Getter
@Setter
@Entity
@Table(name = "REZULTATE")
public class RezultateAlegeri implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8133415273601486902L;

	/**
	 * ID TEAM.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rezultate")
	@SequenceGenerator(name = "seq_rezultate", sequenceName = "seq_rezultate", allocationSize = 1)
	@Column(name = "ID", length = 5)
	private Long id;

	/**
	 * Tipul de alegeri.
	 */
	@Column(name = "tipAlegeri")
	@Enumerated(EnumType.STRING)
	private TipAlegeriEnum tipAlegeri;

	/**
	 * Data alegerilor.
	 */
	@Column(name = "dataAlegerilor")
	private Date dataAlegerilor;

	/**
	 * Numarul total de voturi.
	 */
	@Column(name = "TOTALVOTURIPARTID")
	private Long totalVoturiPartid;

	/**
	 * Turul alegerilor.
	 */
	@Column(name = "TURULALEGERILOR")
	private Long turulAlegerilor;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURIPARTID")
	private Float procentajTotalPartid;

	/**
	 * Numarul total de voturi.
	 */
	@Column(name = "TOTALVOTURI")
	private Long totalVoturi;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURI")
	private Float procentajTotalVoturi;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "TOTALMANDATE")
	private Long totalMandate;

	/**
	 * Procentaj total de mandate.
	 */
	@Column(name = "PROCENTAJTOTALMANDATE")
	private Float procentajTotalMandate;

	/**
	 * Numarul total de voturi.
	 */
	@Column(name = "TOTALVOTURIPRIMARCAP")
	private Long totalVoturiPrimCap;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURIPRIMARCAP")
	private Float procentajTotalVoturiPrimCap;

	/**
	 * Numarul total de voturi primari.
	 */
	@Column(name = "TOTALVOTURIPRIMAR")
	private Long totalVoturiPrim;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURIPRIMAR")
	private Float procentajTotalVoturiPrim;

	/**
	 * Numarul total de voturi primari.
	 */
	@Column(name = "TOTALVOTURICONSLOCAL")
	private Long totalVoturiConsLocal;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURICONSLOCAL")
	private Float procentajTotalVoturiConsLocal;

	/**
	 * Numarul total de voturi primari.
	 */
	@Column(name = "TOTALVOTURICONSJUD")
	private Long totalVoturiConsJud;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURICONSJUD")
	private Float procentajTotalVoturiConsJud;

	/**
	 * Numarul total de voturi primari.
	 */
	@Column(name = "TOTALVOTURISENAT")
	private Long totalVoturiSenat;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURISENAT")
	private Float procentajTotalVoturiSenat;

	/**
	 * Numarul total de voturi primari.
	 */
	@Column(name = "TOTALVOTURIDEPUTAT")
	private Long totalVoturiDeputat;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURIDEPUTAT")
	private Float procentajTotalVoturiDeputat;

	/**
	 * Numarul total de voturi primari.
	 */
	@Column(name = "TOTALVOTURIEURO")
	private Long totalVoturiEuro;

	/**
	 * Procentaj total de voturi.
	 */
	@Column(name = "PROCENTAJTOTALVOTURIEURO")
	private Float procentajTotalVoturiEuro;

}
