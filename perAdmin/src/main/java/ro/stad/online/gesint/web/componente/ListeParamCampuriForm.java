package ro.stad.online.gesint.web.componente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;
import ro.stad.online.gesint.services.LocalitateService;
import ro.stad.online.gesint.services.ProvinciaService;

/**
 * Listas que se usarán como parámetros en combos.
 * @author STAD
 */
@Component
public class ListeParamCampuriForm {

	/**
	 * Servicio de provincias.
	 */
	@Autowired
	private ProvinciaService provinciaService;

	/**
	 * Servicio de localitati.
	 */
	@Autowired
	private LocalitateService localidadService;

	/**
	 * Lista de localitati para una provincia.
	 * @param idProvincia Long provincia
	 * @return lista de localitati
	 */
	public List<Localitate> obtinereListaLocalitati(final Long idProvincia) {
		return localidadService.cautareDupaProvincie(idProvincia);
	}

	/**
	 * Lista de provincias.
	 * @return lista de provincias
	 */
	public List<Provincia> obtenerListaProvincias() {
		return provinciaService.fiindAll();
	}

}