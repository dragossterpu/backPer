package ro.stad.online.gesint.model.filters;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.persistence.entities.Utilizator;
import ro.stad.online.gesint.persistence.entities.enums.StatutCivilEnum;
import ro.stad.online.gesint.persistence.entities.enums.EducatieEnum;
import ro.stad.online.gesint.persistence.entities.enums.RolEnum;
import ro.stad.online.gesint.persistence.entities.enums.SexEnum;
import ro.stad.online.gesint.persistence.entities.enums.TipLocalitateEnum;

/**
 * Controlador de las operaciones relacionadas con la búsqueda de usuarios. Reseteo de valores de búsqueda.
 *
 * @author STAD
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltruUtilizator implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Filtru nune de utilizator.
	 */
	private String username;

	/**
	 * Filtru cnp de utilizator.
	 */
	private String idCard;

	/**
	 * Filtru rol utilizator.
	 */
	private RolEnum role;

	/**
	 * Filtru nume utilizator.
	 */
	private String name;

	/**
	 * Filtru prenume utilizator.
	 */
	private String lastName;

	/**
	 * Filtru email utilizator.
	 */
	private String email;

	/**
	 * Filtru judet utilizator.
	 */
	private Provincia provincia;

	/**
	 * Filtru judet utilizator.
	 */
	private Long id;

	/**
	 * Filtru localitate utilizator.
	 */
	private Localitate locality;

	/**
	 * Filtru nivel de studii utilizator.
	 */
	private EducatieEnum education;

	/**
	 * Filtru sex utilizator.
	 */
	private SexEnum sex;

	/**
	 * Filtru judet utilizator.
	 */
	private String idProvincia;

	/**
	 * Filtru localitate utilizator.
	 */
	private Long idLocalidad;

	/**
	 * Filtru stare civila utilizator.
	 */
	private StatutCivilEnum civilStatus;

	/**
	 * Filtru tipul localitatii utilizator.
	 */
	private TipLocalitateEnum typeLocality;

	/**
	 * Filtru activ/inactiv utilizator.
	 */
	private Boolean validated;

	/**
	 * Filtru destinatar extern.
	 */
	private Boolean destinatarExtern;

	/**
	 * Filtru data inregistrarii incepand.
	 */
	private Date dateFrom;

	/**
	 * Filtru data inregistrarii pana.
	 */
	private Date dateUntil;

	/**
	 * Variable utilizada para almacenar el valor de la provincia seleccionada.
	 *
	 */
	private Provincia provinciaSelected;

	/**
	 * Lista de usuarios seleccionados.
	 */
	private List<Utilizator> utilizatoriSelectionati;

	/**
	 * Variable para personas eliminadas
	 */
	private String eliminado = "";

	/**
	 * Variable para functie
	 */
	private Long idFunctia;
}
