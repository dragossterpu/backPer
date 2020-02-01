package ro.stad.online.gesint.persistence.entities.raport;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Prima cheie a răspunsului la un raport
 *
 * @author STAD
 *
 */
@EqualsAndHashCode(of = { "raport", "subzona" })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DateRaportId implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Raport la care aparține răspunsul.
	 */
	private Long raport;

	/**
	 * SDatele subzonei.
	 */
	private Long subzona;

}
