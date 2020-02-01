package ro.stad.online.gesint.model.filters;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.enums.TipSondajEnum;

/**
 * Controlatorul operatiilor relationate cu cautarea sondajelor. Reseteaza valorile initiale ale cautarii
 * @author STAD
 *
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Controller("sondajCautareBean")
@Scope(Constante.SESSION)
public class FiltruSondaj implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID de sondaj.
	 */
	private String id;

	/**
	 * Intrebarea sondajului.
	 */
	private String intrebare;

	/**
	 * Descriere
	 */
	private String descriere;

	/**
	 * Data incepand sondajul.
	 */
	private Date dataDinSondaj;

	/**
	 * Data pana sondaj.
	 */
	private Date dataPanaSondaj;

	/**
	 * Data din de creare a sondajului.
	 */
	private Date dateFromCreated;

	/**
	 * Data pana la de creare a sondajului.
	 */
	private Date dateUntilCreated;

	/**
	 * Filtru tip sondaj.
	 */
	private TipSondajEnum tipSondaj;

	/**
	 * Filtru activ.
	 */
	private Boolean activ;

	/**
	 * Filtru validate.
	 */
	private Boolean validate;
}
