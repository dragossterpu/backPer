package ro.per.online;

import java.util.Collections;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hibernate.SessionFactory;
import org.primefaces.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;

import ro.per.online.constantes.Constantes;
import ro.per.online.jsf.scope.FacesViewScope;

/**
 * Clase de arranque y configuración de Spring Boot.
 * 
 * @author STAD
 *
 */
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class PerApplication {

	/**
	 * Variable utilizada para inyectar la sesión.
	 */
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/**
	 * Punto de entrada de la aplicación para Spring Boot.
	 * @param args parámetros de entrada del método main
	 */
	public static void main(final String[] args) {
		SpringApplication.run(PerApplication.class, args);
	}

	/**
	 * @return HibernateJpaSessionFactoryBean.
	 */
	@Bean
	public SessionFactory sessionFactory() {
		return entityManagerFactory.unwrap(SessionFactory.class);
	}

	/**
	 * @return scope que simula el scope view de jsf
	 */
	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		final CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(Collections.<String, Object> singletonMap(FacesViewScope.NAME, new FacesViewScope()));
		return configurer;
	}

	/**
	 * Implementación que se va a usar para las páginas de error.
	 * 
	 * @return ErrorPageRegistrar
	 */
	@Bean
	public ErrorPageRegistrar errorPageRegistrar() {
		return new RegistroPaginasError();
	}

	/**
	 * RegistroPaginasError.
	 */
	static class RegistroPaginasError implements ErrorPageRegistrar {

		/**
		 * registerErrorPages.
		 */
		@Override
		public void registerErrorPages(final ErrorPageRegistry registry) {
			registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.xhtml"),
					new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.xhtml"),
					new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/5xx.xhtml"),
					new ErrorPage(HttpStatus.BAD_GATEWAY, "/error/5xx.xhtml"));
		}
	}

	/**
	 * Implementación que se va a usar para las páginas de error.
	 * 
	 * @return ErrorPageRegistrar
	 */
	@Configuration
	@Profile("dev")
	static class ConfigureJSFContextParameters implements ServletContextInitializer {

		/**
		 * Implementación que se va a usar para las páginas de error.
		 * 
		 * @return ErrorPageRegistrar
		 */
		@Override
		public void onStartup(final ServletContext servletContext) throws ServletException {
			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
			servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Constantes.TRUE);
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
			servletContext.setInitParameter("facelets.DEVELOPMENT", Constantes.TRUE);
			servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Constantes.TRUE);
			servletContext.setInitParameter("primefaces.THEME", "blitzer");
			servletContext.setInitParameter("encoding", "UTF-8");
			servletContext.setInitParameter(Constants.ContextParams.FONT_AWESOME, Constantes.TRUE);
		}
	}

	@Configuration
	@Profile("prod")
	static class ConfigureJSFContextParametersProd implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {

			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
			servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Constantes.TRUE);
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
			servletContext.setInitParameter("facelets.DEVELOPMENT", "false");
			servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "-1");
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Constantes.TRUE);
			servletContext.setInitParameter("primefaces.THEME", "blitzer");
			servletContext.setInitParameter("encoding", "UTF-8");
			servletContext.setInitParameter(Constants.ContextParams.FONT_AWESOME, Constantes.TRUE);
		}
	}

}