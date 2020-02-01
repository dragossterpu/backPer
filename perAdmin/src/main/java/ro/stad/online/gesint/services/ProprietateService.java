package ro.stad.online.gesint.services;

import java.util.List;

import ro.stad.online.gesint.persistence.entities.Proprietate;

/**
 * Interfaz del servicio para la gestión de propriedades.
 * 
 * @author STAD
 *
 */
public interface ProprietateService {

	/**
	 * Devuelve todos los nparametros de conexión al servidor de correo.
	 * @param <Proprietate>
	 * @param filename
	 * @return List<Proprietate>
	 */
	List<Proprietate> findByFilename(String filename);

	/**
	 * Devuelve el parametro que tenga el nombre proporcionado.
	 * @param nombreParametro String
	 * @return String
	 */
	String findOneByName(String nombreParametro);
}
