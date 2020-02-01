package ro.stad.online.gesint.exceptions;

/**
 * Excepción personalizada.
 *
 * @author STAD
 *
 */
public class GesintException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje de la excepción.
	 */
	String mensaje;

	/**
	 * Excepción general sin parámetros.
	 */

	public GesintException() {
		super();
	}

	/**
	 * Excepción general.
	 *
	 * @param e Excepción
	 */
	public GesintException(final Exception e) {
		super(e);
		mensaje = e.getMessage();
	}

	/**
	 * Sobreescritura del método getMessage() para mostrar el mensaje personalizado.
	 */
	@Override
	public String getMessage() {
		return mensaje;
	}

	/**
	 * Excepción con mensaje.
	 *
	 * @param mensaje Mensaje que se mostrará al lanzar la excepción.
	 */
	public GesintException(final String mensaje) {
		super(mensaje);
	}
}
