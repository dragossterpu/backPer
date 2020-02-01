package ro.stad.online.gesint.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ro.stad.online.gesint.persistence.entities.Utilizator;

/**
 * Implementación del Service de Login.
 * @author STAD
 *
 */
@Service
public class LoginService implements UserDetailsService, Serializable {

	/**
	 * Clase UserRepositoryUserDetails.
	 */
	private static final class UserRepositoryUserDetails extends Utilizator implements UserDetails {

		private static final long serialVersionUID = 1L;

		/**
		 * Establece los detalles de conexión del usuario.
		 * @param user User
		 */
		public UserRepositoryUserDetails(final Utilizator user) {
			super();
			this.setUsername(user.getUsername());
			this.setPassword(user.getPassword());
			this.setRole(user.getRole());
			this.setAddress(user.getAddress());
			this.setAlertChannel(user.getAlertChannel());
			this.setBirthDate(user.getBirthDate());
			this.setCivilStatus(user.getCivilStatus());
			this.setDateCreate(user.getDateCreate());
			this.setDateDeleted(user.getDateDeleted());
			this.setEducation(user.getEducation());
			this.setEmail(user.getEmail());
			this.setIdCard(user.getIdCard());
			this.setLastName(user.getLastName());
			this.setLocality(user.getLocality());
			this.setName(user.getName());
			this.setNumberCard(user.getNumberCard());
			this.setPersonalEmail(user.getPersonalEmail());
			this.setPhone(user.getPhone());
			this.setPhoto(user.getPhoto());
			this.setProvince(user.getProvince());
			this.setSex(user.getSex());
			this.setValidated(user.getValidated());
			this.setWorkplace(user.getWorkplace());
		}

		/**
		 * Clase GrantedAuthority.
		 */
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			final Set<GrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority(getRole().name()));

			return authorities;
		}

		/**
		 * Metodo que indica que el usuario no esta inactivo.
		 */
		@Override
		public boolean isAccountNonExpired() {
			// Falta implementar las condiciones
			// Ej return getFechaBaja() == null;
			return true;
		}

		/**
		 * Metodo que indica que el usuario no esta inactivo.
		 */
		@Override
		public boolean isAccountNonLocked() {
			boolean isAccountNonLocked = false;
			if (this.getValidated()) {
				isAccountNonLocked = true;
			}
			return isAccountNonLocked;
		}

		/**
		 * Metodo que indica que el usuario no tiene el passpord expirado.
		 */
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		/**
		 * Metodo que indica que el usuario esta activo.
		 */
		@Override
		public boolean isEnabled() {
			boolean isEnabled = false;
			if (this.getDateDeleted() == null) {
				isEnabled = true;
			}
			return isEnabled;
		}

	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Service de usuario.
	 */
	@Autowired
	private transient UtilizatorService utilizatorService;

	/**
	 * Carga los detalles por nombre de usuario.
	 * @param username String
	 * @return UserDetails
	 * @throws UsernameNotFoundException nombre de usuario no encontrado.
	 */
	@Override
	public UserDetails loadUserByUsername(final String username) {
		final Utilizator user = this.utilizatorService.fiindOne(username);
		if (null == user) {
			throw new UsernameNotFoundException("Utilizatorul nu există.");
		}

		return new UserRepositoryUserDetails(user);
	}

}
