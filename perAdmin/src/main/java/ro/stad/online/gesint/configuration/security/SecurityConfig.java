package ro.stad.online.gesint.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.services.LoginService;

/**
 * Controlador de las operaciones relacionadas con el login del usuario y la seguridad de la aplicación.
 * @author ATOS
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        /**
         * Número máximo de usuarios que pueden iniciar sessión con el mismo nombre de usuario.
         */
        private static final int MAXCONCURRENTUSERSESSIONS = 1;

        /**
         * Controlar la sesión de un usuario. Trigger usado cuando un usuario cancela su sesión. Es necesario tenerlo
         * definido para poder validar las sesiones que tiene abiertas un usuario.
         * @return HttpSessionEventPublisher
         */
        @Bean
        public static HttpSessionEventPublisher httpSessionEventPublisher() {
                return new HttpSessionEventPublisher();
        }

        /**
         * Implementación de UserDetailsService que necesita Spring Security.
         */
        @Autowired
        private transient LoginService loginService;

        /**
         * Implementación de lo que tiene que hacer Spring Security cuando un usuario se loguee con éxito. Usado para la
         * auditoría de acceso a la aplicación.
         */
        @Autowired
        private transient AuthenticationSuccessHandlerPersonalizado authenticationSuccessHandlerPersonalizado;

        /**
         * Manejador de login incorrectos en el sistema.
         */
        @Autowired
        private transient AuthenticationFailureHandler authenticationFailureHandlerPersonalizado;

        /**
         * Configuramos el UserDetailsService y el PasswordEncoder que vamos a usar.
         * @param auth constructor de autenticación
         * @throws Exception excepción genérica
         */
        @Override
        protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
        }

        /**
         * Configuración de la seguridad para no permitir el acceso a páginas por usuarios que no tengan los permisos
         * adecuados. Defininicón de la redirección de la apliación al iniciar y cerrar sesión en la aplicación.
         */
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
                // Desactivada protección Cross-Site, incompatible con la implementación actual del sistema
                http.csrf().disable();
                http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
                // Gestión de peticiones HTTP a recursos del sistema en base a la sesión del usuario
                http.authorizeRequests()
                                // Recursos comunes
                                .antMatchers("/css/**", "/images/**", "/js/**", "/javax.faces.resource/**").permitAll()

                                // Acceso al sistema
                                .antMatchers(Constante.LOGINROUTE + "/**").anonymous().antMatchers(Constante.ACCES)
                                .anonymous().antMatchers(Constante.INDEX).authenticated().antMatchers(Constante.ACCES)
                                .anonymous()
                                // Acceso a la administración sólo para el role ADMIN
                                .antMatchers("/usuarioLost/**").hasAnyRole("ADMIN", "ADMIN").and().formLogin()
                                .loginPage(Constante.LOGINROUTE).loginProcessingUrl(Constante.LOGINROUTE)
                                .defaultSuccessUrl(Constante.INDEX).failureUrl(Constante.LOGINROUTE).and().logout()
                                .logoutUrl("/login/logout").logoutSuccessUrl(Constante.LOGINROUTE);
                http.logout().logoutUrl(Constante.LOGOUTROUTE).logoutSuccessUrl(Constante.LOGINROUTE);

                // Inicio de sesión
                http.formLogin().loginPage(Constante.LOGINROUTE).permitAll()
                                .successHandler(authenticationSuccessHandlerPersonalizado)
                                .failureHandler(authenticationFailureHandlerPersonalizado);

                // Cierre de sesión
                http.logout().invalidateHttpSession(true).logoutUrl(Constante.LOGOUTROUTE)
                                .logoutSuccessUrl(Constante.LOGINROUTE).deleteCookies("JSESSIONID");

                // configuración para el manejo de las sessiones de los usuarios
                http.sessionManagement().invalidSessionUrl(Constante.LOGINROUTE)
                                .maximumSessions(MAXCONCURRENTUSERSESSIONS).maxSessionsPreventsLogin(false)
                                .sessionRegistry(sessionRegistry()).expiredUrl(Constante.LOGINROUTE);

        }

        /**
         * Configuración de la codificación de la contraseña usando BCrypt.
         * @return PasswordEncoder
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        /**
         * Usado por spring security para saber los usuarios (Principal) que han iniciado sesión.
         * @return SessionRegistry
         */
        public SessionRegistry sessionRegistry() {
                return new SessionRegistryImpl();
        }

}
