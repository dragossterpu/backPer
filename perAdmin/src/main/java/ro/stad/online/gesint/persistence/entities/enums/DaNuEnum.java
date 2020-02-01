package ro.stad.online.gesint.persistence.entities.enums;

import java.util.ArrayList;
import java.util.List;

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
public enum DaNuEnum {

	/**
	 * Negativ.
	 */
	NOT(0),

	/**
	 * Afirmativ.
	 */
	YES(1);

	/**
	 * @return Returnează o listă cu descrierile enumelor.
	 *
	 */
	public static List<DaNuEnum> getValidated() {
		final List<DaNuEnum> validari = new ArrayList<>();
		for (final DaNuEnum validat : DaNuEnum.values()) {
			validari.add(validat);
		}
		return validari;
	}

	/**
	 * Descrierea enumului.
	 */
	private Integer descripcion;

	/**
	 * @return Nombre del enum.
	 */
	public String getName() {
		return name();
	}
}
