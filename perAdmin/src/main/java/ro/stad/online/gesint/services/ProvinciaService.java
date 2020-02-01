package ro.stad.online.gesint.services;

import java.io.IOException;
import java.util.List;

import ro.stad.online.gesint.persistence.entities.Provincia;

/**
 * Interfață pentru serviciul de Provincia.
 *
 * @author STAD
 *
 */
public interface ProvinciaService {

	/**
	 * Cauta toate judetele
	 *
	 * @return lista de judete.
	 */
	List<Provincia> fiindAll();

	/**
	 * Cauta un judet
	 * @param province Judetul
	 * @return Provincia province
	 */
	Provincia fiindOne(Provincia province);

	/**
	 * Cauta un judet
	 * @param string Judetul
	 * @return Provincia province
	 */
	Provincia findById(String string);

	/**
	 * @param descripción
	 * @return Provincia provincia
	 *
	 */
	Provincia findByName(String descripcion);

	/**
	 * Inregistreaza un judet.
	 * @param judetul Provincia
	 * @return judetulul actualizat
	 */
	Provincia save(Provincia judetul);

	/**
	 * Incarcam fotografia unui judet.
	 * @param Provincia judetul
	 * @throws IOException
	 */

	Provincia incarcareImaginaFaraStocare(byte[] bs, Provincia judetul) throws IOException;
}
