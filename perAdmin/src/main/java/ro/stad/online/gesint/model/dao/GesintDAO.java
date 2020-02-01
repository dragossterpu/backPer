package ro.stad.online.gesint.model.dao;

import javax.sql.DataSource;

/**
 * Interfață parentală care conține metodele comune de acces la date.
 *
 * @author STAD
 *
 */
public interface GesintDAO {

	/**
	 * Metoda de atribuire a sursei de date.
	 *
	 * @param ds sursei de date
	 */
	public void setDataSource(DataSource ds);

}
