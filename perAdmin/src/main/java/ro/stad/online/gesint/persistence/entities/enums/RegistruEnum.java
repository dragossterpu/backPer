package ro.stad.online.gesint.persistence.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * Enumerarea cu valorile posibile ale tipurilor de înregistrări de activitate.
 * @author STAD
 * 
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RegistruEnum {
	/**
	 * El registro corresponde a un alta.
	 */
	ALTA("Inregistrare"),
	/**
	 * El registro corresponde a una modificación.
	 */
	MODIFICACION("Modificare"),
	/**
	 * El registro corresponde a una baja.
	 */
	BAJA("Eliminare"),
	/**
	 * El registro corresponde a un error.
	 */
	ERROR("Eroare");

	/**
	 * Descripción del tipo de registro.
	 */
	private String descripcion;
}
