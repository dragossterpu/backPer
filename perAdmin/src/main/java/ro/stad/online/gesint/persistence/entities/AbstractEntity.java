package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 * Clasa abstracta para a evita duplicitatea in cod si in restulo de entity.
 *
 * @author STAD
 *
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5041785485043966367L;

	/**
	 *
	 * KMNBVCXZ1º
	 */
	@CreatedBy
	@Column(name = "user_create", length = 250)
	private String userCreate;

	/**
	 * Data de creatie.
	 */
	@CreatedDate
	@Column(name = "date_create", nullable = false)
	private Date dateCreate;

	/**
	 * Utilizator care elimina un registru
	 */
	@Column(name = "user_deleted", length = 250)
	private String userDeleted;

	/**
	 * Data eliminarii.
	 */
	@Column(name = "date_deleted")
	private Date dateDeleted;

	/**
	 * Utilizator care modifica un registru
	 */
	@LastModifiedBy
	@Column(name = "user_updated", length = 250)
	private String userUpdated;

	/**
	 * Data ultimei modificari
	 */
	@LastModifiedDate
	@Column(name = "date_updated")
	private Date dateUpdated;

}
