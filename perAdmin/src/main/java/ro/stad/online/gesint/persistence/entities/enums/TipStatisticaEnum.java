package ro.stad.online.gesint.persistence.entities.enums;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defineste tipul statisticii.
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipStatisticaEnum {
	/**
	 * Lunar.
	 */
	LUNAR("Lunar"),

	/**
	 * Trimestrial.
	 */
	TRIMESTRIAL("Trimestrial"),
	/**
	 * Anual.
	 */
	ANUAL("Anual");

	/**
	 * @return Returnează o listă cu descrierile enum.
	 *
	 */
	public static List<TipStatisticaEnum> getTipStatistica() {
		final List<TipStatisticaEnum> tipuriStatistica = new ArrayList<>();
		for (final TipStatisticaEnum tipStatistica : TipStatisticaEnum.values()) {
			tipuriStatistica.add(tipStatistica);
		}
		return tipuriStatistica;
	}

	/**
	 * Descrierea enum.
	 */
	private String description;

	/**
	 * Devuelve numele.
	 * @return String
	 */
	public String getNombre() {
		return this.name();
	}
}
