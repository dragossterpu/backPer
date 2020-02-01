package ro.stad.online.gesint.persistence.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Enumerado para la definición de contentType de ficheros.
 *
 * @author ATOS
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipContinutEnum {
	/**
	 * ContentType para la generación de un archivo DOCX.
	 */
	DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	/**
	 * ContentType para la generación de un archivo PDF.
	 */
	PDF("application/pdf");

	/**
	 * Descripción del contentType.
	 */
	String contentType;
}
