package ro.stad.online.gesint.persistence.entities.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defineste statutul civil al utilizatorului
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatutCivilEnum {
	/**
	 * Necasatorit.
	 */
	SINGLE("Necăsătorit/ă"),
	/**
	 * Casatorit.
	 */
	MARRIED("Căsătorit/ă"),
	/**
	 * Divortat.
	 */
	DIVORCED("Divorţat/ă"),

	/**
	 * Vaduv.
	 */
	WIDOWED("Văduv/ă"),

	/**
	 * Altul.
	 */
	OTHER("Alta"),

	/**
	 * Nespecificat.
	 */
	UNSPECIFIED("Nespecificat");

	private static final List<StatutCivilEnum> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();

	private static final Random RANDOM = new Random();

	/**
	 * @return Returnează o listă cu descrierile enum.
	 *
	 */
	public static List<StatutCivilEnum> getStatus() {
		final List<StatutCivilEnum> civilStatuts = new ArrayList<>();
		for (final StatutCivilEnum civilStatut : StatutCivilEnum.values()) {
			civilStatuts.add(civilStatut);
		}
		return civilStatuts;
	}

	public static StatutCivilEnum randomLetter() {
		return VALUES.get(RANDOM.nextInt(SIZE));
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
