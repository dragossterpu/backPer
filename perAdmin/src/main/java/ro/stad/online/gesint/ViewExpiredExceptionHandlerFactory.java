
package ro.stad.online.gesint;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * @author STAD
 */
public class ViewExpiredExceptionHandlerFactory extends ExceptionHandlerFactory {

	/**
	 *
	 */
	private ExceptionHandlerFactory parent;

	/**
	 *
	 * @param parent
	 */
	public ViewExpiredExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/**
	 *
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = parent.getExceptionHandler();
		result = new ViewExpiredExceptionHandler(result);

		return result;
	}

}