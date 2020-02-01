package ro.stad.online.gesint.exceptions;

/**
 * Excepción personalizada.
 * 
 * @author STAD
 *
 */
public class ExcepcionRollback extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Excepción general sin parámetros.
	 */

	public ExcepcionRollback() {
		super();
	}

	/**
	 * Excepción general.
	 * 
	 * @param e Excepción
	 */
	public ExcepcionRollback(final Exception e) {
		super(e);
	}

	/**
	 * Excepción con mensaje.
	 * 
	 * @param mensaje Mensaje que se mostrará al lanzar la excepción.
	 */
	public ExcepcionRollback(final String mensaje) {
		super(mensaje);
	}

}
