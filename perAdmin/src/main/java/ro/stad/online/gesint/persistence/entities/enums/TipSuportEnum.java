package ro.stad.online.gesint.persistence.entities.enums;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defineste tipul de suport
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipSuportEnum {
	/**
	 * Generale .
	 */
	VOTARE("Suport votare");

	/**
	 * @return Returnează o listă cu descrierile enum.
	 *
	 */
	public static List<TipSuportEnum> getTipSuport() {
		final List<TipSuportEnum> suporturi = new ArrayList<>();
		for (final TipSuportEnum suport : TipSuportEnum.values()) {
			suporturi.add(suport);
		}
		return suporturi;
	}

	/**
	 * Descrierea enum.
	 */
	private String description;

	/**
	 * @return Numele enum-ului "
	 */
	public String getName() {
		return name();
	}
}
