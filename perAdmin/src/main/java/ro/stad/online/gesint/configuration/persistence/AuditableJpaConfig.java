package ro.stad.online.gesint.configuration.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import ro.stad.online.gesint.model.auditor.UsernameSecurityAuditorAwareImpl;

/**
 * Clase para habilitar la auditoría de las entities y definir que implementación se va a utilizar para ello.
 * 
 * @author STAD
 *
 */
@Configuration
@EnableJpaAuditing
public class AuditableJpaConfig {

        /**
         * Definición de la implementación a usar en la auditaría para JPA.
         * 
         * @return proveedor de la auditoría
         */
        @Bean
        public AuditorAware<String> auditorProvider() {
                return new UsernameSecurityAuditorAwareImpl();
        }
}
