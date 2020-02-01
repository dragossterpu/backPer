package ro.stad.online.gesint.persistence.entities.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Controlador de las operaciones relacionadas con la estadistica
 *
 * @author STAD
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersoanaJudet implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * code_province.
	 */
	private String code_province;

	/**
	 * nume.
	 */
	private String nume;

	/**
	 * totalLocuitori.
	 */
	private Integer totalLocuitori;

	/**
	 * numarMembrii.
	 */
	private Integer numarMembrii;

	/**
	 * procentaj.
	 */
	private Float procentaj;

	/**
	 * valoare.
	 */
	private String valoare;

}
