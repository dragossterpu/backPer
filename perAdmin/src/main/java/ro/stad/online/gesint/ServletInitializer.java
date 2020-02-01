package ro.stad.online.gesint;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Configuración del SerlvetInitializer.
 * 
 * @author STAD
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Inidicamos la clase que contiene el método main para que arranque Spring Boot.
	 * @param application constructor de la aplicación de spring
	 * @return constructor de aplicación spring
	 */
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(GesintApplication.class);
	}

}
