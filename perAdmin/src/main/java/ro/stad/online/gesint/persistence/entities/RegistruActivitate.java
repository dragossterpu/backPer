package ro.stad.online.gesint.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ro.stad.online.gesint.persistence.entities.enums.SectiuniEnum;

/**
 * Entitate pentru stocarea înregistrărilor de activitate în baza de date.
 * @author STAD
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Builder
@ToString
@Getter
@Setter
@Entity
@Table(name = "REG_ACTIVIDAD")
public class RegistruActivitate implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador del registro de actividad, se genera por medio de una secuencia.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REGISTRO")
	@SequenceGenerator(name = "SEQ_REGISTRO", sequenceName = "SEQ_REGISTRO", allocationSize = 1)
	@Column(name = "REG_ACTIVIDAD", length = 10, columnDefinition = "NUMERIC")
	private Long idRegActividad;

	/**
	 * Nombre de la sección para la que se registra actividad.
	 */
	@Column(name = "NOMBRE_SECCION", length = 20)
	@Enumerated(EnumType.STRING)
	private SectiuniEnum numeSectiune;

	/**
	 * Descripción del registro que se almacena.
	 */
	@Column(name = "DESCRIPCION")
	private String descripcion;

	/**
	 * Fecha en la que se da de alta el registro.
	 */
	@CreatedDate
	@Column(name = "FECHA_ALTA", nullable = false)
	private Date fechaAlta;

	/**
	 * Username del usuario que da de alta el registro.
	 */
	@CreatedBy
	@Column(name = "USUARIO_REGISTRO", nullable = false)
	private String usernameRegActividad;

	/**
	 * Tipo del registro.
	 */
	@Column(name = "TIPO_REG_ACTIVIDAD", length = 10)
	private String tipRegActivitate;

	/**
	 * Id del usuario que registra la acción.
	 */
	@Column(name = "ID_USUARIO", length = 100)
	private String username;
}
