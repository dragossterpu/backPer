package ro.stad.online.gesint.persistence.entities.enums;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defineste tipul de proiect care se publica
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CategorieEnum {
	/**
	 * Statut .
	 */
	STATUT("Statut"),

	/**
	 * Lege.
	 */
	LEGE("Lege"),

	/**
	 * Proiect.
	 */
	PROIECT("Proiect"),

	/**
	 * Comunicat.
	 */
	COMUNICAT("Comunicat"),

	/**
	 * Raport.
	 */
	RAPORT("Raport"),

	/**
	 * Informare.
	 */
	INFORMARE("Informare"),

	/**
	 * Rezolutie.
	 */
	REZOLUTIE("Rezoluție");

	/**
	 * @return Returnează o listă cu descrierile enum.
	 *
	 */
	public static List<CategorieEnum> getCategoria() {
		final List<CategorieEnum> categorii = new ArrayList<>();
		for (final CategorieEnum categoria : CategorieEnum.values()) {
			categorii.add(categoria);
		}
		return categorii;
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
