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
 * Entitate parametrica pentru mapa.
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
@Table(name = "MAPA")
public class Mapa implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8133415273601486902L;

	/**
	 * ID mapa
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mapa")
	@SequenceGenerator(name = "seq_mapa", sequenceName = "seq_mapa", allocationSize = 1)
	@Column(name = "ID", length = 10)
	private Long id;

	/**
	 * Codul judetului.
	 */
	@Column(name = "COD_JUDET", length = 3)
	private String codJudet;

	/**
	 * Codul judetului.
	 */
	@Column(name = "INDICATIV", length = 20)

	private String indicativ;

	/**
	 * Numele fregistrului.
	 */
	@Column(name = "NUME", length = 100)
	private String name;

	/**
	 * Coordonatele registrului.
	 */
	@Column(name = "COORDONATE", length = 1000)
	private String coordonate;

	/**
	 * eticheta registrului.
	 */
	@Column(name = "eticheta", length = 10)
	private String eticheta;
}
