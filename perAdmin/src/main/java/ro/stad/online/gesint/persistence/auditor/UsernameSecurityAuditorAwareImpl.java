package ro.stad.online.gesint.persistence.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Implementación de la interfaz AuditorAware de Spring para poder realizar la auditoria de login.
 * 
 * @author STAD
 *
 */
public class UsernameSecurityAuditorAwareImpl implements AuditorAware<String> {

	/**
	 * Devuelve el nombre del usuario que ha iniciado sesión con éxito.
	 */
	@Override
	public String getCurrentAuditor() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String nombre = null;
		if (authentication != null && authentication.isAuthenticated()) {
			nombre = authentication.getName();
		}
		return nombre;
	}
}
