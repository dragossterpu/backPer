package ro.stad.online.gesint.services;

import java.io.File;
import java.util.List;

import org.primefaces.model.StreamedContent;

import ro.stad.online.gesint.exceptions.GesintException;
import ro.stad.online.gesint.persistence.entities.Sondaj;
import ro.stad.online.gesint.persistence.entities.Statistica;
import ro.stad.online.gesint.persistence.entities.enums.TipStatisticaEnum;

/**
 * Interfață pentru serviciul de Statistica.
 *
 * @author STAD
 *
 */
public interface StatisticaService {

	/**
	 * Exportați statistici într-un fișier PDF descărcabil.
	 *
	 * @param sondaj Sondaj
	 * @param fileImg Fișier cu imaginea care va fi încorporată în PDF.
	 * @return Fluxul de date cu PDF-ul generat
	 * @throws GesintException Posibilă excepție
	 */
	StreamedContent exportar(Sondaj sondaj, File fileImg) throws GesintException;

	/**
	 * Cauta statistica tuturor judetelor
	 * @param Long id identificator de judet
	 * @return lista de judete.
	 */
	List<Statistica> findByTipStatisticaAndMarcaAndIsjudet(TipStatisticaEnum tip, String marca, Boolean isjudet);
}
