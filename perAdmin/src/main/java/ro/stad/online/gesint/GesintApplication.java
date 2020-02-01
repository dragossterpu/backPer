package ro.stad.online.gesint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.faces.application.ProjectStage;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.primefaces.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sun.faces.config.FacesInitializer;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.jsf.scope.FacesViewScope;

/**
 * Clase de arranque y configuración de Spring Boot.
 *
 * @author STAD
 *
 */
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class GesintApplication {

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
			servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Constante.TRUE);
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
			servletContext.setInitParameter("facelets.DEVELOPMENT", Constante.TRUE);
			servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Constante.TRUE);
			servletContext.setInitParameter(Constants.ContextParams.THEME, "blitzer");
			servletContext.setInitParameter("encoding", "UTF-8");
			servletContext.setInitParameter(Constants.ContextParams.FONT_AWESOME, Constante.TRUE);
			servletContext.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());
			servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");
		}
	}

	@Configuration
	@Profile("prod")
	static class ConfigureJSFContextParametersProd implements ServletContextInitializer {

		@Override
		public void onStartup(final ServletContext servletContext) throws ServletException {

			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
			servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Constante.TRUE);
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
			servletContext.setInitParameter("facelets.DEVELOPMENT", "false");
			servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "-1");
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Constante.TRUE);
			servletContext.setInitParameter(Constants.ContextParams.THEME, "blitzer");
			servletContext.setInitParameter("encoding", "UTF-8");
			servletContext.setInitParameter(Constants.ContextParams.FONT_AWESOME, Constante.TRUE);
			servletContext.setInitParameter(ProjectStage.PROJECT_STAGE_PARAM_NAME, ProjectStage.Development.name());
			servletContext.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/springsecurity.taglib.xml");
		}
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
	 * @return scope que simula el scope view de jsf
	 */
	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		final CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(Collections.<String, Object> singletonMap(FacesViewScope.NAME, new FacesViewScope()));
		return configurer;
	}

	/**
	 * Punto de entrada de la aplicación para Spring Boot.
	 * @param args parámetros de entrada del método main
	 */
	public static void main(final String[] args) {
		SpringApplication.run(GesintApplication.class, args);
	}

	/**
	 * Variable utilizada para inyectar la sesión.
	 */
	@Autowired
	private EntityManagerFactory entityManagerFactory;

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
	 * Realiza la conexión con el servidor de correo.
	 *
	 * @return Objeto sender para realizar operaciones con la conexión
	 */
	@Bean
	public JavaMailSenderImpl javaMailSender() {
		final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		return mailSender;
	}

	/**
	 * @return HibernateJpaSessionFactoryBean.
	 */
	@Bean
	public SessionFactory sessionFactory() {
		return entityManagerFactory.unwrap(SessionFactory.class);
	}

	/**
	 * @return HibernateEntityManagerFactory.
	 */
	@Bean
	public SessionFactory sessionFactory(final HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
	}

	/**
	 * This bean is only needed when running with embedded Tomcat.
	 * @return tomcat Conexion con el servidor
	 */
	@Bean
	public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
		final TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		tomcat.addContextCustomizers(context -> {
			// register FacesInitializer
			context.addServletContainerInitializer(new FacesInitializer(),
					getServletContainerInitializerHandlesTypes(FacesInitializer.class));
			context.addWelcomeFile("index.xhtml");
			context.addMimeMapping("eot", "application/vnd.ms-fontobject");
			context.addMimeMapping("ttf", "application/x-font-ttf");
			context.addMimeMapping("woff", "application/x-font-woff");
			context.addMimeMapping("woff2", "application/fontawesome-webfont.woff2");
		});

		return tomcat;
	}

	/**
	 * Inicializa el contenedor getServletContainerInitializerHandlesTypes.
	 * @param sciClass ServletContainerInitializer
	 * @return classesSet
	 */
	@SuppressWarnings(Constante.RAWTYPES)
	private Set<Class<?>> getServletContainerInitializerHandlesTypes(
			final Class<? extends ServletContainerInitializer> sciClass) {
		Set<Class<?>> coleccion;

		final HandlesTypes annotation = sciClass.getAnnotation(HandlesTypes.class);
		if (annotation == null) {
			coleccion = Collections.emptySet();
		}
		else {
			final Class[] classesArray = annotation.value();
			final Set<Class<?>> classesSet = new HashSet<>(classesArray.length);
			for (final Class clazz : classesArray) {
				classesSet.add(clazz);
			}
			coleccion = classesSet;
		}

		return coleccion;
	}
}