package ro.stad.online.gesint.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.stad.online.gesint.constante.Constante;
import ro.stad.online.gesint.persistence.entities.Proprietate;
import ro.stad.online.gesint.persistence.repositories.ProprietateRepository;
import ro.stad.online.gesint.services.ProprietateService;

/**
 * Implementación del servicio de centros.
 * 
 * @author STAD
 */

@Service
public class ProprietateServiceImpl implements ProprietateService {

	/**
	 * Variable utilizada para inyectar el repositorio de propriedades.
	 */
	@Autowired
	private ProprietateRepository propriedadesRepository;

	/**
	 * Devuelve todos los parametros de conexión al servidor de correo.
	 * @return List<Proprietate>
	 * @see net.atos.mira.elypse.bean.ApplicationBean.init(String)
	 */
	@Override
	public List<Proprietate> findByFilename(final String filename) {
		return propriedadesRepository.findByFilename(filename);
	}

	/**
	 * Devuelve el parametro que tenga el nombre proporcionado.
	 * @param nombreParametro String
	 * @return String
	 */
	@Override
	public String findOneByName(final String nombreParametro) {
		String valor = Constante.SPATIU;
		if (propriedadesRepository.findOneByName(nombreParametro) != null) {
			valor = propriedadesRepository.findOneByName(nombreParametro).getValue();
		}

		return valor;
	}
}
