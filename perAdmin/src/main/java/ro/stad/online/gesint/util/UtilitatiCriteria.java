package ro.stad.online.gesint.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Clase de utilidades para establecer condiciones de criteria.
 * @author STAD
 *
 */
@Component
public final class UtilitatiCriteria {

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el operador like.
	 *
	 * @param value Object
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionCriteriaCadenaLike(final Object value, final Criteria criteria,
			final String nombreCampo) {
		if (value != null && !((String) value).equals("") && !"".equals(value)) {
			criteria.add(Restrictions.ilike(nombreCampo, value.toString().trim(), MatchMode.ANYWHERE));
		}
	}

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el operador mayor igual que.
	 *
	 * @param fechaDesde Date
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionCriteriaFechaMayor(final Date fechaDesde, final Criteria criteria,
			final String nombreCampo) {
		if (fechaDesde != null) {
			final Date fechaDesdeIgual = new Date(fechaDesde.getTime());
			criteria.add(Restrictions.ge(nombreCampo, fechaDesdeIgual));
		}

	}

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el operador menor igual que.
	 *
	 * @param fechaHasta Date
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionCriteriaFechaMenorIgual(final Date fechaHasta, final Criteria criteria,
			final String nombreCampo) {
		if (fechaHasta != null) {
			final Date fechaHastaIgual = new Date(fechaHasta.getTime() + TimeUnit.DAYS.toMillis(1));
			criteria.add(Restrictions.le(nombreCampo, fechaHastaIgual));
		}

	}

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el operador igual y valor booleano.
	 *
	 * @param value Boolean
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionCriteriaIgualdadBoolean(final Boolean value, final Criteria criteria,
			final String nombreCampo) {
		if (value != null) {
			criteria.add(Restrictions.eq(nombreCampo, value));
		}
	}

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el operador igual entre enums.
	 *
	 * @param value Object
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionCriteriaIgualdadEnum(final Object value, final Criteria criteria,
			final String nombreCampo) {
		if (value != null) {
			criteria.add(Restrictions.eq(nombreCampo, value));
		}
	}

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el operador igual y valor int.
	 *
	 * @param value Object
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionCriteriaIgualdadInt(final Object value, final Criteria criteria,
			final String nombreCampo) {
		if (value != null && !"".equals(value.toString().trim())) {
			criteria.add(Restrictions.eq(nombreCampo, Integer.parseInt(value.toString().trim())));
		}
	}

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el operador igual y valor long.
	 *
	 * @param value Object
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionCriteriaIgualdadLong(final Object value, final Criteria criteria,
			final String nombreCampo) {
		if (value != null && !"".equals(value.toString().trim())) {
			criteria.add(Restrictions.eq(nombreCampo, Long.parseLong(value.toString().trim())));
		}
	}

	/**
	 * Método para establecer un criterio de búsqueda sobre un criteria con el valor null sobre un campo.
	 *
	 * @param criteria Criteria
	 * @param nombreCampo String
	 */
	public static void setCondicionNull(final Criteria criteria, final String nombreCampo) {
		criteria.add(Restrictions.isNull(nombreCampo));
	}

	/**
	 * Constructor privado.
	 */
	private UtilitatiCriteria() {

	}
}