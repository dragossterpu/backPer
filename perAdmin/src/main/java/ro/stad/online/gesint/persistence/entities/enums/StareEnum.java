package ro.stad.online.gesint.persistence.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Stări active și inactive.
 *
 * @author STAD
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StareEnum {

	/**
	 * Activ.
	 */
	ACTIV("activ"),
	/**
	 * Inactiv
	 */
	INACTIV("inactiv");

	/**
	 * Descrierea enum.
	 */
	private String description;
}
