package ro.stad.online.gesint.persistence.entities.enums;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defineste rezultatul.
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RezultatEnum {

	/**
	 * Excelent.
	 */
	EXCELENT("Excelent"),

	/**
	 * Bun.
	 */
	BUN("Bun"),

	/**
	 * Acceptabil.
	 */
	ACCEPTABIL("Acceptabil"),

	/**
	 * Neconvingator.
	 */
	NECONVINGATOR("Neconvingator");

	/**
	 * @return Returnează o listă cu descrierile enum.
	 *
	 */
	public static List<RezultatEnum> getTipStatistica() {
		final List<RezultatEnum> tipuriStatistica = new ArrayList<>();
		for (final RezultatEnum tipStatistica : RezultatEnum.values()) {
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
