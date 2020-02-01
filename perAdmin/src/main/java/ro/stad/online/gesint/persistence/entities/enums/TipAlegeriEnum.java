package ro.stad.online.gesint.persistence.entities.enums;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defineste tipul de alegeri
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipAlegeriEnum {
	/**
	 * Generale .
	 */
	GENERALE("Generale"),

	/**
	 * Prezidențiale.
	 */
	PREZIDENTIALE("Prezidențiale"),

	/**
	 * Locale.
	 */
	LOCALE_PRIMAR("Primari"),

	/**
	 * Europarlamentare.
	 */
	EUROPENE("Europarlamentare"),
	/**
	 * Locale.
	 */
	LOCALE_CONSILIU("Consilieri locali"),

	/**
	 * Locale.
	 */
	JUDET_CONSILIU("Consilieri Județeni"),
	/**
	 * Referendum.
	 */
	REFERENDUM("Referendum");

	/**
	 * @return Returnează o listă cu descrierile enum.
	 *
	 */
	public static List<TipAlegeriEnum> getTipAlegeri() {
		final List<TipAlegeriEnum> alegeri = new ArrayList<>();
		for (final TipAlegeriEnum alegere : TipAlegeriEnum.values()) {
			alegeri.add(alegere);
		}
		return alegeri;
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
