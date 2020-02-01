package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * Entitate parametrica para el almacenamiento de o functie.
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
@Table(name = "PTEAM")
public class ParamEchipa implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8133415273601486902L;

	/**
	 * ID PTEAM.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PTEAM")
	@SequenceGenerator(name = "SEQ_PTEAM", sequenceName = "SEQ_PTEAM", allocationSize = 1)
	@Column(name = "ID", length = 5)
	private Long id;

	/**
	 * Organizatia functiei.
	 */
	@Column(name = "ORGANIZATION", length = 100)
	private String organization;

	/**
	 * Descrierea functiei.
	 */
	@Column(name = "DESCRIPTION", length = 255)
	private String description;

	/**
	 * Numele functiei.
	 */
	@Column(name = "NAME", length = 255, nullable = false)
	private String name;

}
