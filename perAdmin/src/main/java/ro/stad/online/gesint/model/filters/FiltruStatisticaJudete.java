package ro.stad.online.gesint.model.filters;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Obiect de filtrare.
 *
 * @author STAD
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltruStatisticaJudete implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7637962187780804682L;

	/**
	 * Fecha mínima de los resultados.
	 */
	private Date fechaDesde;

	/**
	 * code_province.
	 */
	private String code_province;

	/**
	 * descendent.
	 */
	private String descendent;

	/**
	 * descendent.
	 */
	private String generalJudetProcentaj;

}
