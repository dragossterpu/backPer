package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.enums.TipSondajEnum;

/**
 * Entitate pentru sondaje.
 * @author STAD
 */
@TypeDefs({ @TypeDef(name = "double precision", typeClass = java.lang.Float.class) })
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Builder
@ToString
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SONDAJ")
public class Sondaj extends AbstractEntity implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificator.
	 */
	@Id
	@SequenceGenerator(allocationSize = 1, name = "SEQ_SONDAJ", sequenceName = "SEQ_SONDAJ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SONDAJ")
	@Column(name = "ID", nullable = false, columnDefinition = Constante.NUMERIC, length = 10)
	private Long id;

	/**
	 * Descriere sondaj.
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "DESCRIERE", nullable = false)
	private String descriere;

	/**
	 * Intrebarea
	 */

	@Column(name = "INTREBARE", nullable = false, length = 1000)
	private String intrebare;

	/**
	 * Tip de sondaj.
	 */
	@Column(name = "TIPSONDAJ", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipSondajEnum tipSondaj;

	/**
	 * Data Inceperii
	 */
	@Column(name = "DATA_INCEPERE", length = 19)
	private Date dataIncepere;

	/**
	 * Data finalizarii.
	 */
	@Column(name = "DATA_FINALIZARE", length = 19)
	private Date dataFinalizare;

	/**
	 * Sondaj activ.
	 */
	@Column(name = "ACTIV")
	private Boolean activ;

	/**
	 * Raspunsurile sondajului.
	 */
	@OneToMany(mappedBy = "sondaj", fetch = FetchType.LAZY)
	private List<Raspuns> raspunsuri;

	/**
	 * Procentaj de voturi DA.
	 */
	@Column(name = "PROCENTAJ_DA")
	private Float procentajDa;

	/**
	 * Procentaj de voturi NU.
	 */
	@Column(name = "PROCENTAJ_NU")
	private Float procentajNu;

	/**
	 * Procentaj de voturi ABTINERI.
	 */
	@Column(name = "PROCENTAJ_ABT")
	private Float procentajAbt;

	/**
	 * Numarul total de voturi DA.
	 */
	@Column(name = "TOTAL_VOTURI_DA")
	private Long totalVoturiDa;

	/**
	 * Numarul total de voturi NU .
	 */
	@Column(name = "TOTAL_VOTURI_NU")
	private Long totalVoturiNu;

	/**
	 * Numarul total de voturi ABTINERI.
	 */
	@Column(name = "TOTAL_VOTURI_ABT")
	private Long totalVoturiAbt;

	/**
	 * Numarul total de voturi.
	 */
	@Column(name = "TOTAL_VOTURI")
	private Long totalVoturi;

}
