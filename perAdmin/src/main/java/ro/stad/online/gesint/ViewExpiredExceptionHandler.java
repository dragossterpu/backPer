
package ro.stad.online.gesint;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author STAD
 */
public class ViewExpiredExceptionHandler extends ExceptionHandlerWrapper {

	/**
	 *
	 */
	private static final Logger LOG = LogManager.getLogger(ViewExpiredExceptionHandler.class.getSimpleName());

	/**
	 *
	 */
	private ExceptionHandler wrapped;

	/**
	 *
	 * @param wrapped
	 */
	public ViewExpiredExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/**
	 *
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/**
	 *
	 */
	@Override
	public void handle() throws FacesException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			Throwable exception = i.next().getContext().getException();

			if (exception instanceof ViewExpiredException) {
				final ExternalContext externalContext = facesContext.getExternalContext();
				try {
					facesContext.setViewRoot(facesContext.getApplication().getViewHandler().createView(facesContext,
							"/error/401.xhtml"));
					externalContext.redirect(externalContext.getRequestContextPath() + "/error/401.xhtml");
					facesContext.getPartialViewContext().setRenderAll(true);
					facesContext.renderResponse();
					LOG.info("Sesiune închisă");

				}
				catch (IOException e) {
					LOG.error(e);
				}
				finally {
					i.remove();
				}
			}
		}
		getWrapped().handle();

	}
}
