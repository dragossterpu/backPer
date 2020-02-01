package ro.stad.online.gesint;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.faces.config.FacesInitializer;

/**
 * Configuración necesaria para la utilización de JSF junto con Spring.
 * 
 * @author STAD
 *
 */
@Configuration
public class ConfigureJSF {

	/**
	 * Clase que hereda de org.springframework.boot.web.servlet.ServletRegistrationBean para poder sobreescribir el
	 * método onStartup y definir el FacesInitializer de JSF.
	 * @author ATOS
	 */
	public class JsfServletRegistrationBean extends ServletRegistrationBean {

		/**
		 * Constructor.
		 */
		public JsfServletRegistrationBean() {
			super();
		}

		/**
		 * Configuración al arrancar el Servlet.
		 * @param servletContext contexto de servlet
		 * @throws ServletException excepción de servlet
		 */
		@Override
		public void onStartup(final ServletContext servletContext) throws ServletException {

			final FacesInitializer facesInitializer = new FacesInitializer();

			final Set<Class<?>> clazz = new HashSet<>();
			clazz.add(ConfigureJSF.class);
			facesInitializer.onStartup(clazz, servletContext);
		}
	}

	/**
	 * Metodo para facesServletRegistration.
	 * @return JsfServletRegistrationBean
	 */
	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		return new JsfServletRegistrationBean();
	}
}