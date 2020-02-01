package ro.stad.online.gesint.jsf.scope;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import ro.stad.online.gesint.constante.Constante;

/**
 *
 * Permite simular el scope View en Spring.
 *
 * @author STAD
 *
 */
public class FacesViewScope implements Scope {

	/**
	 * Nombre del scope.
	 */
	public static final String NAME = Constante.VIEW;

	/**
	 * Obtiene el objeto de sesión.
	 * @param name nombre
	 * @param objectFactory objeto de factoría
	 * @return objeto
	 */
	@Override
	public Object get(final String name, final ObjectFactory<?> objectFactory) {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext == null) {
			throw new IllegalStateException("FacesContext.getCurrentInstance() returned null");
		}

		final Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

		Object respuesta;
		if (viewMap.containsKey(name)) {
			respuesta = viewMap.get(name);
		}
		else {
			final Object object = objectFactory.getObject();
			viewMap.put(name, object);

			respuesta = object;
		}
		return respuesta;
	}

	/**
	 * Obtiene el id de conersación.
	 * @return id de conversación
	 */
	@Override
	public String getConversationId() {
		return null;
	}

	/**
	 * Registra llamadas de destrucción.
	 * @param name nombre
	 * @param callback callback
	 */
	@Override
	public void registerDestructionCallback(final String name, final Runnable callback) {
		// Not supported by JSF for view scope
	}

	/**
	 * Borra el objeto de sesión.
	 * @param name nombre
	 * @return objeto borrado
	 */
	@Override
	public Object remove(final String name) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
	}

	/**
	 * Resuelve el objeto contextual.
	 * @param key clave
	 * @return objeto
	 */
	@Override
	public Object resolveContextualObject(final String key) {
		return null;
	}
}
