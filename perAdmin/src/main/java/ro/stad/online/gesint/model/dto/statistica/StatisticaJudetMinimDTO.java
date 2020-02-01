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
public class StatisticaJudetMinimDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5468840743886693413L;

	/**
	 * membriiMinim.
	 */
	private Integer membriiMinim;

	/**
	 * voturiMinim.
	 */
	private Integer voturiMinim;
}
