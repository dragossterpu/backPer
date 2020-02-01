package ro.stad.online.gesint.model.dto.statistica;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase DTO que contiene el resultado de una fila perteneciente a estadisticas de denuncias por provincia.
 *
 * @author EZENTIS
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RezultateDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5468840743886693413L;

	/**
	 * id partid.
	 *
	 */
	Long id;

	/**
	 * Nume partid.
	 *
	 */
	String nume;

	/**
	 * Sigla partid.
	 *
	 */
	String sigla;

	/**
	 * voturiPartid.
	 *
	 */
	int voturiPartid;

	/**
	 * mandate partid.
	 *
	 */
	int mandatePartid;

	/**
	 * total voturi.
	 *
	 */
	int totalVoturi;

	/**
	 * total mandate.
	 *
	 */
	int totalMandate;

}
