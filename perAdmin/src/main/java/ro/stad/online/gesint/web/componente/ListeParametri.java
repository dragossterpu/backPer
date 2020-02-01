package ro.stad.online.gesint.web.componente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.stad.online.gesint.persistence.entities.Localitate;
import ro.stad.online.gesint.persistence.entities.Provincia;

/**
 * Listas que se usarán como parámetros en combos.
 * @author STAD
 */
@Component
public class ListeParametri {

	/**
	 * Clase con listas paramétricas combos que contengan campos de formulario (sexo,unidades,acentos..).
	 */
	@Autowired
	private ListeParamCampuriForm listasCamposForm;

	/**
	 * Lista de localitati para una provincia.
	 * @param idProvincia Long
	 * @return lista de localitati
	 */
	public List<Localitate> getListalocalitati(final Long idProvincia) {
		List<Localitate> listalocalitati;
		if (idProvincia == null) {
			listalocalitati = null;
		}
		else {
			listalocalitati = listasCamposForm.obtinereListaLocalitati(idProvincia);
		}
		return listalocalitati;
	}

	/**
	 * Lista de provincias.
	 * @return lista de provincias
	 */
	public List<Provincia> getListaProvincias() {
		return listasCamposForm.obtenerListaProvincias();
	}

	// /**
	// * Lista de localitati para varias provincias.
	// * @param listaProvincias List<>
	// * @return lista de localitati
	// */
	// public List<Localitate> getListalocalitatiMultiProv(final List<Localitate> listaProvincias) {
	// return listasCamposForm.obtinereListaLocalitatiMultiProv(listaProvincias);
	// }

	// /**
	// * Lista de países.
	// * @return lista de paises
	// */
	// public List<PCity> getListaPaises() {
	// return listasCamposForm.obtenerListaPaises();
	// }
	//
	// /**
	// * Lista de sexos.
	// * @return lista de sexos
	// */
	// public List<Sexo> getListaSexos() {
	// return listasCamposForm.obtenerListaSexos();
	// }

}