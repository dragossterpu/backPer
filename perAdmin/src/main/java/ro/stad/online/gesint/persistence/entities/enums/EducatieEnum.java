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
 * Defineste nivelul de educatie al utilizatorului
 *
 * @author STAD
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EducatieEnum {
	/**
	 * Bazic .
	 */
	BAZIC("Bazic"),

	/**
	 * Liceu.
	 */
	LICEU("Liceu"),

	/**
	 * Studii universitare.
	 */
	STUDIISUPERIOARE("Studii universitare"),

	/**
	 * Nespecificat.
	 */
	NESPECIFICAT("Nespecificat");

	private static final List<EducatieEnum> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();

	private static final Random RANDOM = new Random();

	/**
	 * @return Returnează o listă cu descrierile enum.
	 *
	 */
	public static List<EducatieEnum> getEducation() {
		final List<EducatieEnum> educations = new ArrayList<>();
		for (final EducatieEnum education : EducatieEnum.values()) {
			educations.add(education);
		}
		return educations;
	}

	public static EducatieEnum randomLetter() {
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
