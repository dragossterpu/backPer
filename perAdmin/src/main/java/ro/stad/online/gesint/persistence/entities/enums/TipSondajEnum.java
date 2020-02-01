package ro.stad.online.gesint.persistence.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Tipuri de sondaj.
 * @author STAD
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipSondajEnum {

	/**
	 * Public.
	 */
	PUBLIC("Public"),
	/**
	 * Intern
	 */
	INTERN("Intern"),
	/**
	 * Conducere locală
	 */
	LOCALA("Conducere locală"),
	/**
	 * Conducere centrală
	 */
	CENTRALA("Conducere centrală");

	/**
	 * Descrierea enumeratorului.
	 */
	private String descripcion;

}
