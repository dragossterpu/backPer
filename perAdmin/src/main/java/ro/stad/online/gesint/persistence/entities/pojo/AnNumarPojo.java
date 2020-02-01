package ro.stad.online.gesint.persistence.entities.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.enums.SexEnum;

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
public class AnNumarPojo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * An.
	 */
	private String an;

	/**
	 * Numar.
	 */
	private Integer numar;

	private Date hasta;

	private Date desde;

	/**
	 * Filtru sex utilizator.
	 */
	private SexEnum sex;

}
