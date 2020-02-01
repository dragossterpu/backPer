package ro.stad.online.gesint.configuration.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Configuración de la resolución de rutas relativas a la página de login.
 * @author STAD
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	
	
	
	/**
	 * Activa el servlet por defecto.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * Establece /index.html como vista por defecto.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/index.xhtml");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

	/**
	 * Configuración de resolución de ruta de acceso a la vista /acceso/login.xhtml al usar acciones /login.
	 * @return resolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/acceso/");
		resolver.setSuffix(".xhtml");
		resolver.setOrder(1);
		return resolver;
	}

}
